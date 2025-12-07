package tests.api;

import api.BookApi;
import helpers.WithLoginApi;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import static java.lang.System.getProperty;
import model.BooksIsbnModel;
import model.IsbnModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("BookStore")
@Story("Добавление книги")
@Owner("Irina Attano")
public class PostBookTests {

  BookApi bookApi = new BookApi();
  Response responseLogin;

  String isbn = getProperty("isbn", "9781449325862");

  @AfterEach
  void deleteBooks() {
    bookApi.deleteBooks(responseLogin);
  }

  @Test
  @WithLoginApi
  @Description("Добавление книги пользователю")
  @DisplayName("POST /BookStore/v1/Books")
  void postBookTest() {

    BooksIsbnModel responseBooks = bookApi.postBookReturnResponse(responseLogin, isbn);
    IsbnModel resposeIsbn = responseBooks.getBooks().get(0);

    Assertions.assertEquals(isbn, resposeIsbn.getIsbn(),
        "Ожидаемое значение isbn " + isbn + "не соответствует фактическому " + resposeIsbn.getIsbn());
  }
}
