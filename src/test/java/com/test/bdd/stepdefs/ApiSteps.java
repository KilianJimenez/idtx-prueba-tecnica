package com.test.bdd.stepdefs;

import com.test.bdd.pet.Pet;
import com.test.bdd.utils.Utils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.junit.Assert;

import java.util.*;

import static io.restassured.RestAssured.given;

public class ApiSteps {

    private static Response response;

    public ApiSteps() {
        RestAssured.baseURI="https://petstore.swagger.io/v2";
    }

    @Given("the endpoint is {string}")
    public void setEndpoint(String endpoint) {
        RestAssured.basePath = endpoint;
    }

    @When("I request POST with payload")
    public void requestWithPayload(DataTable payload) {
        JSONObject json = getTableAsJSONObject(payload);
        response = doPost(json);
    }

    @When("I request GET with no parameters")
    public void requestEndpointWithEmptyPayload() {
        response = doGet();
    }

    @When("I request GET with parameters {string}")
    public void requestEndpointWithParameters(String parameters) {
        response = doGet(parameters);
    }

    @Then("endpoint code returned is 200")
    public void endpointCodeIs200() {
        Assert.assertEquals("Something went wrong: " +
                response.statusCode() + response.statusLine(), 200, response.statusCode());
        Utils.logger.info("Assert: Endpoint returned code [" + response.statusCode() + "]");
    }

    @Then("I print the results by id and name")
    public void iPrintTheResultsByIdAndName() {
        JSONObject entries = new JSONObject();
        List<Map<String, Object>> jsonResponse = JsonPath.from(response.asString()).get();

        for(Map<String, Object> stringMap : jsonResponse) {
            Object key = stringMap.get("id");
            Object value = stringMap.get("name");
            entries.put(key, value);
        }

        System.out.println(entries);
    }

    @Then("I print how many pets have the same name")
    public void iPrintThePetsWithTheSameName() {
        List<Pet> pets = new ArrayList<>();
        List<Map<String, Object>> jsonResponse = JsonPath.from(response.asString()).get();
        for(int i = 0; i < jsonResponse.size(); i++) {
            Pet pet = new Pet(String.valueOf(jsonResponse.get(i).get("id")), (String) jsonResponse.get(i).get("name"));

            if(pets.size() > 0){
                for (Iterator<Pet> petIterator = pets.iterator(); petIterator.hasNext();) {
                    if (Objects.equals(petIterator.next().getName(), pet.getName())) {
                        pets.add(pet);
                        petIterator.remove();
                    }
                }
            } else if( i+1 != jsonResponse.size()) {
                for (Iterator<Map<String, Object>> iterator = jsonResponse.iterator(); iterator.hasNext(); ) {
                    Pet nextPet = new Pet(String.valueOf(iterator.next().get("id")), String.valueOf(iterator.next().get("name")));
                    if (Objects.equals(nextPet.getName(), pet.getName()) && !Objects.equals(nextPet.getId(), pet.getId())) {
                        pets.add(pet);
                        iterator.remove();
                    }
                }
            }
        }

        System.out.println("Pets with the same name: " + pets.size());
    }

    // Privates
    private Response doGet() {
        return given().log().all().contentType(ContentType.JSON)
                .when()
                .get()
                .then().extract().response();
    }

    private Response doGet(String parameters) {
        return given().log().all().contentType(ContentType.JSON)
                .when()
                .get(parameters)
                .then().extract().response();
    }

    private Response doPost(JSONObject requestBody) {
        return given().log().all().contentType(ContentType.JSON)
                .body(requestBody.toJSONString())
                .when()
                .post()
                .then().extract().response();
    }

    private JSONObject getTableAsJSONObject(DataTable table){
        JSONObject json = new JSONObject();
        Object value;
        String key;

        List<Map<String, String>> list = table.asMaps(String.class, String.class);

        for(Map<String, String> stringMap : list) {
            value = stringMap.get("value");
            key = stringMap.get("key");

            if(StringUtils.isNumeric((CharSequence) value))
                value = Integer.parseInt((String) value);

            json.put(key, value);

        }

        return json;
    }
}
