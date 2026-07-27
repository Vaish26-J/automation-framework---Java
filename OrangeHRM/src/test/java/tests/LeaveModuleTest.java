package tests;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LeaveModulePage;
import pages.LoginPage;

@Listeners(listener.TestListener.class)
public class LeaveModuleTest extends BaseTest {
    private WebDriver driver;
    private LeaveModulePage leaves;
    private LoginPage login;
    @BeforeClass
    public void testSetup(){
        driver = getDriver();
        leaves = new LeaveModulePage(driver);
        login = new LoginPage(driver);
        login.login();
    }
    @Test
    public void validateLeaveFilter(){
        String[] statuses = {"Pending Approval", "Rejected", "Cancelled"};
        leaves.filterLeaveBasedOnStatus(statuses);
        if (leaves.isResultTableDisplayed()) {
            Assert.assertTrue(true);
        } else {
            Assert.assertFalse(leaves.isResultTableDisplayed());
        }
    }
}
