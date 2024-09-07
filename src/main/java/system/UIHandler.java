package system;

import java.io.Console;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

public class UIHandler {
    private Scanner mainScanner;
    private FileHandler fileHandler;
    private Console mainConsole;
    public UIHandler(Scanner inputScanner, FileHandler fileHandler) {
        this.mainScanner = inputScanner;
        this.fileHandler = fileHandler;
        this.mainConsole = System.console();

    }
    public static void clearScreen() throws IOException, InterruptedException { // Used to clear console screen.
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
    public String getInput(){ // Uses console object to get user input, checks if the input is empty or not.
        String returnString = "";
        while (true) {
            returnString = this.mainConsole.readLine();
            if (returnString.isEmpty()) {
                System.out.print("Please enter your input: ");
                System.out.flush();
            }else{
                break;
            }
        }
        return returnString;
    }
    public static String getPassword() { // Used to get user password without it being seen.
        Console console = System.console();
        if (console == null) {
            System.out.println("Couldn't get Console instance");
            System.exit(0);
        }
        System.out.print("Enter your password: ");
        String inputPassword = Arrays.toString(console.readPassword());
        return inputPassword;
    }

    public void listBooks() throws IOException, InterruptedException {
        clearScreen();
        List<String> fileLines = this.fileHandler.readFile();
        for (int i = 2; i < fileLines.size(); i++){
            System.out.println(fileLines.get(i));
        }
        System.out.println("-------------------------------------------");
    }

    public void mainMenuStart() throws IOException, InterruptedException { // Main menu function where user selects their desired action.
        while (true){
            int inputInteger;
            System.out.println(
             "\n1. List books"
            + "\n2. Add a new book"
            + "\n3. Remove a book"
            + "\n4. Exit\n");
            System.out.print("Input: ");
            System.out.flush();
            try{
                inputInteger = Integer.parseInt(getInput());
            }catch (NumberFormatException e){
                System.out.println("Please enter a valid number.");
                continue;
            }
            switch (inputInteger){
                case 1:
                    listBooks();
                    break;
                case 2:
                    System.out.print("Enter the book title: ");
                    String bookTitle = getInput();
                    fileHandler.writeToFile(bookTitle);
                    break;
                case 3:
                    listBooks();
                    System.out.print("Enter the name of the book you wish to be removed: ");
                    String bookName = getInput();
                    fileHandler.removeFromFile(bookName);
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    System.exit(0);
            }
        }
    }
}
