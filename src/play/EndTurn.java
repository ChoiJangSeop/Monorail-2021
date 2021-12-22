package play;

import board.Board;
import checker.*;
import system.MainSystem;

public class EndTurn implements PlayStrategy {

    @Override
    public void play() {
    
    }

    @Override
    public MainSystem.State validCheck() {
        // BUG 초기화가 체커보다 먼저돼 무조건 zerotileError가 발생
        ExecuteChecker executeChecker = new ExecuteChecker();
        return executeChecker.execute("EndTurn");
    }

}