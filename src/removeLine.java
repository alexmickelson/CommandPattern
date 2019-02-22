import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class removeLine extends MyFileCommand {
    private int lineNumber;
    public removeLine(String file, ReentrantReadWriteLock lock, int number, boolean e){
        super(file, lock, e);
        lineNumber = number;
    }
    @Override
    public boolean execute() {

        Vector<String> file = new Vector<String>();
        write.lock();
        try{
            String line;
            while ((line = fileIn.readLine()) != null) {
                file.add(line);
            }
            file.remove(lineNumber);

            if(throwException){
                throw new Exception("Rollback");
            }


            fileOut = new PrintWriter(new FileOutputStream(path, false));
            for (String l : file) {
                fileOut.write(l + "\n");
            }
            fileOut.flush();

        }catch (Exception e){
            undo();
        } finally {
            write.unlock();
        }



        return true;
    }

}
