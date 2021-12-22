package checker;

import board.*;
import system.MainSystem;

public class RestTileChecker extends Checker {
    @Override
    public MainSystem.State check() {

        if (Board.getInstance().getRestTile() < 0) {
            return MainSystem.State.NO_TILE_ERROR;
        }
        return MainSystem.State.NONE;
    }
}