package play;

import checker.ExecuteChecker;

import java.util.*;

import board.Board;

public class HandleError implements PlayStrategy {

    private String message;

    public HandleError(String message) {
        this.message = message;
    }

    @Override
    public void play() {
        switch (message)  {
            case "ZeroTileError" :
                break;

            case "OverTileError" :
                Board.getInstance().popTile();
                break;

            case "NoTileError" :
                Board.getInstance().popTile();    
                break;

            case "TileConnectError" :
                Board.getInstance().popTile();
                break;
        }
    }

    @Override
    public void validCheck() {
        ExecuteChecker executeChecker = new ExecuteChecker();
        executeChecker.execute("HandleError");
    }
}