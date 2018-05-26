package SuiteTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.io.IOException;

public class ParallelTesting {
    WebDriver driver;
    ExtentReports reports;
    ExtentTest logger;

    @Parameters("Browser")
    @BeforeMethod

                    public void openBrowser(String Browser) {
                        if (Browser.equalsIgnoreCase("firefox")) {
                            //Defining the path for firefox driver(gecko)
                            System.setProperty("webdriver.gecko.driver", "C:\\Users\\Jayib\\Desktop\\IntelliJ\\Maven\\src\\main\\resources\\geckodriver.exe");
                            driver = new FirefoxDriver();
                            driver.manage().window().maximize();
                        } else if (Browser.equalsIgnoreCase("chrome")) {
                            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Jayib\\Desktop\\IntelliJ\\Maven\\src\\main\\resources\\chromedriver.exe");
                            ChromeOptions options = new ChromeOptions();
                            options.addArguments("start-maximized");
                            driver = new ChromeDriver(options);
                        }
                        reports = new ExtentReports("C:\\Users\\Jayib\\Desktop\\IntelliJ\\Selenium\\ExtReports\\HTML Reports",true);
                    }

    @Test
            public void testScenario() throws InterruptedException, IOException {
            logger = reports.startTest("Bing Search");
            logger.log(LogStatus.INFO, "Navigating to BING");
            driver.navigate().to("https://www.bing.com");
            Reusable.ReusableMethodsReports.click(driver,"//*[@id='scpl']",logger,"Images");
            //Reusable.ReusableMethodsReports.getScreenshot(driver,logger);
            Thread.sleep(4500);
            reports.endTest(logger);
    }


    @AfterMethod
                public void tearDown(){
                    reports.flush();
                    reports.close();
                    //driver.quit();
                }

}//End of Parallel Class
