package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProjectPage extends BasePage{
    public static String URL = "https://app.qase.io/project/";
    public static final By TEST_REPOSITORY_PAGE_TITLE = By.xpath("//*[contains(text(),'Test repository')]");

    public ProjectPage (WebDriver driver){
        super(driver);
    }

    @Step("Validation that the web page is opened")
    public ProjectPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(TEST_REPOSITORY_PAGE_TITLE));
        return this;
    }

    @Step("Open web page")
    public ProjectPage openPage() {
        driver.get(URL + "DEMO");
        isPageOpened();
        return this;
    }
}
