package myapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Scanner;




public class SimpleClass{

    public static void main(String[] args) {
    
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\areej\\eclipse-workspace\\myapp\\Drivers\\chromedriver.exe");


        WebDriver driver = new ChromeDriver();

 
        driver.get("https://www.browserstack.com/users/sign_in");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press Enter to close the browser...");

        // Open the URL
        driver.get("https://www.facebook.com/");

        // Locate the email input field and enter an email address
        //driver.findElement(By.id("email")).sendKeys("fadwa.zakarneh21@gmail.com");

        // Locate the password input field and enter a password
       driver.findElement(By.id("pass")).sendKeys("FfAa2116");

        // Locate the sign-in button and click it
       driver.findElement(By.name("login")).click();
        
        WebElement forgotPasswordLink = driver.findElement(By.linkText("Forgot password?"));
        forgotPasswordLink.click();


        // Locate the "Create New Account" button using CSS Selector and click it
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        
        driver.findElement(By.cssSelector("a._8esh")).click();
        
       // Locate the eye icon to show/hide password
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       
       // Adjust the selector according to the actual attribute (e.g., id, class)
       WebElement eyeIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[role='button'] ._9lsa ._9lsb._9ls8")));

       // Click the eye icon to show/hide the password
       eyeIcon.click();

        // Create a Scanner object to wait for user input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press Enter to close the browser...");

        // Wait for user to press Enter
        scanner.nextLine();

        // Close the browser
        driver.quit();
        scanner.close();
    
        
    }
}
