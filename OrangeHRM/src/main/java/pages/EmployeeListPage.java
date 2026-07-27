package pages;

import locators.EmployeeDetailsLocators;
import locators.EmployeeListLocators;
import org.openqa.selenium.WebDriver;
import utils.ElementUtils;
import utils.WaitUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class EmployeeListPage {
    private WebDriver driver;
    private ElementUtils elementUtils;
    private LoginPage login;
    private String timeStamp = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("HHmmss"));
    private WaitUtils wait;
    public EmployeeListPage(WebDriver driver) {
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        login = new LoginPage(driver);
        wait = new WaitUtils(driver);
    }

    public void navigateToPIMModule(){
        elementUtils.click(EmployeeListLocators.PIM_MODULE);
    }

    public void addNewEmployee(String fname, String lname) throws InterruptedException {
        navigateToPIMModule();
        elementUtils.click(EmployeeListLocators.ADD_EMPLOYEE);
        wait.waitForLoaderCycle(EmployeeDetailsLocators.LOADER);
        wait.waitUntilVisible(EmployeeListLocators.FIRSTNAME, 30);
        elementUtils.enterText(EmployeeListLocators.FIRSTNAME, fname);
        elementUtils.enterText(EmployeeListLocators.LASTNAME, lname);
        elementUtils.enterText(EmployeeListLocators.EMPID, timeStamp);
        wait.waitUntilClickable(EmployeeListLocators.SAVE, 30);
        elementUtils.click(EmployeeListLocators.SAVE);
        wait.waitForLoaderCycle(EmployeeDetailsLocators.LOADER);
        wait.waitUntilVisible(EmployeeDetailsLocators.SAVE, 50);
    }

    public void searchEmployeeByName(String name){
        navigateToPIMModule();
        elementUtils.enterText(EmployeeListLocators.NAME_FILTER, name);
        elementUtils.click(EmployeeListLocators.SAVE);
    }

    public Boolean findValueInEmpList(String value){
        return elementUtils.isElementPresent(EmployeeListLocators.empListColumnValues(value));
    }

    public void selectEmployeeFromList(String value){
        elementUtils.click(EmployeeListLocators.empListCheckbox(value));
    }

    public void deleteEmployee(){
        elementUtils.click(EmployeeListLocators.DELETE);
        elementUtils.click(EmployeeListLocators.CONFIRM_DELETE);
    }

    public void openEmployeeDetails(String name){
        wait.waitUntilLoaderDisappears(EmployeeDetailsLocators.LOADER);
        wait.waitUntilVisible(EmployeeListLocators.empListColumnValues(name), 30);
        elementUtils.scrollToElement(EmployeeListLocators.empListColumnValues(name));
        elementUtils.click(EmployeeListLocators.empListColumnValues(name));
    }

    public void navigateToEmployeeDetails(String name) throws InterruptedException {
        searchEmployeeByName(name);
        Thread.sleep(5000);
        openEmployeeDetails(name);
    }
}
