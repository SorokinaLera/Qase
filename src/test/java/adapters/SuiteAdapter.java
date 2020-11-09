package adapters;

import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import models.api.Result;
import models.TestSuite;

@Log4j2
public class SuiteAdapter extends BaseAdapter {
    private static final String API_PATH = "/v1/suite/";

    public Result get(String projectName, int suiteId, int statusCode) {
        Response response = super.get(String.format("%s/%s/%s", API_PATH, projectName, suiteId), statusCode);
        if (statusCode == 200) {
            validateTrueStatus(response);
        } else {
            validateFalseStatus(response);
        }
        return gson.fromJson(response.asString(), Result.class);
    }

    public int post(String projectName, TestSuite suite) {
        Response response = super.post(String.format("%s/%s", API_PATH, projectName), gson.toJson(suite), 200);
        validateTrueStatus(response);
        return response.jsonPath().getInt("result.id");
    }

    public int patch(String projectName, int suiteId, TestSuite suite) {
        Response response = super.patch(String.format("%s/%s/%s", API_PATH, projectName, suiteId), gson.toJson(suite), 200);
        validateTrueStatus(response);
        return response.jsonPath().getInt("result.id");
    }

    public Response delete(String projectName, int suiteId) {
        Response response = super.delete(String.format("%s/%s/%s", API_PATH, projectName, suiteId), 200);
        validateTrueStatus(response);
        return response;
    }
}
