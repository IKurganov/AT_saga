package home.atests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;

public class BaseTest {

    @Test
    public void firstTest() throws InterruptedException {
        open("https://mail.ru/");
        $(By.name("login"))
                .shouldBe(Condition.visible)
                .setValue("atkurganov1994");
        $(By.id("mailbox:submit-button"))
                .shouldBe(Condition.enabled)
                .shouldHave(Condition.text("Ввести пароль"))
                .click();

        $(By.name("password"))
                .shouldBe(Condition.visible)
                .setValue("F1234567654321");

        $(By.id("mailbox:submit-button"))
                .shouldBe(Condition.enabled)
                .shouldHave(Condition.text("Войти"))
                .click();
        assertThat($(By.id("mailbox:error"))
                .waitUntil(visible, 1000)
                .has(Condition.visible)).isTrue();

        assertThat($(By.id("mailbox:error"))
                .waitUntil(visible, 1000)
                .has(exactText("Неверное имя или пароль")))
                .as("Почта должна уведомить об ошибке и содержать корректный текст")
                .isTrue();

        /*$$(".results--main .result").shouldHave(CollectionCondition.sizeGreaterThanOrEqual(5));
        $(".results--main .result").shouldHave(text("heisenbug-moscow.ru"));
         */
    }
}
