package helpers;

import api.AccountApi;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import io.restassured.response.Response;
import java.io.IOException;
import java.lang.reflect.Field;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

public class LoginExtension implements BeforeEachCallback {

  AccountApi authHelper = new AccountApi();

  @Override
  public void beforeEach(ExtensionContext context) throws IOException {
    Response responseLogin = authHelper.getResponseAfterLogin();

    Object testInstance = context.getRequiredTestInstance();
    try {
      Field field = testInstance.getClass().getDeclaredField("responseLogin");
      field.setAccessible(true);
      field.set(testInstance, responseLogin);
    } catch (NoSuchFieldException | IllegalAccessException e) {
      throw new RuntimeException("Не удалось установить responseLogin в тестовый класс", e);
    }

    open("/images/Toolsqa.jpg");
    getWebDriver().manage().addCookie(new Cookie("userID", responseLogin.path("userId")));
    getWebDriver().manage().addCookie(new Cookie("expires", responseLogin.path("expires")));
    getWebDriver().manage().addCookie(new Cookie("token", responseLogin.path("token")));
  }
}
