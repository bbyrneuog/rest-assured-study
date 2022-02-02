package com.xyzcorp;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONObject;

public class FruitTest {
    @Test
    public void testGetFruits() {
        given()
                .relaxedHTTPSValidation()
                .accept(ContentType.JSON)
                .when()
                .get("https://staging.tiered-planet.net/mild-temper/fruits")
                .then()
                .assertThat()
                .body("[0].description", equalTo("Winter fruit"));
    }

    @Test
    public void testPostNewFruit () {

        JSONObject bananaObject = new JSONObject()
            .put("description", "a delicious treat")
            .put("name", "banana");

        given()
            .relaxedHTTPSValidation()
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .body(bananaObject.toString())
            .when()
            .post("https://staging.tiered-planet.net/mild-temper/fruits")
            .then()
            .assertThat()
            .statusCode(200);
    }
}