package pages;

import org.openqa.selenium.WebDriver;

public class ProjectPage extends BasePage{

    public ProjectPage (WebDriver driver){
        super(driver);
    }

    public ProjectPage isPageOpened() {
        return this;
    }

    public ProjectPage openPage() {
        return this;
    }
}
