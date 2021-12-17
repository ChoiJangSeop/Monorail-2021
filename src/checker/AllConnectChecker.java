package checker;

import board.Board;
import board.TileState;
import system.System;

public class AllConnectChecker extends Checker {
    @Override
    public System.State check() {

        for (int i=0; i<17; ++i) {
            for (int j=0; j<17; ++i) {
                if (Board.getInstance().getAdjacnetState().stream().filter( s -> s == TileState.Connect.ABLED).count() > 0) {
                    return System.State.TURN_END;
                }
            }
        }
        
        return System.State.GAME_END;
    }
}