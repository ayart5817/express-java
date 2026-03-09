package iteration_1.ui;

import api.models.CreateAccountResponse;
import api.models.CreateUserRequest;
import iteration_1.common.annotations.UserSession;
import iteration_1.common.storage.SessionStorage;
import org.junit.jupiter.api.Test;
import ui.pages.BankAlert;
import ui.pages.UserDashboard;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateAccountTest extends BaseUiTest {
    @Test
    @UserSession
    public void userCanCreateAccountTest() {
        CreateUserRequest user = SessionStorage.getUser(1);

        new UserDashboard().open().createNewAccount();

        List<CreateAccountResponse> createdAccounts = SessionStorage.getSteps()
                .getAllAccounts();

        assertThat(createdAccounts).hasSize(1);

        new UserDashboard().checkAlertMessageAndAccept
                (BankAlert.NEW_ACCOUNT_CREATED.getMessage() + createdAccounts.get(0).getAccountNumber());

        assertThat(createdAccounts.get(0).getBalance()).isZero();
    }
}