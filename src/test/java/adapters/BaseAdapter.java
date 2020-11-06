package adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.apache.http.protocol.HTTP;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Log4j2
public class BaseAdapter {
    private static final String URL = "https://api.qase.io";

    Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    Response response;

    @Step("Doing the get request to: '{request}', validating status code: '{statusCode}'")
    protected Response get(String request, int statusCode) {
        response = given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .header("Token", System.getenv("token"))
                .when()
                .get(String.format("%s%s", URL, request))
                .then()
                .log().body()
                .statusCode(statusCode)
                .extract().response();
        return response;
    }

    @Step("Doing the delete request to: '{request}', validating status code: '{statusCode}'")
    protected Response delete(String urn, int statusCode) {

        response = given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .header("Token", System.getenv("token"))
                .when()
                .delete(String.format("%s%s", URL, urn))
                .then()
                .log().body()
                .statusCode(statusCode)
                .extract().response();
        return response;
    }

    @Step("Doing the post request to: '{request}', sending the body: '{file}', validating status code: '{statusCode}'")
    protected Response post(String request, String body, int statusCode) {

        response = given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .header("Token", System.getenv("token"))
                .body(body)
                .when()
                .post(String.format("%s%s", URL, request))
                .then()
                .log().body()
                .statusCode(statusCode)
                .extract().response();
        return response;
    }

    @Step("Doing the patch request to: '{request}', sending the body: '{file}', validating status code: '{statusCode}'")
    protected Response patch(String uri, String body, int statusCode) {

        response = given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .header("Token", System.getenv("token"))
                .body(body)
                .when()
                .patch(String.format("%s%s", URL, uri))
                .then()
                .log().body()
                .statusCode(statusCode)
                .extract().response();
        return response;
    }

    @Step("Validating response via JsonPath: '{status}'")
    protected BaseAdapter validateTrueStatus(Response response) {
        assertTrue(response.jsonPath().getBoolean("status"));
        return this;
    }

    @Step("Validating response via JsonPath: '{status}'")
    protected BaseAdapter validateFalseStatus(Response response) {
        assertFalse(response.jsonPath().getBoolean("status"));
        return this;
    }
}
