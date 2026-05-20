package com.saucedemo.automation.SauceDemoProject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        // Read a custom property passed from the command line (defaults to "false")
        String runHeadless = System.getProperty("headless", "false");

        if (runHeadless.equalsIgnoreCase("true")) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            System.out.println("🤖 Running on Cloud/Jenkins: Headless Mode Enabled.");
        } else {
            System.out.println("💻 Running on Local Machine: Visual Browser Enabled.");
        }

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void testWebLogin() {
        // Navigate to a reliable, open practice login portal
        driver.get("https://the-internet.herokuapp.com/login");

        // Locate elements and perform actions
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Validate the success banner is visible
        boolean isSuccessMessageDisplayed = driver.findElement(By.id("flash")).isDisplayed();
        Assert.assertTrue(isSuccessMessageDisplayed, "Error: Success login banner was not displayed.");
        
        // Validate the text contents of the alert banner
        String alertText = driver.findElement(By.id("flash")).getText();
        Assert.assertTrue(alertText.contains("You logged into a secure area!"), "Alert text does not match!");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}