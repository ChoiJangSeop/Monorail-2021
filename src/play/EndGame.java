package play;

import checker.ExecuteChecker;
import system.MainSystem;

public class EndGame implements PlayStrategy {

    @Override
    public void play() {
        // TODO endgame play method
        System.out.println("Game-End");
    }

    @Override
    public MainSystem.State validCheck() {
        ExecuteChecker executeChecker = new ExecuteChecker();
        return executeChecker.execute("EndGame");
    }
}