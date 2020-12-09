package home.atests.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import home.atests.model.User;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    private SelenideElement loginBox = $(By.id("mailbox"));
    private SelenideElement logpassForm = loginBox.$("form");
    private SelenideElement enterButton = logpassForm.$("button");
    private SelenideElement loginError = logpassForm.$("div.error");

    public MainPage setLogin(String username) {
        $(By.name("login"))
                .shouldBe(Condition.visible)
                .setValue(username);
        clickLoginButton();
        return this;
    }

    public MainPage setPassword(String password) {
        $(By.name("password"))
                .shouldBe(Condition.visible)
                .clear();
        $(By.name("password"))
                .shouldBe(Condition.visible)
                .setValue(password);
        enterButton.shouldBe(Condition.enabled)
                .shouldHave(Condition.text("Войти"))
                .click();
        return this;
    }

    public void clickLoginButton() {
        enterButton.shouldBe(Condition.enabled)
                .shouldHave(Condition.text("Ввести пароль"))
                .click();
    }

    public void login(User user) {
        setLogin(user.getLogin());
        setPassword(user.getPassword());
        clickLoginButton();
    }

    public String getErrorText() {
        return loginError
                .shouldBe(Condition.visible.because("Почта должна показать, что пользователь ввел не те данные"))
                .text();
    }

    public Boolean isErrorVisible() {
        return loginError.isDisplayed();
    }

}
