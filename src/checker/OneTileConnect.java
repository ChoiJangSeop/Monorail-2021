package checker;

import system.System;

public class OneTileConnect extends Checker {
    @Override
    public System.State check() {
        int flag = 0;

        // checking code

        if (flag == 1) {
            return System.State.TILE_CONNECT_ERROR;
        }

        return System.State.NONE;

    }
}