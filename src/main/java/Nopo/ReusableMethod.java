package Nopo;

// reusable methods from utils class and used data from dta config file

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ReusableMethod extends Utils {
    LoadProbs loadProbs=new LoadProbs();
    //protected static WebDriver driver;
    public static String randomdate(){
        //for create randomdate
        DateFormat format=new SimpleDateFormat("ddMMyyHHmmss");
        return format.format(new Date());
    }
    @BeforeMethod
    public void setup (){
        System.setProperty("webdriver.chrome.driver","src\\main\\Resource\\BrowserDriver\\chromedriver.exe");
        //open the browser
        driver = new ChromeDriver();
        //maximize the window
        driver.manage().window().fullscreen();
        //set implicity wait for driver object
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //open the website
        //driver.get("https://demo.nopcommerce.com/");
        driver.get(loadProbs.getProperty("url"));


    }
    @AfterMethod
    public void browserclose(){
        // close browser
        driver.quit();

    }
    @Test
    public  void registration(){
        // scroll window
        scrollingWindow();
        //click on register button
        clickElement(By.xpath("//a[@class='ico-register']"));
        //scroll till element Register
        scrollTillElement(By.id("register-button"));
        elementPresent(By.xpath("//span[@class='male']"));
        //enter firstname
        // driver.findElement(By.id("FirstName")).sendKeys("Milan");
        enterText(By.id("FirstName"), loadProbs.getProperty("FirstName"));
        clearinputfield(By.id("FirstName"));
        enterText(By.id("FirstName"), loadProbs.getProperty("FirstName"));
        enterText(By.id("LastName"),loadProbs.getProperty("LastName"));
        //Enter lastname
        //driver.findElement(By.id("LastName")).sendKeys("kevadiya");

        //elementPresent(By.xpath("//select[@name=\"DateOfBirthDay\"]"));

        selectVisibleValue(By.xpath("//select[@name=\"DateOfBirthDay\"]"),"2");

        selectVisibleText(By.xpath("//select[@name=\"DateOfBirthMonth\"]"),"September");
        selectbyIndex(By.xpath("//select[@name=\"DateOfBirthYear\"]"),5);
        //Enter email
        //driver.findElement(By.name("Email")).sendKeys("milan"+randomdate()+"@gmail.com");
        enterText(By.name("Email"),loadProbs.getProperty("Email")+randomdate()+"@gmail.com");
        System.out.println("milan"+randomdate()+"@gmail.com");
        //Enter password
        // driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("1234567");
        enterText(By.xpath("//input[@id='Password']"),loadProbs.getProperty("Password"));
        //Enter Confirm password
        //driver.findElement(By.xpath("//input[@name='ConfirmPassword']")).sendKeys("1234567");
        enterText(By.xpath("//input[@name='ConfirmPassword']"),loadProbs.getProperty("ConfirmPassword"));
        //click on register button
       // driver.findElement(By.id("register-button")).click();
        clickElement(By.id("register-button"));
        String Expectedmsg= "Your registration completed";
        elementDisplayed(By.xpath("//div[@class='result']"));

        String Actualmsg = driver.findElement(By.xpath("//div[@class='result']")).getText();
        waitForElementVisible(By.xpath("//input[@name='register-continue']"),30);
        getAttribute(By.xpath("//input[@name='register-continue']"),"name");
        cssValue(By.xpath("//input[@name='register-continue']"),"background-color");
        cssValue(By.xpath("//input[@name='register-continue']"),"color");
        cssValue(By.xpath("//input[@name='register-continue']"),"font-size");
        cssValue(By.xpath("//div[@class='result']"),"color");
        Assert.assertEquals(Actualmsg.contains("complete"),Expectedmsg.contains("complete"));


    }


}

