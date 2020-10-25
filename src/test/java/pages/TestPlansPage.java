package pages;

import elements.Input;
import elements.TextArea;
import io.qameta.allure.Step;
import models.TestPlans;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class TestPlansPage extends BasePage{
    public static String URL = "plan/";
    public static final By TEST_PLANS_PAGE_TITLE = By.xpath("//*[@id='react-app']//h1[contains(text(),'Test plans')]");
    public static final By CREATE_TEST_PLAN_BUTTON = By.xpath("//*[contains(@class, 'btn btn btn-primary') and contains(text(),'Create test plan')]");
    public static final By CREATE_PLAN_BUTTON = By.xpath("//*[contains(@class, 'btn btn btn-primary') and contains(text(),'Create plan')]");
    public static final By ADD_CASES_BUTTON = By.xpath("//*[contains(@class, 'btn btn-invisible b-0 ml-0 mr-3') and contains(text(),' Add cases')]//i[@class='fa fa-plus-circle']");
    public static final By SELECT_TEST_CASES_TITLE = By.xpath("//*[contains(text(),'Select test cases')]");

    public TestPlansPage(WebDriver driver){
        super(driver);
    }

    @Step("Validation that the test plan is opened")
    public TestPlansPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(TEST_PLANS_PAGE_TITLE));
        return this;
    }

    @Step("Open page with testPlan page project")
    public TestPlansPage openPage(String projectName) {
        driver.get(URN + URL + projectName);
        isPageOpened();
        return this;
    }

    public TestPlansPage refreshPage(){
        driver.navigate().refresh();
        return this;
    }

    public TestPlansPage createTestPlan(){
        if(driver.findElement(CREATE_TEST_PLAN_BUTTON).isDisplayed()){
            driver.findElement(CREATE_TEST_PLAN_BUTTON).click();
        }
        else if(driver.findElement(CREATE_PLAN_BUTTON).isDisplayed()){
            driver.findElement(CREATE_PLAN_BUTTON).click();
        }
        return this;
    }

    public TestPlansPage addTestPlanParameters(TestPlans testPlanName){
        new Input(driver, "Test Plan name").write(testPlanName.getTestPlanTitle());
        new TextArea(driver, "Description").write(testPlanName.getDescription());
        driver.findElement(ADD_CASES_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(SELECT_TEST_CASES_TITLE));

        return this;
    }

    public TestPlansPage validateThatTestPlanIsCreated(){
        return this;
    }
}
