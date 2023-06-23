package com.demoqa.tests.HW7;

import com.demoqa.pages.RegistrationPage;
import com.demoqa.tests.BaseTest;
import org.junit.jupiter.api.Test;
public class RegistrationWithPageObjectsTests extends BaseTest {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void successfulRegistrationTest() {

        registrationPage.openPage().executeJs()
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


        registrationPage
                .checkResult("Student Name", "Shah Rukh Khan")
                .checkResult("Student Email", "Shahrukh@bollywood.com")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "9111657385")
                .checkResult("Date of Birth", "02 November,1965")
                .checkResult("Subjects", "Hindi")
                .checkResult("Hobbies", "Music")
                .checkResult("Picture", "1.png")
                .checkResult("Address", "India")
                .checkResult("State and City", "NCR Delhi");
    }
}
