package myapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;
import java.time.Duration;
import java.util.Scanner;
import static org.junit.Assert.assertTrue;

public class run_firefox {

    public static void main(String[] args) {
        // Set the path to the geckodriver executable
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\areej\\eclipse-workspace\\myapp\\Drivers\\geckodriver.exe");

        // Specify the path to the Firefox binary
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe"); // Adjust this path if necessary

        // Initialize Firefox driver with options
        WebDriver driver = new FirefoxDriver(options);

        try {
            // Open the URL
            driver.get("https://www.facebook.com/");
            System.out.println("Opened Facebook login page");

            // Locate the email input field and enter an email address
            WebElement emailField = driver.findElement(By.id("email"));
            emailField.sendKeys("fadwa.zakarneh21@gmail.com");
            assertTrue(emailField.getAttribute("value").equals("fadwa.zakarneh21@gmail.com"));
            System.out.println("Entered email");

            // Locate the password input field and enter a password
            WebElement passwordField = driver.findElement(By.id("pass"));
            passwordField.sendKeys("333555888");
            assertTrue(passwordField.getAttribute("value").equals("333555888"));
            System.out.println("Entered password");

            // Initialize WebDriverWait
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            
            // Locate the eye icon to show/hide password
            WebElement eyeIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[role='button'] ._9lsa ._9lsb._9ls8")));
            eyeIcon.click();
            System.out.println("Clicked eye icon to show/hide password");

            // Locate the sign-in button and click it
            WebElement loginButton = driver.findElement(By.name("login"));
            loginButton.click();
            System.out.println("Clicked login button");

            // Wait for the forgot password link and click it
            WebElement forgotPasswordLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Forgot password?")));
            forgotPasswordLink.click();
            System.out.println("Clicked forgot password link");

            // Locate the "Create New Account" button using the button's attributes and click it
            WebElement createAccountButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-testid='open-registration-form-button']")));
            createAccountButton.click();
            System.out.println("Clicked create new account button");

            // Wait until the registration form is loaded
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("reg_email__")));
            System.out.println("Registration form loaded");

            // Add sleep to ensure you can see the form
            Thread.sleep(5000); // Wait for 5 seconds

            // Click the "Create a Page" link using CSS selector
            WebElement createPageLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a._8esh[href='/pages/create/?ref_type=registration_form']")));
            createPageLink.click();
            System.out.println("Clicked 'Create a Page' link");

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
        } finally {
            // Close the browser
            driver.quit();
            System.out.println("Closed the browser");
        }
    }
}
