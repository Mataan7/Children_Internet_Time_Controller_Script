import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


import net.bytebuddy.implementation.bytecode.Throw;
import org.openqa.selenium.By;

import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import  org.openqa.selenium.WebDriverException;

public class Main {

    public static void main(String[] args) {
        Main r=new Main();
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\sahal\\Desktop\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        while (true){

            try {
                r.WifiBlockingMethod(driver);
            } catch (Exception e) {
                try{

                    driver.quit();
                    driver=new ChromeDriver();
                    System.out.println(e.getClass().getSimpleName());}
                catch (Exception i){
                    //throw e;
                    System.out.println("Router Disconnected :  Reconnect .............");
                }

            }
        }

    }
    //TimeoutException

     public synchronized void WifiBlockingMethod(WebDriver driver){



            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

         while (true){
             System.out.println("-----------------------");
             driver.get("http://192.168.1.1");
             //driver.manage().window().maximize();
             driver.findElement(By.name("http_username")).sendKeys("UserName");
             driver.findElement(By.name("http_passwd")).sendKeys("Password");
             Calendar dateandTime = Calendar.getInstance();


             driver.findElement(By.className("button")).click();


             driver.findElement(By.xpath("/html/body/form/center/table[2]/tbody/tr[3]/td/table/tbody/tr[2]/td[2]")).click();
             driver.findElement(By.xpath("/html/body/form/center/table[2]/tbody/tr[4]/td/table/tbody/tr/td/table/tbody/tr/td[5]")).click();

           String pmoram;
           int clock;



                 if(dateandTime.get(Calendar.HOUR_OF_DAY)<=11){
                     if(dateandTime.get(Calendar.HOUR_OF_DAY)==0){
                         clock=12;
                         pmoram="am";
                     }
                     else {
                         clock=dateandTime.get(Calendar.HOUR_OF_DAY);
                         pmoram="am";
                     }
                 }
                 else {
                     if(Calendar.HOUR_OF_DAY==12){
                         clock=12;
                         pmoram="pm";
                     }
                     else {
                     clock=dateandTime.get(Calendar.HOUR_OF_DAY)-12;
                     pmoram="pm";
                     }

                 }



             System.out.println("Clock :"+clock+" "+pmoram);
             if (clock>=6 && pmoram=="pm" && clock<11 && pmoram=="pm" ) {
                 driver.findElement(By.xpath("/html/body/form/center/table[3]/tbody/tr/td[1]/table/tbody/tr[2]/td[3]/table/tbody/tr/td[2]/input")).click();

             } else {
                 if(driver.findElement(By.xpath("/html/body/form/center/table[3]/tbody/tr/td[1]/table/tbody/tr[2]/td[3]/table/tbody/tr/td[2]/input")).isSelected()){
                     driver.findElement(By.xpath("/html/body/form/center/table[3]/tbody/tr/td[1]/table/tbody/tr[2]/td[3]/table/tbody/tr/td[1]/input")).click();
                     driver.switchTo().alert().accept();
                 }

             }
             System.out.println("yes it work : "+dateandTime.get(Calendar.HOUR_OF_DAY)+":"+dateandTime.get(Calendar.MINUTE));
             if (driver.findElement(By.xpath("/html/body/form/center/table[3]/tbody/tr/td[1]/table/tbody/tr[2]/td[3]/table/tbody/tr/td[2]/input")).isSelected()) {
                 System.out.println("Disabaled");
             } else {
                 System.out.println("Enabled");
             }


             driver.findElement(By.xpath("//*[@id=\"divBT1\"]")).click();
//        System.out.println(dateandTime.get(Calendar.HOUR_OF_DAY));

             try {
                 this.wait(60000);
             } catch (InterruptedException e) {

                 throw new RuntimeException(e);

             }

         }

     }

}