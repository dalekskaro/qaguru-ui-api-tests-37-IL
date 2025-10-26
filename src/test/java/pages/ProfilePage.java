package pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.AlertModal;
import pages.components.DeleteModal;

public class ProfilePage {

  DeleteModal deleteModal = new DeleteModal();
  AlertModal alertModal = new AlertModal();

  private final SelenideElement
      userNameValue = $("#userName-value"),
      deleteBookButton = $("#delete-record-undefined"),
      noRowsText = $(".rt-noData");

  @Step("Открываем заглушку")
  public ProfilePage openImagesPage() {
    open("/images/Toolsqa.jpg");
    return this;
  }

  @Step("Открываем страницу профиля пользователя")
  public ProfilePage openProfilePage() {
    open("/profile");
    return this;
  }

  @Step("Проверяем, что логин пользователя '{userName}' отображается на странице")
  public ProfilePage checkUserName(String userName) {
    userNameValue.shouldHave(text(userName));
    return this;
  }

  @Step("Проверяем, что название книги '{bookTitle}' отображается в гриде")
  public ProfilePage checkBookTitle(String bookTitle) {
    $("[id='see-book-" + bookTitle + "']").shouldHave(text(bookTitle));
    return this;
  }

  @Step("Нажимаем на кнопку удаления книги в гриде")
  public ProfilePage clickOnDeleteBookButton() {
    deleteBookButton.click();
    return this;
  }

  @Step("В модальном окне подтверждаем удаление книги")
  public ProfilePage acceptDeleteBookInModal() {
    deleteModal.acceptDelete();
    return this;
  }

  @Step("Закрываем алерт")
  public ProfilePage closeAlertModal() {
    alertModal.checkTextOnAlert("Book deleted.").confirmAlert();
    return this;
  }

  @Step("Проверяем, что в гриде нет книг - отображается текст 'No rows found'")
  public ProfilePage checkNoBooksFound() {
    noRowsText.shouldHave(text("No rows found"));
    return this;
  }
}
