package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Select {
    WebDriver driver;
    String label;
    String labelLocator = "//*[text()='%s']/parent::div//div[contains(@class, 'container')]";
    String optionLocator = "//*[contains(@id, 'react-select') and contains(text(),'%s')]";

    public Select(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void select(String option) {
        driver.findElement(By.xpath(String.format(labelLocator, label))).click();
        driver.findElement(By.xpath(String.format(optionLocator, option))).click();
    }
}
