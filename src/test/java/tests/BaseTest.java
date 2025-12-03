package tests;

import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaseTest {

  @BeforeAll
  static void setEnv() {
    Configuration.baseUrl = "https://demoqa.com";
    Configuration.browser = System.getProperty("browser", "chrome");
    Configuration.browserVersion = System.getProperty("browserVersion");
    Configuration.browserSize = System.getProperty("browserResolution", "1920x1080");
    Configuration.pageLoadStrategy = "eager";
    Configuration.remote = System.getProperty("remote");

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("selenoid:options", Map.<String, Object>of(
        "enableVNC", true,
        "enableVideo", true
    ));
    Configuration.browserCapabilities = capabilities;
  }

  @BeforeEach
  void addListenerAllure() {
    SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
  }

  @AfterEach
  void closeBrowserDriver() {
    closeWebDriver();
  }

  @AfterEach
  void addAttachments() {
    Attach.screenshotAs("Скрин страницы");
    Attach.addVideo("Видео всего теста");
    Attach.pageSnapshot("Снапшот страницы");
    Attach.pageSource("Source страницы");
    Attach.browserConsoleLogs("Логи браузера");
  }
}
