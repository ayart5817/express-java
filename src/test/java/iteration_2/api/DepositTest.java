package iteration_2.api;

import iteration_1.api.BaseTest;
import models.*;
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
public class DepositTest extends BaseTest {


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
    @DisplayName("Создание user1 админом ")
    public void user1GenerateTest() {
        CreatedUser createUser = AdminSteps.createUser();
        user1 = createUser.getResponse();
        password = createUser.getRequest().getPassword();


        account1 = new ValidatedCrudRequester<AccountResponse>(
                RequestSpecs.authAsUser(user1.getUsername(), password),
                Endpoint.ACCOUNTS,
                ResponseSpecs.entityWasCreated()
        ).postAndExtract(null);


        softly.assertThat(account1.getAccountNumber()).startsWith("ACC");
        softly.assertThat(account1.getBalance()).isEqualTo(0.0);
        softly.assertThat(account1.getTransactions()).isEmpty();
    }

    @Order(2)
    @Test
    @DisplayName("Проверка профиля user1 дефолтное состояние аакаунта")
    void verificationDefaultU1() {
        UserProfileResponse profile = ProfileSteps.getProfile(
                user1.getUsername(),
                password
        );

        softly.assertThat(profile.getId()).isEqualTo(user1.getId());
        softly.assertThat(profile.getUsername()).isEqualTo(user1.getUsername());
        softly.assertThat(profile.getRole()).isEqualTo("USER");
        softly.assertThat(profile.getAccounts().get(0).getTransactions()).isNullOrEmpty();
    }


    @Order(3)
    @Test
    @DisplayName("Создаем User2")
    public void user2GenerateTest() {
        CreatedUser createUser = AdminSteps.createUser();
        user2 = createUser.getResponse();
        password2 = createUser.getRequest().getPassword();

        account2 = new ValidatedCrudRequester<AccountResponse>(
                RequestSpecs.authAsUser(user2.getUsername(), password2),
                Endpoint.ACCOUNTS,
                ResponseSpecs.entityWasCreated()
        ).postAndExtract(null);

        softly.assertThat(account2.getBalance()).isEqualTo(0.00);
    }

    @Order(4)
    @Test
    @DisplayName("Проверка профиля user2 дефолтное состояние акаунта")
    void verificationDefaultU2() {
        UserProfileResponse profile = ProfileSteps.getProfile(
                user2.getUsername(),
                password2
        );

        softly.assertThat(profile.getId()).isEqualTo(user2.getId());
        softly.assertThat(profile.getUsername()).isEqualTo(user2.getUsername());
        softly.assertThat(profile.getRole()).isEqualTo("USER");
        softly.assertThat(profile.getAccounts().get(0).getTransactions()).isNullOrEmpty();
    }

    @Order(5)
    @Test
    @DisplayName("Успешный депозит 4999.99")
    void depositBelowLimit() {
        double amount = 4999.99;
        AccountResponse response = UserSteps.makeDeposit(
                user2.getUsername(), password2, account2.getId(), amount
        );

        softly.assertThat(response.getBalance()).isEqualTo(amount);
        softly.assertThat(response.getTransactions()).hasSize(1);
        softly.assertThat(response.getTransactions().get(0).getAmount()).isEqualTo(amount);
        softly.assertThat(response.getTransactions().get(0).getType()).isEqualTo(TransactionType.DEPOSIT.toString());
    }

    @Order(6)
    @Test
    @DisplayName("Проверка профиля user2 после депозита")
    void verificationAfterDepositU2() {
        UserProfileResponse profile = ProfileSteps.getProfile(
                user2.getUsername(),
                password2
        );

        softly.assertThat(profile.getId()).isEqualTo(user2.getId());
        softly.assertThat(profile.getUsername()).isEqualTo(user2.getUsername());
        softly.assertThat(profile.getRole()).isEqualTo("USER");
        softly.assertThat(profile.getAccounts().get(0).getBalance()).isEqualTo(4999.99);
    }

    @Order(7)
    @Test
    @DisplayName("Проверка транзакций user2 после депозита")
    void verificationTransactionU1AfterFirstDeposit() {
        List<Transaction> transactions = UserSteps.getTransactions(
                user2.getUsername(),
                password,
                account2.getId()
        );

        List<Transaction> sorted = transactions.stream()
                .sorted(Comparator.comparing(Transaction::getId))
                .collect(Collectors.toList());

        softly.assertThat(transactions).hasSize(1);
        softly.assertThat(sorted.get(0).getType()).isEqualTo(TransactionType.DEPOSIT.toString());
        softly.assertThat(sorted.get(0).getAmount()).isEqualTo(4999.99);
    }

