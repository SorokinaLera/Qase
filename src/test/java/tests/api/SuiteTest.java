package tests.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.response.Response;
import org.apache.http.protocol.HTTP;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.api.models.Suite;
import tests.api.models.SuiteDetails;
import tests.api.models.Suites;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SuiteTest {

    @Test
    public void getListOfSuits() {
        Response response = given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .header("Token", "406e1db90ecc3be6dc280f103455695fc48cb868")
                .when()
                .get("https://api.qase.io/v1/suite/DEMO")
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();
        Gson gson = new Gson();
        Suites ourResponse = gson.fromJson(response.body().asString(), Suites.class);
        Assert.assertEquals(ourResponse.getResult().getTotal(), 2, "List was changed, check your response body");
    }

    @Test
    public void getSuiteById() throws FileNotFoundException {
        Response response = given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .header("Token", "406e1db90ecc3be6dc280f103455695fc48cb868")
                .when()
                .get("https://api.qase.io/v1/suite/DEMO/11")
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        Suite responseFromJson = gson.fromJson(new FileReader(new File("src/test/resources/demo11.json")), Suite.class);
        Suite ourResponse = gson.fromJson(response.body().asString(), Suite.class);
        Assert.assertEquals(responseFromJson, ourResponse, "Objects are not equivalent");
    }

    @Test
    public void createNewSuite() {
        String newSuite = "{\n" +
                "    \"title\": \"Test suite\",\n" +
                "    \"parent_id\": null,\n" +
                "    \"description\": \"Suite description\",\n" +
                "    \"preconditions\": \"Suite preconditions\"\n" +
                "}";

        Response response = given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .header("Token", "406e1db90ecc3be6dc280f103455695fc48cb868")
                .body(newSuite)
                .when()
                .post("https://api.qase.io/v1/suite/QASE")
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        Suite ourResponse = gson.fromJson(response.body().asString(), Suite.class);
        int responseId = ourResponse.getResult().getId();
        String createdTitle = gson.fromJson(newSuite, SuiteDetails.class).getTitle();

        Response newResponse = given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .header("Token", "406e1db90ecc3be6dc280f103455695fc48cb868")
                .when()
                .get("https://api.qase.io/v1/suite/QASE/" + responseId)
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();
        Suite checkingResponse = gson.fromJson(newResponse.body().asString(), Suite.class);
        String existedTitle = checkingResponse.getResult().getTitle();
        Assert.assertEquals(createdTitle, existedTitle, "The object was created incorrectly");
    }

    @Test
    public void updateSuit() {
        String newSuite = "{\n" +
                "    \"title\": \"Test suite\",\n" +
                "    \"parent_id\": null,\n" +
                "    \"description\": \"Suite description\",\n" +
                "    \"preconditions\": \"Suite preconditions\"\n" +
                "}";
        String updatedData = "{\n" +
                "    \"title\": \"Test suite\",\n" +
                "    \"parent_id\": null,\n" +
                "    \"description\": \"Updated suite description\",\n" +
                "    \"preconditions\": \"Suite preconditions\"\n" +
                "}";

        Response response = given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .header("Token", "406e1db90ecc3be6dc280f103455695fc48cb868")
                .body(newSuite)
                .when()
                .post("https://api.qase.io/v1/suite/QASE")
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        Suite ourResponse = gson.fromJson(response.body().asString(), Suite.class);
        int responseId = ourResponse.getResult().getId();

        given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .header("Token", "406e1db90ecc3be6dc280f103455695fc48cb868")
                .body(updatedData)
                .when()
                .patch("https://api.qase.io/v1/suite/QASE/" + responseId)
                .then()
                .log().body()
                .statusCode(200);
        String updatedDescription = gson.fromJson(updatedData, SuiteDetails.class).getDescription();

        Response getUpdatedSuite = given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .header("Token", "406e1db90ecc3be6dc280f103455695fc48cb868")
                .when()
                .get("https://api.qase.io/v1/suite/QASE/" + responseId)
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();
        Suite checkingResponse = gson.fromJson(getUpdatedSuite.body().asString(), Suite.class);
        String existedDescription = checkingResponse.getResult().getDescription();
        Assert.assertEquals(updatedDescription, existedDescription, "The object was updated incorrectly");
    }

    @Test
    public void deleteSuite() {
        String newSuite = "{\n" +
                "    \"title\": \"Test suite\",\n" +
                "    \"parent_id\": null,\n" +
                "    \"description\": \"Suite description\",\n" +
                "    \"preconditions\": \"Suite preconditions\"\n" +
                "}";

        Response response = given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .header("Token", "406e1db90ecc3be6dc280f103455695fc48cb868")
                .body(newSuite)
                .when()
                .post("https://api.qase.io/v1/suite/QASE")
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        Suite ourResponse = gson.fromJson(response.body().asString(), Suite.class);
        int responseId = ourResponse.getResult().getId();

        given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .header("Token", "406e1db90ecc3be6dc280f103455695fc48cb868")
                .when()
                .delete("https://api.qase.io/v1/suite/QASE/" + responseId)
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();

        given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .header("Token", "406e1db90ecc3be6dc280f103455695fc48cb868")
                .when()
                .get("https://api.qase.io/v1/suite/QASE/" + responseId)
                .then()
                .log().body()
                .statusCode(404)
                .body("errorMessage", equalTo("Suite not found"));
    }
}
