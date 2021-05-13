package home.atests.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import home.atests.model.Letter;
import home.atests.model.User;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class AccountPage {

    private SelenideElement lettersActionsBox = $("div.sidebar");
    private SelenideElement writeLetterAndOtherActionsBox = lettersActionsBox.$("div.sidebar__header");
    private SelenideElement writeLetterButton = writeLetterAndOtherActionsBox.$("a.compose-button_white");
    private SelenideElement userButton;
    private SelenideElement userLetters = $("div.dataset-letters");
    private SelenideElement userLettersForHimselfButton = $x("//span[text()='Письма себе']");


    public LetterPage clickWriteLetterButton(Letter letter) throws InterruptedException {
        //TODO дикий костыль
        Thread.sleep(1000);
        writeLetterButton
                /*.shouldBe(enabled.because("Кнопка Написать письмо должна отображаться"))
                .scrollIntoView(true)*/
                .click();
        LetterPage letterPage = new LetterPage();
        letterPage.writeAndSendLetter(letter);
        return letterPage;
    }

    public MainPage logout(User user){
        String fullLogin = user.getLogin() + "@mail.ru";
        userButton = $x(String.format("//div[@aria-label = '%s']/span[text()='%s']", fullLogin, fullLogin));
        userButton.shouldBe(visible).click();
        $x("//div[text()='Выйти']").shouldBe(visible).click();
        return new MainPage();
    }

    public RealLetterPage findAndClickFirstLetterToHimself(){
        userLettersForHimselfButton.click();
        userLetters.$("div.dataset__items a:nth-of-type(1) div.llc__item_title").shouldBe(visible).click();
        return new RealLetterPage();
    }


}
