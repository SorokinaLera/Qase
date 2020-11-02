package tests.api;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import tests.api.models.TestProjectAPI;
import org.apache.http.protocol.HTTP;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.api.models.ProjectAPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static io.restassured.RestAssured.given;

@Log4j2
public class ProjectAPITest {
    private static final String URL = "https://api.qase.io/v1/project";
    Faker faker = new Faker();

    @Test
    public void getProjectByName() throws FileNotFoundException {
        Response response = given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .header("Token", System.getenv("token"))
                .when()
                .get(URL + "/QASE")
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        ProjectAPI responseFromJson = gson.fromJson(new FileReader(new File("src/test/resources/qase.json")), ProjectAPI.class);
        ProjectAPI ourResponse = gson.fromJson(response.body().asString(), ProjectAPI.class);
        Assert.assertEquals(responseFromJson, ourResponse, "Objects are not equivalent");
    }

    @Test
    public void createNewProject() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        TestProjectAPI newTestProject = new TestProjectAPI(faker.overwatch().hero(), faker.cat().name());
        String newProject = gson.toJson(newTestProject);

        Response response = given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .header("Token", System.getenv("token"))
                .body(newProject)
                .when()
                .log().body()
                .post(URL)
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();
        ProjectAPI responseToObject = gson.fromJson(response.body().asString(), ProjectAPI.class);
        String responseCode = responseToObject.getResult().getCode();

        Response newResponse = given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .header("Token", System.getenv("token"))
                .when()
                .get(URL + "/" + responseCode)
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();
        ProjectAPI newResponseToObject = gson.fromJson(newResponse.body().asString(), ProjectAPI.class);

        Assert.assertEquals(newTestProject, newResponseToObject.getResult(), "The project was created incorrectly");
    }
}
