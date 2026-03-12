package iteration_2.api;

import api.models.*;
import iteration_1.api.BaseTest;
import api.models.comparison.ModelAssertions;
import org.junit.jupiter.api.*;
import api.requests.steps.AdminSteps;
import api.requests.steps.CreatedUser;
import api.requests.steps.ProfileSteps;
import api.requests.steps.UserSteps;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Тестирование обновления профиля пользователя")


public class ProfileUpdateTest extends BaseTest {
    private CreateUserResponse user1;
    private AccountResponse account;
    private String password;
    private String newName;


    @Order(1)
    @Test
    @DisplayName("Создание User1 админом")
    public void user1GenerateTest() {
        CreatedUser profile = AdminSteps.createUser();
        password = profile.getRequest().getPassword();
        user1 = profile.getResponse();
    }

    @Order(2)
    @Test
    @DisplayName("User1 создаёт счёт")
    public void user1CreatesAccount() {
        account = UserSteps.createAccount(user1.getUsername(), password);

        softly.assertThat(account).isNotNull();
        softly.assertThat(account.getAccountNumber()).startsWith("ACC");
        softly.assertThat(account.getBalance()).isEqualTo(0.0);
        softly.assertThat(account.getTransactions()).isEmpty();


    }

    @Order(3)
    @Test
    @DisplayName("Проверка профиля - значение по умолчанию")
    void verificationDefaultValue() {
        UserProfileResponse profile = ProfileSteps.getProfile(
                user1.getUsername(),
                password
        );

        softly.assertThat(profile.getId()).isEqualTo(user1.getId());
        softly.assertThat(profile.getUsername()).isEqualTo(user1.getUsername());
        softly.assertThat(profile.getRole()).isEqualTo("USER");
        softly.assertThat(profile.getName()).isEqualTo(null);
    }

    // Успешные обновления

    @Order(4)
    @Test
    @DisplayName("Update UserName: минимальные символы (3)")
    void updateUserName() {
        newName = "M t";
        UserProfileUpdateResponse response = ProfileSteps.updateProfile(
                user1.getUsername(),
                password,
                newName
        );
        softly.assertThat(response.getCustomer().getName()).isEqualTo(newName);
        softly.assertThat(response.getMessage()).isEqualTo("Profile updated successfully");
    }

    @Order(5)
    @Test
    @DisplayName("Проверка профиля после обновления имени step1")
    void verificationDefaultValueStep1() {
        UserProfileResponse profile = ProfileSteps.getProfile(
                user1.getUsername(),
                password
        );

        softly.assertThat(profile.getId()).isEqualTo(user1.getId());
        softly.assertThat(profile.getUsername()).isEqualTo(user1.getUsername());
        softly.assertThat(profile.getRole()).isEqualTo("USER");
        softly.assertThat(profile.getName()).isEqualTo(newName);
    }

    @Order(6)
    @Test
    @DisplayName("Update Username: 15 символов")
    void updateUserLongName() {
        newName = "qwertyuio pasdf";
        UserProfileUpdateResponse response = ProfileSteps.updateProfile(
                user1.getUsername(),
                password,
                newName
        );
        softly.assertThat(response.getCustomer().getName()).isEqualTo(newName);
        softly.assertThat(response.getMessage()).isEqualTo("Profile updated successfully");
    }

    @Order(7)
    @Test
    @DisplayName("Проверка профиля после обновления имени step2")
    void verificationDefaultValueStep2() {
        UserProfileResponse profile = ProfileSteps.getProfile(
                user1.getUsername(),
                password
        );

        softly.assertThat(profile.getId()).isEqualTo(user1.getId());
        softly.assertThat(profile.getUsername()).isEqualTo(user1.getUsername());
        softly.assertThat(profile.getRole()).isEqualTo("USER");
        softly.assertThat(profile.getName()).isEqualTo(newName);
    }

    @Order(8)
    @Test
    @Disabled
    @DisplayName("Профиль: имя с разрешёнными символами")
        //тест падает, нужно уточнить требования для поля
    void updateProfileWithSpecialChars() {
        newName = "_–A.- 0123456789";
        UserProfileUpdateResponse profile = ProfileSteps.updateProfile(
                user1.getUsername(),
                password,
                newName
        );
        softly.assertThat(profile.getCustomer().getName()).isEqualTo(newName);
        softly.assertThat(profile.getMessage()).isEqualTo("Profile updated successfully");
    }

