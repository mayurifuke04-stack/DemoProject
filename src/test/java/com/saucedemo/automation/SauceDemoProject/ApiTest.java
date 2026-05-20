package com.saucedemo.automation.SauceDemoProject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class ApiTest {

    @BeforeClass
    public void setupConfig() {
        // Base URI for API endpoints
        RestAssured.baseURI = "https://reqres.in/api";
    }

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
            .post("/login")
        .then()
            .statusCode(200)                         // Asserts HTTP 200 OK status
            .contentType(ContentType.JSON)           // Asserts response format is JSON
            .body("token", notNullValue());          // Asserts that a token was generated
    }
}