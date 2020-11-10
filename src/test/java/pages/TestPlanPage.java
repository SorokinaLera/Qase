package pages;

import elements.Input;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.TestPlan;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

@Log4j2
public class TestPlanPage extends BasePage {
    public static String URL = "plan/";
    public static String testPlanNameForSearching = "//*[(@class= 'defect-title') and contains(text(),'%s')]";
    public static final By TEST_PLANS_PAGE_TITLE = By.xpath("//*[@id='react-app']//h1[contains(text(),'Test plans')]");
    public static final By CREATE_TEST_PLAN_BUTTON = By.id("createButton");
    public static final By ADD_CASES_BUTTON = By.cssSelector(".fa-plus-circle");
    public static final By SELECT_TEST_CASES_TITLE = By.xpath("//*[contains(text(),'Select test cases')]");
    public static final By SELECT_TEST_CASES_CHECKBOX = By.cssSelector(".custom-checkbox");
    public static final By CREATE_PLAN_BUTTON = By.xpath("//*[contains(text(),'Create plan')]");
    public static final By DONE_BUTTON = By.xpath("//*[contains(text(), 'Done')]");
    public static final By LIST_OF_TEST_PLANS = By.cssSelector(".defect-title");
    public static final By TOGGLE_DELETE = By.xpath("//*[contains(text(),'Delete')]//ancestor::tr[@class='project-row']//a[@data-toggle='dropdown']/i");
    public static final By DELETE_TEST_PLAN = By.xpath("//*[contains(text(),'Delete')]//ancestor::a[@class='text-danger']");
    public static final By X_ON_DELETE_TEST_PLAN_BUTTON = By.xpath("//*[@type='submit']");
    public static final By DESCRIPTION_FIELD = By.cssSelector(".empty-node");
    public static final By EDIT_PAGE_DESCRIPTION_FIELD = By.xpath("//*[@id='undefinedGroup']//*[@class = 'ProseMirror']");

    public TestPlanPage(WebDriver driver) {
        super(driver);
    }

    @Step("Validation that the Test Plan is opened")
    public TestPlanPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(TEST_PLANS_PAGE_TITLE));
        return this;
    }

    @Step("Open Test Plan Page")
    public TestPlanPage openPage(String projectName) {
        driver.get(URN + URL + projectName);
        isPageOpened();
        return this;
    }

    public TestPlanPage refreshPage() {
        driver.navigate().refresh();
        return this;
    }

    @Step("Click on \"Create plan\"")
    public TestPlanPage clickOnNewTestPlanCreatingButton() {
        driver.findElement(CREATE_TEST_PLAN_BUTTON).click();
        return this;
    }

    @Step("Setting parameters for a new test plan")
    public TestPlanPage addTestPlanParameters(TestPlan testPlanName) {
        new Input(driver, "Title").write(testPlanName.getTestPlanTitle().replace("\'",""));
        log.info("Enter the title: " + testPlanName.getTestPlanTitle());
        driver.findElement(DESCRIPTION_FIELD).sendKeys(testPlanName.getDescription());
        log.info("Enter the description: " + testPlanName.getDescription());
        driver.findElement(ADD_CASES_BUTTON).click();
        log.info("Click on \"Add cases\" button");
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(SELECT_TEST_CASES_TITLE));
        WebElement saveButton = driver.findElement(SELECT_TEST_CASES_CHECKBOX);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", saveButton);
        driver.findElement(DONE_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(CREATE_PLAN_BUTTON));
        driver.findElement(CREATE_PLAN_BUTTON).click();
        log.info(String.format("New plan is: %s", testPlanName.getTestPlanTitle()));
        return this;
    }

    @Step("Validation that the test plan \"{testPlanName}\" is created")
    public boolean validateThatTestPlanIsCreated(String testPlanName) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(LIST_OF_TEST_PLANS));
        boolean condition = false;
        for (int i = 0; i <= driver.findElements(LIST_OF_TEST_PLANS).size(); i++) {
            String planName = driver.findElement(LIST_OF_TEST_PLANS).getText();
            if (planName.equals(testPlanName)) {
                condition = true;
                log.info("Test run " + testPlanName +" is created");
                break;
            }
        }
        return condition;
    }

    @Step("Delete test plan \"{testPlan}\"")
    public TestPlanPage deleteTestPlan(String testPlan) {
        List<WebElement> list = driver.findElements(LIST_OF_TEST_PLANS);
        for (WebElement element : list) {
            String testPlanName = element.getText();
            log.info("Test plan: " + testPlanName);
            if (testPlan.equals(testPlanName)) {
                log.info(String.format("Test plan \"testPlan\" found", testPlan));
                driver.findElement(TOGGLE_DELETE).click();
                driver.findElement(DELETE_TEST_PLAN).click();
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(X_ON_DELETE_TEST_PLAN_BUTTON));
                driver.findElement(X_ON_DELETE_TEST_PLAN_BUTTON).click();
                break;
            }
        }
        return this;
    }

    @Step("Validation that the test plan \"{testPlan}\" does not exist anymore")
    public TestPlanPage validateThatTestPlanDoesNotExist(String testPlan) {
        List<WebElement> list = driver.findElements(LIST_OF_TEST_PLANS);
        int count = 0;
        for (WebElement element : list) {
            String testPlanName = element.getText();
            if (testPlan.equals(testPlanName)) {
                log.error(String.format("Test plan '%s' still exists", testPlanName));
                count++;
            }
        }
        Assert.assertEquals(count, 0);
        return this;
    }

    @Step("Validation that information in the test plan \"{name}\" is correct")
    public TestPlanPage checkDataInTheCreatedTestPlan(String name, String description) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(LIST_OF_TEST_PLANS));
        driver.findElement(By.xpath(String.format(testPlanNameForSearching, name))).click();
        log.info("Opening the test plan \"" + name + "\"");
        String planDescription = driver.findElement(EDIT_PAGE_DESCRIPTION_FIELD).getText();
        log.info(String.format("We see \"%s\" ", planDescription));
        Assert.assertEquals(planDescription, description);
        return this;
    }
}
