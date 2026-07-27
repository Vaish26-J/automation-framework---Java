package locators;

import org.openqa.selenium.By;

public class LeaveModuleLocators {
    public static final By LEAVELIST = By.xpath("//div[@class='orangehrm-bottom-container']/div");
    public static final By MULTISELECT_DD = By.xpath("//div[contains(@class, 'multiselect-wrapper')]/div[contains(@class, 'select-text')]");
    public static final By SEARCH = By.xpath("//button[@type='submit']");
    public static final By LEAVE_MODULE = By.xpath("//a[contains(@href, 'viewLeaveModule')]");

    public static By multiselect_options(String option){
        String path = "//div[contains(@class, 'multiselect-wrapper')]/div[contains(@role, 'listbox')]//span[contains(text(),'" + option + "')]";
        return By.xpath(path);
    }
}
