package play;

import checker.ExecuteChecker;

import board.Board;
import Monorail.OtherWindow;

public class HandleError implements PlayStrategy {

    private String message;

    public HandleError(String message) {
        this.message = message;
    }

    @Override
    public void play() {
        switch (message)  {
            case "ZeroTileError" :
                System.out.println("error1");
                break;

            case "OverTileError" :
                System.out.println("error2");          
                Board.getInstance().popTile();
                // TODO remove rail img in gui
                break;

            case "NoTileError" :
                System.out.println("error3");    
                Board.getInstance().popTile();
                // TODO remove rail img in gui    
                break;

            case "TileConnectError" :
                System.out.println("error4");
                Board.getInstance().popTile();
                // TODO remove rail img in gui
                break;
            
            case "RailConnectError" :
                System.out.println("error5");    
                Board.getInstance().popTile();
                break;
            
            case "InputArgError" :
                System.out.println("error6");
                break;
        }
    }

    @Override
    public void validCheck() {
        ExecuteChecker executeChecker = new ExecuteChecker();
        executeChecker.execute("HandleError");
    }
}

