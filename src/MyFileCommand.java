import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class MyFileCommand implements Command {
    private static Lock read;
    private static Lock write;
    private FileInputStream fileIn;
    private FileOutputStream fileOut;


    public MyFileCommand(FileInputStream in, FileOutputStream out, ReentrantReadWriteLock lock){
        read = lock.readLock();
        write = lock.writeLock();
        fileIn = in;
        fileOut = out;
    }

    @Override
    public abstract boolean execute();

    @Override
    public abstract boolean undo();
}
