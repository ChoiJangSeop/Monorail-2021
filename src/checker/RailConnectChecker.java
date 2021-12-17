package checker;

import system.System;
import board.*;
import java.util.*;

public class RailConnectChecker extends Checker {
    @Override
    public System.State check() {

        List<TileState.Connect> connectState = Board.getInstance().getAdjacnetState();

        if (connectState.stream().filter( state -> state == TileState.Connect.ERROR ).count() > 0) {
            return System.State.RAIL_CONNECT_ERROR;
        }
        return System.State.NONE;

    }
}