package system;

import java.io.Console;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class UIHandler {
    private Scanner mainScanner;
    public UIHandler(Scanner inputScanner) {
        this.mainScanner = inputScanner;
    }
    public static void clearScreen() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
    public String getInput(){
        String returnString = "";
        while (true) {
            returnString = this.mainScanner.nextLine();
            if (returnString.isEmpty()) {
                System.out.println("Please enter your input.");
            }else{
                break;
            }
        }
        return returnString;
    }
    public static String getPassword() {
        Console console = System.console();
        if (console == null) {
            System.out.println("Couldn't get Console instance");
            System.exit(0);
        }
        System.out.println("");
        String inputPassword = Arrays.toString(console.readPassword("Enter your password "));
        return inputPassword;
    }
}
