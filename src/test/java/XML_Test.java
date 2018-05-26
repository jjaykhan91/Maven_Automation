import Reusable.ReusableMethods;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import sun.rmi.runtime.Log;

import java.io.File;
import java.io.IOException;

public class XML_Test {

    WebDriver driver;
    ExtentReports report;
    ExtentTest logger;


    @BeforeSuite
    public void openDriver() {
        driver = Reusable.ReusableMethods.chromeDriver();
        //Defining The PATH of the Extent Report
        report = new ExtentReports("C:\\Users\\Jayib\\Desktop\\IntelliJ\\Selenium\\ExtReports\\ExtentReport.html",true);

    }


    @AfterSuite
    public void closeDriver(){
        //To close the test and report
        report.flush();
        report.close();
        driver.quit();


    }

    @Test
    public void testScenario() throws InterruptedException, IOException {
        //Log test name using logger
        logger = report.startTest("Google Search");

        //Navigate to google.
        logger.log(LogStatus.INFO,"navigating to google");
        driver.navigate().to("http://www.google.com");

        //Waiting for page to load
        logger.log(LogStatus.INFO,"Waiting for page to load");
        Thread.sleep(1000);

        //Assert that title page is google
        logger.log(LogStatus.INFO,"Verifying page");
        //To use Assertion with EXTENT REPORT WE SHOULD FIRST STORE GETTITLE AS A VARIABLE.
        String actualTitle = driver.getTitle();
        if(actualTitle.equalsIgnoreCase("gooogle")){
            logger.log(LogStatus.PASS,"Title of page is Google");
        } else {
            logger.log(LogStatus.FAIL,"Title of page is not Google" + actualTitle);
            // Define path of the image
            String imagePath = "C:\\Users\\Jayib\\Desktop\\IntelliJ\\Selenium\\ExtReports\\GoogleTitle.png";
            //Line below allows you to take screenshot.
            File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            //Now you can do whatever you need to do for example copy somwhere
            FileUtils.copyFile(sourceFile, new File(imagePath));
            String image = logger.addScreenCapture(imagePath);
            logger.log(LogStatus.FAIL,"googleTitle",image);
        }//End of if/else

        //End test
        report.endTest(logger);




    }





}