package helpers;

import api.AccountApi;
import io.restassured.response.Response;
import java.lang.reflect.Field;
import java.time.Instant;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class LoginExtensionForApi implements BeforeEachCallback {

  AccountApi authHelper = new AccountApi();

  @Override
  public void beforeEach(ExtensionContext context) {
    Response responseLogin = authHelper.getResponseAfterLogin();

    Instant expiresAt = Instant.parse(responseLogin.path("expires"));
    Instant now = Instant.now();

    if (expiresAt.isBefore(now)) {
      System.out.println("Необходимо обновить токен");
      authHelper.generateToken();

      responseLogin = authHelper.getResponseAfterLogin();
    }

    Object testInstance = context.getRequiredTestInstance();
    try {
      Field field = testInstance.getClass().getDeclaredField("responseLogin");
      field.setAccessible(true);
      field.set(testInstance, responseLogin);
    } catch (NoSuchFieldException | IllegalAccessException e) {
      throw new RuntimeException("Не удалось установить responseLogin в тестовый класс", e);
    }
  }
}
