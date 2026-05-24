package com.saucedemo.automation.SauceDemoProject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;

public class ApiTest {

    @Test
    public void testLoginApiEndpoint() {
        // Define payload as a clean JSON String
        String jsonPayload = "{"
                + "\"email\": \"eve.holt@reqres.in\","
                + "\"password\": \"cityslicka\""
                + "}";

        given()
            .contentType(ContentType.JSON)
            .body(jsonPayload)
        .when()
            .post("https://reqres.in/api/login") // Absolute path keeps Jenkins totally safe!
        .then()
            .statusCode(200)                         
            .contentType(ContentType.JSON)           
            .body("token", notNullValue());          
    }
}
