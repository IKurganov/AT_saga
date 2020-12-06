package home.atests.tests.Letters;

import com.codeborne.selenide.Condition;
import home.atests.appmanager.LetterPage;
import home.atests.appmanager.LoginPage;
import home.atests.model.Letter;
import home.atests.tests.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static home.atests.tests.Letters.LettersTestActions.justSetPassword;
import static home.atests.tests.Letters.LettersTestActions.openPageAndInsertLogin;

public class SendLetterTest extends TestBase {

    @Test
    public void loginAndSendLetterTest() throws InterruptedException {
        // тестируем ввод некорректного пароля, а затем вход и открытие формы для написания письма
        // находим поле пароля и вводим некорректное значение
        // открываем нужную страницу в браузере и вводим логин
        openPageAndInsertLogin("atkurganov1994");
        //TODO РАЗОБРАТЬСЯ С ОБЪЕКТАМИ
        justSetPassword("F1234567654321");
        // ловим ошибку и ее текст
        //TODO здесь просто надо сразу из PObj возвращать текст и сверять его тут через ассертзет
        $(By.id("mailbox:error"))
                .shouldBe(Condition.visible.because("Почта должна показать, что пользователь ввел не те данные"))
                .has(exactText("Неверное имя или пароль"));
        // вводим корректный пароль и входим
        justSetPassword("A1234567654321");
        // сообщение об ошибке не будет отображаться
        $(By.id("mailbox:error")).shouldNotBe(visible);
        LetterPage letterPage = new LetterPage();
        letterPage.writeAndTryToSendLetter(new Letter("Хелоу hello Worlddz", "atkurganov1994@mail.ru"));
    }

}
