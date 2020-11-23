package home.atests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class SendLetterTest extends TestBase {

    @Test
    public void loginAndSendLetterTest() throws InterruptedException {
        // тестируем ввод некорректного пароля, а затем вход и открытие формы для написания письма
        // находим поле пароля и вводим некорректное значение
        setPassword("F1234567654321");
        // ловим ошибку и ее текст
        //TODO здесь просто надо сразу из PObj возвращать текст и сверять его тут через ассертзет
        $(By.id("mailbox:error"))
                .shouldBe(Condition.visible.because("Почта должна показать, что пользователь ввел не те данные"))
                .has(exactText("Неверное имя или пароль"));
        // вводим корректный пароль и входим
        setPassword("A1234567654321");
        // сообщение об ошибке не будет отображаться
        $(By.id("mailbox:error")).shouldNotBe(visible);
        writeAndTryToSendLetter(new Letter("Хелоу hello Worlddz", "atkurganov1994@mail.ru"));
    }

}
