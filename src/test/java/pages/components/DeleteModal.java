package pages.components;

import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.SelenideElement;

public class DeleteModal {
  private final SelenideElement
      acceptDeleteButton = $("#closeSmallModal-ok");

  public DeleteModal acceptDelete() {
    acceptDeleteButton.click();
    return this;
  }
}
