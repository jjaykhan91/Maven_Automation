package Reusable;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

public class ReusableMethodsReports {
    public static int timeout = 50;

    public static WebDriver chromeDriver() {
        //Setting up Webdriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Jayib\\Desktop\\IntelliJ\\Applications_Jars\\src\\drivers\\chromedriver.exe");

        //Line below defines ChromeOptions
        ChromeOptions options = new ChromeOptions();

        //Line below will add the maximized argument(s) for chrome options
        options.addArguments(Collections.singletonList("start-maximized"));

        //Define the weddriver
        WebDriver driver = new ChromeDriver(options);
        return driver;
    }// End of Web Driver Method.

    public static void getScreenshot(WebDriver driver,ExtentTest logger ) throws IOException {
        String path = "C:\\Users\\Jayib\\Desktop\\IntelliJ\\Selenium\\ExtReports\\Screenshots";
        String fileName = "image"+UUID.randomUUID() + "png";
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile, new File(path + fileName));
        String image = logger.addScreenCapture("Screenshots\\"+ fileName);
        logger.log(LogStatus.FAIL, "",image);



    }//End of getScreenShot Method


    public static void click (WebDriver driver, String locator, ExtentTest logger, String elementName) throws IOException {
            //method below allows you to create a resuable method to click on an element and you are passing two argument
            //one is webdriver you are using and the locator you are locating the element with
            WebDriverWait wait = new WebDriverWait(driver,timeout);
            try{
                logger.log(LogStatus.INFO, "Clicking on an element" + elementName);
                // driver.findElement(By.xpath(locator)).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator))).click();
            }catch (Exception e){
                logger.log(LogStatus.FAIL, "Unable to click on element" + elementName);
                getScreenshot(driver,logger);

            }////////////////jghfhdjyfbf
    }//end of click method



    public static void sendKeys (WebDriver driver, String locator, String userValue){
            WebDriverWait wait = new WebDriverWait(driver,timeout);
            try{
                //driver.findElement(By.xpath(locator)).sendKeys(userValue);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator))).sendKeys(userValue);
            }catch (Exception e){
                System.out.println("unable to enter user value on sendKeys " + e);
            }

    }//end of sendKeys method

    public static String getContent (WebDriver driver, String locator){
            WebDriverWait wait = new WebDriverWait(driver,timeout);
            String message = null;
            try{
                message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator))).getText();
            }catch (Exception e) {
                System.out.println("unable to capture the text value " + e);
            }
            return message;
    }//end of getContenet method


    public static void mouseHover (WebDriver driver, String locator) {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            try {
                Actions mouseHover = new Actions(driver);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
                mouseHover.moveToElement(driver.findElement(By.xpath(locator))).build().perform();
            }catch (Exception e){
                System.out.println("unable to hover to the Element " + e);
            }
    }//end of mouseHover method



    public static void mouseClick (WebDriver driver, String locator) {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            try{
                Actions mouseClick = new Actions(driver);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
                mouseClick.moveToElement(driver.findElement(By.xpath(locator))).click().build().perform();
            }catch (Exception e){
                System.out.println("unable to click with mouse movement " + e);
            }
    }//end of mouseAction method



    public static void mouseType (WebDriver driver, String locator, String userValue) {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            try{
                Actions mouseType = new Actions(driver);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
                mouseType.moveToElement(driver.findElement(By.xpath(locator))).sendKeys(userValue).build().perform();
            }catch(Exception e){
                System.out.println( "unable to type using mouse movement " +e);
            }


    }//end of mouseType method


    public static void scroll (WebDriver driver, int userScroll){
            try{
                JavascriptExecutor jse = (JavascriptExecutor)driver;
                jse.executeScript("scroll(0,"+userScroll+")");
            }catch (Exception e){
                System.out.println( "my screen did not scroll " + e);
            }

    }//End of Scroll Method


    public static void  scrollIntoElement (WebDriver driver, String locator){
            WebDriverWait wait = new WebDriverWait(driver,timeout);
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            try{
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
                jse.executeScript("argument[0].scrollIntoView(true);",element);

            }catch(Exception e){
            }
    }//End of Scroll into Element










}//End of reusableMethods Class
