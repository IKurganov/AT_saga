package home.atests.tests.Letters;

import com.codeborne.selenide.Condition;
import home.atests.pages.LetterPage;
import home.atests.model.Letter;
import home.atests.pages.MainPage;
import home.atests.tests.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static home.atests.tests.Letters.LettersTestActions.openPageAndInsertLogin;
import static org.assertj.core.api.Assertions.assertThat;

public class SendLetterTest extends TestBase {

    @Test
    public void loginAndSendLetterTest() throws InterruptedException {
        // тестируем ввод некорректного пароля, а затем вход и открытие формы для написания письма
        // находим поле пароля и вводим некорректное значение
        // открываем нужную страницу в браузере и вводим логин
        MainPage mainPage = openPageAndInsertLogin("atkurganov1994");
        mainPage.setPassword("F1234567654321");

        // ловим ошибку и ее текст
        //TODO ждать ошибку, если слишком быстро бежит браузер
        /*assertThat(mainPage.isErrorVisible())
                .as("Проверяем, видно ли ошибку")
                .isTrue(); */

        assertThat(mainPage.getErrorText())
                .as("Проверяем текст ошибки")
                .isEqualTo("Неверное имя или пароль");

        // вводим корректный пароль и входим
        mainPage.setPassword("A1234567654321");

        assertThat(mainPage.isErrorVisible())
                .as("Проверяем, видно ли ошибку")
                .isFalse();

        LetterPage letterPage = new LetterPage();
        letterPage.writeAndTryToSendLetter(new Letter("Хелоу hello Worlddz", "atkurganov1994@mail.ru"));
    }

}
