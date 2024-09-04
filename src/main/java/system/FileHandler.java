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

    public boolean checkDatabase() throws FileNotFoundException {
        final String dir = System.getProperty("user.dir");
        if (new File(dir, "mainDatabase.txt").isFile()){
            this.mainFile = new File(dir, "mainDatabase.txt");
            this.mainScanner = new Scanner(this.mainFile);
            return true;
        }else{
            return false;
        }
    }
    public void makeDatabase(){
        final String dir = System.getProperty("user.dir");
        File mainDatabase = new File(dir, "mainDatabase.txt");
        try{
            mainDatabase.createNewFile();
            this.mainFile = new File(dir, "mainDatabase.txt");
            this.mainScanner = new Scanner(this.mainFile);
        } catch (IOException e) {

        }
    }
    public void writeToFile(String inputText, int inputLine) throws IOException {
        Path path = Paths.get(mainFile.getAbsolutePath());
        List<String> lines = readFile();
        try{
            lines.set(inputLine, inputText);
        }catch (Exception e){
            lines.add(inputText);
        }
        Files.write(path, lines);
    }
    public void writeToFile(String inputText) throws IOException {
        Path path = Paths.get(mainFile.getAbsolutePath());
        List<String> lines = readFile();
        lines.add(inputText);
        Files.write(path, lines);
    }
    public List<String> readFile() throws IOException {
        Path path = Paths.get(this.mainFile.getAbsolutePath());
        return Files.readAllLines(path);
    }
    public String readFile(int lineIndex) throws IOException {
        Path path = Paths.get(this.mainFile.getAbsolutePath());
        return Files.readAllLines(path).get(lineIndex);
    }
}
