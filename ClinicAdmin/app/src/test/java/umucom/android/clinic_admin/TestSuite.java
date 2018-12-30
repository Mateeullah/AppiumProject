package umucom.android.clinic_admin;



import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;




public class TestSuite {

    WebDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        // Created object of DesiredCapabilities class.
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Set android deviceName desired capability. Set your device name.
        capabilities.setCapability("deviceName", "NEXUS 5X API 25");

        // Set BROWSER_NAME desired capability. It's Android in our case here.
        //  capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");

        // Set android VERSION desired capability. Set your mobile device's OS version.
        capabilities.setCapability(CapabilityType.VERSION, "7.1.1");

        // Set android platformName desired capability. It's Android in our case here.
        capabilities.setCapability("platformName", "Android");

        // Set android appPackage desired capability. It is
        // com.android.calculator2 for calculator application.
        // Set your application's appPackage if you are using any other app.
        capabilities.setCapability("appPackage", "umucom.android.clinic_admin");

        // Set android appActivity desired capability. It is
        // com.android.calculator2.Calculator for calculator application.
        // Set your application's appPackage if you are using any other app.
        capabilities.setCapability("appActivity", "umucom.android.clinic_admin.Activities.AccountActivity");

        capabilities.setCapability("noReset", true);

        // Created object of RemoteWebDriver will all set capabilities.
        // Set appium server address and port number in URL string.
        // It will launch calculator app in android device.
        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);


    }

    @Test
    public void loginFailureScenarios() {
        // Click on DELETE/CLR button to clear result text box before running test.
        driver.findElements(By.xpath("//android.widget.EditText")).get(0).sendKeys("robot@gmail.com");
        driver.findElements(By.xpath("//android.widget.EditText")).get(1).sendKeys("robots");
        driver.findElements(By.xpath("//android.widget.Button")).get(0).click();
        try {
            Thread.sleep(1000);
        }
        catch (Exception e){}
        if(driver.findElements(By.xpath("//android.widget.Button")).get(0).getText().equals("MANAGE SLOT")){
            Assert.assertEquals("Error Message","Login Failure Test Failed");
        }
        else{
            Assert.assertEquals("Login Failure Test Passed","Login Failure Test Passed");
        }

        driver.findElements(By.xpath("//android.widget.EditText")).get(0).sendKeys("ali@gmail.com");
        driver.findElements(By.xpath("//android.widget.EditText")).get(1).sendKeys("robot");
        driver.findElements(By.xpath("//android.widget.Button")).get(0).click();
        try {
            Thread.sleep(1000);
        }
        catch (Exception e){}
        if(driver.findElements(By.xpath("//android.widget.Button")).get(0).getText().equals("MANAGE SLOT")){
            Assert.assertEquals("Error Message","Login Failure Test Failed");
        }

        else{
            Assert.assertEquals("Login Failure Test Passed","Login Failure Test Passed");
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void loginSuccessScenarios() {
        // Click on DELETE/CLR button to clear result text box before running test.
        driver.findElements(By.xpath("//android.widget.EditText")).get(0).sendKeys("robot@gmail.com");
        driver.findElements(By.xpath("//android.widget.EditText")).get(1).sendKeys("robot");
        driver.findElements(By.xpath("//android.widget.Button")).get(0).click();
        try {
            Thread.sleep(1000);
        }
        catch (Exception e){}
        if(driver.findElements(By.xpath("//android.widget.Button")).get(0).getText().equals("MANAGE SLOT")){
            Assert.assertEquals("Login Success Test passed","Login Success Test passed");
        }

        else{
            Assert.assertEquals("Error Message","Login Success Test Failed");
        }



        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void SignupSuccessScenarios() {
        // Click on DELETE/CLR button to clear result text box before running test.

        // goto sign up screen
        driver.findElements(By.xpath("//android.widget.Button")).get(1).click();
        try {
            Thread.sleep(500);
        }
        catch (Exception e){}

        //fill form
        driver.findElements(By.xpath("//android.widget.EditText")).get(3).sendKeys("ajmal123");
        driver.findElements(By.xpath("//android.widget.EditText")).get(0).sendKeys("ajmal");
        driver.findElements(By.xpath("//android.widget.EditText")).get(1).sendKeys("hotguy37@gmail.com");
        driver.findElements(By.xpath("//android.widget.EditText")).get(2).sendKeys("ajmal123");

        driver.navigate().back();
        //press sign up
        driver.findElements(By.xpath("//android.widget.Button")).get(0).click();

        try {
            Thread.sleep(1000);
        }
        catch (Exception e){}

        //go back

        driver.navigate().back();
        //fill same login info
        driver.findElements(By.xpath("//android.widget.EditText")).get(0).sendKeys("hotguy37@gmail.com");
        driver.findElements(By.xpath("//android.widget.EditText")).get(1).sendKeys("ajmal123");
        driver.findElements(By.xpath("//android.widget.Button")).get(0).click();
        try {
            Thread.sleep(1000);
        }
        catch (Exception e){}
        if(driver.findElements(By.xpath("//android.widget.Button")).get(0).getText().equals("MANAGE SLOT")){
            Assert.assertEquals("Sign up Success Test passed","Sign up Success Test passed");
        }

        else{
            Assert.assertEquals("Error Message","Sign up Success Test Failed");
        }

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    
    @After
    public void End() {
        driver.quit();
    }

    


}



