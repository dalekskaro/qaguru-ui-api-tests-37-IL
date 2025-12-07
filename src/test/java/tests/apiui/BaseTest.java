package tests.apiui;

import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.WebDriverConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import java.util.Map;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaseTest {
  private static final WebDriverConfig config = ConfigFactory.create(
      WebDriverConfig.class,
      System.getProperties()
  );

  @BeforeAll
  static void setEnv() {
    Configuration.baseUrl = config.baseUrl();
    Configuration.browser = config.browser();
    Configuration.browserVersion = config.browserVersion();
    Configuration.browserSize = config.browserResolution();
    Configuration.pageLoadStrategy = config.pageLoadStrategy();
    Configuration.remote = config.remoteUrl();

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
