import java.io.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class addAlphaCommand extends MyFileCommand {
    String alpha ;

    public addAlphaCommand(String file, ReentrantReadWriteLock lock, boolean e){
        super(file, lock, e);
        alpha = "abcdefghijklmnopqrstuvwxyz\n";
    }

    @Override
    public boolean execute() {
        write.lock();
        try{
            fileOut.write(alpha);
            fileOut.flush();

            if(throwException){
                throw new Exception("Rollback");
            }

        } catch (Exception e){
            undo();
        } finally {
            write.unlock();
        }
        return true;
    }

}
