package helpers;

import api.AccountApi;
import static com.codeborne.selenide.Selenide.open;
import io.restassured.response.Response;
import java.lang.reflect.Field;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import utils.CookieHelper;

public class LoginExtension implements BeforeEachCallback {

  AccountApi authHelper = new AccountApi();
  CookieHelper cookieHelper = new CookieHelper();

  @Override
  public void beforeEach(ExtensionContext context) {
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
    cookieHelper.setCookie(responseLogin);
  }
}
