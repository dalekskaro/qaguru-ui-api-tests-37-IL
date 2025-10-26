package tests;

import api.AccountApi;
import api.BookApi;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

@Tag("homework-16")
@Epic("DemoQa. Проверка удаления книги через UI")
@Owner("Irina Attano")
public class WithoutAnnotationTests extends BaseTest {

  ProfilePage profilePage = new ProfilePage();
  AccountApi accountApi = new AccountApi();
  BookApi bookApi = new BookApi();
  Response responseLogin;

  @BeforeEach
  void auth() throws IOException {
    responseLogin = accountApi.getResponseAfterLogin();
  }

  @AfterEach
  void deleteBooks() {
    bookApi.deleteBooks(responseLogin);
  }

  @Test
  @DisplayName("Удаление книги у пользователя")
  void deleteBookTest() throws IOException {
    String isbn = System.getProperty("isbn", "9781449325862"),
        bookTitle = System.getProperty("bookTitle", "Git Pocket Guide");

    profilePage.openImagesPage();

    accountApi.setCookie(responseLogin);
    bookApi.postBook(responseLogin, isbn);

    profilePage
        .openProfilePage()
        .checkUserName(accountApi.getUserNameFromJson())
        .checkBookTitle(bookTitle)
        .clickOnDeleteBookButton()
        .acceptDeleteBookInModal()
        .closeAlertModal()
        .checkNoBooksFound();

    List<Object> books = accountApi.getBooksFromUser(responseLogin);
    assertTrue(books == null || books.isEmpty(), "У пользователя осталась книга в профиле");
  }
}
