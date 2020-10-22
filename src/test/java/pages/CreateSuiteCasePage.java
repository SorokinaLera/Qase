package pages;

import elements.TextArea;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@Log4j2

public class CreateSuiteCasePage extends BasePage {
    //TODO напомню в JAVA у переменных camelCase, а не Snake_Case
    public static final By Trash_Bin_Button = By.xpath("//*[@class='fa fa-trash']");
    public static final By Suite_name_field = By.id("inputTitle");
    public static final By Parent_suite_field = By.cssSelector(".filter-option.pull-left");
    public static final By Add_Suite_button = By.cssSelector(".btn.mr-3.btn-primary");
    public static final By Save_button = By.id("saveButton");
    public static final By Delete_Suite_Button = By.xpath("//*[contains(text(),'Delete suite')]");


    public CreateSuiteCasePage(WebDriver driver) {
        super(driver);
    }

    @Step("Validation that the web page is opened")
    public CreateSuiteCasePage isPageOpened() {
        return this;
    }

    @Step("Open web page")
    public CreateSuiteCasePage openPage() {
        return this;
    }

    @Step("Create Suite case")
    public CreateSuiteCasePage createNewSuite(String name) {
        driver.findElement(Add_Suite_button).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Save_button));
        driver.findElement(Suite_name_field).sendKeys(name);
        //driver.findElement(Parent_suite_field).submit(); //TODO какой к черту сабмит
        new TextArea(driver, "Preconditions").write("important"); //TODO что за хардкод данных внутри метода
        new TextArea(driver, "Description").write("important");
        driver.findElement(Save_button).click();
        return this;
    }

    @Step("Delete Suite case")
    public CreateSuiteCasePage deleteSuite(String name){
        List<WebElement> trash = driver.findElements(By.cssSelector(".suite-block-header"));
        for (WebElement element: trash) {
            String testSuiteName = element.getText();
            log.info("Test suite: " + testSuiteName);
            if (testSuiteName.equals(name)) {
                driver.findElement(Trash_Bin_Button).click();
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Delete_Suite_Button));
                driver.findElement(Delete_Suite_Button).click();
                log.info(String.format("Test suite '%s' was deleted", testSuiteName));
            }
        }
        return this;
    }

}
