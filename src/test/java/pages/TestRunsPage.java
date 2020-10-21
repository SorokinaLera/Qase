package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class TestRunsPage extends BasePage{

    public TestRunsPage(WebDriver driver){
        super(driver);
    }

    @Step("Validation that the web page is opened")
    public TestRunsPage isPageOpened() {
        return this;
    }

    @Step("Open web page")
    public TestRunsPage openPage() {
        return this;
    }
}
