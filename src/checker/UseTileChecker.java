package checker;

import board.*;
import system.MainSystem;

public class UseTileChecker extends Checker {
    @Override
    public MainSystem.State check() {
        if (Board.getInstance().getUseTile() > 3) {
            return MainSystem.State.OVER_TILE_ERROR;
        }
        if (Board.getInstance().getUseTile() == 0) {
            return MainSystem.State.ZERO_TILE_ERROR;
        }

        
        return MainSystem.State.NONE;
    }
}