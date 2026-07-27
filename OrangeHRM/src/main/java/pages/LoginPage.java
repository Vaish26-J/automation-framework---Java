package pages;

import locators.LoginPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import utils.ElementUtils;
import utils.WaitUtils;

public class LoginPage {

    private WebDriver driver;
    private ElementUtils elements;
    private WaitUtils wait;
    public LoginPage(WebDriver driver){
        this.driver = driver;
        elements = new ElementUtils(driver);
        wait = new WaitUtils(driver);
    }

    public void login(String uname, String pwd){
        elements.navigateTo("https://opensource-demo.orangehrmlive.com");
        elements.enterText(LoginPageLocators.USERNAME, uname);
        elements.enterText(LoginPageLocators.PASSWORD, pwd);
        clickLogin();
    }

    public void login(){
        elements.navigateTo("https://opensource-demo.orangehrmlive.com");
        elements.enterText(LoginPageLocators.USERNAME, "Admin");
        elements.enterText(LoginPageLocators.PASSWORD, "admin123");
        clickLogin();
    }

    public void clickLogin(){
        elements.click(LoginPageLocators.LOGIN);
    }

    public void logout(){
        wait.waitUntilVisible(LoginPageLocators.USERDROPDOWN, 20);
        elements.click(LoginPageLocators.USERDROPDOWN);
        elements.click(LoginPageLocators.userDropdownList("Logout"));
    }

    public Boolean isElementDisplayed(By locator){
       return elements.isElementPresent(locator);
    }
}
