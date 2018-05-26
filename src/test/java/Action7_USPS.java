import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import javax.xml.ws.wsaddressing.W3CEndpointReferenceBuilder;
import java.io.File;
import java.io.IOException;

public class Action7_USPS {

        WebDriver driver;
        ExtentReports report;
        ExtentTest logger;


        @BeforeSuite
        public void openDriver() {
            driver = Reusable.ReusableMethods.chromeDriver();
            //Defining The PATH of the Extent Report
            report = new ExtentReports("C:\\Users\\Jayib\\Desktop\\IntelliJ\\Selenium\\ExtReports\\ExtentReport.html", true);

        }


        @AfterSuite
        public void closeDriver() {
            //To close the test and report
            report.flush();
            report.close();
            driver.quit();
        }


        @Test
        public void testScenario() throws InterruptedException, IOException {
            //Log Test Name
            logger = report.startTest("UPS action item");

            //Navigate to ups.
            logger.log(LogStatus.INFO, "navigating to UPS");
            driver.navigate().to("https://www.ups.com/us");

            //Waiting for page to load
            logger.log(LogStatus.INFO, "Waiting for page to load");
            Thread.sleep(1000);

            //Assert that title page is UPS
            logger.log(LogStatus.INFO, "Verifying page");
            //To use Assertion with EXTENT REPORT WE SHOULD FIRST STORE GETTITLE AS A VARIABLE.
            String actualTitle = driver.getTitle();
            if (actualTitle.equalsIgnoreCase("UPS")) {
                logger.log(LogStatus.PASS, "Title of page is UPS");
            } else {
                logger.log(LogStatus.FAIL, "Title of page is not UPS" + actualTitle);
                // Define path of the image
                String imagePath = "C:\\Users\\Jayib\\Desktop\\IntelliJ\\Selenium\\ExtReports\\UPS.png";
                //Line below allows you to take screenshot.
                File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                //Now you can do whatever you need to do for example copy somwhere
                FileUtils.copyFile(sourceFile, new File(imagePath));
                String image = logger.addScreenCapture(imagePath);
                logger.log(LogStatus.FAIL, "UPSTitle", image);
            }//End of if/else

            driver.findElement(By.xpath("//*[@id='ups-quickStartQuote']")).click();

            WebElement shipment = driver.findElement(By.xpath("//*[@name='shipmentType']"));
            Select dropDown = new Select(shipment);
            dropDown.selectByVisibleText("Letter");

            WebElement shipFrom = driver.findElement(By.xpath("//*[@name='origCountry']"));
            dropDown = new Select(shipFrom);
            dropDown.selectByVisibleText("United States");

            WebElement shipTo = driver.findElement(By.xpath("//*[@name='destCountry']"));
            dropDown = new Select(shipTo);
            dropDown.selectByVisibleText("United States");

            driver.findElement(By.xpath("//*[@name='weight']")).sendKeys("2");
            driver.findElement(By.xpath("//*[@name='origPostalCode']")).sendKeys("11218");
            driver.findElement(By.xpath("//*[@name='destPostalCode']")).sendKeys("10006");


            Boolean elementState = driver.findElement(By.xpath("//*[@type='checkbox']")).isSelected();
            if (elementState == true) {
                logger.log(LogStatus.FAIL, "Checkbox is selected" + elementState);
                // Define path of the image
                String imagePath = "C:\\Users\\Jayib\\Desktop\\IntelliJ\\Selenium\\ExtReports\\UPS_checkBox.png";
                //Line below allows you to take screenshot.
                File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                //Now you can do whatever you need to do for example copy somwhere
                FileUtils.copyFile(sourceFile, new File(imagePath));
                String image = logger.addScreenCapture(imagePath);
                logger.log(LogStatus.FAIL, "ups_checkbox", image);
            } else {
                logger.log(LogStatus.PASS, "CheckBox is not Selected");
            }

            driver.findElement(By.xpath("//*[@name='--qs']")).click();
            Thread.sleep(2500);




        }//End of TEST

    @Test(dependsOnMethods = "testScenario")
    public void testScenario2() throws IOException, InterruptedException {

        //Assert that title page is CALCULATION
        logger.log(LogStatus.INFO, "Verifying Calculate Time and Cost: UPS page");
        //To use Assertion with EXTENT REPORT WE SHOULD FIRST STORE GETTITLE AS A VARIABLE.
        String actualTitle = driver.getTitle();
        if (actualTitle.equalsIgnoreCase("Calculate Time and Cost: UPS")) {
            logger.log(LogStatus.PASS, "Title of page is Calculate Time and Cost: UPS");
        } else {
            logger.log(LogStatus.FAIL, "Title of page is not Calculate Time and Cost: UPS" + actualTitle);
            // Define path of the image
            String imagePath = "C:\\Users\\Jayib\\Desktop\\IntelliJ\\Selenium\\ExtReports\\UPS_CALCULATION.png";
            //Line below allows you to take screenshot.
            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            //Now you can do whatever you need to do for example copy somwhere
            FileUtils.copyFile(sourceFile, new File(imagePath));
            String image = logger.addScreenCapture(imagePath);
            logger.log(LogStatus.FAIL, "CALCULATION", image);
        }//End of if/else

        driver.findElements(By.xpath("//*[@value='Ship Now']")).get(0).click();
        Thread.sleep(2500);

        //Assert that title page is Shipping: UPS
        logger.log(LogStatus.INFO, "Verifying Shipping: UPS page");
        //To use Assertion with EXTENT REPORT WE SHOULD FIRST STORE GETTITLE AS A VARIABLE.
        actualTitle = driver.getTitle();
        if (actualTitle.equalsIgnoreCase("Shipping: UPS")) {
            logger.log(LogStatus.PASS, "Title of page is Shipping: UPS ");
        } else {
            logger.log(LogStatus.FAIL, "Title of page is not Shipping: UPS" + actualTitle);
            // Define path of the image
            String imagePath = "C:\\Users\\Jayib\\Desktop\\IntelliJ\\Selenium\\ExtReports\\UPS_Shipping_UPS.png";
            //Line below allows you to take screenshot.
            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            //Now you can do whatever you need to do for example copy somwhere
            FileUtils.copyFile(sourceFile, new File(imagePath));
            String image = logger.addScreenCapture(imagePath);
            logger.log(LogStatus.FAIL, "Shipping: UPS ", image);
        }//End of if/else




    }//END OF TEST2






}//END OF CLASS
