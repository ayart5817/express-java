package iteration_2.ui;

import api.models.CreateUserRequest;
import api.requests.steps.ProfileSteps;
import com.codeborne.selenide.Selenide;
import iteration_1.common.annotations.UserSession;
import iteration_1.common.storage.SessionStorage;
import iteration_1.ui.BaseUiTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.pages.ProfilePage;
import ui.pages.UserDashboard;

import static org.assertj.core.api.Assertions.assertThat;

public class UIProfileUpdateTest extends BaseUiTest {

    @Test
    @DisplayName("Успешное обновление имени профиля")
    @UserSession // по умолчанию 1
    public void userCanUpdateProfileName() {
        CreateUserRequest user = SessionStorage.getUser(1);

        // ШАГ 1: Создаём счёт
        new UserDashboard().open().createNewAccount();
        acceptAlert();

        // ШАГ 2: Переходим в профиль и обновляем имя
        new ProfilePage().open()
                .enterNewName("John Doe")
                .saveName();

        // ШАГ 3: Проверяем алерт
        String alertText = getAlertTextAndAccept();
        assertThat(alertText).contains("✅ Name updated successfully!");

        // ШАГ 4: Проверяем через API
        String updatedName = ProfileSteps.getProfile(user.getUsername(), user.getPassword()).getName();
        assertThat(updatedName).isEqualTo("John Doe");

        // ШАГ 5: Проверяем через UI
        new UserDashboard().open();
        Selenide.sleep(1000);

        assertThat(new UserDashboard().getWelcomeText().text())
                .contains("Welcome, John Doe!");
    }

    @Test
    @DisplayName("Негативный: имя из 2 символов")
    @UserSession
    public void userCannotUpdateProfileWithInvalidName() {
        new UserDashboard().open().createNewAccount();
        acceptAlert();

        new ProfilePage().open()
                .enterNewName("Mt")
                .saveName();

        String alertText = getAlertTextAndAccept();
        assertThat(alertText).contains("Name must contain two words with letters only");
    }
}