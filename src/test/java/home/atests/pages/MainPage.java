package home.atests.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import home.atests.model.User;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.awaitility.Awaitility.await;

public class MainPage {

    private SelenideElement loginBox = $(By.id("mailbox"));
    private SelenideElement logpassForm = loginBox.$("form");
    private SelenideElement enterLoginButton = logpassForm.$("button.button");
    private SelenideElement enterPasswordButton = logpassForm.$("button.second-button");
    private SelenideElement loginError = logpassForm.$("div.error");
    private SelenideElement loginField = logpassForm.$("input.email-input");
    private SelenideElement passwordField = logpassForm.$("input.password-input");

    public MainPage setLogin(String username) {
        loginField
                .shouldBe(Condition.visible)
                .setValue(username);
        return this;
    }

    public MainPage setPassword(String password) {
        passwordField
                .shouldBe(Condition.visible)
                .clear();
        passwordField.setValue(password);
        return this;
    }

    public MainPage setPasswordAndClickLogin(String password) {
        passwordField
                .shouldBe(Condition.visible)
                .clear();
        passwordField.setValue(password);
        enterPasswordButton
                .shouldBe(Condition.enabled)
                .shouldHave(Condition.text("Войти"))
                .click();
        return this;
    }

    public void clickConfirmPasswordButton() {
        enterLoginButton.shouldBe(Condition.enabled)
                .shouldHave(Condition.text("Ввести пароль"))
                .click();
    }

    public void clickEnterButton() {
        enterPasswordButton
                .shouldBe(Condition.enabled)
                .shouldHave(Condition.text("Войти"))
                .click();
    }

    public void login(User user) {
        open("https://mail.ru/");
        setLogin(user.getLogin());
        clickConfirmPasswordButton();
        setPassword(user.getPassword());
        clickEnterButton();
    }


    public String getErrorText() {
        await("Ждем, пока ошибка появится")
                .pollInSameThread()
                .atMost(500, TimeUnit.MILLISECONDS)
                .until(loginError::exists);
        return loginError
                .shouldBe(Condition.visible.because("Почта должна показать, что пользователь ввел не те данные"))
                .text();
    }

    public Boolean isErrorDisappear() {
        return !loginError.isDisplayed();
    }
}
