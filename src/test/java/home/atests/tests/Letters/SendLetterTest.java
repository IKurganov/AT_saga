package home.atests.tests.Letters;

import home.atests.pages.AccountPage;
import home.atests.model.Letter;
import home.atests.pages.MainPage;
import home.atests.tests.TestBase;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static home.atests.tests.Letters.LettersTestActions.openPageAndInsertLogin;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

public class SendLetterTest extends TestBase {

    @Test
    public void loginAndSendLetterTest() throws InterruptedException {
        // тестируем ввод некорректного пароля, а затем вход и открытие формы для написания письма
        // находим поле пароля и вводим некорректное значение
        // открываем нужную страницу в браузере и вводим логин
        MainPage mainPage = openPageAndInsertLogin("atkurganov1994");
        mainPage.setPassword("F1234567654321");

        // ловим ошибку и ее текст - наличие проверяем внутри элемента через ожидание
        assertThat(mainPage.getErrorText())
                .as("Проверяем текст ошибки")
                .isEqualTo("Неверное имя или пароль");

        // вводим корректный пароль и входим
        mainPage.setPassword("A1234567654321");

        assertThat(mainPage.isErrorDisappear())
                .as("Проверяем, видно ли ошибку")
                .isTrue();

        AccountPage accountPage = new AccountPage();
        accountPage.clickWriteLetterButton(new Letter("Хелоу hello Worlddz", "atkurganov1994@mail.ru"));
        //TODO добавить проверок для LetterPage
    }

}
