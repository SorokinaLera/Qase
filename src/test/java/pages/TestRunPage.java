package pages;

import elements.Input;
import elements.PlanSelect;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.TestRun;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.List;

import static pages.TestPlanPage.*;

@Log4j2

public class TestRunPage extends BasePage {
    public static String URL = "run/";
    public static String testRunName = "//*[(@class= 'defect-title') and contains(text(),'%s')]";
    public static final By TEST_RUN_PAGE_TITLE = By.xpath("//*[@id='react-app']//h1[contains(text(),'Test runs')]");
    public static final By START_TEST_RUN_BUTTON = By.xpath("//*[contains(@class, 'btn btn-primary') and contains(text(),'Start new test run')]");
    public static final By START_RUN_BUTTON = By.xpath("//*[contains(@class, 'btn btn-primary') and contains(text(),'Start run')]");
    public static final By DESCRIPTION_FIELD = By.xpath("//*[@id='undefinedGroup']/parent::div//*[contains(@class, 'empty-node')]");
    public static final By LIST_OF_TEST_RUNS = By.cssSelector(".defect-title");
    public static final By DELETE_TEST_RUN = By.xpath("//*[contains(text(),'Delete')]//ancestor::a[@class='text-danger']");
    public static final By X_ON_DELETE_TEST_RUN_BUTTON = By.cssSelector(".fa.fa-times-circle");
    public static final By RUN_TITLE_FIELD = By.xpath("//*[@id='title']");
    public static final By TEST_RUN_DESCRIPTION = By.cssSelector(".run-description");



    public TestRunPage(WebDriver driver) {
        super(driver);
    }

    @Step("Validation that the web page is opened")
    public TestRunPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(TEST_RUN_PAGE_TITLE));
        return this;
    }

    @Step("Open page with testPlan page project")
    public TestRunPage openPage(String projectName) {
        driver.get(URN + URL + projectName);
        isPageOpened();
        return this;
    }

    public TestRunPage refreshPage() {
        driver.navigate().refresh();
        return this;
    }

    @Step("Click button to create test run")
    public TestRunPage clickOnStartTestRunCreatingButton() {
        driver.findElement(START_TEST_RUN_BUTTON).click();
        return this;
    }

    @Step("Setting parameters for a new test run")
    public TestRunPage addTestRunParameters(TestRun testRun) {
        driver.findElement(RUN_TITLE_FIELD).clear();
        new Input(driver, "Run title").write(testRun.getTestRunTitle());
        wait = new WebDriverWait(driver, 5);
        driver.findElement(DESCRIPTION_FIELD).sendKeys(testRun.getDescription());
        new PlanSelect(driver, "Plan").select(testRun.getPlan());
        new PlanSelect(driver, "Environment").select(testRun.getEnvironment());
        new PlanSelect(driver, "Milestone").select(testRun.getMilestone());
        driver.findElement(START_RUN_BUTTON).click();
        return this;
    }

    @Step("Validation that new test run is created")
    public TestRunPage validateThatTestRunIsCreated(TestRun testRun) {
        List<WebElement> list = driver.findElements(LIST_OF_TEST_RUNS);
        for (WebElement element : list) {
            String testRunName = element.getText();
            log.info("Test run: " + testRunName);
            if (testRun.equals(testRunName)) {
                log.info(String.format("Test run '%s' is created", testRun));
            }
        }
        return this;
    }

    @Step("Delete test run {testRun}")
    public TestRunPage deleteTestRun(String testRun) {
        List<WebElement> list = driver.findElements(LIST_OF_TEST_RUNS);
        for (WebElement element : list) {
            String testRunName = element.getText();
            log.info("Test run: " + testRunName);
            if (testRun.equals(testRunName)) {
                driver.findElement(TOGGLE_DELETE).click();
                driver.findElement(DELETE_TEST_RUN).click();
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(X_ON_DELETE_TEST_RUN_BUTTON));
                driver.findElement(X_ON_DELETE_TEST_PLAN_BUTTON).click();
                break;
            }
        }
        return this;
    }

    @Step("Validation that the test run {testRun} does not exist anymore")
    public TestRunPage validateThatTestRunDoesNotExist(String testRun) {
        List<WebElement> list = driver.findElements(LIST_OF_TEST_RUNS);
        int count = 0;
        for (WebElement element : list) {
            String testRunName = element.getText();
            log.info("Test Plan: " + testRunName);
            if (testRun.equals(testRunName)) {
                log.error(String.format("Test run '%s' still exists", testRunName));
                count++;
            }
        }
        Assert.assertEquals(count, 0);
        return this;
    }

    public TestRunPage deleteAll() {
        List <WebElement> list = driver.findElements(LIST_OF_TEST_RUNS);
        if (list.size() != 0) {
            for (WebElement element : list) {
                String testRunName = element.getText();
                log.info("Test run: " + testRunName);
                driver.findElement(TOGGLE_DELETE).click();
                driver.findElement(DELETE_TEST_RUN).click();
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(X_ON_DELETE_TEST_RUN_BUTTON));
                driver.findElement(X_ON_DELETE_TEST_PLAN_BUTTON).click();
                refreshPage();
                break;
            }
        } else {
            return this;
        }
        deleteAll();
        return this;
    }

    public TestRunPage checkDataInTheCreatedTestRun(String name, TestRun testRun) {
        driver.findElement(By.xpath(String.format(testRunName, name))).click();
        String runDescription = driver.findElement(TEST_RUN_DESCRIPTION).getText();
        String objectDescription = testRun.getDescription();
        Assert.assertEquals(runDescription, objectDescription);
        return this;
    }
}