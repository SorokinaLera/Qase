package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PlanSelect {
    WebDriver driver;
    String label;
    String optionLocator = "//*[contains(@id, 'react-select') and contains(text(),'%s')]";
    String locator = "//*[text()='%s']/parent::div//input[@type = 'text']";


    public PlanSelect(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void select(String option) {
        driver.findElement(By.xpath(String.format(locator, label))).click();
        driver.findElement(By.xpath(String.format(optionLocator, option))).click();
    }
}
