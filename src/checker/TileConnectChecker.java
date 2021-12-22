package checker;

import system.MainSystem;
import board.*;

public class TileConnectChecker extends Checker {

    @Override
    public MainSystem.State check() {

        if (Board.getInstance().getAdjacnetState()
            .stream()
            .filter( s -> (s == TileState.Connect.COMPLETE || s == TileState.Connect.ERROR) )
            .count() == 0) {
                return MainSystem.State.TILE_CONNECT_ERROR;
            }
        return MainSystem.State.NONE;
    }
}