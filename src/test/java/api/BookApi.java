package api;

import static api.EndPoint.BOOKS;
import io.qameta.allure.Step;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import static java.lang.String.format;
import static specs.DemoQaSpecification.requestSpecification;
import static specs.DemoQaSpecification.responseSpecification;

public class BookApi {

  @Step("Добавляем книгу пользователю POST " + BOOKS)
  public void postBook(Response response, String isbn) {
    String bodyBookData = format("{\"userId\":\"%s\",\"collectionOfIsbns\":[{\"isbn\":\"%s\"}]}",
        response.path("userId"),
        isbn);

    given(requestSpecification)
        .header("Authorization", "Bearer " + response.path("token"))
        .body(bodyBookData)
        .when()
        .post(BOOKS)
        .then()
        .spec(responseSpecification(201));
  }

  @Step("Удаление всех книг у пользователю DELETE " + BOOKS)
  public void deleteBooks(Response response) {

    given(requestSpecification)
        .header("Authorization", "Bearer " + response.path("token"))
        .queryParam("UserId", response.path("userId").toString())
        .when()
        .delete(BOOKS)
        .then()
        .spec(responseSpecification(204));
  }
}
