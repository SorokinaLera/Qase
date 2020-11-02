package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDefinitions extends BaseSteps {
    @Before
    public void setUp(){
        openBrowser();
    }

    @After()
    public  void tearDown(){
        closeBrowser();
    }

    @Given("User opens login page")
    public void userOpensLoginPage() {
        loginPage.openPage();
    }

    @When("Login using correct email and password")
    public void loginUsingCorrectEmailAndPassword() {
        loginPage.login(CORRECT_EMAIL, CORRECT_PASSWORD);
    }

    @Then("The project page is opened")
    public void projectPageIsOpened() {
        projectsPage.isPageOpened();
    }
}
