package myapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;
import java.io.InputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import static org.junit.Assert.assertNotNull;

public class SignUp {

    private WebDriver driver;
    private WebDriverWait wait;
    private Properties config;

    public SignUp() {
        config = new Properties();
        try (InputStream input = SignUp.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            config.load(input);
        } catch (IOException e) {
            System.out.println("Unable to load config file: " + e.getMessage());
        }

        System.setProperty("webdriver.gecko.driver", config.getProperty("geckoDriverPath"));

        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(config.getProperty("firefoxBinaryPath"));

        driver = new FirefoxDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
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

    public void findSignupElements() {
        try {
            Thread.sleep(10000); // 

            WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='firstname']")));
            assertNotNull("First name field is missing", firstName);
            firstName.sendKeys(config.getProperty("firstName"));
            System.out.println("Entered first name: " + config.getProperty("firstName"));

            WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='lastname']")));
            assertNotNull("Last name field is missing", lastName);
            lastName.sendKeys(config.getProperty("lastName"));
            System.out.println("Entered last name: " + config.getProperty("lastName"));

            WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email__']")));
            assertNotNull("Email field is missing", email);
            email.sendKeys(config.getProperty("regEmail"));
            System.out.println("Entered email: " + config.getProperty("regEmail"));

            WebElement emailConfirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
            assertNotNull("Email confirmation field is missing", emailConfirmation);
            emailConfirmation.sendKeys(config.getProperty("regEmail"));
            System.out.println("Re-entered email: " + config.getProperty("regEmail"));

            WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_passwd__']")));
            assertNotNull("Password field is missing", password);
            password.sendKeys(config.getProperty("regPassword"));
            System.out.println("Entered password");

            WebElement birthDay = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@name='birthday_day']")));
            assertNotNull("Birth day dropdown is missing", birthDay);
            birthDay.sendKeys(config.getProperty("birthdayDay"));
            System.out.println("Selected birth day: " + config.getProperty("birthdayDay"));

            WebElement birthMonth = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@name='birthday_month']")));
            assertNotNull("Birth month dropdown is missing", birthMonth);
            birthMonth.sendKeys(config.getProperty("birthdayMonth"));
            System.out.println("Selected birth month: " + config.getProperty("birthdayMonth"));

            WebElement birthYear = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@name='birthday_year']")));
            assertNotNull("Birth year dropdown is missing", birthYear);
            birthYear.sendKeys(config.getProperty("birthdayYear"));
            System.out.println("Selected birth year: " + config.getProperty("birthdayYear"));

            WebElement genderFemale = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='sex' and @value='1']")));
            assertNotNull("Female gender option is missing", genderFemale);

            WebElement genderMale = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='sex' and @value='2']")));
            assertNotNull("Male gender option is missing", genderMale);

            WebElement genderCustom = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='sex' and @value='-1']")));
            assertNotNull("Custom gender option is missing", genderCustom);

            String gender = config.getProperty("gender");
            if (gender.equalsIgnoreCase("female")) {
                genderFemale.click();
                System.out.println("Selected female gender");
            } else if (gender.equalsIgnoreCase("male")) {
                genderMale.click();
                System.out.println("Selected male gender");
            } else {
                genderCustom.click();
                System.out.println("Selected custom gender");
            }

            WebElement signupButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='websubmit']")));
            assertNotNull("Sign up button is missing", signupButton);
            signupButton.click();
            System.out.println("Clicked sign up button");

        } catch (NoSuchElementException e) {
            System.out.println("An element was not found: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted: " + e.getMessage());
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    private void enterText(By by, String text) {
        try {
            WebElement element = driver.findElement(by);
            element.sendKeys(text);
            assertNotNull(element.getAttribute("value").equals(text));
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
