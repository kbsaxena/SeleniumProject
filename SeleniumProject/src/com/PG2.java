package com;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PG2 {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\selenium\\driver\\chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "D:\\selenium\\driver\\geckodriver.exe");

        WebDriver driver = null;
        WebDriver driver1 = null;
        int i = 0;
        final long ONE_MINUTE = 60000;
        ArrayList<String> tabs = null;
        ArrayList<String> tabs1 = null;

        try {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            //driver.get("chrome://settings/clearBrowserData");
            //driver.switchTo().activeElement();
            //driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
            driver.manage().deleteAllCookies();
            driver.get("https://www.youtube.com/watch?v=dxeNvhY2HDw&list=PLgjDX8lxSFZJmQxgTjeOrwWuj25RtafgS");  //wfh
            Thread.sleep(5000);
            driver.findElement(By.xpath("//button[@aria-label='Play']")).click();
            driver.findElement(By.xpath("//button[@aria-label='Mute (m)']")).click();

            ((JavascriptExecutor) driver).executeScript("window.open()");
            tabs = new ArrayList<String>(driver.getWindowHandles());
            run(driver, tabs, 1, "https://www.youtube.com/watch?v=MGgCJEcUUI8&list=PLgjDX8lxSFZLxAzxls4c-QJW-3fOBrO2y");
            //driver.switchTo().window(tabs.get(1));
            //driver.get("https://www.youtube.com/watch?v=MGgCJEcUUI8&list=PLgjDX8lxSFZLxAzxls4c-QJW-3fOBrO2y");  //Mot
            
            ((JavascriptExecutor) driver).executeScript("window.open()");
            tabs = new ArrayList<String>(driver.getWindowHandles());
            run(driver, tabs, 2, "https://www.youtube.com/watch?v=rahDIi4dbLY&list=PLgjDX8lxSFZJI6Ty4BxrMU_MSuJP_Km6B");
            //driver.switchTo().window(tabs.get(2));
            //driver.get("https://www.youtube.com/watch?v=rahDIi4dbLY&list=PLgjDX8lxSFZJI6Ty4BxrMU_MSuJP_Km6B");    //funny

            Thread.sleep(2000);

            driver1 = new FirefoxDriver();
            driver1.manage().window().maximize();
            driver1.manage().deleteAllCookies();
            driver.get("https://www.youtube.com/watch?v=dxeNvhY2HDw&list=PLgjDX8lxSFZJmQxgTjeOrwWuj25RtafgS");
            Thread.sleep(5000);
            driver1.findElement(By.xpath("//button[@aria-label='Play']")).click();
            driver1.findElement(By.xpath("//button[@aria-label='Mute (m)']")).click();

            ((JavascriptExecutor) driver1).executeScript("window.open()");
            tabs1 = new ArrayList<String>(driver1.getWindowHandles());
            driver1.switchTo().window(tabs1.get(1));
            driver1.get("https://www.youtube.com/watch?v=gshnnCzp8G0&list=UUVsOVtrAKKdOs7leeJpaHmA&index=6");

            while (i <= 2) {
                Thread.sleep(2 * ONE_MINUTE);
                i++;
                driver.manage().deleteAllCookies();
                //driver1.manage().deleteAllCookies();

                driver.switchTo().window(tabs.get(0));
                driver.get("https://www.youtube.com/watch?v=rahDIi4dbLY&list=UUVsOVtrAKKdOs7leeJpaHmA&index=1");
                
                driver.switchTo().window(tabs.get(1));
                driver.get("https://www.youtube.com/watch?v=gshnnCzp8G0&list=UUVsOVtrAKKdOs7leeJpaHmA&index=6");
                
                driver.switchTo().window(tabs.get(2));
                driver.get("https://www.youtube.com/watch?v=gshnnCzp8G0&list=UUVsOVtrAKKdOs7leeJpaHmA&index=6");
                
                //driver1.switchTo().window(tabs1.get(0));
                //driver1.get("https://www.youtube.com/watch?v=rahDIi4dbLY&list=UUVsOVtrAKKdOs7leeJpaHmA&index=1");
                
                //driver1.switchTo().window(tabs1.get(1));
                //driver1.get("https://www.youtube.com/watch?v=gshnnCzp8G0&list=UUVsOVtrAKKdOs7leeJpaHmA&index=6");
            }
        } catch (Exception e) {
            System.out.println("Exception" + e);
        } finally {
            // close
            System.exit(0);
            driver.close();
            //driver1.close();
        }

    }
    
    public static void run(WebDriver driver, ArrayList<String> tabs, int tab, String url) {
        driver.switchTo().window(tabs.get(2));
        driver.get("https://www.youtube.com/watch?v=gshnnCzp8G0&list=UUVsOVtrAKKdOs7leeJpaHmA&index=6"); 
    }

}