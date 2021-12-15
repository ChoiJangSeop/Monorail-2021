package checker;

import system.System;

public class FullTileConnect extends Checker {
    @Override
    public System.State check() {
        boolean flag = false;

        // check for board to be all connect

        if (flag) {
            return System.State.GAME_END;
        }
        return System.State.TURN_END;
    }
}