package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ProfilePage extends BasePage<ProfilePage> {

    private SelenideElement nameInput = $("input[placeholder='Enter new name']");
    private SelenideElement saveButton = $(Selectors.byText("\uD83D\uDCBE Save Changes"));
    private SelenideElement welcomeText = $(".welcome-text");

    @Override
    public String url() {
        return "/edit-profile";
    }
    @Override
    public ProfilePage open() {
        super.open();
        // Ждём, пока форма полностью загрузится
        nameInput.shouldBe(Condition.visible, Condition.enabled);
        saveButton.shouldBe(Condition.visible, Condition.enabled);
        return this;
    }

    public ProfilePage enterNewName(String newName) {
        nameInput.clear();
        nameInput.setValue(newName);
        return this;
    }

    public ProfilePage saveName() {
        saveButton.click();
        return this;
    }

    public String getWelcomeText() {
        return welcomeText.getText();
    }
}
