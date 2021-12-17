package play;

import checker.ExecuteChecker;

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
                // TODO pop up the error window
                break;

            case "OverTileError" :
                // TODO pop up the error window          
                Board.getInstance().popTile();
                break;

            case "NoTileError" :
                // TODO pop up the error window    
                Board.getInstance().popTile();    
                break;

            case "TileConnectError" :
                // TODO pop up the error window
                Board.getInstance().popTile();
                break;
            
            case "RailConnectError" :
                // TODO pop up the error window    
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