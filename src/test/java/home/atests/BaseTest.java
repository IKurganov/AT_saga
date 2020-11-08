package home.atests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class BaseTest {

    @Test
    public void userCanSearchkeyword() throws InterruptedException {
        open("https://duckduckgo.com");
        $(By.name("q")).shouldBe(Condition.visible)
                .setValue("heisenbug selenide")
                .pressEnter();

        $$(".results--main .result").shouldHave(CollectionCondition.sizeGreaterThanOrEqual(5));
        $(".results--main .result").shouldHave(text("heisenbug-moscow.ru"));
    }
}
