package play;

import system.MainSystem;
public class DeclareImp implements PlayStrategy {

    // TODO implement imp mode
    
    @Override
    public void play() {

    }

    @Override
    public MainSystem.State validCheck() {
        return MainSystem.State.NONE;
    }
}