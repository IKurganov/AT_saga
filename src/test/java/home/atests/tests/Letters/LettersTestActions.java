package home.atests.tests.Letters;

import com.codeborne.selenide.Condition;
import home.atests.appmanager.LoginPage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class LettersTestActions {

    static LoginPage loginPage = new LoginPage();

    public static void openPageAndInsertLogin(String username) {
        open("https://mail.ru/");
        loginPage.setLogin(username);
    }

    public static void justSetPassword(String password){
        loginPage.setPassword(password);
    }

}
