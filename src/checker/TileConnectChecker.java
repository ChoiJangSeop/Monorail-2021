package checker;

import system.System;
import board.*;

public class TileConnectChecker extends Checker {

    @Override
    public System.State check() {

        if (Board.getInstance().getAdjacnetState()
            .stream()
            .filter( s -> (s == TileState.Connect.COMPLETE || s == TileState.Connect.ERROR) )
            .count() == 0) {
                return System.State.TILE_CONNECT_ERROR;
            }
        return System.State.NONE;
    }
}