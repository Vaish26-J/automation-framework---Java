package locators;

import org.openqa.selenium.By;

public class LoginPageLocators {
    public static final By USERNAME = By.name("username");
    public static final By PASSWORD = By.name("password");
    public static final By LOGIN = By.cssSelector("button[type='submit']");
    public static final By USERDROPDOWN = By.xpath("//span[contains(@class, 'userdropdown')]");
    public static final By INVALIDCREDSERROR = By.xpath("//p[text()= 'Invalid credentials']");
    public static final By MANDATORYFIELDERROR = By.xpath("//span[contains(@class, 'field-error-message')]");

    public static By userDropdownList(String item){
        String path = "//ul[contains(@class, 'dropdown-menu')]/li/a[text() = '" + item + "']";
        return By.xpath(path);
    }
}
