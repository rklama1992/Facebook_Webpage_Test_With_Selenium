/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacebookLoginTestWithExcel;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.mail.EmailException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import screenShots.TakeShots;


/**
 *
 * @author RLAMA
 */
public class TestsWithHtmlReports 
{
     WebDriver driver;
     ExtentReports extent;
     ExtentTest test;
     
     @BeforeTest
     public void setupReport() // It sets up report and its path.
     {
         try
         {
            extent = new ExtentReports(
                        "C:\\Users\\rlama\\Documents\\Raj's Folder\\FacebookLogin2Version\\test-reports\\AutomationReport.html",true);
            extent.addSystemInfo("HostName","RajKumar")
                  .addSystemInfo("Environment", "QA")
                  .addSystemInfo("Username", "RajKumar");
            extent.loadConfig(new File(System.getProperty("user.dir")+"/extent-config.xml"));
            test.log(LogStatus.INFO,"Browser Started");
            test.log(LogStatus.INFO,"Application is running");  
         }
         catch(Exception e)
         {
             e.getStackTrace();
         }   
     }
     
    @BeforeMethod
    public void invokeBrowser()
    {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\rlama\\Desktop\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.get("https://www.facebook.com/");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.id("email")).sendKeys("lokumarlama89@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Selenium123");
        driver.findElement(By.id("loginbutton")).click(); 
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        
    }
    
    @Test
    public void verifyTitle()
    {
        test = extent.startTest("verifyTitle");// Test browser.   
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("Google"));       
    }
    @Test
    public void verifyUrl()
    {
        test = extent.startTest("verifyUrl");// Test URL 
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("https://www.facebook.com/"));     
    }
    @Test
    public void verifyPageSource()
    {
        test = extent.startTest("verifyPageSource");
        String pageSrc = driver.getPageSource();
        System.out.println("page source :" + pageSrc);
        Assert.assertTrue(pageSrc.contains("facebook"));    
    }
    
    @AfterMethod
    public void captureTestResult(ITestResult results) throws IOException, EmailException// It is called after executio of all the tests to capture the screen shots.
    {
        if(results.getStatus() == ITestResult.FAILURE)
        {
                String screenshotsPath = TakeShots.captureScreenShots(driver, results.getName());
                String image = test.addScreenCapture(screenshotsPath);
                test.log(LogStatus.FAIL, "Test Failed", image);
                //SendMail.Email();
        }
        else if(results.getStatus() == ITestResult.SUCCESS)
        {
           test.log(LogStatus.PASS, "Test Passed");  
        }
        else if(results.getStatus() == ITestResult.SKIP)
        {
            test.log(LogStatus.PASS, "Test Skipped"); 
            
        }         
        extent.flush();
        extent.endTest(test);
        driver.quit();
        
//        driver.get(
//                "C:\\Users\\rlama\\Documents\\Raj's Folder\\FacebookLogin2Version\\test-reports\\AutomationReport.html");
    }

}
