package iteration_2;

import io.restassured.specification.RequestSpecification;
import iteration_1.BaseTest;
import models.*;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import requests.*;
import specs.RequestSpecs;
import specs.ResponseSpecs;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DepositTest extends BaseTest {


    // Поля для сохранения состояния между тестами (PER_CLASS)
    private CreateUserResponse user1;
    private CreateUserResponse user2;
    private AccountResponse account1;
    private AccountResponse account2;
    private String password;
    private String password2;

    @BeforeEach
    void initSoftly() {
        softly = new SoftAssertions();
    }
    @AfterEach
    void checkSoftly() {
        softly.assertAll();
    }


    @Order(1)
    @Test
    @DisplayName("Создание user1 админом")
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


    @Order(3)
    @Test
    @DisplayName("Создаем User2")
    public void user2GenerateTest() {
        //Подготовка данных статический юзер №2
        String username = "Ayrat2" + System.currentTimeMillis() % 1000000;
        password2 = "Ayrat123@";

        //создание пользователя
        CreateUserRequest request = CreateUserRequest.builder()
                .username(username)
                .password(password)
                .role(UserRole.USER.toString())
                .build();

        //Отправляем запрос через requester и храним по user2
        user2 = new AdminCreateUserRequester(
                RequestSpecs.adminSpec(),
                ResponseSpecs.entityWasCreated())
                .post(request)
                .extract()
                .as(CreateUserResponse.class);

        softly.assertThat(request.getUsername()).isEqualTo(user2.getUsername());

        account2 = new CreateAccountRequester(
                RequestSpecs.authAsUser(username, password),
                ResponseSpecs.entityWasCreated())
                .post(null)
                .extract()
                .as(AccountResponse.class);
        softly.assertThat(account2.getBalance()).isEqualTo(0.00);
    }

    @Order(4)
    @Test
    @DisplayName("Успешный депозит 4999.99")
        //проверяем депозит на 4999.99 U2
    void depositBelowLimit() {
        double amount = 4999.99;
        DepositRequest request = DepositRequest.builder()
                .id(account2.getId())
                .balance(amount)
                .build();

        AccountResponse response = new DepositRequester(
                RequestSpecs.authAsUser(user2.getUsername(), password2),
                ResponseSpecs.depositAccepted()
        )
                .post(request)
                .extract()
                .as(AccountResponse.class);
        softly.assertThat(response.getBalance()).isEqualTo(amount);
        softly.assertThat(response.getTransactions()).hasSize(1);
        softly.assertThat(response.getTransactions().get(0).getAmount()).isEqualTo(amount);
        softly.assertThat(response.getTransactions().get(0).getType()).isEqualTo(TransactionType.DEPOSIT.toString());
    }

    @Order(5)
    @Test
    @DisplayName("Успешный депозит на 5000.00")
    void depositExactLimit() {
        double amount = 5000.00;
        DepositRequest request = DepositRequest.builder()
                .id(account2.getId())
                .balance(amount)
                .build();

        AccountResponse response = new DepositRequester(
                RequestSpecs.authAsUser(user2.getUsername(), password2),
                ResponseSpecs.depositAccepted()
        )
                .post(request)
                .extract()
                .as(AccountResponse.class);
        softly.assertThat(response.getBalance()).isEqualTo(9999.99);
        softly.assertThat(response.getTransactions()).hasSize(2);

    }

    @Order(6)
    @Test
    @DisplayName("Не успешный депозит 5000.01")
        //проверяем депозит границу 5000.01 U2
    void depositAboveLimit() {
        double amount = 5000.01;
        DepositRequest request = DepositRequest.builder()
                .id(account2.getId())
                .balance(amount)
                .build();

        String response = new DepositRequester(
                RequestSpecs.authAsUser(user2.getUsername(), password2),
                ResponseSpecs.depositRejectedPlainText()
        )
                .post(request)
                .extract()
                .asString();

        softly.assertThat(response).contains("Deposit amount cannot exceed 5000");
    }

    @Order(7)
    @Test
    @DisplayName("Отклонение депозита при сумме 0.00 ")
    void depositZero() {
        double amount = 0.00;
        DepositRequest request = DepositRequest.builder()
                .id(account2.getId())
                .balance(amount)
                .build();
        String response = new DepositRequester(
                RequestSpecs.authAsUser(user2.getUsername(), password2),
                ResponseSpecs.depositRejectedPlainText()
        )
                .post(request)
                .extract()
                .asString();

        softly.assertThat(response).contains("Deposit amount must be at least 0.01");
    }

    @Order(8)
    @Test
    @DisplayName("Отклонение депозита при сумме -0.01 ")
    void depositNegative() {
        double amount = -0.01;
        DepositRequest request = DepositRequest.builder()
                .id(account2.getId())
                .balance(amount)
                .build();
        String response = new DepositRequester(
                RequestSpecs.authAsUser(user2.getUsername(), password2),
                ResponseSpecs.depositRejectedPlainText()
        )
                .post(request)
                .extract()
                .asString();

        softly.assertThat(response).contains("Deposit amount must be at least 0.01");
    }

    @Order(9)
    @Test
    @DisplayName("Отклонение депозита Unauthorized")
    void depositToForeignAccount() {
        double amount = 100;
        DepositRequest request = DepositRequest.builder()
                .id(account2.getId())
                .balance(amount)
                .build();
        String response = new DepositRequester(
                RequestSpecs.authAsUser(user1.getUsername(), password2),
                ResponseSpecs.forbiddenAccess()
        )
                .post(request)
                .extract()
                .asString();

        softly.assertThat(response).isEqualTo("Unauthorized access to account");
    }

    @Order(10)
    @Test
    @DisplayName("Успешный депозит на 0.01")
    void depositMinLimit() {
        double amount = 0.01;
        DepositRequest request = DepositRequest.builder()
                .id(account2.getId())
                .balance(amount)
                .build();

        AccountResponse response = new DepositRequester(
                RequestSpecs.authAsUser(user2.getUsername(), password2),
                ResponseSpecs.depositAccepted()
        )
                .post(request)
                .extract()
                .as(AccountResponse.class);
        softly.assertThat(response.getBalance()).isEqualTo(10000);
        softly.assertThat(response.getTransactions()).hasSize(3);

    }

    @Order(13)
    @Test
    @DisplayName("Итоговое состояние счётов u2")
        //проверка что транзакции содержат только успешные кейсы
    void verificationBalance() {
        List<Transaction> transactions = new TransactionRequester(
                RequestSpecs.authAsUser(user2.getUsername(), password2),
                ResponseSpecs.transactionsAccounts(), account2.getId()
        )
                .get()
                .extract()
                .jsonPath()
                .getList(".", Transaction.class);

        softly.assertThat(transactions).hasSize(3);
        softly.assertThat(transactions.get(2).getType()).isEqualTo(TransactionType.DEPOSIT.toString());
        softly.assertThat(transactions.get(1).getRelatedAccountId()).isEqualTo(account2.getId());

    }

    @Order(14)
    @Test
    @DisplayName("Итоговое состояние счётов u1")
        //проверка что транзакции нет у аккаунта без операции
    void verificationBalanceU2() {
        List<Transaction> transactions = new TransactionRequester(
                RequestSpecs.authAsUser(user1.getUsername(), password),
                ResponseSpecs.transactionsAccounts(), account1.getId()
        )
                .get()
                .extract()
                .jsonPath()
                .getList(".", Transaction.class);

        softly.assertThat(transactions).hasSize(0);

    }
}