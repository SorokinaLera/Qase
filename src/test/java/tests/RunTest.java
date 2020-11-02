package tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"classpath:features"},
        glue = "steps",
        plugin = { "pretty", "html:target/cucumber-html-reports",
                "json:target/cucumber-html-reports/cucumber.json"}
)
public class RunTest extends AbstractTestNGCucumberTests {
}
