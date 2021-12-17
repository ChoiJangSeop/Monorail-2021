package checker;

import board.*;
import system.System;

public class NumTileChecker extends Checker {
    @Override
    public System.State check() {
        if (Board.getInstance().getUseTile() > 3) {
            return System.State.OVER_TILE_ERROR;
        }
        if (Board.getInstance().getRestTile() < 0) {
            return System.State.NO_TILE_ERROR;
        }
        if (Board.getInstance().getUseTile() == 0) {
            return System.State.ZERO_TILE_ERROR;
        }
        return System.State.NONE;
    }
}