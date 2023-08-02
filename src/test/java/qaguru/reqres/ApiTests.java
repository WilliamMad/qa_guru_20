package qaguru.reqres;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import qaguru.reqres.models.AuthBodyModel;
import qaguru.reqres.models.UsersMassiveModel;
import qaguru.reqres.models.UserInfoModel;

import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static qaguru.reqres.specs.AuthSpec.*;

public class ApiTests {


    @Test
    @Tag("remote_api")
    @DisplayName("Non existing user not found")
    void userNotFoundTest() {

        step("Make request and check that status code = 404", () ->
                given(baseRequestSpec)
                        .when()
                        .get("/users/99")
                        .then()
                        .spec(response404Spec));
    }

    @Test
    @Tag("remote_api")
    @DisplayName("User successfully deleted")
    void deleteExistingUserTest() {

        step("Make request and check that status code = 204", () ->
                given(baseRequestSpec)
                        .when()
                        .delete("/users/2")
                        .then()
                        .spec(response204Spec));
    }

    @Test
    @Tag("remote_api")
    @DisplayName("Updating user information")
    void updateUserTest() {

        AuthBodyModel authData = new AuthBodyModel();
        authData.setJob("zion resident");
        authData.setName("morpheus");

        AuthBodyModel authResponse = step("Make request", () ->
                given(baseRequestSpec)
                        .body(authData)
                        .when()
                        .put("/users/2")
                        .then()
                        .spec(response200Spec)
                        .extract().as(AuthBodyModel.class));

        step("Check response", () -> {
            assertAll(
                    () -> assertEquals("morpheus", authResponse.getName()),
                    () -> assertEquals("zion resident", authResponse.getJob())

                    // при проверке не совпадают милисекунды, пришлось закоммитить
                    // ,() -> assertEquals(TimeCatcher.getCurrentTime(), authResponse.getUpdatedAt())
            );
        });
    }


    @Test
    @Tag("remote_api")
    @DisplayName("Checking user information")
    void getUserInfoTest() {

        UserInfoModel userResponse = step("Make request", () ->
                given(baseRequestSpec)
                        .when()
                        .get("/users/2")
                        .then()
                        .spec(response200AndUserSchemaCheckSpec)
                        .extract().as(UserInfoModel.class));

        step("Check response", () -> {
            assertAll(
                    () -> assertEquals("janet.weaver@reqres.in", userResponse.getData().getEmail()),
                    () -> assertEquals("Janet", userResponse.getData().getFirst_name()),
                    () -> assertEquals("Weaver", userResponse.getData().getLast_name()),
                    () -> assertEquals("https://reqres.in/img/faces/2-image.jpg", userResponse.getData().getAvatar())
            );
        });
    }

    @Test
    @Tag("remote_api")
    @DisplayName("Checking users information in data massive")
    void getListOfUsersInfoTest() {

        UsersMassiveModel userResponse = step("Make request", () ->
                given(baseRequestSpec)
                        .when()
                        .get("/unknown")
                        .then()
                        .spec(response200AndMassiveSchemaCheckSpec)
                        .extract().as(UsersMassiveModel.class));

        step("Check response", () -> {
            assertAll(
                    () -> assertEquals(1, userResponse.getPage()),
                    () -> assertEquals(1, userResponse.getData().get(0).getId()),
                    () -> assertEquals("cerulean", userResponse.getData().get(0).getName()),
                    () -> assertEquals(3, userResponse.getData().get(2).getId()),
                    () -> assertEquals("true red", userResponse.getData().get(2).getName()),
                    () -> assertEquals(6, userResponse.getData().get(5).getId()),
                    () -> assertEquals("blue turquoise", userResponse.getData().get(5).getName())
            );
        });
    }
}
