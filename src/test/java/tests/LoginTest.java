package tests;

import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import utils.Retry;

@Log4j2
public class LoginTest extends BaseTest {

    @Description("Login using correct data")
    @Test(retryAnalyzer = Retry.class)
    public void loginUsingCorrectData() {
        loginPage
                .openPage()
                .login(CORRECT_EMAIL, CORRECT_PASSWORD)
                .isPageOpened();
    }

}
