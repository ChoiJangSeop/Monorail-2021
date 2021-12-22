package checker;

import board.Board;
import board.TileState;
import system.MainSystem;

public class AllConnectChecker extends Checker {
    @Override
    public MainSystem.State check() {

        for (int i=0; i<17; ++i) {
            for (int j=0; j<17; ++i) {
                if (Board.getInstance().getAdjacnetState().stream().filter( s -> s == TileState.Connect.ABLED).count() > 0) {
                    return MainSystem.State.TURN_END;
                }
            }
        }
        
        return MainSystem.State.GAME_END;
    }
}