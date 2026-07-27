package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class WaitUtils {
    private WebDriver driver;
    private WebDriverWait wait;

    public WaitUtils(WebDriver driver){
        this.driver = driver;
    }

    public void waitUntilVisible(By locator){
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitUntilVisible(By locator, int duration){
        wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForUrlContains(String url){
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.urlContains(url));
    }

    public void waitUntilInvisible(By locator){
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitUntilPresence(By locator){
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitUntilLoaderDisappears(By loaderLocator) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(driver -> {
            List<WebElement> loaders = driver.findElements(loaderLocator);
            return loaders.isEmpty();
        });
    }

    public void waitForLoaderCycle(By loaderLocator){
        try{
            waitUntilVisible(loaderLocator, 50);
        }catch (TimeoutException e){
            e.printStackTrace();
        }
        waitUntilLoaderDisappears(loaderLocator);
    }

    public void waitUntilClickable(By locator, int timeoutInSeconds) {
        wait =  new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}
