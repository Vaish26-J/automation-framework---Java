package base;

import utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseTest {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    @BeforeClass
    public static void setUp(){
        driver.set(DriverFactory.initiateDriver());
        driver.get().manage().window().maximize();
    }

    public static WebDriver getDriver(){
        return driver.get();
    }

    @AfterClass
    public static void tearDown(){
        getDriver().quit();
        driver.remove();
    }
}
