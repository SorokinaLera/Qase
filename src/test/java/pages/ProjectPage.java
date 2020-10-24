package pages;

import elements.Input;
import elements.TextArea;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.TestSuite;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static tests.BaseTest.URN;

@Log4j2
public class ProjectPage extends BasePage {
    public static String URL = "project/";
    public static String PROJECT_NAME = ".subheader";
    public static final By TEST_REPOSITORY_PAGE_TITLE = By.xpath("//*[contains(text(),'Test repository')]");
    public static final By TRASH_BIN_BUTTON = By.xpath("//*[@class='fa fa-trash']");
    public static final By ADD_SUITE_BUTTON = By.cssSelector(".btn.mr-3.btn-primary");
    public static final By SAVE_BUTTON = By.id("saveButton");
    public static final By DELETE_SUITE_BUTTON = By.xpath("//*[contains(text(),'Delete suite')]");
    public static final By TEST_SUITE_NAME_TITLE = By.cssSelector(".suite-header");

    public ProjectPage(WebDriver driver) {
        super(driver);
    }

    @Step("Validation that the project is opened")
    public ProjectPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(TEST_REPOSITORY_PAGE_TITLE));
        return this;
    }

    @Step("Open page with {projectName} project")
    public ProjectPage openPage(String projectName) {
        driver.get(URN + URL + projectName);
        isPageOpened();
        return this;
    }

    public ProjectPage validateThatProjectIsOpened(String projectName) {
        assertEquals(driver.findElement(By.cssSelector(PROJECT_NAME)).getText(), projectName.toUpperCase());
        return this;
    }

    public ProjectPage refreshPage() {
        driver.navigate().refresh();
        return this;
    }

    @Step("Click button to create test suite")
    public ProjectPage clickOnNewSuiteCreatingButton(TestSuite testSuite) {
        driver.findElement(ADD_SUITE_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(SAVE_BUTTON));
        return this;
    }

    @Step("Setting parameters for a new test suite")
    public ProjectPage addTestSiteParameters(TestSuite testSuite) {
        new Input(driver, "Suite name").write(testSuite.getSuiteName());
        new TextArea(driver, "Preconditions").write(testSuite.getPreconditions());
        new TextArea(driver, "Description").write(testSuite.getDescription());
        return this;
    }

    @Step("Click on 'Save' button")
    public ProjectPage clickOnSaveButton() {
        driver.findElement(SAVE_BUTTON).click();
        return this;
    }

    @Step("Validation that new suite is created")
    public boolean validateThatNewSuiteIsCreated(String testSuite) {
        boolean condition = false;
        for (int i = 0; i <= driver.findElements(TEST_SUITE_NAME_TITLE).size(); i++) {
            String testSuiteName = driver.findElement(TEST_SUITE_NAME_TITLE).getText();
            if (testSuiteName.equals(testSuite)) {
                condition = true;
            }
        }
        return condition;
    }

    @Step("Delete test suite")
    public ProjectPage deleteSuite(String name) {
        List<WebElement> trash = driver.findElements(TEST_SUITE_NAME_TITLE);
        for (WebElement element : trash) {
            String testSuiteName = element.getText();
            log.info("Test suite: " + testSuiteName);
            if (testSuiteName.equals(name)) {
                WebElement trashBinIconElement = driver.findElement(TRASH_BIN_BUTTON);
                Actions actions = new Actions(driver);
                actions.moveToElement(trashBinIconElement).click().perform();
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(DELETE_SUITE_BUTTON));
                driver.findElement(DELETE_SUITE_BUTTON).click();
                log.info(String.format("Test suite '%s' was deleted", testSuiteName));
            }
        }
        return this;
    }

    @Step("Validation that the suite does not exist anymore")
    public ProjectPage validateThatSuiteDoesNotExist(String testSuite) {

        List<WebElement> trash = driver.findElements(TEST_SUITE_NAME_TITLE);
        int count = 0;
        for (WebElement element : trash) {
            String testSuiteName = element.getText();
            log.info("Test suite: " + testSuiteName);
            if (testSuiteName.equals(testSuite)) {
                log.error(String.format("Test suite '%s' still exists", testSuite));
                count++;
            }
        }
        Assert.assertEquals(count, 0);
        return this;
    }
}
