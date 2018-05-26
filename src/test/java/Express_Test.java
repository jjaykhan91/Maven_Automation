import org.apache.bcel.generic.Select;
import org.omg.PortableServer.THREAD_POLICY_ID;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;


public class Express_Test {
    WebDriver driver;

    @BeforeSuite
    public void openBrowser() {
        //Defining the WebDriver.
        driver =Reusable.ReusableMethods.chromeDriver();
    }

    @Test(priority = 1)
        public void testCase1() throws InterruptedException {
        driver.navigate().to("http://www.Express.com");
        Thread.sleep(2500);

        Reusable.reusableMethods.Hover(driver, "//*[@href='/womens-clothing']");
        //Reusable.reusableMethods.Hover(driver,"//*[@class='subnav-target' and contains(@href, 'womens-clothing')]");
        Thread.sleep(2500);


        //Reusable.reusableMethods.Hover(driver,"//*[contains(@href, 'womens-clothing')]");
        WebElement element = driver.findElement(By.xpath("//*[@href='/womens-clothing/accessories/cat740011']"));
        Reusable.reusableMethods.mouseClick(driver, element);
        Reusable.reusableMethods.mouseClick(driver, element);
        Thread.sleep(2500);

        WebElement jewelry = driver.findElement(By.xpath("//*[text() = 'Jewelry']"));
        Reusable.reusableMethods.mouseClick(driver, jewelry);
        Thread.sleep(2000);

        WebElement earrings = driver.findElement(By.xpath("//*[text() = 'Earrings']"));
        Reusable.reusableMethods.mouseClick(driver, earrings);
        Thread.sleep(2000);

        Assert.assertEquals("Earrings for Women - Earrings", driver.getTitle());

        Reusable.reusableMethods.Hover(driver, "//*[@alt='large metal hoop earrings']");
        Thread.sleep(1000);

        WebElement expressView = driver.findElement(By.xpath("//*[@class='express-view']"));
        Reusable.reusableMethods.mouseClick(driver, expressView);
        Thread.sleep(2000);

        WebElement color = driver.findElement(By.xpath("//*[@aria-label='select color SHINY SILVER']"));
        Reusable.reusableMethods.mouseClick(driver, color);
        Thread.sleep(1000);

        WebElement addToBag = driver.findElement(By.xpath("//*[@data-add-to-bag-text='Add to Bag']"));
        Reusable.reusableMethods.mouseClick(driver, addToBag);
        Thread.sleep(1000);

        Reusable.reusableMethods.Hover(driver, "//*[@class='bag-icon']");
        WebElement checkout = driver.findElement(By.xpath("//*[text() = 'CHECKOUT']"));
        Reusable.reusableMethods.mouseClick(driver, checkout);
        Thread.sleep(2500);
    }

        @Test(dependsOnMethods = "testCase1") // or if not dependant(priority = 2)
            public void TestCase2() throws InterruptedException {
            Thread.sleep(2000);
            Assert.assertTrue(driver.findElement(By.xpath("//*[@class='_3OxzC']")).isDisplayed(), "My Shopping Bag Page");
            WebElement quantity = driver.findElement(By.id("qdd-0-quantity"));
            org.openqa.selenium.support.ui.Select dropDown = new org.openqa.selenium.support.ui.Select(quantity);
            dropDown.selectByIndex(1);
            Thread.sleep(2000);

            WebElement cartCheckout = driver.findElement(By.xpath("//*[@aria-label='Continue to Checkout']"));
            Reusable.reusableMethods.mouseClick(driver, cartCheckout);
            Thread.sleep(2000);

            WebElement guest = driver.findElement(By.xpath("//*[text() = 'Continue as Guest']"));
            Reusable.reusableMethods.mouseClick(driver, guest);
            Thread.sleep(2000);

            driver.findElement(By.name("firstname")).sendKeys("Junaid");
            driver.findElement(By.name("lastname")).sendKeys("Khan");
            driver.findElement(By.name("email")).sendKeys("jayibnkhan91@gmail.com");
            driver.findElement(By.name("confirmEmail")).sendKeys("jayibnkhan91@gmail.com");
            driver.findElement(By.name("phone")).sendKeys("9175325789");
            Thread.sleep(1000);

            WebElement cont = driver.findElement(By.xpath("//*[@type='submit']"));
            Reusable.reusableMethods.mouseClick(driver, cont);
            Thread.sleep(2500);
        }

        @Test(priority = 3)
                public void testCase3() throws InterruptedException {

        driver.findElement(By.name("shipping.line1")).sendKeys("288 E 8th Street");
        driver.findElement(By.name("shipping.postalCode")).sendKeys("11218");
        driver.findElement(By.name("shipping.city")).sendKeys("Brooklyn");

        WebElement state = driver.findElement(By.name("shipping.state"));
        org.openqa.selenium.support.ui.Select statedropDown = new org.openqa.selenium.support.ui.Select(state);
        statedropDown.selectByIndex(33);

        Boolean elementState = driver.findElement(By.xpath("//*[@type='checkbox']")).isSelected();
        System.out.println("Is Element Selected ? " + elementState);

        WebElement lastContinue = driver.findElement(By.xpath("//*[@type='submit']"));
        Reusable.reusableMethods.mouseClick(driver,lastContinue);
        Thread.sleep(2500);

        String contactInfo = driver.findElement(By.xpath("//*[@id='contact-information']")).getText();
        System.out.println(contactInfo);



    }



    @AfterSuite
    public void closeBrowser() {
        //Quitting the driver
        //driver.quit();

    }






}
