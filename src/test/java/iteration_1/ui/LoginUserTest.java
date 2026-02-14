package iteration_1.ui;

import api.models.CreateUserRequest;
import api.requests.steps.AdminSteps;
import com.codeborne.selenide.Condition;
import iteration_1.common.annotations.AdminSession;
import iteration_1.common.annotations.Browsers;
import org.junit.jupiter.api.Test;
import ui.pages.AdminPanel;
import ui.pages.LoginPage;
import ui.pages.UserDashboard;

public class LoginUserTest extends BaseUiTest {


    @Test
    @Browsers(values = {"chrome"})
    public void adminCanLoginWithCorrectDataTest() {
        CreateUserRequest admin = CreateUserRequest.getAdmin();

        new LoginPage().open().login(admin.getUsername(), admin.getPassword())
                .getPage(AdminPanel.class).getAdminPanelText().shouldBe(Condition.visible);
    }

    @Test
    public void userCanLoginWithCorrectDataTest() {
        CreateUserRequest user = AdminSteps.createUser().getRequest();

        new LoginPage().open().login(user.getUsername(), user.getPassword())
                .getPage(UserDashboard.class).getWelcomeText()
                .shouldBe(Condition.visible).shouldHave(Condition.text("Welcome, noname!"));
    }


}

