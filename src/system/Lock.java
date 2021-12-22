package system;

public class Lock {

    boolean lock;
    private static Lock instance = null;

    private Lock() {
        this.lock = true;
    }

    public static Lock getInstance() {
        if (instance == null) {
            instance = new Lock();
        }
        return instance;
    }

    public boolean getLock() { return this.lock; }
    public void openLock() { this.lock = true; }
    public void closeLock() { this.lock = false; }
}