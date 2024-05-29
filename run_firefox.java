package myapp;
//newline
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.util.Scanner;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

//nddd
public class run_firefox{

    public static void main(String[] args) {
        // Set the path to the geckodriver executable
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\areej\\eclipse-workspace\\myapp\\Drivers\\geckodriver.exe");

        // Specify the path to the Firefox binary
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe"); // Adjust this path if necessary

        // Initialize Firefox driver with options
        WebDriver driver = new FirefoxDriver(options);

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
