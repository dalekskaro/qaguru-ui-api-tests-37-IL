package tests;

import api.AccountApi;
import api.BookApi;
import static api.data.AuthData.AUTH_DATA;
import helpers.WithLogin;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

@Tag("homework-16")
@Epic("DemoQa. Проверка удаления книги через UI с аннотацией @WithLogin")
@Owner("Irina Attano")
public class WithAnnotationTests extends BaseTest {

  ProfilePage profilePage = new ProfilePage();
  AccountApi accountApi = new AccountApi();
  BookApi bookApi = new BookApi();
  Response responseLogin;

  @AfterEach
  void deleteBooks() {
    bookApi.deleteBooks(responseLogin);
  }

  @Test
  @WithLogin
  @DisplayName("Удаление книги у пользователя")
  void deleteBookWithAnnotationTest() {
    String isbn = System.getProperty("isbn", "9781449325862"),
        bookTitle = System.getProperty("bookTitle", "Git Pocket Guide");

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
