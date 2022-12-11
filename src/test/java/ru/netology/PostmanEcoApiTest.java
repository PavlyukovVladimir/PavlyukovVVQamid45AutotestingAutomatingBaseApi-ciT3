package ru.netology;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

class PostmanEcoApiTest {
    @Test
    void shouldReturnSomeData() {
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
                .contentType(ContentType.JSON)
                .body(matchesJsonSchemaInClasspath("schema.json"))
                .body("data", equalTo("some data"))
                .body("headers", hasEntry("x-forwarded-proto", "https"))
                .body("data == \"some data\"", is(true));
    }
}
