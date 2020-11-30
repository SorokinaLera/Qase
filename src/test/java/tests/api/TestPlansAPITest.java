package tests.api;

import adapters.TestPlanAdapter;
import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import models.TestPlan;
import models.TestPlanResult;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Log4j2
public class TestPlansAPITest {
    TestPlanAdapter testPlanAdapter = new TestPlanAdapter();
    Faker faker = new Faker();

    @Test
    public void getTestPlanById() {
        TestPlan expectedResult = TestPlan.builder()
                .testPlanTitle("Ambassador")
                .description("To save one from a mistake is a gift of paradise.")
                .build();

        TestPlanResult result = testPlanAdapter
                .get("QASE", 583, 200);
        assertEquals(result.getResult(), expectedResult);
    }

    @Test
    public void createNewTestPlan() {
        TestPlan newTestPlan = TestPlan.builder()
                .testPlanTitle(faker.rickAndMorty().character())
                .description(faker.rickAndMorty().quote())
                .cases(new int[]{4})
                .build();

        int idOfTheCreatedTestPlan = testPlanAdapter
                .post("QASE", newTestPlan);
        TestPlanResult result = testPlanAdapter
                .get("QASE", idOfTheCreatedTestPlan, 200);
        assertEquals(result.getResult(), newTestPlan);
    }
}
