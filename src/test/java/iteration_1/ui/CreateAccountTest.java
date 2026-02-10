package iteration_1.ui;

import api.models.CreateAccountResponse;
import api.models.CreateUserRequest;
import api.models.LoginUserRequest;
import api.requests.skeleton.Endpoint;
import api.requests.skeleton.requester.CrudRequester;
import api.requests.steps.AdminSteps;
import api.requests.steps.UserSteps;
import api.specs.RequestSpecs;
import api.specs.ResponseSpecs;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import ui.pages.BankAlert;
import ui.pages.UserDashboard;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateAccountTest extends BaseUiTest {
    @Test
    public void userCanCreateAccountTest() {
        CreateUserRequest user = AdminSteps.createUser().getRequest();

        authAsUser(user);

        new UserDashboard().open().createNewAccount();

        List<CreateAccountResponse> createdAccounts = new UserSteps(user.getUsername(), user.getPassword())
                .getAllAccounts();

        assertThat(createdAccounts).hasSize(1);

        new UserDashboard().checkAlertMessageAndAccept
                (BankAlert.NEW_ACCOUNT_CREATED.getMessage() + createdAccounts.get(0).getAccountNumber());

        assertThat(createdAccounts.get(0).getBalance()).isZero();
    }
}