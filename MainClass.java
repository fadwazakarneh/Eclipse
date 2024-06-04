package myapp;

import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        SignUp signup = new SignUp();
        signup.execute();

        try {
            
            System.out.println("Waiting for 10 seconds before proceeding to sign up...");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted: " + e.getMessage());
        }

        signup.findSignupElements();

        // Create a Scanner object to wait for user input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press Enter to close the browser...");

        // Wait for user to press Enter
        scanner.nextLine();

        // Close the browser after user presses Enter
        signup.getDriver().quit();
    }
}
