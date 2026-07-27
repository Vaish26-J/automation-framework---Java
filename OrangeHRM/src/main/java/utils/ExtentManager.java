package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports reports;
    public static ExtentReports getInstance(){
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("reports/extent-reports.html");
        reports = new ExtentReports();
        reports.attachReporter(htmlReporter);
        return reports;
    }
}
