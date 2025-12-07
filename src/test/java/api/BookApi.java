package api;

import io.qameta.allure.Step;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import static java.lang.String.format;
import model.BookDemoQaModel;
import model.BooksDemoQaModel;
import model.BooksIsbnModel;
import static specs.DemoQaSpecification.requestSpecification;
import static specs.DemoQaSpecification.responseSpecification;

public class BookApi {

  private static final String BOOKS = "/BookStore/v1/Books";
  private static final String BOOK = "/BookStore/v1/Book";

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

  @Step("Добавляем книгу пользователю POST " + BOOKS + ", Возвращаем response")
  public BooksIsbnModel postBookReturnResponse(Response response, String isbn) {
    String bodyBookData = format("{\"userId\":\"%s\",\"collectionOfIsbns\":[{\"isbn\":\"%s\"}]}",
        response.path("userId"),
        isbn);

    return given(requestSpecification)
        .header("Authorization", "Bearer " + response.path("token"))
        .body(bodyBookData)
        .when()
        .post(BOOKS)
        .then()
        .spec(responseSpecification(201))
        .extract().as(BooksIsbnModel.class);
  }

  @Step("Удаление всех книг у пользователя DELETE " + BOOKS)
  public void deleteBooks(Response response) {

    given(requestSpecification)
        .header("Authorization", "Bearer " + response.path("token"))
        .queryParam("UserId", response.path("userId").toString())
        .when()
        .delete(BOOKS)
        .then()
        .spec(responseSpecification(204));
  }

  @Step("Удаление книги у пользователя DELETE " + BOOK + ", Возвращаем statusCode")
  public int deleteBookReturnStatus(Response response, String isbn) {
    String body = format("{\"isbn\":\"%s\",\"userId\":\"%s\"}",
        isbn,
        response.path("userId"));

    return given(requestSpecification)
        .header("Authorization", "Bearer " + response.path("token"))
        .body(body)
        .when()
        .delete(BOOK)
        .then()
        .spec(responseSpecification(204))
        .extract().statusCode();
  }

  @Step("Получения списка книг GET " + BOOKS + ", Возвращаем response")
  public BooksDemoQaModel getBooksReturnResponse() {

    return given(requestSpecification)
        .when()
        .get(BOOKS)
        .then()
        .spec(responseSpecification(200))
        .extract().as(BooksDemoQaModel.class);
  }

  @Step("Получения книги по ISBN GET " + BOOK + ", Возвращаем response")
  public BookDemoQaModel getBookReturnResponse(String isbn) {

    return given(requestSpecification)
        .queryParam("ISBN", isbn)
        .when()
        .get(BOOK)
        .then()
        .spec(responseSpecification(200))
        .extract().as(BookDemoQaModel.class);
  }
}