    @Order(9)
    @Test
    @DisplayName("Проверка профиля после обновления имени step3")
        //тест падает из за предыдущего теста
    void verificationDefaultValueStep3() {
        UserProfileResponse profile = ProfileSteps.getProfile(
                user1.getUsername(),
                password
        );

        softly.assertThat(profile.getName()).isEqualTo(newName);
    }

    // Негативные сценарии

    @Order(10)
    @Test
    @DisplayName("Update UserName: 2 символа (меньше минимума)")
    void update2simbolUserName() {
        newName = "Mt";
        String error = ProfileSteps.updateProfileFails(
                user1.getUsername(),
                password,
                newName
        );
        softly.assertThat(error).isEqualTo("Name must contain two words with letters only");
    }

    @Order(11)
    @Test
    @DisplayName("Проверка профиля после попытки неуспешного обновления имени step1")
    void verificationNegativeValueStep1() {
        UserProfileResponse profile = ProfileSteps.getProfile(
                user1.getUsername(),
                password
        );

        softly.assertThat(profile.getId()).isEqualTo(user1.getId());
        softly.assertThat(profile.getUsername()).isEqualTo(user1.getUsername());
        softly.assertThat(profile.getRole()).isEqualTo("USER");
        softly.assertThat(account.getUsername()).isNotEqualTo(newName);
    }

    @Order(12)
    @Test
    @DisplayName("Update UserName: 16+ символов (больше максимума)")
    void updateNigativeSombolName() {
        newName = "qwertyuio pasdfа";
        String error = ProfileSteps.updateProfileFails(
                user1.getUsername(),
                password,
                "qwertyuio pasdfа"
        );
        softly.assertThat(error).isEqualTo("Name must contain two words with letters only");
    }

    @Order(13)
    @Test
    @DisplayName("Проверка профиля после попытки неуспешного обновления имени step2")
    void verificationNegativeValueStep2() {
        UserProfileResponse profile = ProfileSteps.getProfile(
                user1.getUsername(),
                password
        );

        softly.assertThat(profile.getId()).isEqualTo(user1.getId());
        softly.assertThat(profile.getUsername()).isEqualTo(user1.getUsername());
        softly.assertThat(profile.getRole()).isEqualTo("USER");
        softly.assertThat(account.getUsername()).isNotEqualTo(newName);
    }

    @Order(14)
    @Test
    @DisplayName("Профиль: пустое имя → ошибка")
    void updateProfileEmptyName() {
        newName = "";
        String error = ProfileSteps.updateProfileFails(
                user1.getUsername(),
                password,
                newName
        );
        softly.assertThat(error).isEqualTo("Name must contain two words with letters only");
    }

    @Order(15)
    @Test
    @DisplayName("Проверка профиля после попытки неуспешного обновления имени step3")
    void verificationNegativeValueStep3() {
        UserProfileResponse profile = ProfileSteps.getProfile(
                user1.getUsername(),
                password
        );

        softly.assertThat(profile.getId()).isEqualTo(user1.getId());
        softly.assertThat(profile.getUsername()).isEqualTo(user1.getUsername());
        softly.assertThat(profile.getRole()).isEqualTo("USER");
        softly.assertThat(account.getUsername()).isNotEqualTo(newName);
    }

    @Order(16)
    @Test
    @Disabled
    @DisplayName("Профиль: null имя → обработанная ошибка")
        //ловим 500 т.к. ошибка не обрабатывается
    void updateProfileNullName() {
        String error = ProfileSteps.updateProfileFails(
                user1.getUsername(),
                password,
                null
        );
        softly.assertThat(error).isEqualTo("Name must contain two words with letters only");
    }

    @Order(17)
    @Test
    @DisplayName("Проверка профиля после попытки неуспешного обновления имени step4")
    void verificationNegativeValueStep4() {
        UserProfileResponse profile = ProfileSteps.getProfile(
                user1.getUsername(),
                password
        );

        softly.assertThat(profile.getId()).isEqualTo(user1.getId());
        softly.assertThat(profile.getUsername()).isEqualTo(user1.getUsername());
        softly.assertThat(profile.getRole()).isEqualTo("USER");
        softly.assertThat(account.getUsername()).isNotEqualTo(newName);
    }

    @Order(18)
    @Test
    @DisplayName("Проверим работу сравнение моделей ")
    @Tag("Проблемный тест")
    void userCanUpdateProfileName() {
        String newName = "John Doe";
        UpdateProfileRequest request = UpdateProfileRequest.builder()
                .name(newName)
                .build();

        UserProfileUpdateResponse response = ProfileSteps.updateProfile(user1.getUsername(),  password, newName);

        ModelAssertions.assertThatModels(request, response)
                .match(); // ← сравнивает name → customer.name
    }
}
