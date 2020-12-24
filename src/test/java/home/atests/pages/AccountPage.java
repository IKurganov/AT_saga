package home.atests.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import home.atests.model.Letter;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AccountPage {

    private SelenideElement lettersActionsBox = $("div.sidebar");
    private SelenideElement writeLetterAndOtherActionsBox = lettersActionsBox.$("div.sidebar__header");
    private SelenideElement writeLetterButton = writeLetterAndOtherActionsBox.$("a.compose-button_white");


    public LetterPage clickWriteLetterButton(Letter letter){
        writeLetterButton
                .shouldBe(Condition.visible.because("Кнопка Написать письмо должна отображаться"))
                .click();
        LetterPage letterPage = new LetterPage();
        letterPage.writeAndSendLetter(letter);
        return letterPage;
    }


}
