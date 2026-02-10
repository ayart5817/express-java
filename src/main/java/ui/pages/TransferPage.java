package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TransferPage extends BasePage<TransferPage> {
    private SelenideElement senderAccountSelect = $("#root select.account-selector");
    private SelenideElement recipientInput = $(Selectors.byAttribute("placeholder", "Enter recipient account number"));
    private SelenideElement amountInput = $(Selectors.byAttribute("placeholder", "Enter amount"));
    private SelenideElement confirmCheckbox = $("#confirmCheck");
    private SelenideElement sendButton = $(byText("🚀 Send Transfer"));


    @Override
    public TransferPage open() {
        super.open();
        // Ждём появления ключевых элементов
        senderAccountSelect.shouldBe(visible);
        recipientInput.shouldBe(visible);
        sendButton.shouldBe(visible, enabled);
        return this;
    }

    @Override
    public String url() {
        return "/transfer";
    }

    public TransferPage selectSenderAccount(String accountNumber) {
        senderAccountSelect.selectOptionContainingText(accountNumber);
        return this;
    }

    public TransferPage enterRecipient(String accountNumber) {
        recipientInput.setValue(accountNumber);
        return this;
    }

    public TransferPage enterAmount(double amount) {
        amountInput.setValue(String.valueOf(amount));
        return this;
    }

    public TransferPage confirm() {
        confirmCheckbox.click();
        return this;
    }

    public TransferPage sendTransfer() {
        sendButton.click();
        return this;
    }

}