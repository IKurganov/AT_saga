package home.atests.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import home.atests.model.Letter;
import home.atests.model.User;
import home.atests.tests.TestBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.awaitility.Awaitility.await;

public class AccountPage {

    private SelenideElement lettersActionsBox = $("div.sidebar");
    private SelenideElement writeLetterAndOtherActionsBox = lettersActionsBox.$("div.sidebar__header");
    private SelenideElement writeLetterButton = writeLetterAndOtherActionsBox.$("a.compose-button_white");
    private SelenideElement userButton;
    private SelenideElement userLetters = $("div.dataset-letters");
    private SelenideElement userLettersForHimselfButton = $x("//span[text()='Письма себе']");

    private Logger logger = LogManager.getLogger(AccountPage.class);

    public AccountPage() {
        //TODO wait
        await("Ждем, пока сайдбар будет")
                .pollInSameThread()
                .atMost(10, TimeUnit.SECONDS)
                .pollInterval(500, TimeUnit.MILLISECONDS)
                .until(() -> {
                    boolean done = lettersActionsBox.exists();
                    logger.info("DONE : {} ",
                            done);
                    return done;
                });
    }

    public LetterPage clickWriteLetterButton(Letter letter) {
        //TODO дикий костыль
        writeLetterButton
                .shouldBe(enabled.because("Кнопка Написать письмо должна отображаться"))
                .scrollIntoView(true)
                .click();
        LetterPage letterPage = new LetterPage();
        letterPage.writeAndSendLetter(letter);
        return letterPage;
    }

    public MainPage logout(User user) {
        String fullLogin = user.getLogin() + "@mail.ru";
        userButton = $x(String.format("//div[@aria-label = '%s']/span[text()='%s']", fullLogin, fullLogin));
        userButton.shouldBe(visible).click();
        $x("//div[text()='Выйти']").shouldBe(visible).click();
        return new MainPage();
    }

    public RealLetterPage findAndClickFirstLetterToHimself() {
        userLettersForHimselfButton.click();
        userLetters.$("div.dataset__items a:nth-of-type(1) div.llc__item_title").shouldBe(visible).click();
        return new RealLetterPage();
    }


}
