package iteration_2;

import io.restassured.specification.RequestSpecification;
import iteration_1.BaseTest;
import models.*;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import requests.*;
import specs.RequestSpecs;
import specs.ResponseSpecs;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransferTest extends BaseTest {


    // Поля для сохранения состояния между тестами (PER_CLASS)
    private CreateUserResponse user1;
    private CreateUserResponse user2;
    private AccountResponse account1;
    private AccountResponse account2;
    private AccountResponse account2_2;
    private String password;
    private String password2;


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
    @DisplayName("Пополняем депозит на 20000 U2")
        //Готовим депозит для тестов
    void depositBelowLimit() {
        double amount = 5000;
        int times = 3;

        for (int i = 1; i <= times; i++) {
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
            softly.assertThat(response.getBalance()).isEqualTo(amount * i);

        }
    }

    @Order(5)
    @Test
    @DisplayName("Успешный трансфер макс сумма")
    void transferExactLimit() {
        Double sum = 10000.00;
        TransferRequest request = TransferRequest.builder()
                .senderAccountId(account2.getId())
                .receiverAccountId(account1.getId())
                .amount(sum)
                .build();

        TransferResponse response = new TransferRequester(
                RequestSpecs.authAsUser(user2.getUsername(), password2),
                ResponseSpecs.transferAccepted())
                .post(request)
                .extract()
                .as(TransferResponse.class);
        softly.assertThat(response.getMessage()).isEqualTo("Transfer successful");
        softly.assertThat(response.getAmount()).isEqualTo(10000.00);
        softly.assertThat(response.getSenderAccountId()).isEqualTo(account2.getId());
        softly.assertThat(response.getReceiverAccountId()).isEqualTo(account1.getId());


    }

    @Order(6)
    @Test
    @DisplayName("Проверка балансов после перевода U2 → u1")
        //проверка что транзакции содержат только успешные кейсы
    void verificationBalanceU2AfterFirtsDeposit() {
        List<Transaction> transactions = new TransactionRequester(
                RequestSpecs.authAsUser(user2.getUsername(), password2),
                ResponseSpecs.transactionsAccounts(), account2.getId()
        )
                .get()
                .extract()
                .jsonPath()
                .getList(".", Transaction.class);

        List<Transaction> transactionsSorted = transactions.stream()
                .sorted(Comparator.comparing(Transaction::getId))
                .collect(Collectors.toList());

        softly.assertThat(transactions).hasSize(4);
        softly.assertThat(transactionsSorted.get(3).getType()).isEqualTo(TransactionType.TRANSFER_OUT.toString());
        softly.assertThat(transactionsSorted.get(3).getAmount()).isEqualTo(10000.00);


    }

    @Order(7)
    @Test
    @DisplayName("Проверка балансов после перевода u2 → U1")
        //проверка что транзакции содержат только успешные кейсы U1
    void verificationBalanceU1AfterFirtsTransfer() {

        UserProfileResponse profile = new UserProfileRequester(
                RequestSpecs.authAsUser(user1.getUsername(), password),
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(UserProfileResponse.class);

        softly.assertThat(profile.getId()).isEqualTo(user1.getId());
        softly.assertThat(profile.getUsername()).isEqualTo(user1.getUsername());
        softly.assertThat(profile.getRole()).isEqualTo("USER");


    }

    @Order(8)
    @Test
    @DisplayName("Не успешный трансфер мин сумма 10000.01")
    void transfer() {
        Double sum = 10000.01;
        TransferRequest request = TransferRequest.builder()
                .senderAccountId(account2.getId())
                .receiverAccountId(account1.getId())
                .amount(sum)
                .build();

        String response = new TransferRequester(
                RequestSpecs.authAsUser(user2.getUsername(), password2),
                ResponseSpecs.transferRejectedPlainText())
                .post(request)
                .extract()
                .asString();

        softly.assertThat(response).isEqualTo("Transfer amount cannot exceed 10000");

    }

    @Order(9)
    @Test
    @DisplayName("Не успешный трансфер мин сумма -00000.01")
    void transferNegativeSum() {
        Double sum = -0000.01;
        TransferRequest request = TransferRequest.builder()
                .senderAccountId(account2.getId())
                .receiverAccountId(account1.getId())
                .amount(sum)
                .build();

        String response = new TransferRequester(
                RequestSpecs.authAsUser(user2.getUsername(), password2),
                ResponseSpecs.transferRejectedPlainText())
                .post(request)
                .extract()
                .asString();

        softly.assertThat(response).isEqualTo("Transfer amount must be at least 0.01");

    }

    @Order(10)
    @Test
    @DisplayName("Успешный трансфер мин сумма 0.01")
    void transferMinValue() {
        Double sum = 0.01;
        TransferRequest request = TransferRequest.builder()
                .senderAccountId(account2.getId())
                .receiverAccountId(account1.getId())
                .amount(sum)
                .build();

        TransferResponse response = new TransferRequester(
                RequestSpecs.authAsUser(user2.getUsername(), password2),
                ResponseSpecs.requestReturnsOK())
                .post(request)
                .extract()
                .as(TransferResponse.class);

        softly.assertThat(response.getMessage()).isEqualTo("Transfer successful");
        softly.assertThat(response.getAmount()).isEqualTo(0.01);
        softly.assertThat(response.getSenderAccountId()).isEqualTo(account2.getId());
        softly.assertThat(response.getReceiverAccountId()).isEqualTo(account1.getId());

    }

    @Order(11)
    @Test
    @DisplayName("Проверка балансов после второго перевода u2 → U1")
        //проверка что транзакции содержат только успешные кейсы U1
    void verificationBalanceU1AfterSecondTransfer() {

        UserProfileResponse profile = new UserProfileRequester(
                RequestSpecs.authAsUser(user1.getUsername(), password),
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(UserProfileResponse.class);
        //запоминаем ответ значения баланса профиля
        double balance = profile.getAccounts().get(0).getBalance();

        softly.assertThat(profile.getId()).isEqualTo(user1.getId());
        softly.assertThat(profile.getUsername()).isEqualTo(user1.getUsername());
        softly.assertThat(profile.getRole()).isEqualTo("USER");
        softly.assertThat(balance).isEqualTo(10000.01);
    }

    @Order(12)
    @Test
    @DisplayName("12 Проверка балансов после второго перевода U2 → u1")
        //проверка что транзакции содержат только успешные кейсы U1
    void verificationBalanceU2AfterSecondTransfer() {

        UserProfileResponse profile = new UserProfileRequester(
                RequestSpecs.authAsUser(user2.getUsername(), password),
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(UserProfileResponse.class);
        //запоминаем сортированный ответ транзакции первого аккаунта
        AccountResponse account = profile.getAccountById(account2.getId())
                .orElseThrow(() -> new RuntimeException("Нет аккаунтов"));
        List<Transaction> sortedTransactions = account.getSortedTransactions();
        Transaction lastTransaction = account.getLastTransaction()
                .orElseThrow(() -> new RuntimeException("Нет транзакций"));

        double balance = profile.getAccounts().get(0).getBalance();

        softly.assertThat(profile.getId()).isEqualTo(user2.getId());
        softly.assertThat(profile.getUsername()).isEqualTo(user2.getUsername());
        softly.assertThat(profile.getRole()).isEqualTo("USER");
        softly.assertThat(balance).isEqualTo(4999.99);
        softly.assertThat(lastTransaction.getAmount()).isEqualTo(0.01);
        softly.assertThat(sortedTransactions.size()).isEqualTo(5);


    }

    @Order(13)
    @Test
    @DisplayName("Не успешный трансфер > суммы на депозите")
    void transferMoreBalance() {
        Double sum = 5000.00;
        TransferRequest request = TransferRequest.builder()
                .senderAccountId(account2.getId())
                .receiverAccountId(account1.getId())
                .amount(sum)
                .build();

        String response = new TransferRequester(
                RequestSpecs.authAsUser(user2.getUsername(), password2),
                ResponseSpecs.depositRejectedPlainText())
                .post(request)
                .extract()
                .asString();

        softly.assertThat(response).isEqualTo("Invalid transfer: insufficient funds or invalid accounts");

    }
    @Order(14)
    @Test
    @DisplayName("User2 логинится и создаёт 2 счёт")
    public void user1CreatesSecondAccount() {
        //логин U2
        RequestSpecification user2Spec = RequestSpecs.authAsUser(user2.getUsername(), password2);


        //создаем счет U2 аккаунт 2
        account2_2 = new CreateAccountRequester(
                user2Spec,
                ResponseSpecs.entityWasCreated())
                .post(null)
                .extract()
                .as(AccountResponse.class);


        //мягкие проверки
        softly.assertThat(account1.getAccountNumber()).startsWith("ACC");
        softly.assertThat(account1.getBalance()).isEqualTo(0.0);
        softly.assertThat(account1.getTransactions()).isEmpty();
    }
    @Order(15)
    @Test
    @DisplayName("Успешный трансфер мин сумма 0.01 c 1 аккаунта на 2 U2")
    void transferMinValueTwoAccounts() {
        Double sum = 0.01;
        TransferRequest request = TransferRequest.builder()
                .senderAccountId(account2.getId())
                .receiverAccountId(account2_2.getId())
                .amount(sum)
                .build();

        TransferResponse response = new TransferRequester(
                RequestSpecs.authAsUser(user2.getUsername(), password2),
                ResponseSpecs.requestReturnsOK())
                .post(request)
                .extract()
                .as(TransferResponse.class);

        softly.assertThat(response.getMessage()).isEqualTo("Transfer successful");
        softly.assertThat(response.getAmount()).isEqualTo(sum);
        softly.assertThat(response.getSenderAccountId()).isEqualTo(account2.getId());
        softly.assertThat(response.getReceiverAccountId()).isEqualTo(account2_2.getId());

    }
    @Order(16)
    @Test
    @DisplayName("12 Проверка балансов после второго перевода U2 → U2")
        //проверка что транзакции содержат только успешные кейсы U1
    void verificationBalanceU2AfterTransferTwoAccount() {

        UserProfileResponse profile = new UserProfileRequester(
                RequestSpecs.authAsUser(user2.getUsername(), password),
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(UserProfileResponse.class);
        //запоминаем сортированный ответ транзакции первого аккаунта
        AccountResponse account = profile.getAccountById(account2.getId())
                .orElseThrow(() -> new RuntimeException("Нет аккаунтов"));
        List<Transaction> sortedTransactions = account.getSortedTransactions();
        Transaction lastTransaction = account.getLastTransaction()
                .orElseThrow(() -> new RuntimeException("Нет транзакций"));

        double balance = account.getBalance();

        softly.assertThat(profile.getId()).isEqualTo(user2.getId());
        softly.assertThat(profile.getUsername()).isEqualTo(user2.getUsername());
        softly.assertThat(profile.getRole()).isEqualTo("USER");
        softly.assertThat(balance).isEqualTo(4999.98);
        softly.assertThat(lastTransaction.getAmount()).isEqualTo(0.01);
        softly.assertThat(lastTransaction.getType()).isEqualTo(TransactionType.TRANSFER_OUT.toString());
        softly.assertThat(sortedTransactions.size()).isEqualTo(6);


    }
    @Order(17)
    @Test
    @DisplayName("Успешный трансфер мин сумма 0.01 c 2 аккаунта U2 на аккаунт 1 U1")
    void transferMinValueTwoAccountsOnUser1() {
        Double sum = 0.01;
        TransferRequest request = TransferRequest.builder()
                .senderAccountId(account2_2.getId())
                .receiverAccountId(account1.getId())
                .amount(sum)
                .build();

        TransferResponse response = new TransferRequester(
                RequestSpecs.authAsUser(user2.getUsername(), password2),
                ResponseSpecs.requestReturnsOK())
                .post(request)
                .extract()
                .as(TransferResponse.class);

        softly.assertThat(response.getMessage()).isEqualTo("Transfer successful");
        softly.assertThat(response.getAmount()).isEqualTo(sum);
        softly.assertThat(response.getSenderAccountId()).isEqualTo(account2_2.getId());
        softly.assertThat(response.getReceiverAccountId()).isEqualTo(account1.getId());

    }
    @Order(18)
    @Test
    @DisplayName("12 Проверка балансов после второго перевода U2 → U2")
        //проверка что транзакции содержат только успешные кейсы U1
    void verificationBalanceU2Account2() {

        UserProfileResponse profile = new UserProfileRequester(
                RequestSpecs.authAsUser(user2.getUsername(), password),
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(UserProfileResponse.class);
        //запоминаем нужный аккаунт
        AccountResponse accountFiltered = profile.getAccounts().stream()
                .filter(account -> account.getId() == account2_2.getId()) // Или account2.getId()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Аккаунт с " + account2_2 + " не найден"));

        List<Transaction> lastTransaction = accountFiltered.getSortedTransactions();

        double balance = accountFiltered.getBalance();


        softly.assertThat(profile.getId()).isEqualTo(user2.getId());
        softly.assertThat(profile.getUsername()).isEqualTo(user2.getUsername());
        softly.assertThat(profile.getRole()).isEqualTo("USER");
        softly.assertThat(balance).isEqualTo(0);
        softly.assertThat(lastTransaction.get(0).getAmount()).isEqualTo(0.01);
        softly.assertThat(lastTransaction.get(0).getType()).isEqualTo(TransactionType.TRANSFER_OUT.toString());
        softly.assertThat(accountFiltered.getTransactions().size()).isEqualTo(2);


    }
}