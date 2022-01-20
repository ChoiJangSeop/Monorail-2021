package gui;

public class Mode {
    private boolean imp;
    private boolean lock;

    private static Mode instance = null;

    private Mode() {
        this.imp = false;
        this.lock = true;
    }

    public static Mode getInstance() {
        if (instance == null) { instance = new Mode(); }
        return instance;
    }

    public boolean getImp() { return this.imp; }
    public void goImp() { this.imp = true; }

    public boolean getLock() { return this.lock; }
    public void openLock() { this.lock = false; }
    public void closeLock() { this.lock = true; }
}