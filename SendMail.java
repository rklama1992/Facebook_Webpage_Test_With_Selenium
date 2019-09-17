/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonsEmail;

import java.net.SocketException;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;





/**
 *
 * @author RLAMA
 */
public class SendMail 
{
    public static void main(String[] args) throws InterruptedException, EmailException, SocketException
    {
    
        System.out.println("Test has started.");

        Email email = new SimpleEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("lokumarlama89@gmail.com","Selenium123"));
        email.setSSLOnConnect(true);
        email.setFrom("lokumarlama89@gmail.com");
        email.setSubject("Selenium Test Report");
        email.setMsg("This is a test mail for Selenium WebDriver.");
        email.addTo("lraj1992@gmail.com");
        email.send();  
        
        System.out.println("Email has been sent.");
       

        
    }
//   public static void Email() throws EmailException
//   {
//              try
//       {
//        System.out.println("Test has started.");
//
//        Email email = new SimpleEmail();
//        email.setHostName("smtp.gmail.com");
//        email.setSmtpPort(465);
//        email.setAuthenticator(new DefaultAuthenticator("lokumarlama89@gmail.com", "Selenium123"));
//        email.setSSLOnConnect(true);
//        email.setFrom("lokumarlama89@gmail.com");
//        email.setSubject("Selenium Test Report");
//        email.setMsg("This is a test mail for Selenium WebDriver.");
//        email.addTo("rklama1992@gmail.com");
//        email.send();  
//        System.out.println("Email has been sent.");
//       
//           
//       }
//       catch(EmailException  e)
//       {
//           e.getStackTrace();
//       }
//       
// 
//   }
    
}
