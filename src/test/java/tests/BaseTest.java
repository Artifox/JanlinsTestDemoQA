package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.RegistrationFromPage;
import utils.Attach;
import utils.CredentialsConfig;

public class BaseTest {
    public static CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);
    RegistrationFromPage registrationFromPage = new RegistrationFromPage();

    @BeforeAll
    public static void setUp() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = System.getProperty("browserSize","1920x1080");
        Configuration.browserPosition = "0x0";
        Configuration.holdBrowserOpen = false;
        //TODO: Запихнуть baseWDHubURL в конфиг в Jenkins, но передавать как переменную
        String baseWdHubURL = System.getProperty("baseWdHubURL", "selenoid.autotests.cloud/wd/hub");
        String username = System.getProperty("login");
        Configuration.remote = String.format("https://%s:1234@%s",username,baseWdHubURL);//String.format("https://%s:%s@%s", config.login(), config.password(), baseWdHubURL);
        //"https://user1:1234@selenoid.autotests.cloud/wd/hub";
    }

    @AfterAll
    public static void tearDown() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        WebDriverRunner.getWebDriver().quit();
    }
}
