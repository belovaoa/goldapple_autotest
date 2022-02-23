package com_belovva.tests;

import com_belovva.helpers.DriverUtils;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.title;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class GoldappleTests extends TestBase{

    @Test
    @DisplayName("Проверка наличия лого на главной странице сайта")
    @Severity(SeverityLevel.CRITICAL)
    void checkLogoTest() {

        step("Проверяем наличие логотипа на главной странице", () ->
                $(".header-logo-icon").shouldBe(visible));
    }

    @Test
    @DisplayName("Проверка названия интернет-магазина на главной")
    @Severity(SeverityLevel.CRITICAL)
    void titleTest() {

        step("Название страницы содержит '«Золотое яблоко» - интернет-магазин косметики и парфюмерии." +
                "Официальный сайт | Gold Apple'", () -> {
            String expectedTitle = "«Золотое яблоко» - интернет-магазин косметики и парфюмерии. Официальный сайт | Gold Apple";
            String actualTitle = title();

            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }

    @Test
    @DisplayName("Проверка функции поиска на сайте")
    @Severity(SeverityLevel.BLOCKER)
    void searchTest() {

        step("Ввести в поле для поиска запрос", () -> {
            $(".header-tab-button_search").click();
            $("#search_multiline").setValue("помада").click();
        });

        step("Проверка результатов поиска", () ->
                $(".digi-ac_products").shouldHave(text("помада")));
    }

    @Test
    @DisplayName("Проверка наличия ссылки на контакты")
    void footerListShouldHaveContacts() {
        step("Проверка наличия ссылки на контакты", () ->
                $(".footer__row_up").shouldHave(text("\n" +
                        "            контакты\n" +
                        "        ")));
    }

    @Test
    @DisplayName("Page console log should not have errors")
    @Severity(SeverityLevel.NORMAL)
    void consoleShouldNotHaveErrorsTest() {

        step("Логи в консоли не должны содержать текст 'SEVERE'", () -> {
            String consoleLogs = DriverUtils.getConsoleLogs();
            String errorText = "SEVERE";

            assertThat(consoleLogs).doesNotContain(errorText);
        });
    }
}
