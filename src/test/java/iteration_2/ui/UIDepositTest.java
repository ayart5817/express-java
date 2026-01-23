package iteration_2.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import iteration_1.api.BaseTest;
import models.CreateAccountResponse;
import models.CreateUserRequest;
import models.LoginUserRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import requests.skeleton.Endpoint;
import requests.skeleton.requester.CrudRequester;
import requests.steps.AdminSteps;
import specs.RequestSpecs;
import specs.ResponseSpecs;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class UIDepositTest extends BaseTest {

    @BeforeAll
    public static void setupSelenoid() {
        Configuration.baseUrl = "http://192.168.31.192:3000";
        Configuration.timeout = 10000;
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";

        Configuration.browserCapabilities.setCapability("selenoid:option",
                Map.of("enableVNC", true, "enableLog", true));

        System.out.println("Конфигурация Selenide:");
        System.out.println("  Base URL: " + Configuration.baseUrl);
        System.out.println("  Remote: " + Configuration.remote);
    }

    @Test
    public void userCanCreateAccountTest() {
        // ШАГИ ПО НАСТРОЙКЕ ОКРУЖЕНИЯ
        // ШАГ 1: админ логинится в банке
        // ШАГ 2: админ создает юзера
        // ШАГ 3: юзер логинится в банке

        CreateUserRequest user = AdminSteps.createUser().getRequest();

        String userAuthHeader = new CrudRequester(
                RequestSpecs.unauthSpec(),
                Endpoint.LOGIN,
                ResponseSpecs.requestReturnsOK())
                .post(LoginUserRequest.builder().username(user.getUsername()).password(user.getPassword()).build())
                .extract()
                .header("Authorization");

        Selenide.open("/");

        executeJavaScript("localStorage.setItem('authToken', arguments[0]);", userAuthHeader);

        Selenide.open("/dashboard");

        // ШАГИ ТЕСТА
        // ШАГ 4: юзер создает аккаунт
        $(Selectors.byText("➕ Create New Account")).click();

        // ШАГ 5: проверка, что аккаунт создался на UI
        Alert alert = switchTo().alert();
        String alertText = alert.getText();

        assertThat(alertText).contains("✅ New Account Created! Account Number:");

        alert.accept();

        // ШАГ 6: проверка, что аккаунт был создан на API
        CreateAccountResponse[] existingUserAccounts = given()
                .spec(RequestSpecs.authAsUser(user.getUsername(), user.getPassword()))
                .get("http://localhost:4111/api/v1/customer/accounts")
                .then().assertThat()
                .extract().as(CreateAccountResponse[].class);

        assertThat(existingUserAccounts).hasSize(1);

        CreateAccountResponse createdAccount = existingUserAccounts[0];

        assertThat(createdAccount).isNotNull();
        assertThat(createdAccount.getBalance()).isZero();

        // ШАГ 7: проверка, что можно положить деньги на депозит

        //клик по кнопке депозита
        $(Selectors.byText("\uD83D\uDCB0 Deposit Money")).shouldBe(Condition.visible).click();


        // ШАГ 8: выбираем счет для депозита
        // Извлекаем номер счета из alert
        Pattern pattern = Pattern.compile("Account Number: (\\d+)");
        Matcher matcher = pattern.matcher(alertText);
        String accountNumber = "";
        if (matcher.find()) {
            accountNumber = matcher.group(1);
        }

        // Ищем select элемент
        $("#root > div > div:nth-child(2) > select").click();

         $("select").selectOptionContainingText("ACC" + accountNumber);;

        // Проверяем что счет выбран
        $("select option:checked").shouldHave(Condition.value(accountNumber));

        // ШАГ 9: вводим сумму для депозита
        $("input.form-control.deposit-input").shouldBe(Condition.visible);
        $("input.form-control.deposit-input").setValue("1000");

        // Проверяем что значение установлено
        $("input.form-control.deposit-input").shouldHave(Condition.value("1000"));

        // ШАГ 10: нажимаем кнопку Deposit
        $("button.btn.btn-primary.shadow-custom.mt-4").shouldBe(Condition.enabled);
        $("button.btn.btn-primary.shadow-custom.mt-4").click();

        // ШАГ 11: проверяем успешное сообщение
        // Ищем сообщение об успехе (alert)

            Alert depositAlert = switchTo().alert();
            assertThat(depositAlert.getText()).contains("Successfully deposited $1000 to account ACC" + accountNumber);
            depositAlert.accept();


        // ШАГ 12: проверяем баланс через API
        CreateAccountResponse[] accountsAfterDeposit = given()
                .spec(RequestSpecs.authAsUser(user.getUsername(), user.getPassword()))
                .get("http://localhost:4111/api/v1/customer/accounts")
                .then().assertThat()
                .extract().as(CreateAccountResponse[].class);

        assertThat(accountsAfterDeposit).hasSize(1);
        assertThat(accountsAfterDeposit[0].getBalance()).isEqualTo(1000.0);

        // ШАГ 13: негативный текст аллерта на ошибку
        $(Selectors.byText("\uD83D\uDCB0 Deposit Money")).shouldBe(Condition.visible).click();
        // Ищем select элемент
        $("#root > div > div:nth-child(2) > select").click();

        $("select").selectOptionContainingText("ACC" + accountNumber);;

        // Проверяем что счет выбран
        $("select option:checked").shouldHave(Condition.value(accountNumber));

        // вводим сумму для депозита
        $("input.form-control.deposit-input").shouldBe(Condition.visible);
        $("input.form-control.deposit-input").setValue("5001");

        // Проверяем что значение установлено
        $("input.form-control.deposit-input").shouldHave(Condition.value("5001"));

        // нажимаем кнопку Deposit
        $("button.btn.btn-primary.shadow-custom.mt-4").shouldBe(Condition.enabled);
        $("button.btn.btn-primary.shadow-custom.mt-4").click();

        // проверяем не успешное сообщение
        // Ищем сообщение неуспешной транзакции (alert)

        Alert depositNegativeAlert = switchTo().alert();
        assertThat(depositNegativeAlert.getText()).contains("❌ Please deposit less or equal to 5000$.");
        depositAlert.accept();

        // Ищем отображение баланса на странице
        // Это зависит от того, как отображается баланс в вашем приложении
        // Например: $(".balance, [data-testid='balance'], [class*='balance']")
        //           .shouldHave(Condition.text("1000"));
    }
}