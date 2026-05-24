package com.saucedemo.automation.SauceDemoProject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class ApiTest {

    private static final String API_KEY = "YOUR_ACTUAL_API_KEY_FROM_DASHBOARD";

    @BeforeClass
    public void setupConfig() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    public void testLoginApiEndpoint() {
        String jsonPayload = "{"
                + "\"email\": \"eve.holt@reqres.in\","
                + "\"password\": \"cityslicka\""
                + "}";

        given()
            .header("x-api-key", API_KEY)
            .contentType(ContentType.JSON)
            .body(jsonPayload)
        .when()
            .post("/login")
        .then()
            .log().ifValidationFails()
            .statusCode(200)                         
            .contentType(ContentType.JSON)           
            .body("token", notNullValue());          
    }
}