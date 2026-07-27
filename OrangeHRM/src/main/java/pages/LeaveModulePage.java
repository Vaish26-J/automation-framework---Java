package pages;

import locators.EmployeeDetailsLocators;
import locators.LeaveModuleLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import utils.ElementUtils;
import utils.WaitUtils;

public class LeaveModulePage {
    private WebDriver driver;
    private ElementUtils elementUtils;
    private WaitUtils wait;
    public LeaveModulePage(WebDriver driver){
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        wait = new WaitUtils(driver);
    }

    public void navigateToLeaveMod(){
        elementUtils.click(LeaveModuleLocators.LEAVE_MODULE);
    }

    public void selectMultiDropdownOptions(String[] options){
        for(int i=0; i<options.length;i++){
            elementUtils.selectDropdownOption(LeaveModuleLocators.MULTISELECT_DD, LeaveModuleLocators.multiselect_options(options[i]));
        }
    }

    public void filterLeaveBasedOnStatus(String[] statuses){
        navigateToLeaveMod();
        wait.waitUntilLoaderDisappears(EmployeeDetailsLocators.LOADER);
        wait.waitUntilVisible(LeaveModuleLocators.MULTISELECT_DD);
        selectMultiDropdownOptions(statuses);
        elementUtils.click(LeaveModuleLocators.SEARCH);
    }

    public Boolean isResultTableDisplayed(){
        return elementUtils.isElementPresent(LeaveModuleLocators.LEAVELIST);
    }
}
