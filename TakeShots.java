
package screenShots;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;


/**
 *
 * It takes the screen shots of the designated web pages and store it in a particular
 * folder by assigning it a name. 
 */
public class TakeShots 
{ 
    public static String captureScreenShots(WebDriver driver,String screenShotName) throws IOException
    {
        TakesScreenshot screen = (TakesScreenshot)driver;
        File src = screen.getScreenshotAs(OutputType.FILE);
        String pathName = System.getProperty("user.dir") + "/screenShots/" + screenShotName + ".png";
        File destination = new File(pathName);
        try
        {
        
            FileHandler.copy(src, destination);
            
        }
        catch(Exception e)
        {
            System.out.println("Error while taking screenshots.");
            
        }
        return pathName;
    }
}
