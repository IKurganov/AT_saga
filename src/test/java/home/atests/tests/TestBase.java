package home.atests.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {

    @BeforeEach
    public void setUp() {
        // пока для упрощения запускаю только хром
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        Configuration.reportsFolder = "target/reports";
    }

    @AfterEach
    public void finishTesting() {
        WebDriverRunner.getWebDriver().quit();
    }

}
