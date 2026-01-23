package iteration_2.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import models.AccountResponse;
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

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class UIProfileUpdateTest {
    private CreateUserResponse user1;
    private String password;
    private AccountResponse account1;


    @BeforeAll
    public static void setupSelenoid() {
        Configuration.baseUrl = "http://192.168.31.192:3000";
        Configuration.timeout = 3000;
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
    public void renameProfileAccountName() {

        //user1
        CreatedUser createUser = AdminSteps.createUser();
        user1 = createUser.getResponse();
        password = createUser.getRequest().getPassword();
        account1 = UserSteps.createAccount(user1.getUsername(), password);

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

        SelenideElement userInfo = $("div.user-info");
        userInfo.shouldBe(visible);
        userInfo.click();

        //тест 1 невалидное имя
        $("input[placeholder='Enter new name']").click();
        $("input[placeholder='Enter new name']").setValue("Mark");
        System.out.println("Ввели новое имя case 1");
        $(Selectors.byText("💾 Save Changes")).click();

        Alert alert = switchTo().alert();
        String alertText = alert.getText();
        assertThat(alertText).contains("Name must contain two words with letters only");
        alert.accept();

        //тест 2 валидное имя
        $("input[placeholder='Enter new name']").shouldBe(visible).clear();
        $("input[placeholder='Enter new name']").setValue("Mark Car");
        $(Selectors.byText("💾 Save Changes")).click();

        System.out.println("Ввели новое имя case 2");
        $(Selectors.byText("💾 Save Changes")).click();

        Alert alert2 = switchTo().alert();
        String alertText2 = alert.getText();
        assertThat(alertText2).contains("✅ Name updated successfully!");
        alert.accept();

        $(Selectors.byText("\uD83C\uDFE0 Home")).click();
        // Проверяем новое имя в приветствии
        $("h2.welcome-text").shouldHave(text("Welcome, Mark Car!"));
        System.out.println("Имя успешно обновлено в приветствии");
    }

}