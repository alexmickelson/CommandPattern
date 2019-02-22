import java.io.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class MyFileCommand implements Command {
    protected static Lock read;
    protected static Lock write;
    protected boolean throwException;
    protected BufferedReader fileIn;
    protected PrintWriter fileOut;
    protected String backup;
    protected String path;


    public MyFileCommand(String file, ReentrantReadWriteLock lock, boolean throwException){
        read = lock.readLock();
        write = lock.writeLock();
        path=file;
        this.throwException = throwException;

        try {
            fileIn = new BufferedReader(new FileReader(file));
            fileOut = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        backup = previousFile();
    }

    @Override
    public abstract boolean execute();

    @Override
    public boolean undo(){

        write.lock();
        try {
            fileOut = new PrintWriter(new FileOutputStream(path, false));
            fileOut.write(backup);
            fileOut.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            write.unlock();
        }
        return true;
    };

    protected String previousFile(){


        String file = "";
        read.lock();
        try{
            for(String line; (line = fileIn.readLine()) != null; ){
                file += line + "\n";
            }
            fileIn = new BufferedReader(new FileReader(path));
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            read.unlock();
        }
        return file;
    }
}
