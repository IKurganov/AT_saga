package home.atests;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;

public class BaseTest {

    @BeforeEach
    public void setUp() {
        // пока для упрощения запускаю только хром
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        Configuration.reportsFolder = "target/reports";
        // открываем нужную страницу в браузере и вводим логин
        openPageAndInsertLogin();
    }

    private void openPageAndInsertLogin() {
        open("https://mail.ru/");
        $(By.name("login"))
                .shouldBe(Condition.visible)
                .setValue("atkurganov1994");
        $(By.id("mailbox:submit-button"))
                .shouldBe(Condition.enabled)
                .shouldHave(Condition.text("Ввести пароль"))
                .click();
    }

    @Test
    public void loginAndSendLetterTest() throws InterruptedException {
        // тестируем ввод некорректного пароля, а затем вход и открытие формы для написания письма
        // находим поле пароля и вводим значение
        setIncorrectPassword();
        // ловим ошибку и ее текст
        //TODO здесь просто надо сразу из PObj возвращать текст и сверять его тут через ассертзет
        $(By.id("mailbox:error"))
                .shouldBe(Condition.visible.because("Почта должна показать, что пользователь ввел не те данные"))
                .has(exactText("Неверное имя или пароль"));
        // вводим корректный пароль и входим
        setCorrectPassword();
        sendLetter();
    }

    private void sendLetter() {
        $("a.compose-button_white")
                .shouldBe(Condition.visible.because("Кнопка Написать письмо должна отображаться"))
                .click();
        SelenideElement mainForm = $("div.compose-windows div.compose-app_popup").shouldBe(visible.because("Нажали кнопку для открытия формы"));
        SelenideElement upperForm = mainForm.$("div.compose-app__compose").shouldBe(Condition.visible.because("Будет место для печати письма"));
        SelenideElement lowerForm = mainForm.$("div.compose-app__footer")
                .shouldBe(Condition.visible.because("Также кроме формы будет отображаться панель с кнопками/действиями для письма"));
        upperForm.$x(".//div[contains(@class, 'editor_container')]//div[contains(@class, 'cke_editable_inline')]/div")
                .shouldBe(visible.because("Строчка для печати будет видима"))
                .sendKeys("Хелоу hello Worlddz");
        //TODO нужно указать адресата
        lowerForm.$("div.compose-app__buttons span[title='Отправить']").click();
    }

    private void setCorrectPassword() {
        $(By.name("password"))
                .shouldBe(Condition.visible)
                .clear();
        $(By.name("password"))
                .shouldBe(Condition.visible)
                .setValue("A1234567654321");
        $(By.id("mailbox:submit-button"))
                .shouldBe(Condition.enabled)
                .shouldHave(Condition.text("Войти"))
                .click();
        // сообщение об ошибке не будет отображаться
        $(By.id("mailbox:error")).shouldNotBe(visible);
    }

    private void setIncorrectPassword() {
        $(By.name("password"))
                .shouldBe(Condition.visible)
                .setValue("F1234567654321");
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
