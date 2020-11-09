package tests.api;

import adapters.SuiteAdapter;
import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import models.TestSuite;
import org.testng.annotations.Test;
import models.api.Result;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

@Log4j2
public class SuiteTest {
    SuiteAdapter suiteAdapter = new SuiteAdapter();
    Faker faker = new Faker();

    @Test
    public void getSuiteById() {
        TestSuite expectedResult = TestSuite.builder()
                .suiteName("New Suite666")
                .parent(null)
                .description(null)
                .preconditions(null)
                .build();

        Result result = suiteAdapter
                .get("DEMO", 11, 200);
        assertEquals(result.getResult(), expectedResult);
    }

    @Test
    public void createNewSuite() {
        TestSuite newTestSuite = TestSuite.builder()
                .suiteName(faker.howIMetYourMother().character())
                .parent(null)
                .description(faker.howIMetYourMother().quote())
                .preconditions(faker.howIMetYourMother().catchPhrase())
                .build();

        int idOfTheCreatedTestSuite = suiteAdapter
                .post("QASE", newTestSuite);
        Result result = suiteAdapter
                .get("QASE", idOfTheCreatedTestSuite, 200);
        assertEquals(result.getResult(), newTestSuite);
    }

    @Test
    public void updateSuit() {
        TestSuite newTestSuite = TestSuite.builder()
                .suiteName(faker.backToTheFuture().character())
                .parent(null)
                .description(faker.backToTheFuture().quote())
                .preconditions(faker.backToTheFuture().quote())
                .build();
        TestSuite updatedTestSuite = TestSuite.builder()
                .suiteName(newTestSuite.getSuiteName())
                .parent(null)
                .description(faker.chuckNorris().fact())
                .preconditions(faker.chuckNorris().fact())
                .build();

        int idOfTheCreatedTestSuite = suiteAdapter
                .post("QASE", newTestSuite);
        suiteAdapter
                .patch("QASE", idOfTheCreatedTestSuite, updatedTestSuite);
        Result result = suiteAdapter
                .get("QASE", idOfTheCreatedTestSuite, 200);
        assertEquals(result.getResult(), updatedTestSuite);
    }

    @Test
    public void deleteSuite() {
        TestSuite newTestSuite = TestSuite.builder()
                .suiteName(faker.hobbit().character())
                .parent(null)
                .description(faker.hobbit().quote())
                .preconditions(faker.hobbit().thorinsCompany())
                .build();

        int idOfTheCreatedTestSuite = suiteAdapter
                .post("QASE", newTestSuite);
        suiteAdapter
                .delete("QASE", idOfTheCreatedTestSuite);
        Result result = suiteAdapter
                .get("QASE", idOfTheCreatedTestSuite, 404);
        assertFalse(result.isStatus());

    }
}
