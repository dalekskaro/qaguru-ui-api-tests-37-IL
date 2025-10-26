package api;

import static api.EndPoint.GENERATE_TOKEN;
import static api.EndPoint.LOGIN;
import static api.EndPoint.USER;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import java.io.IOException;
import java.util.List;
import model.AuthDemoQaModel;
import model.GenerateTokenDemoQaModel;
import org.openqa.selenium.Cookie;
import static specs.DemoQaSpecification.requestSpecification;
import static specs.DemoQaSpecification.responseSpecification;

public class AccountApi {

  private AuthDemoQaModel authBody;

  private AuthDemoQaModel getAuthBody() throws IOException {
    if (authBody == null) {
      authBody = new ObjectMapper().readValue(
          this.getClass().getResource("/json/AuthBody.json"), AuthDemoQaModel.class);
    }
    return authBody;
  }

  public String getUserNameFromJson() throws IOException {
    return getAuthBody().getUserName();
  }

  public String getPasswordFromJson() throws IOException {
    return getAuthBody().getPassword();
  }

  @Step("Возвращаем body, Генерация токена POST " + GENERATE_TOKEN)
  public GenerateTokenDemoQaModel getBodyAfterGenerateToken() throws IOException {
    AuthDemoQaModel body = getAuthBody();

    return given(requestSpecification)
        .body(body)
        .when()
        .post(GENERATE_TOKEN)
        .then()
        .spec(responseSpecification(200))
        .extract().as(GenerateTokenDemoQaModel.class);
  }

  @Step("Возвращаем response, Генерация токена POST " + GENERATE_TOKEN)
  public Response getResponseAfterGenerateToken() throws IOException {
    AuthDemoQaModel body = getAuthBody();

    return given(requestSpecification)
        .body(body)
        .when()
        .post(GENERATE_TOKEN)
        .then()
        .spec(responseSpecification(200))
        .extract().response();
  }

  @Step("Возвращаем response, Авторизация POST " + LOGIN)
  public Response getResponseAfterLogin() throws IOException {
    AuthDemoQaModel body = getAuthBody();

    return given(requestSpecification)
        .body(body)
        .when()
        .post(LOGIN)
        .then()
        .spec(responseSpecification(200))
        .extract().response();
  }

  @Step("Получение количества книг у пользователя GET " + USER + "/{UserId}")
  public List<Object> getBooksFromUser(Response response) {

    return given(requestSpecification)
        .header("Authorization", "Bearer " + response.path("token"))
        .when()
        .get(USER + "/" + response.path("userId").toString())
        .then()
        .spec(responseSpecification(200))
        .extract().response().jsonPath().getList("Books");
  }

  @Step("Устанавливаем cookie")
  public void setCookie(Response response) {
    getWebDriver().manage().addCookie(new Cookie("userID", response.path("userId")));
    getWebDriver().manage().addCookie(new Cookie("expires", response.path("expires")));
    getWebDriver().manage().addCookie(new Cookie("token", response.path("token")));
  }
}
