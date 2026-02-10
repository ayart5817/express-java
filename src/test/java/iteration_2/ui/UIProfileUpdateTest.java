package iteration_2.ui;

import api.models.CreateUserRequest;
import api.requests.steps.AdminSteps;
import api.requests.steps.CreatedUser;
import api.requests.steps.ProfileSteps;
import com.codeborne.selenide.Selenide;
import iteration_1.ui.BaseUiTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.pages.ProfilePage;
import ui.pages.UserDashboard;

import static org.assertj.core.api.Assertions.assertThat;

public class UIProfileUpdateTest extends BaseUiTest {

    @Test
    @DisplayName("Успешное обновление имени профиля")
    public void userCanUpdateProfileName() {
        // ШАГ 1: Создаём пользователя

        CreatedUser userAll = AdminSteps.createUser();
        CreateUserRequest user = userAll.getRequest();

        authAsUser(user);


        // ШАГ 2: Создаём счёт
        UserDashboard dashbord = new UserDashboard().open().createNewAccount();
        acceptAlert();

        // ШАГ 3: Переходим в профиль и обновляем имя
        new ProfilePage().open()
                .enterNewName("John Doe")
                .saveName();

        // ШАГ 5: Проверяем через API
        String updatedName = ProfileSteps.getProfile(user.getUsername(), user.getPassword())
                .getName();
        assertThat(updatedName).isEqualTo("John Doe");

        // ШАГ 4: Проверяем алерт
        String alertText2 = getAlertTextAndAccept();
        assertThat(alertText2).contains("✅ Name updated successfully!");

        // ШАГ 6: Проверяем через UI
        new UserDashboard().open();
        Selenide.sleep(1000);

        assertThat(new UserDashboard().getWelcomeText().text())
                .contains("Welcome, John Doe!");
    }

    @Test
    @DisplayName("Негативный: имя из 2 символов")
    public void userCannotUpdateProfileWithInvalidName() {
        CreateUserRequest user = AdminSteps.createUser().getRequest();
        authAsUser(user);

        new UserDashboard().open().createNewAccount();
        acceptAlert();

        new ProfilePage().open()
                .enterNewName("Mt")
                .saveName();

        String alertText = getAlertTextAndAccept();
        assertThat(alertText).contains("Name must contain two words with letters only");
    }
}