    @Order(8)
    @Test
    @DisplayName("Успешный депозит на 5000.00")
    void depositExactLimit() {
        double amount = 5000.00;
        AccountResponse response = UserSteps.makeDeposit(
                user2.getUsername(), password2, account2.getId(), amount
        );

        // Баланс = 4999.99 (после @Order(4)) + 5000.00 = 9999.99
        softly.assertThat(response.getBalance()).isEqualTo(9999.99);
        softly.assertThat(response.getTransactions()).hasSize(2); // две транзакции: 4999.99 + 5000.00
    }

    @Order(9)
    @Test
    @DisplayName("Проверка транзакций user2 после депозита ExactLimit")
    void verificationTransactionU1AfterDepositExactLimit() {
        List<Transaction> transactions = UserSteps.getTransactions(
                user2.getUsername(),
                password2,
                account2.getId()
        );

        List<Transaction> sorted = transactions.stream()
                .sorted(Comparator.comparing(Transaction::getId))
                .collect(Collectors.toList());

        softly.assertThat(transactions).hasSize(2);
        softly.assertThat(sorted.get(0).getType()).isEqualTo(TransactionType.DEPOSIT.toString());
        softly.assertThat(sorted.get(transactions.size() - 1).getAmount()).isEqualTo(5000.0);
    }

    @Order(10)
    @Test
    @DisplayName("Проверка профиля user2 после второго депозита")
    void verificationAfterDepositStep2U2() {
        UserProfileResponse profile = ProfileSteps.getProfile(
                user2.getUsername(),
                password2
        );

        softly.assertThat(profile.getId()).isEqualTo(user2.getId());
        softly.assertThat(profile.getUsername()).isEqualTo(user2.getUsername());
        softly.assertThat(profile.getRole()).isEqualTo("USER");
        softly.assertThat(profile.getAccounts().get(0).getBalance()).isEqualTo(9999.99);
    }

    @Order(11)
    @Test
    @DisplayName("Не успешный депозит 5000.01")
    void depositAboveLimit() {
        String error = UserSteps.makeDepositFails(
                user2.getUsername(), password2, account2.getId(), 5000.01
        );

        softly.assertThat(error).contains("Deposit amount cannot exceed 5000");
    }

    @Order(12)
    @Test
    @DisplayName("Проверка профиля user2 после неуспешного депозита")
    void verificationInvalidDepositStep1U2() {
        UserProfileResponse profile = ProfileSteps.getProfile(
                user2.getUsername(),
                password2
        );

        softly.assertThat(profile.getId()).isEqualTo(user2.getId());
        softly.assertThat(profile.getUsername()).isEqualTo(user2.getUsername());
        softly.assertThat(profile.getRole()).isEqualTo("USER");
        softly.assertThat(profile.getAccounts().get(0).getBalance()).isEqualTo(9999.99);
    }

    @Order(13)
    @Test
    @DisplayName("Отклонение депозита при сумме 0.00")
    void depositZero() {
        double amount = 0.00;
        String error = UserSteps.makeDepositFails(
                user2.getUsername(), password2, account2.getId(), amount
        );
        softly.assertThat(error).contains("Deposit amount must be at least 0.01");
    }

    @Order(14)
    @Test
    @DisplayName("Проверка баланса профиля user2 после неуспешного депозита")
    void verificationInvalidDepositZeroU2() {
        UserProfileResponse profile = ProfileSteps.getProfile(
                user2.getUsername(),
                password2
        );

        softly.assertThat(profile.getId()).isEqualTo(user2.getId());
        softly.assertThat(profile.getUsername()).isEqualTo(user2.getUsername());
        softly.assertThat(profile.getRole()).isEqualTo("USER");
        softly.assertThat(profile.getAccounts().get(0).getBalance()).isEqualTo(9999.99);
    }

