package adapters;

import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import models.TestPlan;
import models.TestPlanResult;

@Log4j2
public class TestPlanAdapter extends BaseAdapter{
    private static final String API_PATH = "/v1/plan";

    public TestPlanResult get(String projectName, int planId, int statusCode){
        Response response = super.get(String.format("%s/%s/%s",API_PATH, projectName, planId),statusCode);
        validateTrueStatus(response);
        return gson.fromJson(response.asString(), TestPlanResult.class);
    }

    public int post(String projectName, TestPlan testPlan) {
        Response response = super.post(String.format("%s/%s", API_PATH, projectName), gson.toJson(testPlan), 200);
        validateTrueStatus(response);
        log.info(response.jsonPath());
        return response.jsonPath().getInt("result.id");
    }
}
