package tests.api;

import api.BookApi;
import helpers.WithLoginApi;
import static io.qameta.allure.Allure.step;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import static java.lang.System.getProperty;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("BookStore")
@Story("Удаление книги")
@Owner("Irina Attano")
public class DeleteBookTests {

  BookApi bookApi = new BookApi();
  Response responseLogin;

  String isbn = getProperty("isbn", "9781449325862");

  @BeforeEach
  void postBookToUser() {
    bookApi.postBook(responseLogin, isbn);
  }

  @AfterEach
  void deleteBooks() {
    bookApi.deleteBooks(responseLogin);
  }

  @Test
  @WithLoginApi
  @Description("Удаление книги у пользователя")
  @DisplayName("DELETE /BookStore/v1/Book")
  void deleteBookTest() {

    int status = bookApi.deleteBookReturnStatus(responseLogin, isbn);

    step("Проверяем статус ответа", () ->
    {
      Assertions.assertEquals(204, status,
          "Ожидаемый статус 204 не соответствует фактическому " + status);
    });
  }
}
