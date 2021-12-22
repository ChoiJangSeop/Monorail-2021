package system;

public class Lock {

    boolean lock;

    public Lock() {
        this.lock = true;
    }

    public boolean getLock() { return this.lock; }
    public void openLock() { this.lock = true; }
    public void closeLock() { this.lock = false; }
}