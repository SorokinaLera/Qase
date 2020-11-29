package adapters;

import io.restassured.response.Response;
import models.TestPlanResult;

public class TestPlanAdapter extends BaseAdapter{
    private static final String API_PATH = "/v1/plan";

    public TestPlanResult get(String projectName, int planId, int statusCode){
        Response response = super.get(String.format("%s/%s/%s",API_PATH, projectName, planId),statusCode);
        validateTrueStatus(response);
        return gson.fromJson(response.asString(), TestPlanResult.class);
    }
}
