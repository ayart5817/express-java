package iteration_1.ui;

import api.generators.RandomModelGenerator;
import api.models.CreateUserRequest;
import api.models.CreateUserResponse;
import api.models.comparison.ModelAssertions;
import api.requests.steps.AdminSteps;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.Test;
import ui.pages.AdminPanel;
import ui.pages.BankAlert;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateUserTest extends BaseUiTest {

    @Test
    public void adminCanCreateUserTest() {
        // ШАГ 1: админ залогинился в банке
        CreateUserRequest admin = CreateUserRequest.getAdmin();
        authAsUser(admin);

        // ШАГ 2: админ создает юзера в банке
        CreateUserRequest newUser = RandomModelGenerator.generate(CreateUserRequest.class);
        new AdminPanel().open().createUser(newUser.getUsername(), newUser.getPassword())
                .checkAlertMessageAndAccept(BankAlert.USER_CREATED_SUCCESSFULLY.getMessage());

        // ШАГ 4: проверка, что юзер отображается на UI
        ElementsCollection allUsersFromDashboard = $(Selectors.byText("All Users")).parent().findAll("li");
        allUsersFromDashboard.findBy(exactText(newUser.getUsername() + "\nUSER")).shouldBe(Condition.visible);

        // ШАГ 5: проверка, что юзер создан на API
        CreateUserResponse createUser = AdminSteps.getAllUsers().stream().filter(user -> user
                        .getUsername().equals(newUser.getUsername()))
                .findFirst().get();

        ModelAssertions.assertThatModels(newUser, createUser).match();
    }

    @Test
    public void adminCannotCreateUserWithInvalidDataTest() {
        // ШАГ 1: админ залогинился в банке
        CreateUserRequest admin = CreateUserRequest.getAdmin();
        authAsUser(admin);

        // ШАГ 2: админ создает юзера в банке
        CreateUserRequest newUser = RandomModelGenerator.generate(CreateUserRequest.class);
        newUser.setUsername("a");

        new AdminPanel().open().createUser(newUser.getUsername(), newUser.getPassword())
                .checkAlertMessageAndAccept(BankAlert.USERNAME_MUST_BE_3_AND_15_CHARACTERS.getMessage())
                .getAllUsers().findBy(Condition.exactText(newUser.getUsername() + "\nUSER")).shouldNot(Condition.exist);


        // ШАГ 5: проверка, что юзер НЕ создан на API


        long usersWithSameUsernameAsNewUser = AdminSteps.getAllUsers().stream()
                .filter(user -> user.getUsername().equals(newUser.getUsername()))
                .count();

        assertThat(usersWithSameUsernameAsNewUser).isZero();
    }
}