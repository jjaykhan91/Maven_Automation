package Count_USPS_Tabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class count_TABS {

    @Test
    public void testScenarios() throws InterruptedException {
        //Open Chrome Browser
        WebDriver driver = Reusable.reusableMethods.chromeDriver();
        //Navigating to page
        driver.navigate().to("http://www.USPS.com");
        //Wait
        Thread.sleep(2500);
        //Get all the  navigation tab counts
        List<WebElement> tabCount = driver.findElements(By.xpath("//*[@class='menuitem']"));
        System.out.println("The Tab count is " + tabCount.size());

        //iterate throw list and capture name

        for(int i = 0;i< tabCount.size();i++){
            String getName = driver.findElements(By.xpath("//*[@class='menuitem']")).get(i).getText();
            System.out.println("The Tabs are " + getName);
        }//End of Loop
        driver.quit();

    }//End of Test




}//End of class
