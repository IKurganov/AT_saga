package home.atests.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class RealLetterPage {
    SelenideElement messageBox = $("div.letter-body__body-content");


    public String getMessage(){
        //return messageBox.$x("div[@data-signature-widget='container']/ancestor::div[2]").getText();
        // так не сработало
        //TODO id="style_16209847540115719604_BODY"
        return messageBox.$x(".//div[@data-signature-widget='container']/preceding-sibling::div[2]").getText();

    }
}