    @Order(15)
    @Test
    @DisplayName("Проверка транзакций user2 после 2-х неуспешных кейсов")
    void verificationTransactionU1AfterNegativeTests() {
        List<Transaction> transactions = UserSteps.getTransactions(
                user2.getUsername(),
                password2,
                account2.getId()
        );

        List<Transaction> sorted = transactions.stream()
                .sorted(Comparator.comparing(Transaction::getId))
                .collect(Collectors.toList());

        softly.assertThat(transactions).hasSize(2);
        softly.assertThat(sorted.get(0).getType()).isEqualTo(TransactionType.DEPOSIT.toString());
        softly.assertThat(sorted.get(transactions.size() - 1).getAmount()).isEqualTo(5000.0);
        ;
    }


    @Order(16)
    @Test
    @DisplayName("Отклонение депозита при сумме -0.01")
    void depositNegative() {
        double amount = -0.00;
        String error = UserSteps.makeDepositFails(
                user2.getUsername(), password2, account2.getId(), amount
        );
        softly.assertThat(error).contains("Deposit amount must be at least 0.01");
    }

    @Order(17)
    @Test
    @DisplayName("Проверка баланса профиля user2 после depositNegativeSum")
    void verificationInvalidDepositNegativeSumU2() {
        UserProfileResponse profile = ProfileSteps.getProfile(
                user2.getUsername(),
                password2
        );

        softly.assertThat(profile.getId()).isEqualTo(user2.getId());
        softly.assertThat(profile.getUsername()).isEqualTo(user2.getUsername());
        softly.assertThat(profile.getRole()).isEqualTo("USER");
        softly.assertThat(profile.getAccounts().get(0).getBalance()).isEqualTo(9999.99);
    }

    @Order(18)
    @Test
    @DisplayName("Отклонение депозита Unauthorized")
    void depositToForeignAccount() {
        double amount = 1000.00;
        String error = UserSteps.makeDepositForbidden(
                user1.getUsername(), user1.getPassword(), account2.getId(), amount
        );
        softly.assertThat(error).isEqualTo("Unauthorized access to account");
    }

    @Order(19)
    @Test
    @DisplayName("Проверка баланса профиля user2 после depositToForeignAccount")
    void verificationInvalidDepositToForeignAccountU2() {
        UserProfileResponse profile = ProfileSteps.getProfile(
                user2.getUsername(),
                password2
        );

        softly.assertThat(profile.getId()).isEqualTo(user2.getId());
        softly.assertThat(profile.getUsername()).isEqualTo(user2.getUsername());
        softly.assertThat(profile.getRole()).isEqualTo("USER");
        softly.assertThat(profile.getAccounts().get(0).getBalance()).isEqualTo(9999.99);
    }

    @Order(20)
    @Test
    @DisplayName("Проверка баланса профиля user1 после depositToForeignAccount")
    void verificationInvalidDepositToForeignAccountU1() {
        UserProfileResponse profile = ProfileSteps.getProfile(
                user1.getUsername(),
                password
        );

        softly.assertThat(profile.getId()).isEqualTo(user1.getId());
        softly.assertThat(profile.getUsername()).isEqualTo(user1.getUsername());
        softly.assertThat(profile.getRole()).isEqualTo("USER");
        softly.assertThat(profile.getAccounts().get(0).getBalance()).isEqualTo(0);
    }

    @Order(21)
    @Test
    @DisplayName("Успешный депозит на 0.01")
    void depositMinLimit() {
        double amount = 0.01;
        AccountResponse response = UserSteps.makeDeposit(
                user2.getUsername(), password2, account2.getId(), 0.01
        );
        softly.assertThat(response.getBalance()).isEqualTo(10000.0); // 9999.99 + 0.01
        softly.assertThat(response.getTransactions()).hasSize(3);
    }

    @Order(22)
    @Test
    @DisplayName("Итоговое состояние счётов u2")
    void verificationBalanceU2() {
        List<Transaction> transactions = UserSteps.getTransactions(
                user2.getUsername(), password2, account2.getId()
        );

        softly.assertThat(transactions).hasSize(3);
        softly.assertThat(transactions.get(0).getType()).isEqualTo(TransactionType.DEPOSIT.toString());
        softly.assertThat(transactions.get(0).getRelatedAccountId()).isEqualTo(account2.getId());
    }

    @Order(23)
    @Test
    @DisplayName("Итоговое состояние счётов u1")
    void verificationBalanceU1() {
        List<Transaction> transactions = UserSteps.getTransactions(
                user1.getUsername(), user1.getPassword(), account1.getId()
        );
        softly.assertThat(transactions).hasSize(0);
    }

}