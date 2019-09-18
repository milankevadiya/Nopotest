package Nopo;


import com.sun.org.apache.bcel.internal.generic.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class EndToEndTesting extends Utils {
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


        //click on register button
        clickElement(By.xpath("//a[@class='ico-register']"));
        //enter firstname
       // driver.findElement(By.id("FirstName")).sendKeys("Milan");
        enterText(By.id("FirstName"), loadProbs.getProperty("FirstName"));
        clearinputfield(By.id("FirstName"));
        enterText(By.id("FirstName"), loadProbs.getProperty("FirstName"));
        enterText(By.id("LastName"),loadProbs.getProperty("LastName"));
        //Enter lastname
        //driver.findElement(By.id("LastName")).sendKeys("kevadiya");

        //Enter email
        selectVisibleValue(By.xpath("//select[@name=\"DateOfBirthDay\"]"),"2");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //selectVisibleValue(By.xpath("//select[@name=\"DateOfBirthMonth\"]"),"September");
       // driver.findElement(By.xpath("//select[@name=\"DateOfBirthMonth\"]")).click();
        selectVisibleText(By.xpath("//select[@name=\"DateOfBirthMonth\"]"),"September");
        selectbyIndex(By.xpath("//select[@name=\"DateOfBirthYear\"]"),5);
        driver.findElement(By.name("Email")).sendKeys("milan"+randomdate()+"@gmail.com");
        //Enter password
        System.out.println("milan"+randomdate()+"@gmail.com");
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("1234567");
        //Enter Confirm password
        driver.findElement(By.xpath("//input[@name='ConfirmPassword']")).sendKeys("1234567");
        //click on register button
        driver.findElement(By.id("register-button")).click();
        String Expectedmsg= "Your registration completed";

        String Actualmsg = driver.findElement(By.xpath("//div[@class='result']")).getText();
        Assert.assertEquals(Actualmsg.contains("complete"),Expectedmsg.contains("complete"));

    }

    @Test
    public  void referefriend(){
        registration();
        driver.findElement(By.xpath("//img[@alt='nopCommerce demo store']")).click();
        driver.findElement(By.linkText("Apple MacBook Pro 13-inch")).click();
        //click on 'Email a friend' link
        driver.findElement(By.xpath("//input[@value='Email a friend']")).click();
        //Enter friend Email id
        driver.findElement(By.name("FriendEmail")).sendKeys("devanshujjoshi@gmail.com");
        //Enter personal message
        driver.findElement(By.xpath("//textarea[@placeholder='Enter personal message (optional).']")).sendKeys("check your email id for message");
        //click on send email
        driver.findElement(By.name("send-email")).click();
        //define Expected message
        String Expectedmsg = "Your message has been sent.";
        //Get Actual message text
        String Actualmsg = driver.findElement(By.xpath("//div[@class=\"result\"]")).getText();
        //compare Actual value and Expected value
        Assert.assertEquals(Actualmsg,Expectedmsg);

    }
@Test
    public static void cameraphoto(){
        //click on electronics link
        driver.findElement(By.linkText("Electronics")).click();
        //click on link of camera&photo
        driver.findElement(By.linkText("Camera & photo")).click();
        //define Expected message
        String Expectedtitle = "Camera & photo";
        //Get Actual message text
        String Actualtitle = driver.findElement(By.xpath("//div[@class='page-title']")).getText();
        //compare Actual value and Expected value
        Assert.assertEquals(Actualtitle,Expectedtitle);

}

@Test
    public static void jewelleryfilter(){
        //click on Jewelry link from menu
        driver.findElement(By.linkText("Jewelry")).click();
        //click on price category "$700.00 - $3,000.00"
        driver.findElement(By.linkText("$700.00 - $3,000.00")).click();
        //define Expected title
        String Expectedtitle="$700.00 - $3,000.00";
        //Get Actual title
        String Actualtitle= driver.findElement(By.xpath("//span[@class='item']")).getText();
        //compare Actual value and Expected value
        Assert.assertEquals(Actualtitle,Expectedtitle);
        //Get product price
        String Productprice = driver.findElement(By.xpath("//span[@class='price actual-price']")).getText();
        //remove $ from price
        String price1 =String.valueOf(Productprice.replace("$",""));
        //remove"," from price
        String price2 =String.valueOf(price1.replace(",",""));
        //convert string value in to double
        double price = Double.valueOf(price2);
        //condition for price is between 700 to 3000
        Assert.assertTrue(price >=700 && price <= 3000);
}
    @Test
    public static void producttocart(){
        //Click on Books category link from menu
        driver.findElement(By.linkText("Books")).click();
        //click on 'Add to cart' for product 1
        driver.findElement(By.xpath("//div[@data-productid=\"37\"]/div[2]/div[3]/div[2]//input[@value=\"Add to cart\"]")).click();
        //click on 'cancel' button of confirmation message
        driver.findElement(By.xpath("//span[@class='close']")).click();
        //set implicity wait for driver object
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        //click on 'Add to cart' for product 2
        driver.findElement(By.xpath("//div[@data-productid=\"38\"]/div[2]/div[3]/div[2]//input[@value=\"Add to cart\"]")).click();
        //set implicity wait for driver object
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.findElement(By.linkText("Shopping cart")).click();
        //define Expected product title
        String ExpectedProduct1 = "Fahrenheit 451 by Ray Bradbury";
        //Get Actutal product title
        String ActualProduct1 = driver.findElement(By.xpath("//tbody/tr[1]/td[4]/a[@class='product-name']")).getText();
        //compare Actual and expected product title
        Assert.assertEquals(ActualProduct1,ExpectedProduct1);
        //define Expected product title
        String ExpectedProduct2 = "First Prize Pies";
        //Get Actutal product title
        String ActualProduct2 = driver.findElement(By.xpath("//tbody/tr[2]/td[4]/a[@class='product-name']")).getText();
        //compare Actual and expected product title
        Assert.assertEquals(ActualProduct2,ExpectedProduct2);

    }
}

