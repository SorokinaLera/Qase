package adapters;

import io.restassured.response.Response;
import models.api.CaseResult;

public class CaseAdapter extends BaseAdapter {
    private static final String API_PATH = "/v1/case/";

    public CaseResult get(String projectName, int caseId, int statusCode) {
        Response response = super.get(String.format("%s%s/%s", API_PATH, projectName, caseId), statusCode);
        if (statusCode == 200) {
            validateTrueStatus(response);
        } else {
            validateFalseStatus(response);
        }
        return gson.fromJson(response.asString(), CaseResult.class);
    }
}
