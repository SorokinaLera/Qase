package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TextArea {
    WebDriver driver;
    String label;
    String locator = "//label[text()='%s']/ancestor::div[contains(@class,'form-group')]//ancestor::div[contains(@class,'CodeMirror')]";


    public TextArea(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void write(String text) {
        WebElement codeMirror = driver.findElement(By.xpath(String.format(locator, label)));
        WebElement codeLine = codeMirror.findElements(By.className("CodeMirror-line")).get(0);
        codeLine.click();
        WebElement txtbx = codeMirror.findElement(By.cssSelector("textarea"));
        txtbx.sendKeys(text);
    }
}
