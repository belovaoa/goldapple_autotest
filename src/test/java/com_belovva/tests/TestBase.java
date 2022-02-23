package com_belovva.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import com_belovva.config.ProjectConfig;
import com_belovva.helpers.AllureAttachments;
import com_belovva.helpers.DriverSettings;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class TestBase {

    public static ProjectConfig credentials =
            ConfigFactory.create(ProjectConfig.class);

    @BeforeAll
    static void setup() {

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        DriverSettings.configure();
    }

    @BeforeEach
    void openMainPage() {

        step("Открыть главную страницу сайта https://goldapple.ru/", () ->
                open("https://goldapple.ru/"));
    }

    @AfterEach
    public void addAttachments() {
        AllureAttachments.screenshotAs("Last screenshot");
        AllureAttachments.pageSource();
        AllureAttachments.browserConsoleLogs();
        AllureAttachments.addVideo();
    }
}
