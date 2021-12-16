package checker;

import system.System;
import board.*;
import java.util.*;

public class OneTileConnect extends Checker {
    @Override
    public System.State check() {

        if (Board.getInstance().isTileConnect() && Board.getInstance().isTileConnect()) {
            // 에러 팝업창 띄우기
            return System.State.TILE_CONNECT_ERROR;
        }

        return System.State.NONE;

    }
}