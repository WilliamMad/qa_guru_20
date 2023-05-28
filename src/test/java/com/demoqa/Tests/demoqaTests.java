package com.demoqa.Tests;

import com.codeborne.selenide.Condition;
import com.demoqa.Settings.Config;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.util.Arrays;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class demoqaTests extends Config {

    @Test
    void practiseFormTest() {
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
            $("[class = 'modal-content']").$(byText(s))
                    .should(appear);
        }
    }
}
