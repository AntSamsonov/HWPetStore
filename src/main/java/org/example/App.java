package ru.dexsys;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;

public class App
{
    private static final String baseUri = "https://petstore.swagger.io/v2/pet";

    public static void createPet(int id, String name, String tagName) {
        String body = "{\n" +
                "  \"id\": " + id + ",\n" +
                "  \"category\": {\n" +
                "    \"id\": 46,\n" +
                "    \"name\": \"Dogs\"\n" +
                "  },\n" +
                "  \"name\": \"" + name + "\",\n" +
                "  \"photoUrls\": [\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 1, \n" +
                "      \"name\": \"" + tagName + "\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        RestAssured.given()
                .body(body)
                .contentType(ContentType.JSON)
                .post(baseUri)
                .then()
                .log().ifValidationFails(LogDetail.ALL)
                .statusCode(200)
                .extract()
                .response()
                .body()
                .prettyPrint();
    }

    public static void findPetById(int petId){
        RestAssured.given()
                .baseUri(baseUri)
                .when()
                .get("/{petId}", petId)
                .then()
                .log().all()
                .statusCode(200);
    }

    public static void deletePet(int petId){
        RestAssured.given()
                .baseUri(baseUri)
                .when()
                .delete("/{petId}", petId)
                .then()
                .log().all()
                .statusCode(200);
    }

    public static void updatePet(int petId, String newName, String newTagName, String newStatus){
        String newBody = "{\n" +
                "  \"id\": " + petId + ",\n" +
                "  \"category\": {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Dogs\"\n" +
                "  },\n" +
                "  \"name\": \"" + newName + "\",\n" +
                "  \"photoUrls\": [\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 1, \n" +
                "      \"name\": \"" + newTagName + "\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"" + newStatus + "\"\n" +
                "}";

        RestAssured.given()
                .body(newBody)
                .contentType(ContentType.JSON)
                .post(baseUri)
                .then()
                .log().ifValidationFails(LogDetail.ALL)
                .statusCode(200)
                .extract()
                .response()
                .body()
                .prettyPrint();
    }

    public static void main(String[] args) {
        createPet(2502, "DOGGYY", "Testoviy tag number 1");
        findPetById(2502);
        updatePet(2502, "Sobaken", "New test tag number 1!", "sold");
        deletePet(2502);
    }


}
