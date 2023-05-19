package com.BookStore.step_definitions;

import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class BookStore {

    String url = "https://bookstore.toolsqa.com";


    @Test
    public void bookStore(){

        given().accept(ContentType.JSON)
                .when().get(url + "/BookStore/v1/Book?ISBN=9781449325862")
                .then().contentType(ContentType.JSON)
                .and().statusCode(200)
                .body("title", equalTo("Git Pocket Guide."))
                .body("pages", equalTo(234))
                .extract().response().prettyPrint();

    }



}
