package steps;

import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import pages.*;
import utils.CapabilitiesGenerator;
import utils.TestListener;

import java.util.concurrent.TimeUnit;

@Log4j2
@Listeners(TestListener.class)
public class BaseSteps {
    WebDriver driver;
    LoginPage loginPage;
    ProjectPage projectPage;
    ProjectsPage projectsPage;
    TestPlanPage testPlanPage;
    TestRunPage testRunPage;
    Faker faker;
    public final static String CORRECT_EMAIL = "arimelka@yandex.by";
    public final static String CORRECT_PASSWORD = "Q033008061zxcv";

    public void openBrowser() {

        try {
            driver = new ChromeDriver(CapabilitiesGenerator.getChromeOptions());
        } catch (SessionNotCreatedException ex) {
            Assert.fail("The browser wasn't opened. Make sure that you are using the correct driver version");
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        loginPage = new LoginPage(driver);
        projectPage = new ProjectPage(driver);
        projectsPage = new ProjectsPage(driver);
        testPlanPage = new TestPlanPage(driver);
        testRunPage = new TestRunPage(driver);
        faker = new Faker();
    }

    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
