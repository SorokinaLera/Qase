package tests.api;

import adapters.ProjectAdapter;
import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import models.Project;
import models.api.ProjectResult;
import org.testng.annotations.Test;
import utils.Retry;

import static org.testng.Assert.assertEquals;

@Log4j2
public class ProjectAPITest {
    ProjectAdapter projectAdapter = new ProjectAdapter();
    Faker faker = new Faker();

    @Test
    public void getProjectByName() {
        Project expectedResult = Project.builder()
                .name("Qase")
                .code("QASE")
                .build();

        ProjectResult result = projectAdapter
                .get("QASE");
        log.info(result.getResult());
        assertEquals(result.getResult(), expectedResult);
    }
}
