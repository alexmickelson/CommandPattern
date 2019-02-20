import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class MyFileCommand implements Command {
    private static Lock read;
    private static Lock write;
    private String fileName;

    public MyFileCommand(String fileName, ReentrantReadWriteLock lock){
        read = lock.readLock();
        write = lock.writeLock();
        this.fileName = fileName;

    }

    @Override
    public abstract boolean execute();

    @Override
    public abstract boolean undo();
}
