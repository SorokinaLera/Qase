package tests.api;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.response.Response;
import models.TestSuite;
import org.apache.http.protocol.HTTP;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.api.models.Suite;
import tests.api.models.Suites;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SuiteTest {
    private static final String URL = "https://api.qase.io/v1/suite/";
    Faker faker = new Faker();

    @Test
    public void getListOfSuits() {
        Response response = given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .header("Token", System.getenv("token"))
                .when()
                .get(URL + "DEMO")
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
                .header("Token", System.getenv("token"))
                .when()
                .get(URL + "DEMO/" + "11")
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
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        TestSuite newTestSuite = new TestSuite(faker.howIMetYourMother().character(), null,faker.howIMetYourMother().quote(), faker.howIMetYourMother().catchPhrase());
        String newSuite = gson.toJson(newTestSuite);

        Response response = given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .header("Token", System.getenv("token"))
                .body(newSuite)
                .when()
                .log().body()
                .post(URL + "QASE")
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();
        Suite ourResponse = gson.fromJson(response.body().asString(), Suite.class);
        int responseId = ourResponse.getResult().getId();

        Response newResponse = given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .header("Token", System.getenv("token"))
                .when()
                .get("https://api.qase.io/v1/suite/QASE/" + responseId)
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();
        Suite checkingResponse = gson.fromJson(newResponse.body().asString(), Suite.class);
        String existedTitle = checkingResponse.getResult().getTitle();
        Assert.assertEquals(newTestSuite.getSuiteName(), existedTitle, "The object was created incorrectly");
    }

    @Test
    public void updateSuit() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        TestSuite newTestSuite = new TestSuite(faker.backToTheFuture().character(), null, faker.backToTheFuture().quote(), faker.backToTheFuture().quote());
        String newSuite = gson.toJson(newTestSuite);
        TestSuite updatedTestSuite = new TestSuite(newTestSuite.getSuiteName(), null, faker.chuckNorris().fact(), faker.chuckNorris().fact());
        String updatedData = gson.toJson(updatedTestSuite);

        Response response = given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .header("Token", System.getenv("token"))
                .body(newSuite)
                .when()
                .post(URL + "QASE")
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();
        Suite ourResponse = gson.fromJson(response.body().asString(), Suite.class);
        int responseId = ourResponse.getResult().getId();

        given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .header("Token", System.getenv("token"))
                .body(updatedData)
                .when()
                .patch(URL + "QASE/" + responseId)
                .then()
                .log().body()
                .statusCode(200);
        String updatedDescription = updatedTestSuite.getDescription();

        Response getUpdatedSuite = given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .header("Token", System.getenv("token"))
                .when()
                .get(URL + "QASE/" + responseId)
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
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        TestSuite newTestSuite = new TestSuite(faker.hobbit().character(), null,faker.hobbit().quote(), faker.hobbit().thorinsCompany());
        String newSuite = gson.toJson(newTestSuite);

        Response response = given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .header("Token", System.getenv("token"))
                .body(newSuite)
                .when()
                .post(URL + "QASE")
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();
        Suite ourResponse = gson.fromJson(response.body().asString(), Suite.class);
        int responseId = ourResponse.getResult().getId();

        given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .header("Token", System.getenv("token"))
                .when()
                .delete(URL + "QASE/" + responseId)
                .then()
                .log().body()
                .statusCode(200);

        given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .header("Token", System.getenv("token"))
                .when()
                .get(URL + "QASE/" + responseId)
                .then()
                .log().body()
                .statusCode(404)
                .body("errorMessage", equalTo("Suite not found"));
    }
}
