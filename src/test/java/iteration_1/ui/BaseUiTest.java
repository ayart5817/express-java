package iteration_1.ui;

import api.configs.Config;
import api.models.CreateUserRequest;
import api.specs.RequestSpecs;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import iteration_1.api.BaseTest;
import iteration_1.common.extensions.AdminSessionExtension;
import iteration_1.common.extensions.BrowserMatchExtension;
import iteration_1.common.extensions.UserSessionExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Alert;

import java.util.Map;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(AdminSessionExtension.class)
@ExtendWith(UserSessionExtension.class)
@ExtendWith(BrowserMatchExtension.class)
public class BaseUiTest extends BaseTest {
    @BeforeAll
    public static void setupSelenoid() {
        Configuration.remote = Config.getProperty("uiRemote");
        Configuration.baseUrl = Config.getProperty("uiBaseUrl");
        Configuration.browser = Config.getProperty("browser");
        Configuration.browserSize = Config.getProperty("browserSize");

        Configuration.browserCapabilities.setCapability("selenoid:options",
                Map.of("enableVNC", true, "enableLog", true)
        );
    }

    public void authAsUser(String username, String password) {
        Selenide.open("/");
        String userAuthHeader = RequestSpecs.getUserAuthHeader(username, password);
        executeJavaScript("localStorage.setItem('authToken', arguments[0]);", userAuthHeader);
    }

    public void authAsUser(CreateUserRequest createUserRequest) {
        authAsUser(createUserRequest.getUsername(), createUserRequest.getPassword());
    }
    protected String getAlertTextAndAccept() {
        Alert alert = switchTo().alert();
        String text = alert.getText();
        alert.accept();
        return text;
    }

    protected void acceptAlert() {
        switchTo().alert().accept();
    }

    protected void checkAlertContains(String expectedText) {
        Alert alert = switchTo().alert();
        assertThat(alert.getText()).contains(expectedText);
        alert.accept();
    }
}