package com;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

public class MyChannelRunner_bkp {
    
    static final String playListMot = "https://www.youtube.com/watch?v=MGgCJEcUUI8&list=PLgjDX8lxSFZLxAzxls4c-QJW-3fOBrO2y";
    static final String playListWfh = "https://www.youtube.com/watch?v=dxeNvhY2HDw&list=PLgjDX8lxSFZJmQxgTjeOrwWuj25RtafgS";
    static final String playListfunny = "https://www.youtube.com/watch?v=rahDIi4dbLY&list=PLgjDX8lxSFZJI6Ty4BxrMU_MSuJP_Km6B";
    static final String playListfunnyAct = "https://www.youtube.com/watch?v=ZSOJU2wQWNo&list=PLgjDX8lxSFZL6UrOYxmsgFNizXeBu6r3n";

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
        System.setProperty("webdriver.opera.driver", "operadriver.exe");

        WebDriver driver = null;
        WebDriver driver1 = null;
        WebDriver driver2 = null;
        
        ArrayList<String> tabs = null;
        ArrayList<String> tabs1 = null;
        ArrayList<String> tabs2 = null;
        
        int i = 0;
        final long ONE_MINUTE = 60000;
        
        boolean chromeEnabled = Boolean.valueOf(args[0]);
        boolean mozillaEnabled = Boolean.valueOf(args[1]);
        boolean operaEnabled = Boolean.valueOf(args[2]);
        boolean chromeMinimize = Boolean.valueOf(args[3]);
        boolean mozillaMinimize = Boolean.valueOf(args[4]);
        boolean operaMinimize = Boolean.valueOf(args[5]);
        boolean enableClick = Boolean.valueOf(args[6]);
        
        try {
            if(chromeEnabled) {
                driver = new ChromeDriver();
                initialSetup(driver, enableClick);
    
                tabs = run(driver, tabs, true, 1, playListMot);
                tabs = run(driver, tabs, true, 2, playListfunny);
                tabs = run(driver, tabs, true, 3, playListfunnyAct);
            }
            
            Thread.sleep(2000);
            
            if(mozillaEnabled) {
                driver1 = new FirefoxDriver();
                initialSetup(driver1, enableClick);
                
                tabs1 = run(driver1, tabs1, true, 1, playListMot);
                tabs1 = run(driver1, tabs1, true, 2, playListfunny);
                tabs1 = run(driver1, tabs1, true, 3, playListfunnyAct);
            }
            
            Thread.sleep(2000);
            
            if(operaEnabled) {
                driver2 = new OperaDriver();
                initialSetup(driver2, enableClick);
    
                tabs2 = run(driver2, tabs2, true, 1, playListMot);
                tabs2 = run(driver2, tabs2, true, 2, playListfunny);
                tabs2 = run(driver2, tabs2, true, 3, playListfunnyAct);
            }
            
            while (i <= 100) {
                System.out.println("Running Step = "+ i);
                Thread.sleep(9 * ONE_MINUTE);
                i++;
                
                if(chromeEnabled) {
                    driver.manage().deleteAllCookies();
                    run(driver, tabs,false, 0, playListWfh);
                    run(driver, tabs,false, 1, playListMot);
                    run(driver, tabs,false, 2, playListfunny);
                    run(driver, tabs,false, 3, playListfunnyAct);
                    if(chromeMinimize) {
                        driver.manage().window().setPosition(new Point(-2000, 0));
                    }
                }
                
                if(mozillaEnabled) {
                    driver1.manage().deleteAllCookies();
                    run(driver1, tabs1,false, 0, playListWfh);
                    run(driver1, tabs1,false, 1, playListMot);
                    run(driver1, tabs1,false, 2, playListfunny);
                    run(driver1, tabs1,false, 3, playListfunnyAct);
                    if(mozillaMinimize) {
                        driver1.manage().window().setPosition(new Point(-2000, 0));
                    }
                }
                
                if(operaEnabled) {
                    driver2.manage().deleteAllCookies();
                    run(driver2, tabs2,false, 0, playListWfh);
                    run(driver2, tabs2,false, 1, playListMot);
                    run(driver2, tabs2,false, 2, playListfunny);
                    run(driver2, tabs2,false, 3, playListfunnyAct);
                    if(operaMinimize) {
                        driver2.manage().window().setPosition(new Point(-2000, 0));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Exception" + e);
        } finally {
            // close
            System.exit(0);
            if(chromeEnabled) {
                driver.close();
            }
            if(mozillaEnabled) {
                driver1.close();
            }
            if(operaEnabled) {
                driver2.close();
            }
        }

    }
    
    public static ArrayList<String> run(WebDriver driver,ArrayList<String> tabs, boolean opentab, int tab, String url) {
        if(opentab) {
            ((JavascriptExecutor) driver).executeScript("window.open()");
            tabs = new ArrayList<String>(driver.getWindowHandles());
        }
        driver.switchTo().window(tabs.get(tab));
        driver.get(url);
        return tabs;
    }
    
    public static void initialSetup(WebDriver driver, boolean enableClick) throws InterruptedException {
        driver.manage().window().maximize();
        driver.get(playListWfh);
        Thread.sleep(5000);
        driver.manage().deleteAllCookies();
        if(enableClick) {
            driver.findElement(By.xpath("//button[@aria-label='Play']")).click();
        }
        driver.findElement(By.xpath("//button[@aria-label='Mute (m)']")).click();
    }

}