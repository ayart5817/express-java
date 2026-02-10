package iteration_2.ui;

import api.models.CreateUserRequest;
import api.requests.steps.AdminSteps;
import api.requests.steps.CreatedUser;
import api.requests.steps.UserSteps;
import iteration_1.ui.BaseUiTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.pages.DepositPage;
import ui.pages.UserDashboard;

import static org.assertj.core.api.Assertions.assertThat;

public class UIDepositTest extends BaseUiTest {

    @Test
    @DisplayName("Успешный депозит через UI")
    public void userCanDepositMoneySuccessfully() {

        // ШАГ 1: Создаём пользователя
        CreatedUser userAll = AdminSteps.createUser();
        CreateUserRequest user = userAll.getRequest();
        authAsUser(user);

        // ШАГ 2: Логинимся
        authAsUser(user);

        // ШАГ 3: Создаём счёт
        new UserDashboard().open().createNewAccount();
        String accountNumber = new UserSteps(user.getUsername(), user.getPassword()).getAccountNumber();

        // ШАГ 4: Переходим на страницу депозита
        new DepositPage().open()
                .selectAccount(accountNumber)
                .enterAmount(1000.0)
                .submitDeposit();

        // ШАГ 5: Проверяем алерт
        String alertText = getAlertTextAndAccept();
        assertThat(alertText).contains("✅ Successfully deposited $1000");

        // ШАГ 6: Проверяем баланс через API
        double balance = new UserSteps(user.getUsername(), user.getPassword())
                .getAccountBalance();
        assertThat(balance).isEqualTo(1000.0);
    }

    @Test
    @DisplayName("Негативный депозит: сумма > 5000")
    public void userCannotDepositAboveLimit() {
        CreateUserRequest user = AdminSteps.createUser().getRequest();
        authAsUser(user);

        new UserDashboard().open().createNewAccount();
        String accountNumber = new UserSteps(user.getUsername(), user.getPassword()).getAccountNumber();

        new DepositPage().open()
                .selectAccount(accountNumber)
                .enterAmount(5001.0)
                .submitDeposit();

        String alertText = getAlertTextAndAccept();
        assertThat(alertText).contains("❌ Please deposit less or equal to 5000$");

        // Проверяем, что баланс не изменился
        double balance = new UserSteps(user.getUsername(), user.getPassword())
                .getAccountBalance();
        assertThat(balance).isEqualTo(0.0);
    }
}