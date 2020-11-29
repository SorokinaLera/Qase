package tests.api;

import adapters.TestPlanAdapter;
import lombok.extern.log4j.Log4j2;
import models.TestPlan;
import models.TestPlanResult;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Log4j2
public class TestPlansAPITest {
    TestPlanAdapter testPlanAdapter = new TestPlanAdapter();

    @Test
    public void getTestPlanById() {
        TestPlan expectedResult = TestPlan.builder()
                .testPlanTitle("Ambassador")
                .description("To save one from a mistake is a gift of paradise.")
                .build();

        TestPlanResult result = testPlanAdapter
                .get("QASE", 583, 200);
        log.info(result.getResult());
        assertEquals(result.getResult(), expectedResult);
    }
}
