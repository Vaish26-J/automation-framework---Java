package locators;

import org.openqa.selenium.By;

public class EmployeeDetailsLocators {
    public static final By SAVE = By.xpath("//div[contains(@class,'horizontal')]//button[@type='submit']");
    public static final By ADD_ATTACHMENT = By.xpath("//div[contains(@class, 'action-header')]//button[contains(.,'Add')]");
    public static final By ATTACHMENT_SAVE = By.xpath("//div[contains(@class,'attachment')]//button[@type='submit']");
    public static final By LOADER = By.xpath("//div[@class='oxd-loading-spinner']");
    public static final By CONFIRM_DELETE = By.xpath("//button[contains(.,'Yes, Delete')]");
    public static final By ATTACHMENT = By.xpath("//input[@type='file']");
    public static By sideTabs(String tabName){
        String path = "//div[contains(@role, 'tablist')]//a[contains(.,'" + tabName + "')]";
        return By.xpath(path);
    }

    public static By dropDown(String fieldName){
        String path = "//label[contains(.,'" + fieldName +  "')]/parent::div[contains(@class, 'input-group')]/following-sibling::div//div[contains(@class,'select-wrapper')]";
        return By.xpath(path);
    }

    public static By dropDownOptions(String fieldName, String option){
        String path = "//label[contains(.,'" + fieldName +  "')]/parent::div[contains(@class, 'input-group')]/following-sibling::div//div[contains(@class,'select-wrapper')]//span[contains(text(),'" + option + "')]";
        return By.xpath(path);
    }

    public static By attachedFile(String fileName){
        String path = "//div[contains(@class,'attachment')]//div[contains(text(),'" + fileName + "')]";
        return By.xpath(path);
    }

    public static By downloadAttach(String filename){
        String path = "//div[contains(@class,'attachment')]//div[contains(text(),'" + filename + "')]/parent::div/following-sibling::div//i[contains(@class,'download')]";
        return By.xpath(path);
    }

    public static By editAttach(String filename){
        String path = "//div[contains(@class,'attachment')]//div[contains(text(),'" + filename + "')]/parent::div/following-sibling::div//i[contains(@class,'pencil')]";
        return By.xpath(path);
    }

    public static By deleteAttach(String filename){
        String path = "//div[contains(@class,'attachment')]//div[contains(text(),'" + filename + "')]/parent::div/following-sibling::div//i[contains(@class,'trash')]";
        return By.xpath(path);
    }

}
