package tests;

import base.BaseTest;
import locators.EmployeeDetailsLocators;
import locators.EmployeeListLocators;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.EmployeeDetailsPage;
import pages.EmployeeListPage;
import pages.LoginPage;
import utils.WaitUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Listeners(listener.TestListener.class)
public class EmployeeCRUDTest extends BaseTest {
    private WebDriver driver;
    private EmployeeListPage pim;
    private String timeStamp = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("HHmmss"));
    private WaitUtils wait;
    private LoginPage login;
    private EmployeeDetailsPage empDetails;
    @BeforeClass
    public void testSetup(){
        driver = getDriver();
        pim = new EmployeeListPage(driver);
        wait = new WaitUtils(driver);
        login = new LoginPage(driver);
        empDetails = new EmployeeDetailsPage(driver);
        login.login();
    }

    @Test
    public void validateEmployeeAddition() throws InterruptedException {
        String fname = "test"+timeStamp;
        String lname = "auto"+timeStamp;
        String expected = "viewPersonalDetails/empNumber";
        pim.addNewEmployee(fname, lname);
        wait.waitForUrlContains(expected);
        Assert.assertTrue(driver.getCurrentUrl().contains(expected), "Employee not created");
    }

    @Test
    public void ValidateEmployeeSearch() throws InterruptedException {
        String fname = "test2"+timeStamp;
        String lname = "auto2"+timeStamp;
        pim.addNewEmployee(fname, lname);
        pim.searchEmployeeByName(fname);
        wait.waitUntilVisible(EmployeeListLocators.empListColumnValues(fname));
        Assert.assertTrue(pim.findValueInEmpList(fname));
    }

    @Test
    public void ValidateEmployeeUpdation() throws InterruptedException {
        String fname = "test4"+timeStamp;
        String lname = "auto4"+timeStamp;
        pim.addNewEmployee(fname, lname);
        wait.waitUntilVisible(EmployeeDetailsLocators.sideTabs("Job"), 10);
        pim.navigateToEmployeeDetails(fname);
        wait.waitUntilVisible(EmployeeDetailsLocators.sideTabs("Job"));
        String[] ddTitles = {"Job Title", "Sub Unit"};
        String[] options = {"QA Engineer", "Engineering"};
        empDetails.editFields("Job", ddTitles, options);
        pim.navigateToEmployeeDetails(fname);
        empDetails.goToSideTab("Job");
        wait.waitForLoaderCycle(EmployeeDetailsLocators.LOADER);
        Assert.assertEquals(empDetails.getDropdownValue(ddTitles[0]), options[0], "Value not updated");
        Assert.assertEquals(empDetails.getDropdownValue(ddTitles[1]), options[1], "Value not updated");
    }

    @Test
    public void ValidateEmployeeDeletion() throws InterruptedException {
        String fname = "test3"+timeStamp;
        String lname = "auto3"+timeStamp;
        pim.addNewEmployee(fname, lname);
        pim.searchEmployeeByName(fname);
        pim.selectEmployeeFromList(fname);
        pim.deleteEmployee();
        pim.searchEmployeeByName(fname);
        wait.waitUntilInvisible(EmployeeListLocators.empListColumnValues(fname));
        Assert.assertFalse(pim.findValueInEmpList(fname));
    }

    @Test
    public void validateUploadAndDownloadAttachment() throws InterruptedException, IOException {
        String fname = "test4"+timeStamp;
        String lname = "auto4"+timeStamp;
        pim.addNewEmployee(fname, lname);
        pim.navigateToEmployeeDetails(fname);
        empDetails.uploadFile("Job");
        pim.navigateToEmployeeDetails(fname);
        empDetails.goToSideTab("Job");
        wait.waitUntilLoaderDisappears(EmployeeDetailsLocators.LOADER);
        wait.waitUntilVisible(EmployeeDetailsLocators.SAVE);
        Assert.assertTrue(empDetails.isFileUploaded("sampleFile.jpeg"));
        empDetails.downloadFile("sampleFile.jpeg");
        boolean isDownloaded = empDetails.waitForFileDownload("sampleFile.jpeg", 20);
        Assert.assertTrue(isDownloaded);
    }

    @Test
    public void validateAttachmentEdit() throws InterruptedException {
        String fname = "test5"+timeStamp;
        String lname = "auto5"+timeStamp;
        pim.addNewEmployee(fname, lname);
        pim.navigateToEmployeeDetails(fname);
        empDetails.uploadFile("Job");
        pim.navigateToEmployeeDetails(fname);
        empDetails.goToSideTab("Job");
        wait.waitUntilLoaderDisappears(EmployeeDetailsLocators.LOADER);
        wait.waitUntilVisible(EmployeeDetailsLocators.SAVE);
        empDetails.editAttach("sampleFile.jpeg", "sampleFile2.jpeg");
        wait.waitForLoaderCycle(EmployeeDetailsLocators.LOADER);
        Assert.assertTrue(empDetails.isFileUploaded("sampleFile2.png"));
    }

    @Test
    public void validateAttachmentDelete() throws InterruptedException {
        String fname = "test6"+timeStamp;
        String lname = "auto6"+timeStamp;
        pim.addNewEmployee(fname, lname);
        pim.navigateToEmployeeDetails(fname);
        empDetails.uploadFile("Job");
        pim.navigateToEmployeeDetails(fname);
        empDetails.goToSideTab("Job");
        wait.waitUntilLoaderDisappears(EmployeeDetailsLocators.LOADER);
        wait.waitUntilVisible(EmployeeDetailsLocators.SAVE);
        empDetails.deleteAttach("sampleFile.jpeg");
        wait.waitForLoaderCycle(EmployeeDetailsLocators.LOADER);
        Assert.assertTrue(empDetails.isFileDeleted("sampleFile.jpeg"));
    }

}
