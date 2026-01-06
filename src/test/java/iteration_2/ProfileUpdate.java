package iteration_2;


import io.restassured.specification.RequestSpecification;
import models.*;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import requests.AdminCreateUserRequester;
import requests.CreateAccountRequester;
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


        //мягкие проверки
        softly.assertThat(account1.getAccountNumber()).startsWith("ACC");
        softly.assertThat(account1.getBalance()).isEqualTo(0.0);
        softly.assertThat(account1.getTransactions()).isEmpty();
    }


}
