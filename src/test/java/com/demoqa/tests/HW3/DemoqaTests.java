package com.demoqa.tests.HW3;

import com.demoqa.tests.BaseTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class DemoqaTests extends BaseTest {

    @Test
    void practiseFormTest() {

        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("#firstName").setValue("Shah Rukh");
        $("#lastName").setValue("Khan");
        $("#userEmail").setValue("Shahrukh@bollywood.com");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("9111657385");
        $("#dateOfBirthInput").click();
        $("[class*='month-select']").selectOptionByValue("11");
        $("[class*='year-select']").selectOptionByValue("1965");
        $("[class*='day--002']").click();
        $("#subjectsInput").setValue("Hindi").pressEnter();
        $("#subjectsInput").setValue("Arts").pressEnter();
        $("#hobbiesWrapper").$(byText("Music")).click();
        $("input#uploadPicture").uploadFromClasspath("1.png");
        $("#currentAddress").setValue("India");
        $("#react-select-3-input").setValue("NCR").pressEnter();
        $("#react-select-4-input").setValue("Delhi").pressEnter();
        $("#submit").click();

        checkResult();
    }

    void checkResult() {
        for (String s : Arrays.asList("Thanks for submitting the form", "Shah Rukh Khan",
                "Shahrukh@bollywood.com", "Male", "9111657385", "02 December,1965",
                "Hindi, Arts", "Music", "1.png", "India", "NCR Delhi")) {
            $("[class = 'modal-content']").shouldHave(text(s));
        }
    }
}
