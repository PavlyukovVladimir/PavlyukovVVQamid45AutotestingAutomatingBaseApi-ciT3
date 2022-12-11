package ru.netology;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

class PostmanEcoApiTest {
    @Test
    void shouldReturnDemoAccounts() {
        // Given - When - Then
        // Предусловия
        given()
                .baseUri("https://postman-echo.com")
                .contentType("text/plain; charset=UTF-8")
                .body("some data")
                // Выполняемые действия
                .when()
                .log()
                .all()
                .post("/post")
                // Проверки
                .then()
                .log()
                .all()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schema.json"));
    }
}
