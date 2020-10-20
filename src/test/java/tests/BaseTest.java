package tests;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.*;
import utils.CapabilitiesGenerator;
import utils.TestListener;

import java.util.concurrent.TimeUnit;

@Log4j2
@Listeners(TestListener.class)

public class BaseTest {
    public final static String EMAIL = System.getProperty("email");
    public final static String PASSWORD = System.getProperty("password");
    WebDriver driver;
    LoginPage loginPage;
    ProjectPage projectPage;
    ProjectsPage projectsPage;
    TestPlanPage testPlanPage;
    TestRunPage testRunPage;

    @BeforeMethod
    public void openBrowser(ITestContext context) {

        try {
            driver = new ChromeDriver(CapabilitiesGenerator.getChromeOptions());
        } catch (SessionNotCreatedException ex) {
            Assert.fail("Браузер не был открыт. Проверьте, что используется корректная версия драйвера");
        }
        String variable = "driver";
        log.info("Setting driver into context with variable name " + variable);
        context.setAttribute(variable, driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        loginPage = new LoginPage(driver);
        projectPage = new ProjectPage(driver);
        projectsPage = new ProjectsPage(driver);
        testPlanPage = new TestPlanPage(driver);
        testRunPage = new TestRunPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
    }
}
