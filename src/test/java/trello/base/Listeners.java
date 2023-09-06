package trello.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class Listeners extends BaseTest implements ITestListener  {

    ExtentTest test;
    ExtentReports extent=ExtentReportNG.getReportObject();

    @Override
    public void onTestStart(ITestResult result) {
        test=extent.createTest(result.getMethod().getMethodName());
    }


    @Override
    public void onTestFailure(ITestResult result) {
        test.fail(result.getThrowable());
    }


    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}

