package ui.pages;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DepositPage extends BasePage<DepositPage> {
    private SelenideElement accountSelect = $(".form-control.account-selector");
    private SelenideElement amountInput = $("input.deposit-input");
    private SelenideElement depositButton = $(Selectors.byText("\uD83D\uDCB5 Deposit"));

    private SelenideElement accountOption(String accountNumber) {
        return $(Selectors.byText(accountNumber));
    }


    @Override
    public String url() {
        return "/deposit";
    }

    public DepositPage selectAccount(String accountNumber) {
        accountSelect.selectOptionContainingText(accountNumber);
        return this;
    }

    public DepositPage enterAmount(double amount) {
        amountInput.setValue(String.valueOf(amount));
        return this;
    }

    public DepositPage submitDeposit() {
        depositButton.click();
        return this;
    }

    private String extractAccountNumber(String alertText) {

        return alertText.replaceAll(".*Account Number: (ACC\\d+).*", "$1");
    }
}