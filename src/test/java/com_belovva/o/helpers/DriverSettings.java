package com_belovva.o.helpers;

import com.codeborne.selenide.Configuration;
import com_belovva.o.config.Project;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com_belovva.o.tests.TestBase.credentials;
import static java.lang.String.format;

public class DriverSettings {

    public static void configure() {

        String selenoidUrl = credentials.URL();
        String login = credentials.login();
        String password = credentials.password();
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
