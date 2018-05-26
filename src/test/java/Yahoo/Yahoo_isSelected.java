package Yahoo;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Yahoo_isSelected {
    @Test
    public void test_Scenario() throws InterruptedException {
        //Open Chrome Browser
        WebDriver driver = Reusable.reusableMethods.chromeDriver();

        //Navigating to page
        driver.navigate().to("http://www.Yahoo.com");

        //Wait
        Thread.sleep(2000);

        //Verifying if we are on Yahoo
        Assert.assertEquals("Yahoo",driver.getTitle());

        //Get all the  navigation tab counts
        List<WebElement> tabCount = driver.findElements(By.xpath("//*[contains(@class,'Mstart(21px)')]"));
        System.out.println("The Tab count is " + tabCount.size());

        //Entering "NUTRITION" on search field.
        driver.findElement(By.xpath("//*[@id='uh-search-box']")).sendKeys("Nutrition");

        //Clicking on search
        driver.findElement(By.xpath("//*[@type='submit']")).click();
        Thread.sleep(2500);

        //Setting up Javascript Executor
        JavascriptExecutor jse = (JavascriptExecutor)driver;

        //invoking jse for scrolling down
        jse.executeScript("scroll(0,10000)");

        //capturing text and storing in results variable.
        String results = driver.findElement(By.xpath("//*[contains (@class,'reg searchBottom')]")).getText();

        // splitting by NEXT to capture results
        String[] arrResults = results.split("Next");
        System.out.println("The Results are " + arrResults[1]);

        //invoking jse to scroll up
        jse.executeScript("scroll(0,-10000)");

        //clicking on sign in
        driver.findElement(By.xpath("//*[@id='yucs-login_signIn']")).click();

        // boolean to check if checkbox is selected
        Boolean elementState = driver.findElement(By.xpath("//*[@type='checkbox']")).isSelected();
        System.out.println("Is Element Selected? " + elementState);

        //Sending text to username input
        driver.findElement(By.xpath("//*[@id='login-username']")).sendKeys("ABC");

        //clicking on next
        driver.findElement(By.xpath("//*[@value='Next']")).click();
        Thread.sleep(2500);

        //capturing error message and storing in a string
        String errMessage = driver.findElement(By.xpath("//*[@id='username-error']")).getText();

        //Hard Coded message
        String err = "Sorry, we don't recognize this email.";

        // Using assert to check if they are equal
        Assert.assertEquals(err,errMessage);
    }

}
