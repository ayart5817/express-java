package iteration_2.ui;

import api.models.CreateUserRequest;
import api.requests.steps.UserSteps;
import iteration_1.common.annotations.UserSession;
import iteration_1.common.storage.SessionStorage;
import iteration_1.ui.BaseUiTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.pages.DepositPage;
import ui.pages.UserDashboard;

import static org.assertj.core.api.Assertions.assertThat;

public class UIDepositTest extends BaseUiTest {

    @Test
    @DisplayName("Успешный депозит через UI")
    @UserSession
    public void userCanDepositMoneySuccessfully() {
        CreateUserRequest user = SessionStorage.getUser();
        // ШАГ: Создаём счёт

        new UserDashboard().open().createNewAccount();
        String alertAcceptText = getAlertTextAndAccept();
        String accountNumber = new UserSteps(user.getUsername(), user.getPassword()).getAccountNumber();

        // ШАГ: Переходим на страницу депозита
        new DepositPage().open()
                .selectAccount(accountNumber)
                .enterAmount(1000.0)
                .submitDeposit();

        // ШАГ: Проверяем алерт
        String alertText = getAlertTextAndAccept();
        assertThat(alertText).contains("✅ Successfully deposited $1000");

        // ШАГ: Проверяем баланс через API
        double balance = new UserSteps(user.getUsername(), user.getPassword())
                .getAccountBalance();
        assertThat(balance).isEqualTo(1000.0);
    }

    @Test
    @DisplayName("Негативный депозит: сумма > 5000")
    @UserSession
    public void userCannotDepositAboveLimit() {
        CreateUserRequest user = SessionStorage.getUser(1);
        new UserDashboard().open().createNewAccount();
        String alertAcceptText = getAlertTextAndAccept();
        String accountNumber = new UserSteps(user.getUsername(), user.getPassword()).getAccountNumber();

        //Пытаемся сделать депозит выше лимита и получить алерт с ошибкой
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