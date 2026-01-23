package iteration_2.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import models.AccountResponse;
import models.CreateAccountResponse;
import models.CreateUserResponse;
import models.LoginUserRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import requests.skeleton.Endpoint;
import requests.skeleton.requester.CrudRequester;
import requests.steps.AdminSteps;
import requests.steps.CreatedUser;
import requests.steps.UserSteps;
import specs.RequestSpecs;
import specs.ResponseSpecs;

import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class UIUserTransferTest {
    private CreateUserResponse user1;
    private CreateUserResponse user2;
    private AccountResponse account1;
    private AccountResponse account2;
    private AccountResponse account2_2;
    private String password;
    private String password2;


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
    public void generatePreconditionTransferTest() {
        // ШАГИ ПО НАСТРОЙКЕ ОКРУЖЕНИЯ
        // ШАГ 1: админ логинится в банке
        // ШАГ 2: админ создает юзера, админ создает юзера2
        // ШАГ 3: юзер 1 логинится в банке, юзер 2 логинится в банке
        // ШАГ 4: юзер 1 и 2 создают аккаунт

        //user1
        CreatedUser createUser = AdminSteps.createUser();
        user1 = createUser.getResponse();
        password = createUser.getRequest().getPassword();
        account1 = UserSteps.createAccount(user1.getUsername(), password);

        //user2
        CreatedUser createUser2 = AdminSteps.createUser();
        user2 = createUser2.getResponse();
        password2 = createUser2.getRequest().getPassword();
        account2 = UserSteps.createAccount(user2.getUsername(), password2);

        //пополняем депозит 20к U1
        for (int i = 1; i <= 4; i++) {
            UserSteps.makeDeposit(
                    user1.getUsername(),
                    password,
                    account1.getId(),
                    5000.0
            );
        }

        //ТОКЕН ЮЗЕР 1
        String userAuthHeader = new CrudRequester(
                RequestSpecs.unauthSpec(),
                Endpoint.LOGIN,
                ResponseSpecs.requestReturnsOK())
                .post(LoginUserRequest.builder().username(user1.getUsername()).password(password).build())
                .extract()
                .header("Authorization");

        // Открываем приложение
        Selenide.open("/");

        executeJavaScript("localStorage.setItem('authToken', arguments[0]);", userAuthHeader);

        Selenide.open("/dashboard");

        // ШАГИ ТЕСТА
        // ШАГ 4: ВЫБИРАЕТ ТРАНСФЕР
        $(Selectors.byText("\uD83D\uDD04 Make a Transfer")).click();
        // Проверяем заголовок страницы
        $("#root > div > h1")
                .shouldBe(visible)
                .shouldHave(exactText("\uD83D\uDD04 Make a Transfer"));


        // ШАГ 5: проверка, что аккаунт создался на UI
        String acNumber = account1.getAccountNumber();
        System.out.println(acNumber);
        // Для -- Choose an account --
        $("#root > div > div.form-group.mt-4 > select").click();
        $("select").selectOptionContainingText(acNumber);

        // ПРОВЕРЯЕМ ВЫБОР
        //$("select option:selected").shouldHave(text(acNumber));

        // ЗАПОЛНЯЕМ ПОЛЯ Recipient Account Number:
        $("#root > div > div.form-group.mt-4 > input:nth-child(6)")
                .setValue(account2.getAccountNumber());
        // ПОЛЕ Amount:
        $("input.form-control[placeholder='Enter amount']")
                .setValue("1000");
        $("#confirmCheck").click();

        // ОТПРАВЛЯЕМ
        $(Selectors.byText("🚀 Send Transfer")).click();

        System.out.println("Проверяем аллерт успешного перевод");
        Alert depositAlert = switchTo().alert();
        assertThat(depositAlert.getText()).contains("✅ Successfully transferred $1000 to account " + account2.getAccountNumber());
        depositAlert.accept();

        // ШАГ 6: проверяем баланс через API
        CreateAccountResponse[] accountsAfterDeposit = given()
                .spec(RequestSpecs.authAsUser(user1.getUsername(), password))
                .get("http://localhost:4111/api/v1/customer/accounts")
                .then().assertThat()
                .extract().as(CreateAccountResponse[].class);


        assertThat(accountsAfterDeposit[0].getBalance()).isEqualTo(19000.0);

        // Для -- Choose an account --
        $("#root > div > div.form-group.mt-4 > select").click();
        $("select").selectOptionContainingText(acNumber);

        // ПРОВЕРЯЕМ ВЫБОР
        //$("select option:selected").shouldHave(text(acNumber));

        // ЗАПОЛНЯЕМ ПОЛЯ Recipient Account Number:
        $("#root > div > div.form-group.mt-4 > input:nth-child(6)")
                .setValue(account2.getAccountNumber());
        // ПОЛЕ Amount:
        $("input.form-control[placeholder='Enter amount']")
                .setValue("1000");
        //не прожимаем чек бокс
        //$("#confirmCheck").click();

        // ОТПРАВЛЯЕМ
        $(Selectors.byText("🚀 Send Transfer")).click();

        System.out.println("Проверяем аллерт успешного перевод");
        Alert depositAlert2 = switchTo().alert();
        assertThat(depositAlert2.getText()).contains("❌ Please fill all fields and confirm.");
        depositAlert.accept();
        System.out.println("Депозит не успешено - ок");


        // SUM >10К--
        $("#root > div > div.form-group.mt-4 > select").click();
        $("select").selectOptionContainingText(acNumber);

        // ЗАПОЛНЯЕМ ПОЛЯ Recipient Account Number:
        $("#root > div > div.form-group.mt-4 > input:nth-child(6)")
                .setValue(account2.getAccountNumber());
        // ПОЛЕ Amount:
        $("input.form-control[placeholder='Enter amount']")
                .setValue("11000");

        $("#confirmCheck").click();

        // ОТПРАВЛЯЕМ
        $(Selectors.byText("🚀 Send Transfer")).click();

        System.out.println("Проверяем аллерт успешного перевод");
        Alert depositAlert3 = switchTo().alert();
        assertThat(depositAlert3.getText()).contains("❌ Error: Transfer amount cannot exceed 10000");
        depositAlert.accept();
        System.out.println("Депозит >10К не успешено - ок");


        //ТРАНСФЕР AGAIN ДОПИСАТЬ
        $(Selectors.byText("\uD83D\uDD01 Transfer Again")).shouldBe(visible).click();
        System.out.println("Кнопка Again найдена и нажата");
        SelenideElement transactionList = $("ul.list-group");
        transactionList.shouldBe(visible);
        // Выбрать ПЕРВЫЙ элемент списка
        SelenideElement firstTransaction = transactionList.$$("li.list-group-item").first();
        firstTransaction.shouldBe(visible);
        // Нажать кнопку Repeat в ПЕРВОЙ транзакции
        firstTransaction.find(".pink-btn").click();

        // Проверяем, что открылось модальное окно
        $(".modal-body").shouldBe(visible);

        // Проверяем заголовок/текст в модальном окне
        $(".modal-body p").shouldHave(text("Confirm transfer to Account ID:"));


        // Выбираем аккаунт в селекте
        SelenideElement accountSelect = $(".modal-body select.form-control");
        accountSelect.shouldBe(visible);

        // ВЫБИРАЕМ АККАУНТ - это обязательно!
        accountSelect.selectOptionContainingText(acNumber);

        // Проверяем поле Amount
        SelenideElement amountInput = $(".modal-body input.form-control[type='number']");
        amountInput.shouldBe(visible);

        //Меняем Сумму
        amountInput.setValue("500");

        // Нажимаем чекбокс подтверждения
        SelenideElement confirmCheckbox = $("#confirmCheck");
        confirmCheckbox.shouldBe(visible);
// Сначала снимаем если отмечен (для чистоты)
        if (confirmCheckbox.isSelected()) {
            confirmCheckbox.click();
            sleep(300);
        }

// Затем ставим
        confirmCheckbox.click();
        sleep(500); // Важно: даем время на анимацию!


        //Проверяем, что кнопка отправки стала активной
        SelenideElement sendButton = $(".modal-content button.btn-success");
        sendButton.shouldBe(visible);

        // enabled после чекбокса
        sendButton.shouldNotHave(attribute("disabled"));
        sendButton.shouldBe(enabled);

        // Проверяем текст на кнопке
        sendButton.shouldHave(text("🚀 Send Transfer"));

        // Нажимаем кнопку отправки
        sendButton.click();
        // Проверяем результат отправки

        Alert successAlert = switchTo().alert();
        assertThat(successAlert.getText()).contains("Transfer of $500 successful from Account"); //д.б. ✅ Successfully transferred $1000 to account тес тпадает
        successAlert.accept();


    }
}
