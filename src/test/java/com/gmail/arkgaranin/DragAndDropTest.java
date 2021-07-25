package com.gmail.arkgaranin;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DragAndDropTest {

  @BeforeAll
  static void setUp() {
    Configuration.startMaximized = true;
    Configuration.browser = "chrome";
  }

  @Test
  void checkDragAndDropTest() {
    // Открытие drag_and_drop стр-цы
    open("https://the-internet.herokuapp.com/drag_and_drop");
    // Перенос прямоугольника А на прямоугольник В
    $("#column-a").dragAndDropTo("#column-b");

    // Проверка, что прямоугольники действительно поменялись
    $("#column-a").shouldHave(exactText("B"));
    $("#column-b").shouldHave(exactText("A"));
  }
}