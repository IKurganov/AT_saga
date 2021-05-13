package home.atests.tests.Letters;

import home.atests.model.User;
import home.atests.pages.AccountPage;
import home.atests.model.Letter;
import home.atests.pages.MainPage;
import home.atests.pages.RealLetterPage;
import home.atests.tests.TestBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static com.codeborne.selenide.Selenide.$;
import static home.atests.tests.Letters.LettersTestActions.openPageAndInsertLogin;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

public class SendLetterTest extends TestBase {

    //TODO прологгировать здесь действия
    private Logger logger  = LogManager.getLogger(SendLetterTest.class);

    @Test
    public void loginTest() {
        logger.info("Проверим срабатывание проверки о некорректном пароле");
        // тестируем ввод некорректного пароля, а затем вход и открытие формы для написания письма
        // находим поле пароля и вводим некорректное значение
        // открываем нужную страницу в браузере и вводим логин
        MainPage mainPage = openPageAndInsertLogin("atkurganov1994");
        mainPage.setPasswordAndClickLogin("F1234567654321");

        // ловим ошибку и ее текст - наличие проверяем внутри элемента через ожидание
        assertThat(mainPage.getErrorText())
                .as("Проверяем текст ошибки")
                .isEqualTo("Неверное имя или пароль");

        // вводим корректный пароль и входим
        mainPage.setPasswordAndClickLogin("A1234567654321");

        assertThat(mainPage.isErrorDisappear())
                .as("Проверяем, видно ли ошибку")
                .isTrue();
/*
        AccountPage accountPage = new AccountPage();
        accountPage.clickWriteLetterButton(new Letter("Хелоу hello Worlddz", "atkurganov1994@mail.ru"));
        //TODO добавить проверок для LetterPage
        */
    }

    @Test
    public void sendLetterTest() throws InterruptedException {
        logger.info("Проверим, что письмо уходит самому себе");
        MainPage mainPage = new MainPage();
        User user = new User(appProps.getProperty("loginValue"), appProps.getProperty("passwordValue"));
        mainPage.login(user);
        AccountPage accountPage = new AccountPage();
        // создаем письмо выходим и заходим обратно
        Letter testLetter = new Letter("Хелоу hello Worlddz 2223 лаулуьаль " + new Date().toString(), "atkurganov1994@mail.ru");
        String controlStr = testLetter.getMessage();
        accountPage.clickWriteLetterButton(testLetter);
        mainPage = accountPage.logout(user);
        mainPage.login(user);
        logger.info("Достанем письмо, и посмотрим, что там будет");

        accountPage.findAndClickFirstLetterToHimself();
        RealLetterPage actualLetter = new RealLetterPage();
        assertThat(actualLetter.getMessage()).isEqualTo(controlStr);
    }

}
