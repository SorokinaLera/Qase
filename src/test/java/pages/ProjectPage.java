package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class ProjectPage extends BasePage{

    public ProjectPage (WebDriver driver){
        super(driver);
    }

    @Step("Validation that the web page is opened")
    public ProjectPage isPageOpened() {
        return this;
    }

    @Step("Open web page")
    public ProjectPage openPage() {
        return this;
    }
}
