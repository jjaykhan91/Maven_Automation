import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class Screenshot {
    WebDriver driver;


    @BeforeSuite
    public void openBroweser(){
        driver = Reusable.reusableMethods.chromeDriver();
    }
    @Test
    public void gettingScreenshot() throws IOException, InterruptedException {
        driver.navigate().to("http://www.WWE.com");
        //Reusable.reusableMethods.getScreenshot(driver,"C:\\Users\\Jayib\\Desktop\\IntelliJ\\Selenium\\ExtReports\\wweHomePage.png");

        List<WebElement> wweTabList = driver.findElements(By.xpath("//*[@class='leaf']"));
        System.out.println("The Tabs total is " + wweTabList.size());

        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("scroll (0,10000)");
        Thread.sleep(2500);

        for (int i = 0; i<wweTabList.size();i++){
            System.out.println("The tabs are " + wweTabList.get(i).getText());
        }

        driver.findElement(By.xpath("//*[text() = 'Superstars']")).click();
        Thread.sleep(2500);

        Reusable.reusableMethods.getScreenshot(driver,"C:\\Users\\Jayib\\Desktop\\IntelliJ\\Selenium\\ExtReports\\wweSuperstarPage.png");
    }
    @AfterSuite
    public void closeBrowser(){
        driver.quit();
    }
}
