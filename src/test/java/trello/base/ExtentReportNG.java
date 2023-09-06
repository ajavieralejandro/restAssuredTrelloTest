package trello.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG  {

    public static ExtentReports getReportObject() {

        String reportPath = System.getProperty("user.dir") + "//reports/index.html";
        ExtentSparkReporter report = new ExtentSparkReporter(reportPath);
        report.config().setReportName("Trello Tests Report");
        report.config().setDocumentTitle("Trello Results");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(report);
        extent.setSystemInfo("Eslam Mashaly", "Tester");
        return extent;
    }
}
