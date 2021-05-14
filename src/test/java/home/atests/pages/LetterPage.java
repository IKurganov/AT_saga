package home.atests.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.sun.org.apache.xpath.internal.objects.XBoolean;
import com.sun.org.apache.xpath.internal.operations.Bool;
import home.atests.model.Letter;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LetterPage {

    SelenideElement mainForm = $("div.compose-windows div.compose-app_popup");
    SelenideElement upperForm = mainForm.$("div.compose-app__compose");
    //TODO убрать шуд би
    SelenideElement lowerForm = mainForm.$("div.compose-app__footer")
            .shouldBe(Condition.visible.because("Также кроме формы будет отображаться панель с кнопками/действиями для письма"));
    SelenideElement mainText = upperForm.$x(".//div[contains(@class, 'editor_container')]//div[contains(@class, 'cke_editable_inline')]/div");
    SelenideElement recipientAddress = upperForm.$x(".//div[contains(@class, 'head_container')]//div[contains(@class, 'inputContainer')]/input");
    SelenideElement sendButton = lowerForm.$("div.compose-app__buttons span[title='Отправить']");

    public LetterPage writeAndSendLetter(Letter letter) {
        //TODO разнести на отдельные действия
        mainText
                .shouldBe(visible.because("Строчка для печати будет видима"))
                .sendKeys(letter.getMessage());
        recipientAddress
                .shouldBe(visible.because("Строчка для адресата будет видима"))
                .setValue(letter.getRecipient());
        sendButton
                .shouldBe(enabled.because("Кнопка для отправки будет активна"))
                .click();
        return this;
    }

    public Boolean isMainFormVisible(){
        return mainForm.has(Condition.visible);
    }

    public Boolean isUpperFormVisible(){
        return upperForm.has(Condition.visible);
    }

    public Boolean isLowerFormVisible(){
        return lowerForm.has(Condition.visible);
    }

}
