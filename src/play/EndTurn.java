package play;

import checker.*;

public class EndTurn implements PlayStrategy {

    @Override
    public void play() {

    }

    @Override
    public void validCheck() {
        ExecuteChecker executeChecker = new ExecuteChecker();
        executeChecker.execute("EndTurn");
    }

}