package api;

import static api.data.AuthData.AUTH_DATA;
import io.qameta.allure.Step;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import java.util.List;
import model.GenerateTokenDemoQaModel;
import static specs.DemoQaSpecification.requestSpecification;
import static specs.DemoQaSpecification.responseSpecification;

public class AccountApi {

  private static final String LOGIN = "/Account/v1/Login";
  private static final String USER = "/Account/v1/User";
  private static final String GENERATE_TOKEN = "/Account/v1/GenerateToken";

  @Step("Авторизация POST " + LOGIN + ", Возвращаем response")
  public Response getResponseAfterLogin() {

    return given(requestSpecification)
        .body(AUTH_DATA)
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

  @Step("Генерация токена POST " + GENERATE_TOKEN)
  public void generateToken() {

    given(requestSpecification)
        .body(AUTH_DATA)
        .when()
        .post(GENERATE_TOKEN)
        .then()
        .spec(responseSpecification(200));
  }

  @Step("Генерация токена POST " + GENERATE_TOKEN + ", Возвращаем response")
  public GenerateTokenDemoQaModel generateTokenReturnResponse() {

    return given(requestSpecification)
        .body(AUTH_DATA)
        .when()
        .post(GENERATE_TOKEN)
        .then()
        .spec(responseSpecification(200))
        .extract().as(GenerateTokenDemoQaModel.class);
  }
}
