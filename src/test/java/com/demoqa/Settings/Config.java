package com.demoqa.Settings;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.open;

public class Config {

@BeforeEach
public void beforeTests() {
    clearBrowserCookies();

    Configuration.baseUrl = "https://demoqa.com/automation-practice-form";
    Configuration.browserSize = "1920x1080";
    Configuration.holdBrowserOpen = true;
    Configuration.pageLoadStrategy = "eager";

    open("");
}

}
