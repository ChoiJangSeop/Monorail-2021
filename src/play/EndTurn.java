package play;

import board.Board;
import checker.*;

public class EndTurn implements PlayStrategy {

    @Override
    public void play() {
        Board.getInstance().initUseTile();
    }

    @Override
    public void validCheck() {
        ExecuteChecker executeChecker = new ExecuteChecker();
        executeChecker.execute("EndTurn");
    }

}