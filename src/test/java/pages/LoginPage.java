package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class LoginPage extends BasePage {
    public static String URL = "https://app.qase.io/login";
    public static final By EMAIL = By.id("inputEmail");
    public static final By PASSWORD = By.id("inputPassword");
    public static final By LOGIN_BUTTON = By.id("btnLogin");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(LOGIN_BUTTON));
        return this;
    }

    public LoginPage openPage() {
        driver.get(URL);
        isPageOpened();
        return this;
    }

    public ProjectsPage login(String email, String password){
        driver.findElement(EMAIL).sendKeys(email);
        driver.findElement(PASSWORD).sendKeys(password);
        log.info("Email: " + email + " Password: " + password);
        driver.findElement(LOGIN_BUTTON).click();
        return new ProjectsPage(driver);
    }

}
