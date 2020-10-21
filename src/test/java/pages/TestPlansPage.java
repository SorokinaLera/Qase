package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class TestPlansPage extends BasePage{

    public TestPlansPage(WebDriver driver){
        super(driver);
    }

    @Step("Validation that the web page is opened")
    public TestPlansPage isPageOpened() {
        return this;
    }

    @Step("Open web page")
    public TestPlansPage openPage() {
        return this;
    }
}
