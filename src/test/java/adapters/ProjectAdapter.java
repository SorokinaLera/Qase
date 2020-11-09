package adapters;

import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import models.Project;
import models.api.ProjectResult;

@Log4j2
public class ProjectAdapter extends BaseAdapter {
    private static final String API_PATH = "/v1/project/";

    public ProjectResult get(String projectName) {
        Response response = super.get(String.format("%s/%s", API_PATH, projectName), 200);
        validateTrueStatus(response);
        return gson.fromJson(response.asString(), ProjectResult.class);
    }

    public String post(Project project) {
        Response response = super.post(String.format("%s", API_PATH), gson.toJson(project), 200);
        validateTrueStatus(response);
        return response.jsonPath().getString("result.code");
    }

}
