package system;


import org.apache.commons.codec.digest.DigestUtils;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner mainScanner = new Scanner(System.in);
        FileHandler fileHandler = new FileHandler();
        UIHandler mainUI = new UIHandler(mainScanner, fileHandler);
        System.out.println("Welcome");
        if (fileHandler.checkDatabase()){
            while (true){
                System.out.print("Enter username: ");
                System.out.flush();
                String inputUser = mainUI.getInput();
                if (inputUser.equals(fileHandler.readFile(0))){
                    String passwordInput = DigestUtils.sha256Hex(UIHandler.getPassword());
                    if (passwordInput.equals(fileHandler.readFile(1))){
                        System.out.print("Successful login.");
                        break;
                    }
                }
            }
        }else{
            System.out.println("Database does not exist");
            fileHandler.makeDatabase();
            mainUI.clearScreen();
            System.out.println("Since this is your first time, you must choose a name and a password.");
            while (true){
                System.out.println("Username: ");
                String inputUser = mainUI.getInput();
                String passwordInput = DigestUtils.sha256Hex(UIHandler.getPassword());
                fileHandler.writeToFile(inputUser,0);
                fileHandler.writeToFile(passwordInput,1);
                break;
            }
        }
        mainUI.clearScreen();
        mainUI.mainMenuStart();
    }
}