package com.demoqa.tests;

import org.junit.jupiter.api.Test;
public class RegistrationWithPageObjectsTests extends BaseTest{

    @Test
    void successfulRegistrationTest() {

        registrationPage.openPage()
                .setFirstName("Shah Rukh")
                .setLastName("Khan")
                .setUserEmail("Shahrukh@bollywood.com")
                .setGender("Male")
                .setUserNumber("9111657385")
                .setBirthDay("02", "November", "1965")
                .setSubject("Hindi")
                .setHobbies("Music")
                .uploadFile("1.png")
                .setAddress("India")
                .setState("NCR")
                .setCity("Delhi")
                .clickSubmit();

        registrationPage.checkResult(
                "Shah Rukh",
                "Khan",
                "Shahrukh@bollywood.com",
                "9111657385"
        );
    }
}
