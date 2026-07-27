package tests;

import base.BaseTest;
import locators.LoginPageLocators;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.WaitUtils;

@Listeners(listener.TestListener.class)
public class LoginTest extends BaseTest {
    private WebDriver driver;
    private LoginPage loginPage;
    @BeforeClass
    public void testSetUp(){
        driver = getDriver();
        loginPage = new LoginPage(driver);
    }

    @BeforeMethod
    public void refreshPage(){
        driver.navigate().refresh();
    }

    @Test
    public void validate_User_Login() throws InterruptedException {
        loginPage.login("Admin", "admin123");
        Thread.sleep(3000);
        Boolean isPresent = loginPage.isElementDisplayed(LoginPageLocators.USERDROPDOWN);
        Assert.assertTrue(isPresent);
        loginPage.logout();
    }

    @Test
    public void validate_Invalid_Credentials() throws InterruptedException {
        loginPage.login("addmin", "aadmin2123");
        Thread.sleep(3000);
        Boolean isPresent = loginPage.isElementDisplayed(LoginPageLocators.INVALIDCREDSERROR);
        Assert.assertTrue(isPresent);
    }

    @Test
    public void validate_Mandatory_Field_Error_On_Empty_Creds() throws InterruptedException {
        loginPage.clickLogin();
        Thread.sleep(3000);
        Boolean isPresent = loginPage.isElementDisplayed(LoginPageLocators.MANDATORYFIELDERROR);
        Assert.assertTrue(isPresent);
    }
}
