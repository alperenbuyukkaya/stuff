package system;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
    private File mainFile;
    Scanner mainScanner;
    public FileHandler(){
        this.mainFile = null;
        this.mainScanner = null;
    }

    public boolean checkDatabase() throws FileNotFoundException { // Checks if database exists or not and returns the value.
        final String dir = System.getProperty("user.dir");
        if (new File(dir, "mainDatabase.txt").isFile()){
            this.mainFile = new File(dir, "mainDatabase.txt");
            this.mainScanner = new Scanner(this.mainFile);
            return true;
        }else{
            return false;
        }
    }
    public void makeDatabase(){ // Makes a new database.
        final String dir = System.getProperty("user.dir");
        File mainDatabase = new File(dir, "mainDatabase.txt");
        try{
            mainDatabase.createNewFile();
            this.mainFile = new File(dir, "mainDatabase.txt");
            this.mainScanner = new Scanner(this.mainFile);
        } catch (IOException e) {
            System.out.println("Error creating main database");
        }
    }
    public void writeToFile(String inputText, int inputLine) throws IOException { // Writes to the file at a specific line.
        Path path = Paths.get(mainFile.getAbsolutePath());
        List<String> lines = readFile();
        try{
            lines.set(inputLine, inputText);
        }catch (Exception e){
            lines.add(inputText);
        }
        Files.write(path, lines);
    }
    public void writeToFile(String inputText) throws IOException { // Appends to the file.
        Path path = Paths.get(mainFile.getAbsolutePath());
        List<String> lines = readFile();
        lines.add(inputText);
        Files.write(path, lines);
    }
    public List<String> readFile() throws IOException { // Reads the file, returns all lines.
        Path path = Paths.get(this.mainFile.getAbsolutePath());
        return Files.readAllLines(path);
    }
    public String readFile(int lineIndex) throws IOException { // Reads the file, returns the desired line.
        Path path = Paths.get(this.mainFile.getAbsolutePath());
        return Files.readAllLines(path).get(lineIndex);
    }
    public void removeFromFile(String removalText) throws IOException { // Removes desired string from the file.
        List<String> fileLines = readFile();
        for (int i = 2; i < fileLines.size(); i++){
            if (fileLines.get(i).equals(removalText)){
                fileLines.remove(i);
                System.out.println(removalText + " is removed.");
                Files.write(Paths.get(mainFile.getAbsolutePath()), fileLines);
                return;
            }
        }
        System.out.println(removalText + " is not found.");
    }
}
