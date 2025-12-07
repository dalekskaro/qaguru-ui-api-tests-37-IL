package tests.apiui;

import api.AccountApi;
import api.BookApi;
import static api.data.AuthData.AUTH_DATA;
import helpers.WithLoginUi;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import static java.lang.System.getProperty;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;


@Epic("BookStore")
@Story("Удаление книги")
@Owner("Irina Attano")
public class DeleteBookTests extends BaseTest {

  ProfilePage profilePage = new ProfilePage();
  AccountApi accountApi = new AccountApi();
  BookApi bookApi = new BookApi();
  Response responseLogin;

  @AfterEach
  void deleteBooks() {
    bookApi.deleteBooks(responseLogin);
  }

  @Test
  @WithLoginUi
  @DisplayName("Удаление книги у пользователя через UI")
  void deleteBookTest() {
    String isbn = getProperty("isbn", "9781449325862"),
        bookTitle = getProperty("bookTitle", "Git Pocket Guide");

    bookApi.postBook(responseLogin, isbn);

    profilePage
        .openProfilePage()
        .checkUserName(AUTH_DATA.getUserName())
        .checkBookTitle(bookTitle)
        .clickOnDeleteBookButton()
        .acceptDeleteBookInModal()
        .closeAlertModal()
        .checkNoBooksFound();

    List<Object> books = accountApi.getBooksFromUser(responseLogin);
    assertTrue(books == null || books.isEmpty(), "У пользователя осталась книга в профиле");
  }
}
