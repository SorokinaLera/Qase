package tests.api;

import adapters.CaseAdapter;
import lombok.extern.log4j.Log4j2;
import models.TestCase;
import models.api.CaseResult;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Log4j2
public class CaseAPITest {
    CaseAdapter caseAdapter = new CaseAdapter();

    @Test
    public void getCaseById() {
        TestCase expectedResult = TestCase.builder()
                .caseName("XOXOXO")
                .id(7)
                .build();

        CaseResult result = caseAdapter
                .get("QASE", 7, 200);
        assertEquals(result.getResult(), expectedResult);
    }
}
