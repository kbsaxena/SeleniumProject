package com;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

public class MyChannelRunner_Dynamic {
    static final List<String> playlist = new ArrayList<>();
    static final long ONE_MINUTE = 60000;
    static final String YES = "y";
    
    public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
        System.setProperty("webdriver.opera.driver", "operadriver.exe");
        
        try(BufferedReader objReader = new BufferedReader(new FileReader("Playlist.txt"))){
            String strCurrentLine;
            while((strCurrentLine = objReader.readLine()) != null) {
                playlist.add(strCurrentLine);
            }
            
            System.out.println("Playlist Added");
        } catch(Exception e) {
            System.out.println("Playlist Missing !!!!!!");
        }

        WebDriver driver = null;
        WebDriver driver1 = null;
        WebDriver driver2 = null;
        
        ArrayList<String> tabs = null;
        ArrayList<String> tabs1 = null;
        ArrayList<String> tabs2 = null;
        
        int i = 1;
        
        boolean chromeEnabled = args[0].equalsIgnoreCase(YES);
        boolean mozillaEnabled = args[1].equalsIgnoreCase(YES);
        boolean operaEnabled = args[2].equalsIgnoreCase(YES);
        boolean chromeMinimize = args[3].equalsIgnoreCase(YES);
        boolean mozillaMinimize = args[4].equalsIgnoreCase(YES);
        boolean operaMinimize = args[5].equalsIgnoreCase(YES);
        boolean enableClick = args[6].equalsIgnoreCase(YES);
        double time = Double.valueOf(args[7]);
        long milliSeconds = (long) (time * ONE_MINUTE);
        
        System.out.println("Each Playlist will run for - " + (((float)milliSeconds)/ONE_MINUTE));
        
        try {
            if(chromeEnabled) {
                driver = new ChromeDriver();
                initialSetup(driver, enableClick);
    
                for(String item : playlist) {
                    if(!playlist.get(0).equalsIgnoreCase(item)) {
                        tabs = run(driver, tabs, true, playlist.indexOf(item) , item);
                    }
                }
                System.out.println("Started Chrome");
            }
            
            Thread.sleep(2000);
            
            if(mozillaEnabled) {
                Thread.sleep(2000);
                driver1 = new FirefoxDriver();
                initialSetup(driver1, enableClick);
                
                for(String item : playlist) {
                    if(!playlist.get(0).equalsIgnoreCase(item)) {
                        tabs1 = run(driver1, tabs1, true, playlist.indexOf(item) , item);
                    }
                }
                System.out.println("Started Mozilla");
            }
            
            Thread.sleep(2000);
            
            if(operaEnabled) {
                
                driver2 = new OperaDriver();
                initialSetup(driver2, enableClick);
    
                for(String item : playlist) {
                    if(!playlist.get(0).equalsIgnoreCase(item)) {
                        tabs2 = run(driver2, tabs2, true, playlist.indexOf(item) , item);
                    }
                }
                System.out.println("Started Opera");
            }
            
            while (i <= 1000) {
                System.out.println("Running Step = " + i);
                Thread.sleep(milliSeconds);
                
                i++;
                
                if(chromeEnabled) {
                    driver.manage().deleteAllCookies();
                    Thread.sleep(5000);
                    for(String item : playlist) {
                        run(driver, tabs, false, playlist.indexOf(item), item);
                    }
                    if(chromeMinimize) {
                        driver.manage().window().setPosition(new Point(-2000, 0));
                    }
                    System.out.println("Chrome Running Step - " + i);
                }
                
                if(mozillaEnabled) {
                    driver1.manage().deleteAllCookies();
                    Thread.sleep(5000);
                    for(String item : playlist) {
                        run(driver1, tabs1,false, playlist.indexOf(item) , item);
                    }
                    if(mozillaMinimize) {
                        driver1.manage().window().setPosition(new Point(-2000, 0));
                    }
                    System.out.println("Mozilla Running Step - " + i);
                }
                
                if(operaEnabled) {
                    driver2.manage().deleteAllCookies();
                    Thread.sleep(5000);
                    for(String item : playlist) {
                        run(driver2, tabs2,false, playlist.indexOf(item) , item);
                    }
                    if(operaMinimize) {
                        driver2.manage().window().setPosition(new Point(-2000, 0));
                    }
                    System.out.println("Opera Running Step - " + i);
                }
            }
            
            if(chromeEnabled) {
                driver.close();
            }
            if(mozillaEnabled) {
                driver1.close();
            }
            if(operaEnabled) {
                driver2.close();
            }
        } catch (Exception e) {
            System.out.println("Exception" + e);
            e.printStackTrace();
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
        driver.get(playlist.get(0));
        Thread.sleep(5000);
        driver.manage().deleteAllCookies();
        if(enableClick) {
            driver.findElement(By.xpath("//button[@aria-label='Play']")).click();
        }
        driver.findElement(By.xpath("//button[@aria-label='Mute (m)']")).click();
    }

}