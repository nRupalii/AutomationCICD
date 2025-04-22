package Ecommeres.TestCompounts;

import Ecommeres.resources.ExtentReporterNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public  class Listeners  extends BaseTest implements ITestListener {

    ExtentTest test;
    ExtentReports extent = ExtentReporterNG.getReportObject();
    ThreadLocal extentThread = new ThreadLocal();


    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        extentThread.set(test);

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed");


    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.fail(result.getThrowable());
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
                    .get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();


            //Screenshort // attched to the result
            String filePath = null;
            try {
                filePath = getScreenshot(result.getMethod().getMethodName(), driver);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());

        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}




