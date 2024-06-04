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
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;
import static org.junit.Assert.assertTrue;

public class run_firefox {

    private WebDriver driver;
    private WebDriverWait wait;
    private Properties config;

    public run_firefox() {
        config = new Properties();
        try (InputStream input = run_firefox.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            config.load(input);
        } catch (IOException e) {
            System.out.println("Unable to load config file: " + e.getMessage());
            return;
        }

        System.setProperty("webdriver.gecko.driver", config.getProperty("geckoDriverPath"));

        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(config.getProperty("firefoxBinaryPath"));

        driver = new FirefoxDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void execute() {
        try {
            driver.get("https://www.facebook.com/");
            System.out.println("Opened Facebook login page");

            enterText(By.xpath("//input[@id='email']"), config.getProperty("email"));
            enterText(By.xpath("//input[@id='pass']"), config.getProperty("password"));

            clickElement(wait, By.xpath("//a[@role='button']//div[@class='_9lsa']"));
            clickElement(driver, By.xpath("//button[@name='login']"));
            clickElement(wait, By.xpath("//a[contains(text(),'Forgot password?')]"));
            clickElement(wait, By.xpath("//a[@data-testid='open-registration-form-button']"));

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email__']")));
            System.out.println("Registration form loaded");

            Thread.sleep(5000);

            clickElement(wait, By.xpath("//a[@class='_8esh' and @href='/pages/create/?ref_type=registration_form']"));

            Thread.sleep(5000);

        } catch (NoSuchElementException e) {
            System.out.println("An element was not found: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    private void enterText(By by, String text) {
        try {
            WebElement element = driver.findElement(by);
            element.sendKeys(text);
            assertTrue(element.getAttribute("value").equals(text));
            System.out.println("Entered text: " + text);
        } catch (NoSuchElementException e) {
            System.out.println("Failed to locate element to enter text: " + e.getMessage());
        }
    }

    private void clickElement(WebDriverWait wait, By by) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
            element.click();
            System.out.println("Clicked element: " + by);
        } catch (NoSuchElementException e) {
            System.out.println("Failed to locate element to click: " + e.getMessage());
        }
    }

    private void clickElement(WebDriver driver, By by) {
        try {
            WebElement element = driver.findElement(by);
            element.click();
            System.out.println("Clicked element: " + by);
        } catch (NoSuchElementException e) {
            System.out.println("Failed to locate element to click: " + e.getMessage());
        }
    }
}
