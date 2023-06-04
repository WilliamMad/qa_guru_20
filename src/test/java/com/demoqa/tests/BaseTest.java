package com.demoqa.tests;

import com.demoqa.pages.RegistrationPage;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Configuration.*;


public class BaseTest {

    RegistrationPage registrationPage = new RegistrationPage();

    @BeforeEach
     void beforeEach() {
        baseUrl = "https://demoqa.com";
        browserSize = "1920x1080";
        holdBrowserOpen = true;
        pageLoadStrategy = "eager";
    }

}
