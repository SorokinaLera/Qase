package tests.api;

import adapters.TestPlanAdapter;
import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import models.TestPlanRequest;
import models.TestPlanResponseResult;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

@Log4j2
public class TestPlansAPITest {
    TestPlanAdapter testPlanAdapter = new TestPlanAdapter();
    Faker faker = new Faker();

    @Test
    public void getTestPlanById() {
        TestPlanRequest expectedResult = TestPlanRequest.builder()
                .testPlanTitle("Ambassador")
                .description("To save one from a mistake is a gift of paradise.")
                .build();

        TestPlanResponseResult result = testPlanAdapter
                .get("QASE", 583, 200);
        assertEquals(result.getResult().getTestPlanTitle(), expectedResult.getTestPlanTitle());
        assertEquals(result.getResult().getDescription(), expectedResult.getDescription());
    }

    @Test
    public void createNewTestPlan() {
        TestPlanRequest testPlanRequest = TestPlanRequest.builder()
                .testPlanTitle(faker.rickAndMorty().character())
                .description(faker.rickAndMorty().quote())
                .cases(new int[]{4, 7})
                .build();

        int idOfTheCreatedTestPlan = testPlanAdapter
                .post("QASE", testPlanRequest);
        TestPlanResponseResult result = testPlanAdapter
                .get("QASE", idOfTheCreatedTestPlan, 200);
        assertEquals(result.getResult().getDescription(), testPlanRequest.getDescription());
        assertEquals(result.getResult().getTestPlanTitle(), testPlanRequest.getTestPlanTitle());
        for (int i = 0; i < testPlanRequest.getCases().length; i++) {
            assertEquals(result.getResult().getCases().get(i).getCaseId(), (Integer) testPlanRequest.getCases()[i]);
        }
    }

    @Test
    public void deleteTestPlan() {
        TestPlanRequest testPlanRequest = TestPlanRequest.builder()
                .testPlanTitle(faker.rickAndMorty().character())
                .description(faker.rickAndMorty().quote())
                .cases(new int[]{4, 7})
                .build();

        int idOfTheCreatedTestPlan = testPlanAdapter
                .post("QASE", testPlanRequest);
        log.info(idOfTheCreatedTestPlan);
        TestPlanResponseResult result = testPlanAdapter
                .delete("QASE", idOfTheCreatedTestPlan, 200);
        assertFalse(result.isStatus());
    }
}
