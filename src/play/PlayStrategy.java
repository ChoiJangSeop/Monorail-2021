package play;

import system.MainSystem;

public interface PlayStrategy {

    public void play();
    public MainSystem.State validCheck();
}