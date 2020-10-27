package pages;

import elements.Input;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.TestPlan;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

@Log4j2

public class TestPlanPage extends BasePage {
    public static String URL = "plan/";
    public static String testPlanName = "//*[(@class= 'defect-title') and contains(text(),'%s')]";
    public static final By TEST_PLANS_PAGE_TITLE = By.xpath("//*[@id='react-app']//h1[contains(text(),'Test plans')]");
    public static final By CREATE_TEST_PLAN_BUTTON = By.xpath("//*[contains(@class, 'btn btn btn-primary') and contains(text(),'Create')]");
    public static final By ADD_CASES_BUTTON = By.xpath("//*[contains(@class, 'btn btn-invisible b-0 ml-0 mr-3') and contains(text(),' Add cases')]//i[@class='fa fa-plus-circle']");
    public static final By SELECT_TEST_CASES_TITLE = By.xpath("//*[contains(text(),'Select test cases')]");
    public static final By SELECT_TEST_CASES_CHECKBOX = By.xpath("//*[@class='custom-control custom-checkbox']/input[1]");
    public static final By CREATE_PLAN_BUTTON = By.xpath("//*[contains(text(),'Create plan')]");
    public static final By DONE_BUTTON = By.xpath("//*[contains(@class, 'btn btn-primary') and contains(text(), 'Done')]");
    public static final By LIST_OF_TEST_PLANS = By.xpath("//*[@class='project-row']//parent::a[@class='defect-title']");
    public static final By TOGGLE_DELETE = By.xpath("//*[contains(text(),'Delete')]//ancestor::tr[@class='project-row']//a[@data-toggle='dropdown']/i");
    public static final By DELETE_TEST_PLAN = By.xpath("//*[contains(text(),'Delete')]//ancestor::a[@class='text-danger']");
    public static final By X_ON_DELETE_TEST_PLAN_BUTTON = By.cssSelector(".fa.fa-times-circle");
    public static final By DESCRIPTION_FIELD = By.xpath("//*[@id='undefinedGroup']/parent::div//*[contains(@class, 'empty-node')]");
    public static final By EDIT_PAGE_DESCRIPTION_FIELD = By.xpath("//*[@id='undefinedGroup']/parent::div//*[contains(@class, 'ProseMirror')]");



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

    @Step("")
    public TestPlanPage refreshPage() {
        driver.navigate().refresh();
        return this;
    }

    @Step("Click button to create test plan")
    public TestPlanPage clickOnNewTestPlanCreatingButton() {
        driver.findElement(CREATE_TEST_PLAN_BUTTON).click();
        return this;
    }

    @Step("Setting parameters for a new test plan")
    public TestPlanPage addTestPlanParameters(TestPlan testPlanName) {
        new Input(driver, "Title").write(testPlanName.getTestPlanTitle());
        driver.findElement(DESCRIPTION_FIELD).sendKeys(testPlanName.getDescription());
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

    @Step("Validation that new test plan is created")
    public TestPlanPage validateThatTestPlanIsCreated(TestPlan testPlan) {
        List<WebElement> list = driver.findElements(LIST_OF_TEST_PLANS);
        for (WebElement element : list) {
            String testPlanName = element.getText();
            log.info("Test plan: " + testPlanName);
            if (testPlan.equals(testPlanName)) {
                log.info(String.format("Test plan '%s' is created", testPlan));
            }
        }
        return this;
    }

    @Step("Delete test plan {testPlan}")
    public TestPlanPage deleteTestPlan(String testPlan) {
        List<WebElement> list = driver.findElements(LIST_OF_TEST_PLANS);
        for (WebElement element : list) {
            String testPlanName = element.getText();
            log.info("Test plan: " + testPlanName);
            if (testPlan.equals(testPlanName)) {
                driver.findElement(TOGGLE_DELETE).click();
                driver.findElement(DELETE_TEST_PLAN).click();
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(X_ON_DELETE_TEST_PLAN_BUTTON));
                driver.findElement(X_ON_DELETE_TEST_PLAN_BUTTON).click();
                break;
            }
        }
        return this;
    }

    @Step("Validation that the test plan {testPlan} does not exist anymore")
    public TestPlanPage validateThatTestPlanDoesNotExist(TestPlan testPlan) {
        List<WebElement> list = driver.findElements(LIST_OF_TEST_PLANS);
        int count = 0;
        for (WebElement element : list) {
            String testPlanName = element.getText();
            log.info("Test Plan: " + testPlanName);
            if (testPlan.equals(testPlanName)) {
                log.error(String.format("Test plan '%s' still exists", testPlanName));
                count++;
            }
        }
        Assert.assertEquals(count, 0);
        return this;
    }

    public TestPlanPage checkDataInTheCreatedTestPlan(String name, TestPlan testPlan) {
        driver.findElement(By.xpath(String.format(testPlanName, name))).click();
        String planDescription = driver.findElement(EDIT_PAGE_DESCRIPTION_FIELD).getText();
        String objectDescription = testPlan.getDescription();
        Assert.assertEquals(planDescription, objectDescription);
        return this;
    }
}
