package checker;

import board.Board;
import system.System;

public class FullTileConnect extends Checker {
    @Override
    public System.State check() {
       
        if (Board.getInstance().isAllConnect()) {
            return System.State.GAME_END;
        }
        
        return System.State.TURN_END;
    }
}