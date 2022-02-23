package com_belovva.helpers;

import com.codeborne.selenide.Configuration;
import com_belovva.config.Project;
import com_belovva.tests.TestBase;
import org.openqa.selenium.remote.DesiredCapabilities;

import static java.lang.String.format;

public class DriverSettings {

    public static void configure() {

        String selenoidUrl = TestBase.credentials.URL();
        String login = TestBase.credentials.login();
        String password = TestBase.credentials.password();
        Configuration.remote = format("https://%s:%s@" + selenoidUrl, login, password);
        Configuration.browser = Project.config.browser();
        Configuration.browserVersion = Project.config.browserVersion();
        Configuration.browserSize = Project.config.browserSize();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;
        Configuration.timeout = 10000;
        Configuration.startMaximized = true;
    }
}
