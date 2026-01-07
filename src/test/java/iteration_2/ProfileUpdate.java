package iteration_2;


import io.restassured.specification.RequestSpecification;
import models.*;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import requests.AdminCreateUserRequester;
import requests.CreateAccountRequester;
import requests.UpdateProfileRequester;
import requests.UserProfileRequester;
import specs.RequestSpecs;
import specs.ResponseSpecs;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Тестирование обновления профиля пользователя")


public class ProfileUpdate extends BaseModel {
    private CreateUserResponse user1;
    private AccountResponse account1;
    private String password;
    private SoftAssertions softly;

    @AfterEach
    void checkSoftly() {
        softly.assertAll();
    }

    @BeforeEach
    void initSoftly() {
        softly = new SoftAssertions();
    }

    @Order(1)
    @Test
    @DisplayName("Создание User1 админом")
    public void user1GenerateTest() {
        //Подготовка данных статический юзер №1
        String username = "Ayrat" + System.currentTimeMillis() % 100000;
        password = "Ayrat123@";

        //создание пользователя
        CreateUserRequest request = CreateUserRequest.builder()
                .username(username)
                .password(password)
                .role(UserRole.USER.toString())
                .build();

        //Отправляем запрос через requester и храним по user1
        user1 = new AdminCreateUserRequester(
                RequestSpecs.adminSpec(),
                ResponseSpecs.entityWasCreated())
                .post(request)
                .extract()
                .as(CreateUserResponse.class);


        softly.assertThat(request.getUsername()).isEqualTo(user1.getUsername());
    }


    @Order(2)
    @Test
    @DisplayName("User1 логинится и создаёт счёт")
    public void user1CreatesAccount() {
        //логин
        RequestSpecification user1Spec = RequestSpecs.authAsUser(user1.getUsername(), password);


        //создаем счет
        account1 = new CreateAccountRequester(
                user1Spec,
                ResponseSpecs.entityWasCreated())
                .post(null)
                .extract()
                .as(AccountResponse.class);


        softly.assertThat(account1.getAccountNumber()).startsWith("ACC");
        softly.assertThat(account1.getBalance()).isEqualTo(0.0);
        softly.assertThat(account1.getTransactions()).isEmpty();
    }

