package checker;

import system.MainSystem;
import board.*;
import java.util.*;

public class RailConnectChecker extends Checker {
    @Override
    public MainSystem.State check() {

        List<TileState.Connect> connectState = Board.getInstance().getAdjacnetState();

        // TODO : please remove
        System.out.println("[연결성 검사] " + connectState);

        if (connectState.stream().filter( state -> state == TileState.Connect.ERROR ).count() > 0) {
            return MainSystem.State.RAIL_CONNECT_ERROR;
        }
        return MainSystem.State.NONE;

    }
}