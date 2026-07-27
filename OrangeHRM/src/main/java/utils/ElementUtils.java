package utils;

import locators.EmployeeDetailsLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementUtils {
    private WebDriver driver;
    private WaitUtils wait;
    public ElementUtils(WebDriver driver){
        this.driver = driver;
        wait = new WaitUtils(driver);
    }

    public void enterText(By locator, String text){
        wait.waitUntilVisible(locator);
        driver.findElement(locator).sendKeys(text);
    }

    public void uploadFile(By locator, String path){
        wait.waitUntilPresence(locator);
        driver.findElement(locator).sendKeys(path);
    }

    public void click(By locator){
        wait.waitUntilVisible(locator);
        driver.findElement(locator).click();
    }

    public void navigateTo(String url){
        driver.get(url);
    }

    public Boolean isElementPresent(By locator){
        return !driver.findElements(locator).isEmpty();
    }

    public void selectDropdownOption(By dropdown, By ddOption){
        wait.waitUntilVisible(dropdown, 10);
        click(dropdown);
        click(ddOption);
    }

    public String getTextValue(By locator){
        return driver.findElement(locator).getText();
    }

    public WebElement findElementWrapper(By locator){
        return driver.findElement(locator);
    }

    public void scrollToElement(By locator){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", findElementWrapper(locator));
    }

}
