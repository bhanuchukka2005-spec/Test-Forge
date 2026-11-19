package com.testforge.automation;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentPortalSeleniumTest {
    private WebDriver driver;
    private final String baseUrl = System.getProperty("testforge.url", "http://localhost:8080");

    @BeforeEach void setUp() {
        ChromeOptions options = new ChromeOptions();
        if (Boolean.getBoolean("headless")) options.addArguments("--headless=new");
        options.addArguments("--window-size=1440,1000");
        driver = new ChromeDriver(options); // Selenium Manager resolves the driver.
    }
    @AfterEach void tearDown() { if (driver != null) driver.quit(); }

    @Test @Order(1) void studentCanSignInAndSeeDashboard() {
        driver.get(baseUrl);
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("student");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("Student@123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dashboard")));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("studentName"), "Aarav"));
        Assertions.assertTrue(driver.findElement(By.id("dashboard")).isDisplayed());
    }

    @Test @Order(2) void invalidCredentialsShowAnError() {
        driver.get(baseUrl);
        driver.findElement(By.id("username")).clear(); driver.findElement(By.id("username")).sendKeys("unknown_student");
        driver.findElement(By.id("password")).clear(); driver.findElement(By.id("password")).sendKeys("wrong-password");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("loginMessage"), "Invalid username or password"));
    }
}
