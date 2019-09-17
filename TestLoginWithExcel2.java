/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacebookLoginTestWithExcel;

import facebooklogin.FacebookLoginWithExcel;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import screenShots.TakeShots;

/**
 *
 * @author RLAMA
 */
public class TestLoginWithExcel2 
{
     WebDriver driver;
     @Test
     public void testbrowserInvoker()
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
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        System.out.println("The title of the webpage is: " + driver.getTitle());
        Assert.assertEquals("Google", driver.getTitle());    
     }
    @Test(dataProvider="facebookData")
    public void TestFacebooklogin(String username, String password)
    {
       
        System.setProperty("webdriver.chrome.driver","C:\\Users\\rlama\\Desktop\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.get("https://www.facebook.com/");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.id("email")).sendKeys(username);
        driver.findElement(By.id("pass")).sendKeys(password);
        driver.findElement(By.id("loginbutton")).click(); 
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    
    @DataProvider(name="facebookData")
    public String[][]passData()
    {
        FacebookLoginWithExcel excel = new FacebookLoginWithExcel(
                "C:\\\\Users\\\\rlama\\\\Documents\\\\Raj's Folder\\\\FacebookLogin2Version\\\\ExcelDataSheet\\\\LoginData.xlsx");
        int numRows = excel.displayNumRows(0);
        String[][] data = new String[numRows][2];
        
        for(int i = 0; i < numRows; i++)
        {
            data[i][0] = excel.getData(0, i, 0);
            data[i][1] = excel.getData(0, i, 1);  
        }
        return data;
    }
    
    @AfterMethod
    public void captureTestResult(ITestResult result)
    {
        if(ITestResult.SUCCESS == result.getStatus())
        {
            try 
            {
                TakeShots.captureScreenShots(driver, result.getTestName());
            } 
            catch (Exception ex) 
            {
                System.out.println("Error in capturing fail test cases");
            }
        }
        
    }
}

