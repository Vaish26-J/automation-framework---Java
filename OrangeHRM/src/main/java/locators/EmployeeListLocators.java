package locators;

import org.openqa.selenium.By;

public class EmployeeListLocators {
    public static final By ADD_EMPLOYEE = By.xpath("//button[contains(., 'Add')]");
    public static final By FIRSTNAME = By.name("firstName");
    public static final By LASTNAME = By.name("lastName");
    public static final By EMPID = By.xpath("//label[contains(., 'Employee Id')]/parent::div/following-sibling::div/input");
    public static final By SAVE = By.cssSelector("button[type='submit']");
    public static final By PIM_MODULE = By.xpath("//a[contains(@href, 'viewPimModule')]");
    public static final By NAME_FILTER = By.xpath("//label[contains(.,'Employee Name')]/parent::div/following-sibling::div//input");
    public static final By DELETE = By.xpath("//button[contains(.,'Delete Selected')]");
    public static final By CONFIRM_DELETE = By.xpath("//button[contains(.,'Yes, Delete')]");
    public static By empListColumnValues(String value){
        String path = "//div[contains(@class, 'employee-list')]//div[contains(@class,'table-row')]//div[contains(@class,'table-cell') and contains(.,'" + value + "')]";
        return By.xpath(path);
    }

    public static By empListCheckbox(String value){
        String path = "//div[contains(@class, 'employee-list')]//div[contains(@class,'table-cell') and contains(.,'" + value + "')]/parent::div[contains(@class,'table-row')]//div[contains(@class,'checkbox-wrapper')]";
        return By.xpath(path);
    }
}
