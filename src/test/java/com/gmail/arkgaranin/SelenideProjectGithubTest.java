package com.gmail.arkgaranin;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SelenideProjectGithubTest {

  @BeforeAll
  static void setUp() {
    Configuration.startMaximized = true;
    Configuration.browser = "chrome";
    Configuration.baseUrl = "https://github.com";
  }

  @Test
  void checkLocationJunit5CodeOnSelenidePageTest() {
    // Открытие home page github
    open("/");
    // Ввод в поле поиска Selenide и старт поиска
    $("[name=q]").setValue("Selenide").pressEnter();
    // Клик на первую найденную ссылку Selenide и переход на стр-цу
    $$("ul.repo-list li").first().$("a").click();
    // Переход в таб Wiki
    $("ul.UnderlineNav-body li", 4).$("a#wiki-tab").click();
    // Клик на "Show 1 more pages"
    $("li.wiki-more-pages-link button").click();

    // Проверка, что в списке Pages есть стр-ца SoftAssertions
    $("div#wiki-pages-box").shouldHave(text("SoftAssertions"));

    // Переход на стр-цу SoftAssertions
    $("div#wiki-pages-box").find(byText("SoftAssertions")).click();

    // Проверка, что на стр-це SoftAssertions есть пример кода для JUnit5
    $("#wiki-body").shouldHave(text("Using JUnit5 extend test class:"));
  }
}