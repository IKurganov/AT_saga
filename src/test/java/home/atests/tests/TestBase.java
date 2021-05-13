package home.atests.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
    public Properties appProps;

    private Logger logger  = LogManager.getLogger(TestBase.class);
    @BeforeEach
    public void setUp() {
        // пока для упрощения запускаю только хром
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        Configuration.reportsFolder = "target/reports";
        logger.info("Запущен браузер: {}", Configuration.browser);

        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "test.properties";

        appProps = new Properties();
        try {
            appProps.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void finishTesting() {
        logger.info("Заканчиваем тесты");
        WebDriverRunner.getWebDriver().quit();
    }

}
