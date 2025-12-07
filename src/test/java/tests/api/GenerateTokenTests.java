package tests.api;

import api.AccountApi;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import java.time.Year;
import model.GenerateTokenDemoQaModel;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Account")
@Story("Генерация токена")
@Owner("Irina Attano")
public class GenerateTokenTests {

  String year = String.valueOf(Year.now().getValue());
  AccountApi accountApi = new AccountApi();

  @Test
  @Description("Генерация токена")
  @DisplayName("POST /Account/v1/GenerateToken")
  void getBooksTest() {
    GenerateTokenDemoQaModel response = accountApi.generateTokenReturnResponse();

    SoftAssertions.assertSoftly(softAssertions -> {
      softAssertions.assertThat(response.getToken())
          .describedAs("Проверяем, что значение token не пусто")
          .isNotNull();

      softAssertions.assertThat(response.getStatus())
          .describedAs("Проверяем значение status")
          .isEqualTo("Success");

      softAssertions.assertThat(response.getResult())
          .describedAs("Проверяем значение result")
          .isEqualTo("User authorized successfully.");

      softAssertions.assertThat(response.getExpires())
          .describedAs("Проверяем, что значение expires содержит текущий год")
          .contains(year);
    });
  }
}
