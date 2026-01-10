package iteration_2;

import iteration_1.BaseTest;
import models.*;
import models.comparison.ModelAssertions;
import org.junit.jupiter.api.*;
import requests.skeleton.Endpoint;
import requests.skeleton.requester.ValidatedCrudRequester;
import requests.steps.AdminSteps;
import requests.steps.CreatedUser;
import requests.steps.ProfileSteps;
import requests.steps.UserSteps;
import specs.RequestSpecs;
import specs.ResponseSpecs;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransferTest extends BaseTest {


    // Поля для сохранения состояния между тестами
    private CreateUserResponse user1;
    private CreateUserResponse user2;
    private AccountResponse account1;
    private AccountResponse account2;
    private AccountResponse account2_2;
    private String password;
    private String password2;

    @Order(1)
    @Test
    @DisplayName("Создание user1 + аккаунт – админом ")
    public void user1GenerateTest() {
        CreatedUser createUser = AdminSteps.createUser();
        user1 = createUser.getResponse();
        password = createUser.getRequest().getPassword();

        account1 = UserSteps.createAccount(user1.getUsername(), password);

        softly.assertThat(account1.getAccountNumber()).startsWith("ACC");
        softly.assertThat(account1.getBalance()).isEqualTo(0.0);
        softly.assertThat(account1.getTransactions()).isEmpty();
        softly.assertThat(createUser.getRequest().getUsername()).isEqualTo(createUser.getResponse().getUsername());
    }

    @Order(2)
    @Test
    @DisplayName("Создание user2 + аккаунт – админом ")
    public void user2CreatesAccount() {

        CreatedUser createUser = AdminSteps.createUser();
        user2 = createUser.getResponse();
        password2 = createUser.getRequest().getPassword();

        account2 = UserSteps.createAccount(user2.getUsername(), password2);

        softly.assertThat(account1.getAccountNumber()).startsWith("ACC");
        softly.assertThat(account1.getBalance()).isEqualTo(0.0);
        softly.assertThat(account1.getTransactions()).isEmpty();
        softly.assertThat(createUser.getRequest().getUsername()).isEqualTo(createUser.getResponse().getUsername());

    }

    @Order(3)
    @Test
    @DisplayName("Пополняем депозит на 15000 U2 (3 × 5000)")
    void depositBelowLimit() {
        for (int i = 1; i <= 4; i++) {
            AccountResponse response = UserSteps.makeDeposit(
                    user2.getUsername(),
                    password2,
                    account2.getId(),
                    5000.0
            );
            softly.assertThat(response.getBalance()).isEqualTo(5000.0 * i);
        }
    }

    @Order(5)
    @Test
    @DisplayName("Успешный трансфер макс сумма + тестовая проба использовать сравнение моделей")
    void transferExactLimit() {
        double sum = 10000.00;
        TransferRequest request = TransferRequest.builder()
                .senderAccountId(account2.getId())
                .receiverAccountId(account1.getId())
                .amount(sum)
                .build();

        TransferResponse response = new ValidatedCrudRequester<TransferResponse>(
                RequestSpecs.authAsUser(user2.getUsername(), password2),
                Endpoint.ACCOUNTS_TRANSFER,
                ResponseSpecs.transferAccepted()
        ).postAndExtract(request);


        softly.assertThat(response.getMessage()).isEqualTo("Transfer successful");
        softly.assertThat(response.getAmount()).isEqualTo(sum);
        softly.assertThat(response.getSenderAccountId()).isEqualTo(account2.getId());
        softly.assertThat(response.getReceiverAccountId()).isEqualTo(account1.getId());

        //тестовая проба использовать сравнение моделей, работает, но в текущих тестах не очень удобен
        ModelAssertions.assertThatModels(request, response).match();
    }

    @Order(6)
    @Test
    @DisplayName("Проверка транзакций user2 после перевода")
    void verificationTransactionU2AfterFirstTransfer() {
        List<Transaction> transactions = UserSteps.getTransactions(
                user2.getUsername(),
                password2,
                account2.getId()
        );

        List<Transaction> sorted = transactions.stream()
                .sorted(Comparator.comparing(Transaction::getId))
                .collect(Collectors.toList());

        softly.assertThat(transactions).hasSize(5);
        softly.assertThat(sorted.get(4).getType()).isEqualTo(TransactionType.TRANSFER_OUT.toString());
        softly.assertThat(sorted.get(4).getAmount()).isEqualTo(10000.0);
    }

    @Order(7)
    @Test
    @DisplayName("Проверка транзакций user1 после перевода")
    void verificationTransactionU1AfterFirstTransfer() {
        List<Transaction> transactions = UserSteps.getTransactions(
                user1.getUsername(),
                password,
                account1.getId()
        );

        List<Transaction> sorted = transactions.stream()
                .sorted(Comparator.comparing(Transaction::getId))
                .collect(Collectors.toList());

        softly.assertThat(transactions).hasSize(1);
        softly.assertThat(sorted.get(0).getType()).isEqualTo(TransactionType.TRANSFER_IN.toString());
        softly.assertThat(sorted.get(0).getAmount()).isEqualTo(10000.0);
    }


    @Order(8)
    @Test
    @DisplayName("Проверка профиля user1 после перевода")
    void verificationBalanceU1AfterFirstTransfer() {
        UserProfileResponse profile = ProfileSteps.getProfile(
                user1.getUsername(),
                password
        );

        softly.assertThat(profile.getId()).isEqualTo(user1.getId());
        softly.assertThat(profile.getUsername()).isEqualTo(user1.getUsername());
        softly.assertThat(profile.getRole()).isEqualTo("USER");
        softly.assertThat(profile.getAccounts().get(0).getBalance()).isEqualTo(10000.0);
    }

    @Order(9)
    @Test
    @DisplayName("Проверка профиля user2 после перевода")
    void verificationBalanceU2AfterFirstTransfer() {
        UserProfileResponse profile = ProfileSteps.getProfile(
                user2.getUsername(),
                password2
        );

        softly.assertThat(profile.getId()).isEqualTo(user2.getId());
        softly.assertThat(profile.getUsername()).isEqualTo(user2.getUsername());
        softly.assertThat(profile.getRole()).isEqualTo("USER");
        softly.assertThat(profile.getAccounts().get(0).getBalance()).isEqualTo(10000.0);
    }

    @Order(10)
    @Test
    @DisplayName("Не успешный трансфер: сумма > 10000")
    void transferAboveLimit() {
        String error = UserSteps.makeTransferFails(
                user2.getUsername(),
                password2,
                account2.getId(),
                account1.getId(),
                10000.01,
                ResponseSpecs.transferRejectedPlainText()
        );
        softly.assertThat(error).isEqualTo("Transfer amount cannot exceed 10000");
    }

    @Order(11)
    @Test
    @DisplayName("Проверка профиля user1 после неудачного перевода")
    void verificationBalanceU1AfterFirstNegativeTransfer() {
        UserProfileResponse profile = ProfileSteps.getProfile(
                user1.getUsername(),
                password
        );

        softly.assertThat(profile.getId()).isEqualTo(user1.getId());
        softly.assertThat(profile.getUsername()).isEqualTo(user1.getUsername());
        softly.assertThat(profile.getRole()).isEqualTo("USER");
        softly.assertThat(profile.getAccounts().get(0).getBalance()).isEqualTo(10000.0);
    }

    @Order(12)
    @Test
    @DisplayName("Проверка профиля user2 после неудачного перевода")
    void verificationBalanceU2AfterFirstNegativeTransfer() {
        UserProfileResponse profile = ProfileSteps.getProfile(
                user2.getUsername(),
                password2
        );

        softly.assertThat(profile.getId()).isEqualTo(user2.getId());
        softly.assertThat(profile.getUsername()).isEqualTo(user2.getUsername());
        softly.assertThat(profile.getRole()).isEqualTo("USER");
        softly.assertThat(profile.getAccounts().get(0).getBalance()).isEqualTo(10000.0);
    }

    @Order(13)
    @Test
    @DisplayName("Не успешный трансфер мин сумма -00.01")
    void transferNegativeSum() {
        String error = UserSteps.makeTransferFails(
                user2.getUsername(),
                password2,
                account2.getId(),
                account1.getId(),
                -0.01,
                ResponseSpecs.transferRejectedPlainText()
        );
        softly.assertThat(error).isEqualTo("Transfer amount must be at least 0.01");
    }

    @Order(14)
    @Test
    @DisplayName("Успешный трансфер: минимальная сумма 0.01")
    void transferMinValue() {
        TransferResponse response = UserSteps.makeTransfer(
                user2.getUsername(),
                password2,
                account2.getId(),
                account1.getId(),
                0.01
        );

        softly.assertThat(response.getMessage()).isEqualTo("Transfer successful");
        softly.assertThat(response.getAmount()).isEqualTo(0.01);
    }

    @Order(15)
    @Test
    @DisplayName("Проверка баланса user1 после второго перевода и что негативный кеис не изменил баланс")
    void verificationBalanceU1AfterSecondTransfer() {
        UserProfileResponse profile = ProfileSteps.getProfile(
                user1.getUsername(),
                password
        );
        softly.assertThat(profile.getAccounts().get(0).getBalance()).isEqualTo(10000.01);
    }

    @Order(16)
    @Test
    @DisplayName("Проверка баланса user2 после второго перевода и что негативный кеис не изменил баланс")
    void verificationBalanceU2AfterSecondTransfer() {
        UserProfileResponse profile = ProfileSteps.getProfile(
                user2.getUsername(),
                password2
        );
        AccountResponse account = profile.getAccountById(account2.getId())
                .orElseThrow(() -> new RuntimeException("Аккаунт не найден"));

        softly.assertThat(account.getBalance()).isEqualTo(9999.99);
        softly.assertThat(account.getLastTransaction().get().getAmount()).isEqualTo(0.01);
        softly.assertThat(account.getTransactions()).hasSize(6);
    }
//        //запоминаем сортированный ответ транзакции первого аккаунта
//        AccountResponse account = profile.getAccountById(account2.getId())
//                .orElseThrow(() -> new RuntimeException("Нет аккаунтов"));
//        List<Transaction> sortedTransactions = account.getSortedTransactions();
//        Transaction lastTransaction = account.getLastTransaction()
//                .orElseThrow(() -> new RuntimeException("Нет транзакций"));
//
//        double balance = profile.getAccounts().get(0).getBalance();
//
//        softly.assertThat(profile.getId()).isEqualTo(user2.getId());
//        softly.assertThat(profile.getUsername()).isEqualTo(user2.getUsername());
//        softly.assertThat(profile.getRole()).isEqualTo("USER");
//        softly.assertThat(balance).isEqualTo(4999.99);
//        softly.assertThat(lastTransaction.getAmount()).isEqualTo(0.01);
//        softly.assertThat(sortedTransactions.size()).isEqualTo(5);


    @Order(17)
    @Test
    @DisplayName("Не успешный трансфер: недостаточно средств")
    void transferMoreBalance() {
        String error = UserSteps.makeTransferFails(
                user2.getUsername(),
                password2,
                account2.getId(),
                account1.getId(),
                10000.00,
                ResponseSpecs.depositRejectedPlainText() // или создай transferInsufficientFunds()
        );
        softly.assertThat(error).isEqualTo("Invalid transfer: insufficient funds or invalid accounts");
    }

    @Order(18)
    @Test
    @DisplayName("Проверка баланса user1 что негативный кеис не изменил баланс")
    void verificationBalanceAfterNegativeTestU1() {
        UserProfileResponse profile = ProfileSteps.getProfile(
                user1.getUsername(),
                password
        );
        softly.assertThat(profile.getAccounts().get(0).getBalance()).isEqualTo(10000.01);
    }

    @Order(18)
    @Test
    @DisplayName("Проверка баланса user2 что негативный кеис не изменил баланс")
    void verificationBalanceAfterNegativeTestU2() {
        UserProfileResponse profile = ProfileSteps.getProfile(
                user2.getUsername(),
                password2
        );
        AccountResponse account = profile.getAccountById(account2.getId())
                .orElseThrow(() -> new RuntimeException("Аккаунт не найден"));

        softly.assertThat(account.getBalance()).isEqualTo(9999.99);
        softly.assertThat(account.getLastTransaction().get().getAmount()).isEqualTo(0.01);
        softly.assertThat(account.getTransactions()).hasSize(6);
    }

    @Order(18)
    @Test
    @DisplayName("User2 создаёт второй счёт")
    public void user2CreatesSecondAccount() {
        account2_2 = UserSteps.createAccount(
                user2.getUsername(),
                password2
        );
        softly.assertThat(account2_2.getBalance()).isEqualTo(0.0);
    }

    @Order(19)
    @Test
    @DisplayName("Проверка что 2 аккаунта у User2")
    void verificationAccountUser2() {
        UserProfileResponse profile = ProfileSteps.getProfile(
                user2.getUsername(),
                password2
        );
        AccountResponse mainAccount = profile.getAccountById(account2.getId())
                .orElseThrow();
        softly.assertThat(mainAccount.getBalance()).isEqualTo(9999.99);
        softly.assertThat(mainAccount.getLastTransaction().get().getType()).isEqualTo(TransactionType.TRANSFER_OUT.toString());
        softly.assertThat(profile.getAccounts().size()).isEqualTo(2);
    }

    @Order(20)
    @Test
    @DisplayName("Успешный трансфер со второго счёта user2 на user1")
    void transferFromSecondAccountToUser2() {
        TransferResponse response = UserSteps.makeTransfer(
                user2.getUsername(),
                password2,
                account2.getId(),
                account2_2.getId(),
                0.01
        );
        softly.assertThat(response.getMessage()).isEqualTo("Transfer successful");
    }


    @Order(21)
    @Test
    @DisplayName("Проверка баланса основного аккаунта  user2 после внутреннего перевода")
    void verificationBalanceU2MainAccount() {
        UserProfileResponse profile = ProfileSteps.getProfile(
                user2.getUsername(),
                password2
        );
        AccountResponse mainAccount = profile.getAccountById(account2.getId())
                .orElseThrow();
        softly.assertThat(mainAccount.getBalance()).isEqualTo(9999.98);
        softly.assertThat(mainAccount.getLastTransaction().get().getType())
                .isEqualTo(TransactionType.TRANSFER_OUT.toString());
    }


    @Order(21)
    @Test
    @DisplayName("Проверка баланса второго аккаунта user2")
    void verificationBalanceU2SecondAccount() {
        UserProfileResponse profile = ProfileSteps.getProfile(
                user2.getUsername(),
                password2
        );
        AccountResponse secondAccount = profile.getAccountById(account2_2.getId())
                .orElseThrow();
        softly.assertThat(secondAccount.getBalance()).isEqualTo(0.01);
        softly.assertThat(secondAccount.getTransactions()).hasSize(1);
    }

    @Order(22)
    @Test
    @DisplayName("Успешный трансфер со второго счёта user2 на user1")
    void transferFromSecondAccountToUser1() {
        TransferResponse response = UserSteps.makeTransfer(
                user2.getUsername(),
                password2,
                account2_2.getId(),
                account1.getId(),
                0.01
        );
        softly.assertThat(response.getMessage()).isEqualTo("Transfer successful");
    }

    @Order(23)
    @Test
    @DisplayName("Проверка баланса второго счёта user2 после перевода")
    void verificationSecondAccountAfterTransfer() {
        UserProfileResponse profile = ProfileSteps.getProfile(
                user2.getUsername(),
                password2
        );
        AccountResponse secondAccount = profile.getAccountById(account2_2.getId())
                .orElseThrow();
        softly.assertThat(secondAccount.getBalance()).isEqualTo(0.0);
        softly.assertThat(secondAccount.getLastTransaction().get().getType())
                .isEqualTo(TransactionType.TRANSFER_OUT.toString());
    }

    @Order(24)
    @Test
    @DisplayName("Проверка баланса user1 после перевода")
    void verificationBalanceUser1AfterTransfer() {
        UserProfileResponse profile = ProfileSteps.getProfile(
                user1.getUsername(),
                password
        );
        AccountResponse Account = profile.getAccountById(account1.getId())
                .orElseThrow();
        softly.assertThat(Account.getBalance()).isEqualTo(10000.02);
        softly.assertThat(Account.getLastTransaction().get().getType())
                .isEqualTo(TransactionType.TRANSFER_IN.toString());
        softly.assertThat(Account.getTransactions().size()).isEqualTo(3);
    }

}