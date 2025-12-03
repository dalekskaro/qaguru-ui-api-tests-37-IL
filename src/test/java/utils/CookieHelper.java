package utils;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.openqa.selenium.Cookie;

public class CookieHelper {

  @Step("Устанавливаем cookie")
  public void setCookie(Response response) {
    getWebDriver().manage().addCookie(new Cookie("userID", response.path("userId")));
    getWebDriver().manage().addCookie(new Cookie("expires", response.path("expires")));
    getWebDriver().manage().addCookie(new Cookie("token", response.path("token")));
  }
}
