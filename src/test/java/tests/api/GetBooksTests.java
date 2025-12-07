package tests.api;

import api.BookApi;
import static io.qameta.allure.Allure.step;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import model.BookDemoQaModel;
import model.BooksDemoQaModel;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("BookStore")
@Story("Проверка получения информации об книгах")
@Owner("Irina Attano")
public class GetBooksTests {

  BookApi bookApi = new BookApi();

  @Test
  @Description("Получение списка книг. Проверка данных у первой книги")
  @DisplayName("GET /BookStore/v1/Books")
  void getBooksTest() {

    BooksDemoQaModel responseBooks = bookApi.getBooksReturnResponse();
    BookDemoQaModel book = responseBooks.getBooks().get(0);

    step("Проверяем тело ответа", () ->
    {
      SoftAssertions.assertSoftly(softAssertions -> {
        softAssertions.assertThat(book.getIsbn())
            .describedAs("Проверяем значение isbn")
            .isEqualTo("9781449325862");

        softAssertions.assertThat(book.getTitle())
            .describedAs("Проверяем значение title")
            .isEqualTo("Git Pocket Guide");

        softAssertions.assertThat(book.getAuthor())
            .describedAs("Проверяем значение author")
            .isEqualTo("Richard E. Silverman");

        softAssertions.assertThat(book.getPages())
            .describedAs("Проверяем значение pages")
            .isEqualTo(234);

        softAssertions.assertThat(book.getDescription())
            .describedAs("Проверяем значение description")
            .contains("This pocket guide");

        softAssertions.assertThat(book.getWebsite())
            .describedAs("Проверяем значение website")
            .contains("chimera.labs.oreilly");
      });
    });
  }

  @Test
  @Description("Получение данных о книге")
  @DisplayName("GET /BookStore/v1/Books?ISBN={isbn}")
  void getBookTest() {
    String isbn = "9781449325862";

    BookDemoQaModel book = bookApi.getBookReturnResponse(isbn);

    step("Проверяем тело ответа", () ->
    {
      SoftAssertions.assertSoftly(softAssertions -> {
        softAssertions.assertThat(book.getIsbn())
            .describedAs("Проверяем значение isbn")
            .isEqualTo(isbn);

        softAssertions.assertThat(book.getTitle())
            .describedAs("Проверяем значение title")
            .isEqualTo("Git Pocket Guide");

        softAssertions.assertThat(book.getAuthor())
            .describedAs("Проверяем значение author")
            .isEqualTo("Richard E. Silverman");

        softAssertions.assertThat(book.getPages())
            .describedAs("Проверяем значение pages")
            .isEqualTo(234);

        softAssertions.assertThat(book.getDescription())
            .describedAs("Проверяем значение description")
            .contains("This pocket guide");

        softAssertions.assertThat(book.getWebsite())
            .describedAs("Проверяем значение website")
            .contains("chimera.labs.oreilly");
      });
    });
  }
}
