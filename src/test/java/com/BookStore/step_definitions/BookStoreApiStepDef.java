package com.BookStore.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class BookStoreApiStepDef {



    String baseUrl = "https://bookstore.toolsqa.com/BookStore";
    Response response;

    @Given("User sends GET request to {string} and content type should be {string}")
    public void user_sends_GET_request_to_and_content_type_should_be(String string, String string2) {

        response = given().accept(string2)
                .queryParam("ISBN", "9781449325862")
                .when().get(baseUrl + string);

    }

    @Then("status code should be {int}")
    public void status_code_should_be(int statusCode) {

        Assert.assertEquals(statusCode,response.statusCode());

    }

    @Then("{string} should {string}")
    public void should(String string, String string2) {

        Assert.assertEquals(string2,response.jsonPath().getString(string));
    }
    @Then("{string} should be {int}")
    public void should_be(String string, int int1) {

        Assert.assertEquals(int1, response.jsonPath().getInt(string));
    }




}
