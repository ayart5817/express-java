package iteration_2.ui;

import api.models.CreateUserRequest;
import api.requests.steps.UserSteps;
import iteration_1.common.annotations.UserSession;
import iteration_1.common.storage.SessionStorage;
import iteration_1.ui.BaseUiTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.pages.TransferPage;
import ui.pages.UserDashboard;

import static org.assertj.core.api.Assertions.assertThat;

public class UIUserTransferTest extends BaseUiTest {

    @Test
    @DisplayName("Успешный трансфер между пользователями")
    @UserSession(value = 2, auth = 1)
    public void userCanTransferMoneyToAnotherUser() {
        // ШАГ 1: Создаём двух пользователей
        CreateUserRequest user1 = SessionStorage.getUser(1);
        CreateUserRequest user2 = SessionStorage.getUser(2);

        // ШАГ 2: Пополняем счёт user1 через API
        new UserDashboard().open().createNewAccount();
        String account1 = new UserSteps(user1.getUsername(), user1.getPassword()).getAccountNumber();
        long accountUser1ID = new UserSteps(user1.getUsername(), user1.getPassword()).getFirstAccountID();
        UserSteps.makeDeposit(user1.getUsername(), user1.getPassword(), accountUser1ID, 5000);

        // ШАГ 3: Создаём счёт у user2 API
        String account2 = UserSteps.createAccount(user2.getUsername(), user2.getPassword()).getAccountNumber();

        // ШАГ 4: Выполняем трансфер через UI
        new TransferPage().open()
                .selectSenderAccount(account1)
                .enterRecipient(account2)
                .enterAmount(1000.0)
                .confirm()
                .sendTransfer();

        // ШАГ 5: Проверяем алертs
        String alertText = getAlertTextAndAccept();
        assertThat(alertText).contains("✅ Successfully transferred $1000");

        // ШАГ 6: Проверяем балансы через API
        double balance1 = new UserSteps(user1.getUsername(), user1.getPassword())
                .getAccountBalance();
        double balance2 = new UserSteps(user2.getUsername(), user2.getPassword())
                .getAccountBalance();

        assertThat(balance1).isEqualTo(4000.0);
        assertThat(balance2).isEqualTo(1000.0);
    }

    @Test
    @DisplayName("Негативный: трансфер > 10000")
    @UserSession(value = 2, auth = 1)
    public void userCannotTransferAboveLimit() {
        CreateUserRequest user1 = SessionStorage.getUser(1);
        CreateUserRequest user2 = SessionStorage.getUser(2);
        String account1 = UserSteps.createAccount(user1.getUsername(), user1.getPassword()).getAccountNumber();
        String account2 = UserSteps.createAccount(user1.getUsername(), user1.getPassword()).getAccountNumber();
        //  пополняем счёт user1 через API
        new UserSteps(user1.getUsername(), user2.getPassword()).makeDeposit20000();
        // Создаём счёт user2 через API
        UserSteps.createAccount(user2.getUsername(), user2.getPassword());
        // Выполняем трансфер через UI
        new TransferPage().open()
                .selectSenderAccount(account1)
                .enterRecipient(account2)
                .enterAmount(10001.0)
                .confirm()
                .sendTransfer();
        //проверяем аллерт
        String alertText = getAlertTextAndAccept();
        assertThat(alertText).contains("❌ Error: Transfer amount cannot exceed 10000");

        // Проверяем баланс через API
        double balance1 = new UserSteps(user1.getUsername(), user1.getPassword()).getAccountBalance();
        assertThat(balance1).isEqualTo(20000.0);
    }
}