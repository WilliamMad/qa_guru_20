package qaguru.reqres;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class ApiTests {

    @Test
    void UserNotFoundTest() {

        given()
                .log().uri()
                .log().method()
                .log().body()
                .when()
                .get("https://reqres.in/api/users/99")
                .then()
                .log().status()
                .log().body()
                .statusCode(404);
    }

    @Test
    void deleteExistingUserTest() {

        given()
                .log().uri()
                .log().method()
                .log().body()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }

    @Test
    void updateUserTest() {

        String authData = "{ \"name\": \"morpheus\", \"job\": \"zion resident\" }";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(authData)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"),
                        "job", is("zion resident"));
    }


    @Test
    void getUserInfoTest() {

        given()
                .log().uri()
                .log().method()
                .log().body()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.size()", is(5),
                        "data.email", is("janet.weaver@reqres.in"),
                        "data.first_name", is("Janet"),
                        "data.last_name", is("Weaver"),
                        "data.avatar", is("https://reqres.in/img/faces/2-image.jpg")
                );
    }

    @Test
    void getListOfUsersInfoTest() {

        given()
                .log().uri()
                .log().method()
                .log().body()
                .when()
                .get("https://reqres.in/api/unknown")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data", hasItem(hasEntry("id", 1)))
                .body("data.find {it.id == 1}.name", equalTo("cerulean"))
                .body("data", hasItem(hasEntry("id", 3)))
                .body("data.find {it.id == 3}.name", equalTo("true red"))
                .body("data", hasItem(hasEntry("id", 6)))
                .body("data.find {it.id == 6}.name", equalTo("blue turquoise"));
    }
}
