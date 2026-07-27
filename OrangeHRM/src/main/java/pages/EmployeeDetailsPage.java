package pages;

import locators.EmployeeDetailsLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import utils.ElementUtils;
import utils.WaitUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EmployeeDetailsPage {
    private WebDriver driver;
    private ElementUtils elementUtils;
    private WaitUtils wait;
    public EmployeeDetailsPage(WebDriver driver){
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        wait = new WaitUtils(driver);
    }

    public void goToSideTab(String tabName){
        wait.waitUntilVisible(EmployeeDetailsLocators.sideTabs(tabName));
        elementUtils.click(EmployeeDetailsLocators.sideTabs(tabName));
    }

    public void uploadFile(String tabName) throws InterruptedException {
        goToSideTab(tabName);
        wait.waitUntilLoaderDisappears(EmployeeDetailsLocators.LOADER);
        wait.waitUntilVisible(EmployeeDetailsLocators.SAVE);
        wait.waitUntilLoaderDisappears(EmployeeDetailsLocators.LOADER);
        elementUtils.scrollToElement(EmployeeDetailsLocators.ADD_ATTACHMENT);
        elementUtils.click(EmployeeDetailsLocators.ADD_ATTACHMENT);
        String filePath = Paths.get(
                System.getProperty("user.dir"),
                "assets",
                "sampleFile.jpeg"
        ).toString();
        Thread.sleep(2000);
        elementUtils.uploadFile(EmployeeDetailsLocators.ATTACHMENT, filePath);
        wait.waitUntilVisible(EmployeeDetailsLocators.ATTACHMENT_SAVE);
        elementUtils.click(EmployeeDetailsLocators.ATTACHMENT_SAVE);
    }

    public void selectDropdownOptions(String[] title, String[] options){
        for(int i=0; i<title.length;i++){
            elementUtils.selectDropdownOption(EmployeeDetailsLocators.dropDown(title[i]), EmployeeDetailsLocators.dropDownOptions(title[i], options[i]));
        }
    }

    public void editFields(String tabName, String[] title, String[] options){
        goToSideTab(tabName);
        wait.waitForLoaderCycle(EmployeeDetailsLocators.LOADER);
        selectDropdownOptions(title, options);
        elementUtils.click(EmployeeDetailsLocators.SAVE);
    }

    public String getDropdownValue(String title){
        return elementUtils.getTextValue(EmployeeDetailsLocators.dropDown(title));
    }

    public Boolean isFileUploaded(String filename){
        wait.waitUntilLoaderDisappears(EmployeeDetailsLocators.LOADER);
        return elementUtils.isElementPresent(EmployeeDetailsLocators.attachedFile(filename));
    }

    public void downloadFile(String filename) throws IOException, InterruptedException {
        Path filePath = Paths.get(
                System.getProperty("user.dir"),
                "downloads",
                filename);
        Files.deleteIfExists(filePath);
        Thread.sleep(3000);
        elementUtils.click(EmployeeDetailsLocators.downloadAttach(filename));
    }

    public boolean waitForFileDownload(String fileName, int retryCount) {

        Path file = Paths.get(
                System.getProperty("user.dir"),
                "downloads",
                fileName);
        for (int i = 0; i < retryCount; i++) {
            if (Files.exists(file)) {
                return true;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                return false;
            }
        }
        return Files.exists(file);
    }

    public void deleteAttach(String filename){
        wait.waitForLoaderCycle(EmployeeDetailsLocators.LOADER);
        elementUtils.click(EmployeeDetailsLocators.deleteAttach(filename));
        elementUtils.click(EmployeeDetailsLocators.CONFIRM_DELETE);
    }

    public void editAttach(String oldFilename, String newFilename) throws InterruptedException {
        wait.waitForLoaderCycle(EmployeeDetailsLocators.LOADER);
        elementUtils.click(EmployeeDetailsLocators.editAttach(oldFilename));
        wait.waitForLoaderCycle(EmployeeDetailsLocators.LOADER);
        String filePath = Paths.get(
                System.getProperty("user.dir"),
                "assets",
                "sampleFile2.png"
        ).toString();
        Thread.sleep(2000);
        elementUtils.uploadFile(EmployeeDetailsLocators.ATTACHMENT, filePath);
        wait.waitUntilVisible(EmployeeDetailsLocators.ATTACHMENT_SAVE);
        elementUtils.click(EmployeeDetailsLocators.ATTACHMENT_SAVE);
    }

    public Boolean isFileDeleted(String filename){
        wait.waitUntilLoaderDisappears(EmployeeDetailsLocators.LOADER);
        return !elementUtils.isElementPresent(EmployeeDetailsLocators.attachedFile(filename));
    }
}
