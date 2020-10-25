package pages;

import elements.Input;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.TestPlans;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@Log4j2

public class TestPlanPage extends BasePage {
    public static String URL = "plan/";
    public static final By TEST_PLANS_PAGE_TITLE = By.xpath("//*[@id='react-app']//h1[contains(text(),'Test plans')]");
    public static final By CREATE_TEST_PLAN_BUTTON = By.xpath("//*[contains(@class, 'btn btn btn-primary') and contains(text(),'Create')]");
    public static final By ADD_CASES_BUTTON = By.xpath("//*[contains(@class, 'btn btn-invisible b-0 ml-0 mr-3') and contains(text(),' Add cases')]//i[@class='fa fa-plus-circle']");
    public static final By SELECT_TEST_CASES_TITLE = By.xpath("//*[contains(text(),'Select test cases')]");
    public static final By SELECT_TEST_CASES_CHECKBOX = By.xpath("//*[@class='custom-control custom-checkbox']/input[1]");
    public static final By CREATE_PLAN_BUTTON = By.xpath("//*[contains(text(),'Create plan')]");
    public static final By DONE_BUTTON = By.xpath("//*[contains(@class, 'btn btn-primary') and contains(text(), 'Done')]");
    public static final By LIST_OF_TEST_PLANS = By.xpath("//*[@class='project-row']//parent::a[@class='defect-title']");


    public TestPlanPage(WebDriver driver) {
        super(driver);
    }

    @Step("Validation that the test plan is opened")
    public TestPlanPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(TEST_PLANS_PAGE_TITLE));
        return this;
    }

    @Step("Open page with testPlan page project")
    public TestPlanPage openPage(String projectName) {
        driver.get(URN + URL + projectName);
        isPageOpened();
        return this;
    }

    public TestPlanPage refreshPage() {
        driver.navigate().refresh();
        return this;
    }

    public TestPlanPage clickOnNewTestPlanCreatingButton() {
        driver.findElement(CREATE_TEST_PLAN_BUTTON).click();
        return this;
    }

    public TestPlanPage addTestPlanParameters(TestPlans testPlanName) {
        new Input(driver, "Title").write(testPlanName.getTestPlanTitle());
        driver.findElement(ADD_CASES_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(SELECT_TEST_CASES_TITLE));
        WebElement icon = driver.findElement(SELECT_TEST_CASES_CHECKBOX);
        Actions ob = new Actions(driver);
        ob.click(icon);
        Action action = ob.build();
        action.perform();
        driver.findElement(DONE_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(CREATE_PLAN_BUTTON));
        driver.findElement(CREATE_PLAN_BUTTON).click();
        return this;
    }

    public TestPlanPage validateThatTestPlanIsCreated(TestPlans testPlanName) {
        List<WebElement> list = driver.findElements(LIST_OF_TEST_PLANS);
        for (WebElement element : list) {
            String testSuiteName = element.getText();
            log.info("Test suite: " + testSuiteName);
            if (testSuiteName.equals(testPlanName.getTestPlanTitle())) {
                log.info(String.format("Test suite '%s' is created", testPlanName.getTestPlanTitle()));
            }
        }
        return this;
    }
}
