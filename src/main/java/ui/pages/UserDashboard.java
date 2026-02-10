package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
public class UserDashboard extends BasePage<UserDashboard> {

    public SelenideElement getWelcomeText() {
        return $(Selectors.byClassName("welcome-text"));
    }

    public SelenideElement getCreateNewAccount() {
        return $(Selectors.byText("➕ Create New Account"));
    }

    @Override
    public String url() {
        return "/dashboard";
    }

    public UserDashboard createNewAccount() {
        getCreateNewAccount().click();
        return this;
    }
}