    @Order(3)
    @Test
    @DisplayName("Проверка профиля - значение по умолчанию")
        //проверка что транзакции содержат только успешные кейсы U1
    void verificationDefaultValue() {

        UserProfileResponse profile = new UserProfileRequester(
                RequestSpecs.authAsUser(user1.getUsername(), password),
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(UserProfileResponse.class);
        //берем нужный аккаунт
        AccountResponse account = profile.getAccountById(account1.getId())
                .orElseThrow(() -> new RuntimeException("Нет аккаунтов"));

        double balance = profile.getAccounts().get(0).getBalance();

        softly.assertThat(profile.getId()).isEqualTo(user1.getId());
        softly.assertThat(profile.getUsername()).isEqualTo(user1.getUsername());
        softly.assertThat(profile.getRole()).isEqualTo("USER");


    }

    @Order(4)
    @Test
    @DisplayName("Update UserName минимальные символы")
        //проверка что транзакции содержат только успешные кейсы U1
    void updateUserName() {
        String newName = "M t";

        UpdateProfileRequest request = UpdateProfileRequest.builder()
                .name(newName)
                .build();
        UserProfileUpdateResponse response = new UpdateProfileRequester(
                RequestSpecs.authAsUser(user1.getUsername(), password),
                ResponseSpecs.requestReturnsOK())
                .put(request)
                .extract()
                .as(UserProfileUpdateResponse.class);

        softly.assertThat(response.getCustomer().getName()).isEqualTo(newName);
        softly.assertThat(response.getMessage()).isEqualTo("Profile updated successfully");
    }

    @Order(5)
    @Test
    @DisplayName("Update UserName 15")
        //15 символов ок
    void updateUserLongName() {
        String newName = "qwertyuio pasdf";

        UpdateProfileRequest request = UpdateProfileRequest.builder()
                .name(newName)
                .build();
        UserProfileUpdateResponse response = new UpdateProfileRequester(
                RequestSpecs.authAsUser(user1.getUsername(), password),
                ResponseSpecs.requestReturnsOK())
                .put(request)
                .extract()
                .as(UserProfileUpdateResponse.class);

        softly.assertThat(response.getCustomer().getName()).isEqualTo(newName);
        softly.assertThat(response.getMessage()).isEqualTo("Profile updated successfully");
    }

    @Order(6)
    @Test
    @DisplayName("Update UserName Негативный - минимальные символы в имени")
        //проверка что транзакции содержат только успешные кейсы U1
    void update2simbolUserName() {
        String newName = "Mt";

        UpdateProfileRequest request = UpdateProfileRequest.builder()
                .name(newName)
                .build();
        String response = new UpdateProfileRequester(
                RequestSpecs.authAsUser(user1.getUsername(), password),
                ResponseSpecs.transferRejectedPlainText())
                .put(request)
                .extract()
                .asString();

        softly.assertThat(response).isEqualTo("Name must contain two words with letters only");

    }

    @Order(7)
    @Test
    @DisplayName("Update UserName Негативный - максимальные  символы в имени")
        //16 символов НЕ ок должен падать с ошибкой (>16 символов в имени)
    void updateNigativeSombolName() {
        String newName = "qwertyuio pasdfа";

        UpdateProfileRequest request = UpdateProfileRequest.builder()
                .name(newName)
                .build();
        String response = new UpdateProfileRequester(
                RequestSpecs.authAsUser(user1.getUsername(), password),
                ResponseSpecs.transferRejectedPlainText())
                .put(request)
                .extract()
                .asString();

        softly.assertThat(response).isEqualTo("Name must contain two words with letters only");

    }

    @Order(8)
    @Test
    @DisplayName("Профиль: пустое имя → ошибка")
        //16 символов НЕ ок должен падать с ошибкой (>16 символов в имени)
    void updateProfileEmptyName() {
        String newName = "";

        UpdateProfileRequest request = UpdateProfileRequest.builder()
                .name(newName)
                .build();
        String response = new UpdateProfileRequester(
                RequestSpecs.authAsUser(user1.getUsername(), password),
                ResponseSpecs.transferRejectedPlainText())
                .put(request)
                .extract()
                .asString();

        softly.assertThat(response).isEqualTo("Name must contain two words with letters only");

    }

    @Order(9)
    @Test
    @DisplayName("Профиль: null имя → обработанная ошибка")
        //не обработана ошибка null (500 ответ) updateCustomerProfile 90 строка пытается вызвать метод matches() с null
    void updateProfileNameWithDigits() {
        String newName = null;

        UpdateProfileRequest request = UpdateProfileRequest.builder()
                .name(newName)
                .build();
        String response = new UpdateProfileRequester(
                RequestSpecs.authAsUser(user1.getUsername(), password),
                ResponseSpecs.transferRejectedPlainText())
                .put(request)
                .extract()
                .asString();

        softly.assertThat(response).isEqualTo("Name must contain two words with letters only");

    }

    @Order(10)
    @Test
    @DisplayName("Профиль: имя с разрешенными символами → обработанная ошибка")
        //по ТЗ разрешены символы уточнить какие символы разрешены
    void updateProfileNullName() {
        String newName = "_–A.- 0123456789";

        UpdateProfileRequest request = UpdateProfileRequest.builder()
                .name(newName)
                .build();
        UserProfileUpdateResponse response = new UpdateProfileRequester(
                RequestSpecs.authAsUser(user1.getUsername(), password),
                ResponseSpecs.requestReturnsOK())
                .put(request)
                .extract()
                .as(UserProfileUpdateResponse.class);

        softly.assertThat(response.getCustomer().getName()).isEqualTo(newName);
        softly.assertThat(response.getMessage()).isEqualTo("Profile updated successfully");

    }

}
