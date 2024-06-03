package myapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;
import java.time.Duration;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;
import java.util.Scanner;
import static org.junit.Assert.assertTrue;

public class SimpleClass {

    public static void main(String[] args) {
        Properties config = new Properties();
        try (InputStream input = SimpleClass.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            config.load(input);
        } catch (IOException e) {
            System.out.println("Unable to load config file: " + e.getMessage());
            return;
        }

        // Set the path to the chromedriver executable
        System.setProperty("webdriver.chrome.driver", config.getProperty("chromeDriverPath"));

        // Initialize Chrome driver
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Open the URL
            driver.get("https://www.facebook.com/");
            System.out.println("Opened Facebook login page");

            enterText(driver, By.xpath("//input[@id='email']"), config.getProperty("email"));
            enterText(driver, By.xpath("//input[@id='pass']"), config.getProperty("password"));

            clickElement(wait, By.xpath("//a[@role='button']//div[@class='_9lsa']"));
            clickElement(driver, By.xpath("//button[@name='login']"));
            clickElement(wait, By.xpath("//a[contains(text(),'Forgot password?')]"));
            clickElement(wait, By.xpath("//a[@data-testid='open-registration-form-button']"));

            // Wait until the registration form is loaded
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email__']")));
            System.out.println("Registration form loaded");

            // Add sleep to ensure you can see the form
            Thread.sleep(5000); // Wait for 5 seconds

            clickElement(wait, By.xpath("//a[@class='_8esh' and @href='/pages/create/?ref_type=registration_form']"));

            // Add sleep to ensure you can see the result of clicking the link
            Thread.sleep(5000); // Wait for 5 seconds

            // Create a Scanner object to wait for user input
            Scanner scanner = new Scanner(System.in);
            System.out.println("Press Enter to close the browser...");

            // Wait for user to press Enter
            scanner.nextLine();

        } catch (NoSuchElementException e) {
            System.out.println("An element was not found: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            // Close the browser
            driver.quit();
            System.out.println("Closed the browser");
        }
    }

    private static void enterText(WebDriver driver, By by, String text) {
        try {
            WebElement element = driver.findElement(by);
            element.sendKeys(text);
            assertTrue(element.getAttribute("value").equals(text));
            System.out.println("Entered text: " + text);
        } catch (NoSuchElementException e) {
            System.out.println("Failed to locate element to enter text: " + e.getMessage());
        }
    }

    private static void clickElement(WebDriverWait wait, By by) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
            element.click();
            System.out.println("Clicked element: " + by);
        } catch (NoSuchElementException e) {
            System.out.println("Failed to locate element to click: " + e.getMessage());
        }
    }

    private static void clickElement(WebDriver driver, By by) {
        try {
            WebElement element = driver.findElement(by);
            element.click();
            System.out.println("Clicked element: " + by);
        } catch (NoSuchElementException e) {
            System.out.println("Failed to locate element to click: " + e.getMessage());
        }
    }
}
