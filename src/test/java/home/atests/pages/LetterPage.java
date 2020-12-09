package home.atests.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import home.atests.model.Letter;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LetterPage {

    public void writeAndTryToSendLetter(Letter letter) {
        $("a.compose-button_white")
                .shouldBe(Condition.visible.because("Кнопка Написать письмо должна отображаться"))
                .click();
        SelenideElement mainForm = $("div.compose-windows div.compose-app_popup").shouldBe(visible.because("Нажали кнопку для открытия формы"));
        SelenideElement upperForm = mainForm.$("div.compose-app__compose").shouldBe(Condition.visible.because("Будет место для печати письма"));
        SelenideElement lowerForm = mainForm.$("div.compose-app__footer")
                .shouldBe(Condition.visible.because("Также кроме формы будет отображаться панель с кнопками/действиями для письма"));
        upperForm.$x(".//div[contains(@class, 'editor_container')]//div[contains(@class, 'cke_editable_inline')]/div")
                .shouldBe(visible.because("Строчка для печати будет видима"))
                .sendKeys(letter.getMessage());
        upperForm.$x(".//div[contains(@class, 'head_container')]//div[contains(@class, 'inputContainer')]/input")
                .shouldBe(visible.because("Строчка для адресата будет видима"))
                .setValue(letter.getRecipient());
        //TODO нужно указать адресата
        lowerForm.$("div.compose-app__buttons span[title='Отправить']")
                .shouldBe(enabled.because("Кнопка для отправки будет активна"))
                .click();
    }
}
