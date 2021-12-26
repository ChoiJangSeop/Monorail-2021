package play;

import system.MainSystem;
public class DeclareImp implements PlayStrategy {

    // COMPLETE implement imp mode => NO NEED
    
    @Override
    public void play() {

    }

    @Override
    public MainSystem.State validCheck() {
        return MainSystem.State.NONE;
    }
}