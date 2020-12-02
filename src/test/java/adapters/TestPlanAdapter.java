package adapters;

import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import models.TestPlanRequest;
import models.TestPlanResponseResult;

@Log4j2
public class TestPlanAdapter extends BaseAdapter{
    private static final String API_PATH = "/v1/plan";

    public TestPlanResponseResult get(String projectName, int planId, int statusCode){
        Response response = super.get(String.format("%s/%s/%s",API_PATH, projectName, planId),statusCode);
        validateTrueStatus(response);
        return gson.fromJson(response.asString(), TestPlanResponseResult.class);
    }

    public int post(String projectName, TestPlanRequest testPlanRequest) {
        Response response = super.post(String.format("%s/%s", API_PATH, projectName), gson.toJson(testPlanRequest), 200);
        validateTrueStatus(response);
        return response.jsonPath().getInt("result.id");
    }

    public TestPlanResponseResult delete(String projectName, int planId, int statusCode){
        Response response = super.delete(String.format("%s/%s/%s",API_PATH, projectName, planId), statusCode);
        validateTrueStatus(response);
        return gson.fromJson(response.asString(),TestPlanResponseResult.class);
    }
}
