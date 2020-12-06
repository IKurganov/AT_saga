package home.atests.appmanager;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import home.atests.model.User;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {


    public LoginPage setLogin(String username) {
        $(By.name("login"))
                .shouldBe(Condition.visible)
                .setValue(username);
        clickLoginButton();
        return this;
    }
    public LoginPage setPassword(String password) {
        $(By.name("password"))
                .shouldBe(Condition.visible)
                .clear();
        $(By.name("password"))
                .shouldBe(Condition.visible)
                .setValue(password);
        $(By.id("mailbox:submit-button"))
                .shouldBe(Condition.enabled)
                .shouldHave(Condition.text("Войти"))
                .click();
        return this;
    }

    public void clickLoginButton() {
        $(By.id("mailbox:submit-button"))
                .shouldBe(Condition.enabled)
                .shouldHave(Condition.text("Ввести пароль"))
                .click();
    }

    public void login(User user){
        setLogin(user.getLogin());
        setPassword(user.getPassword());
        clickLoginButton();
    }
}
