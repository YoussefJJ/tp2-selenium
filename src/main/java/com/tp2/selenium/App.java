package com.tp2.selenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {
    	//Initializing Chrome Driver
    	WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        
        //Configuring driver timeouts
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(2));
        
        //Initializing Javascript Executor
        JavascriptExecutor js = (JavascriptExecutor) driver;
        
        //Go to https://www.tunisianet.com.tn
        driver.get("https://www.tunisianet.com.tn");
		Thread.sleep(2000);
		
		//Click on User Icon
        driver.findElement(By.className("user-info")).click();
        Thread.sleep(2000);
        
        //Click on Login option from the dropdown menu
        driver.findElement(By.cssSelector(".user-down > li > a > span")).click();
        Thread.sleep(1000);
        
        //Fill the field with their respective credentials
        driver.findElement(By.name("email")).sendKeys("tp.selenium@gmail.com");
        Thread.sleep(1000);
        driver.findElement(By.name("password")).sendKeys("tpselenium");
        Thread.sleep(1000);
        
        //Click on Login
        driver.findElement(By.id("submit-login")).click();
        Thread.sleep(1000);
        
        //Type the search query
        driver.findElement(By.id("search_query_top")).sendKeys("Macbook Pro M1 13.3");
        Thread.sleep(1000);
        
        //Click on Search Icon
        driver.findElement(By.name("submit_search")).click();
        Thread.sleep(3000);
        
        //Select and click the first Product on the list
        driver.findElement(By.className("product-title")).click();
        Thread.sleep(2000);
        
        //Scroll down
        js.executeScript("window.scrollBy(0,200)", "");
        
        //After clicking on add to cart button await the modal
        WebDriverWait wait = new WebDriverWait(driver, 30);
        driver.findElement(By.className("add-to-cart")).click();
        WebElement orderButton = driver.findElement(By.cssSelector("a.btn-block"));
        wait.until(ExpectedConditions.visibilityOf(orderButton));
        
        //Click the Order Button
        orderButton.click();
        
        //Confirm the cart and proceed with the order
        driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[3]/div/a")).click();
        
        //Exit Chrome Driver
        Thread.sleep(5000);
        driver.quit();
    }
}
