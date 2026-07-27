package listener;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentManager;
import utils.ScreenshotUtils;

public class TestListener implements ITestListener {
    private ExtentReports reports;
    private ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
    @Override
    public void onStart(ITestContext context){
        reports = ExtentManager.getInstance();
    }

    @Override
    public void onFinish(ITestContext context){
        reports.flush();
    }

    @Override
    public void onTestStart(ITestResult result){
        test.set(reports.createTest(result.getName()));
    }

    @Override
    public void onTestSuccess(ITestResult result){
        test.get().pass("Test "+ result.getName() + " Passed");
    }

    @Override
    public void onTestFailure(ITestResult result){
        String filePath = ScreenshotUtils.captureScreenshot(BaseTest.getDriver(), result.getName());
        test.get().fail(result.getThrowable());
        test.get().addScreenCaptureFromPath(filePath);
    }
}
