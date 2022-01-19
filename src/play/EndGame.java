package play;

import checker.ExecuteChecker;
import system.MainSystem;

public class EndGame implements PlayStrategy {

    @Override
    public void play() {

    }

    @Override
    public MainSystem.State validCheck() {
        return MainSystem.State.GAME_END;
    }
}