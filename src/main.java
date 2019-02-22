import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class main {
    public static void main(String[] args){
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        Command command;
        String file = "file.txt";

        if(Files.notExists(Paths.get(file))){
            try {
                Files.createFile(Paths.get(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        boolean throwException = true;

        command = new addAlphaCommand(file, lock, throwException);

        command.execute();

        command = new addNumericCommand(file, lock, throwException);
        command.execute();

        command = new removeLine(file,lock, 0, throwException);
        command.execute();


    }
}
