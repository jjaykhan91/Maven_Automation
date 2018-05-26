package TestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import javax.rmi.CORBA.Util;
import javax.swing.text.Utilities;

public class Google_TestNG {

    //Placed outside methods to make it Global.
    WebDriver driver;
    SoftAssert softAssert;

   @BeforeMethod
    public void openBrowser(){
       //Define the Chrome Driver using reusable method
       driver = Reusable.reusableMethods.chromeDriver();
//       softAssert.assertEquals("Go0ooogle",driver.getTitle());
//       //Verify im on the right page *HARD ASSERTION IF FAILS: Code will Stop!
//       Assert.assertEquals("Google",driver.getTitle());
//       //Verifying if google image is there.
//       Assert.assertTrue(driver.findElement(By.xpath("//*[@id='hplogo']")).isDisplayed(), "Logo should be displayed");
   }

   @Test
    public void testScenario() throws InterruptedException {
       softAssert = new SoftAssert();
       //Entering value in search field.
       driver.navigate().to("http://www.google.com");
       softAssert.assertEquals("Gooogle",driver.getTitle());
       driver.findElement(By.xpath("//*[@name ='q']")).sendKeys("Junaid Khan");
       driver.findElement(By.xpath("//*[@id='body']")).click();
       driver.findElement(By.xpath("//*[@name='btnK']")).click();
       String message = driver.findElement(By.xpath("//*[@id='resultStats']")).getText();
       String[] arrMessage = message.split(" ");
       System.out.println("Results are " + arrMessage[1]);

   }

   @AfterMethod
    public void closeBrowser(){
       //Quitting the driver
       driver.quit();
       //Placed here to throw exception for all tests.
       softAssert.assertAll();

   }

}// End of class.
