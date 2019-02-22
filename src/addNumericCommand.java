import java.util.concurrent.locks.ReentrantReadWriteLock;

public class addNumericCommand extends addAlphaCommand {
    public addNumericCommand(String file, ReentrantReadWriteLock lock, boolean e) {
        super(file, lock, e);
        alpha = "0123456789\n";
    }
}
