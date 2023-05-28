package com.demoqa.settings;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;

public class BaseTest {

@BeforeEach
public void beforeTests() {

    clearBrowserCookies();

    Configuration.baseUrl = "https://demoqa.com";
    Configuration.browserSize = "1920x1080";
    Configuration.holdBrowserOpen = true;
    Configuration.pageLoadStrategy = "eager";
}

}
