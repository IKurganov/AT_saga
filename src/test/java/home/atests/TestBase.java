package home.atests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {

    @BeforeEach
    public void setUp() {
        // пока для упрощения запускаю только хром
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        Configuration.reportsFolder = "target/reports";
        // открываем нужную страницу в браузере и вводим логин
        openPageAndInsertLogin("atkurganov1994");
    }

    private void openPageAndInsertLogin(String username) {
        open("https://mail.ru/");
        $(By.name("login"))
                .shouldBe(Condition.visible)
                .setValue(username);
        $(By.id("mailbox:submit-button"))
                .shouldBe(Condition.enabled)
                .shouldHave(Condition.text("Ввести пароль"))
                .click();
    }

    protected void writeAndTryToSendLetter(Letter letter) {
        $("a.compose-button_white")
                .shouldBe(Condition.visible.because("Кнопка Написать письмо должна отображаться"))
                .click();
        SelenideElement mainForm = $("div.compose-windows div.compose-app_popup").shouldBe(visible.because("Нажали кнопку для открытия формы"));
        SelenideElement upperForm = mainForm.$("div.compose-app__compose").shouldBe(Condition.visible.because("Будет место для печати письма"));
        SelenideElement lowerForm = mainForm.$("div.compose-app__footer")
                .shouldBe(Condition.visible.because("Также кроме формы будет отображаться панель с кнопками/действиями для письма"));
        upperForm.$x(".//div[contains(@class, 'editor_container')]//div[contains(@class, 'cke_editable_inline')]/div")
                .shouldBe(visible.because("Строчка для печати будет видима"))
                .sendKeys(letter.getMessage());
        upperForm.$x(".//div[contains(@class, 'head_container')]//div[contains(@class, 'inputContainer')]/input")
                .shouldBe(visible.because("Строчка для адресата будет видима"))
                .setValue(letter.getRecipient());
        //TODO нужно указать адресата
        lowerForm.$("div.compose-app__buttons span[title='Отправить']")
                .shouldBe(enabled.because("Кнопка для отправки будет активна"))
                .click();
    }

    protected void setPassword(String password) {
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

    }

    @AfterEach
    public void finishTesting() {
        WebDriverRunner.getWebDriver().quit();
    }
}
