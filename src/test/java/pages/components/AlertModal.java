package pages.components;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import static org.assertj.core.api.Assertions.assertThat;

public class AlertModal {

  @Step("Проверяем, что в алерте есть текст {text}")
  public AlertModal checkTextOnAlert(String text) {
    String alertText = Selenide.switchTo().alert().getText();
    assertThat(alertText).isEqualTo("Book deleted.");
    return this;
  }

  @Step("Нажимаем на подтверждение в алерте")
  public AlertModal confirmAlert() {
    Selenide.confirm();
    return this;
  }
}
