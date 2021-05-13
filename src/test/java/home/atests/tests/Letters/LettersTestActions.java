package home.atests.tests.Letters;

import home.atests.pages.MainPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class LettersTestActions {

    public static MainPage openPageAndInsertLogin(String username) {
        open("https://mail.ru/");
        MainPage mainPage = new MainPage();
        mainPage.setLogin(username);
        //do something more than
        return mainPage;
    }
    // and again - do something more than
}
