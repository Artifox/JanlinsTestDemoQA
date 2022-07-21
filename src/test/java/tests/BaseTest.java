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

        Attach.attachAsText("browser", System.getProperty("browser"));
        Attach.attachAsText("browserVersion", System.getProperty("browserVersion"));
        Attach.attachAsText("browserSize", System.getProperty("browserSize"));
        Configuration.browserCapabilities = capabilities;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browser = System.getProperty("browser");
        Configuration.browserVersion = System.getProperty("browserVersion");
        Configuration.browserSize = System.getProperty("browserSize");
        Configuration.browserPosition = "0x0";
        Configuration.holdBrowserOpen = false;
        String baseWdHubURL = System.getProperty("baseWdHubURL");
        String login = config.login();
        String password = config.password();
        Configuration.remote = String.format("https://%s:%s@%s", login, password, baseWdHubURL);
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
