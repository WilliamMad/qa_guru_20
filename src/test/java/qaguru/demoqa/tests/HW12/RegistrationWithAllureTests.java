package qaguru.demoqa.tests.HW12;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import qaguru.demoqa.pages.RegistrationPage;
import qaguru.demoqa.tests.BaseTestRemote;
import qaguru.demoqa.utils.GetRandomData;

import static io.qameta.allure.Allure.step;

@Tag("remote")
@DisplayName("Successful registration test")
public class RegistrationWithAllureTests extends BaseTestRemote {

    RegistrationPage registrationPage = new RegistrationPage();
    GetRandomData randomData = new GetRandomData();

    String firstName = randomData.getFirstName(),
           lastName = randomData.getLastName(),
           email = randomData.getEmail(),
           gender = randomData.getGender(),
           phoneNumber = randomData.getPhoneNumber(),
           day = randomData.getDay(),
           month = randomData.getMonth(),
           year = randomData.getYear(),
           subject = randomData.getSubjects(),
           hobby = randomData.getHobbies(),
           fileName = "1.png",
           address = randomData.getAddress(),
           state = randomData.getState(),
           city = randomData.getCity(state);


    @Test
    void successfulRegistrationTest() {

        step ("Open form", () -> {
            registrationPage.openPage().executeJs();
        });
        step("Fill form", () -> {
                registrationPage.setFirstName(firstName)
                            .setLastName(lastName)
                            .setUserEmail(email)
                            .setGender(gender)
                            .setUserNumber(phoneNumber)
                            .setBirthDay(day, month, year)
                            .setSubject(subject)
                            .setHobbies(hobby)
                            .uploadFile(fileName)
                            .setAddress(address)
                            .setState(state)
                            .setCity(city)
                            .clickSubmit();
                });
        step("Verify results", () -> {
            registrationPage
                    .checkResult("Student Name", firstName + " " + lastName)
                    .checkResult("Student Email", email)
                    .checkResult("Gender", gender)
                    .checkResult("Mobile", phoneNumber)
                    .checkResult("Date of Birth", day + " " + month + "," + year)
                    .checkResult("Subjects", subject)
                    .checkResult("Hobbies", hobby)
                    .checkResult("Picture", fileName)
                    .checkResult("Picture", "1.png")
                    .checkResult("Address", address)
                    .checkResult("State and City", state + " " + city);
        });
    }
}
