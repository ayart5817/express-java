package iteration_1.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import models.CreateUserRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import requests.steps.AdminSteps;

import java.time.Duration;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

public class LoginUserTest {
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
    public void adminCanLoginWithCorrectDataTest() {
        CreateUserRequest admin = CreateUserRequest.builder().username("admin").password("admin").build();
        open("/admin");
        Configuration.timeout = 10000;
        // ДОБАВЬТЕ ЭТО для отладки:
        System.out.println("=== DEBUG INFO ===");
        System.out.println("Current URL: " + webdriver().driver().url());
        System.out.println("Page title: " + Selenide.title());
        System.out.println("Page source length: " + webdriver().driver().source().length());


        Selenide.screenshot("login-page-debug");


        $("body").shouldBe(Condition.visible, Duration.ofSeconds(10));


        $(Selectors.byAttribute("placeholder", "Username"))
                .shouldBe(Condition.visible, Duration.ofSeconds(5))
                .setValue(admin.getUsername());

        $(Selectors.byAttribute("placeholder", "Password"))
                .shouldBe(Condition.visible, Duration.ofSeconds(5))
                .setValue(admin.getPassword());
        $("button").shouldHave(Condition.or(
                "Кнопка логина",
                Condition.text("Login"),
                Condition.attribute("type", "submit")
        )).click();

        $(Selectors.byText("Admin Panel")).shouldBe(Condition.visible);

    }

    @Test
    public void userCanloginWithCorrectDataTest() {
        //create user
        CreateUserRequest user = AdminSteps.createUser().getRequest();

        Selenide.open("/login");

        $(Selectors.byAttribute("placeholder", "Username"))
                .shouldBe(Condition.visible, Duration.ofSeconds(5))
                .setValue(user.getUsername());

        $(Selectors.byAttribute("placeholder", "Password"))
                .shouldBe(Condition.visible, Duration.ofSeconds(5))
                .setValue(user.getPassword());
        $("button").shouldHave(Condition.or(
                "Кнопка логина",
                Condition.text("Login"),
                Condition.attribute("type", "submit")
        )).click();

        $(Selectors.byClassName("welcome-text")).shouldBe(Condition.visible).shouldHave(Condition.text("Welcome, noname!"));

    }



}

