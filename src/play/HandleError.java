package play;

import checker.ExecuteChecker;

import board.Board;
import gui.ErrorWindow;
import system.MainSystem;

public class HandleError implements PlayStrategy {

    private String message;

    public HandleError(String message) {
        this.message = message;
    }

    @Override
    public void play() {
         
        // TODO remove rail img in GUI
        // TODO pop up the error windows
        

        switch (message)  {
            case "ZeroTileError" :
                System.out.println("ZeroTileError");
                new ErrorWindow();
                break;

            case "OverTileError" :
                System.out.println("OverTileError"); 
                new ErrorWindow();         
                Board.getInstance().popTile();
              
                break;

            case "NoTileError" :
                System.out.println("NoTileError"); 
                new ErrorWindow();   
                Board.getInstance().popTile();
            
                break;

            case "TileConnectError" :
                System.out.println("TileConnectError");
                new ErrorWindow();
                Board.getInstance().popTile();
             
                break;
            
            case "RailConnectError" :
                System.out.println("RaileConnectError");  
                new ErrorWindow();  
                Board.getInstance().popTile();
                break;
            
            case "InputArgError" :
                System.out.println("InputAgError");
                break;
        }
    }

    @Override
    public MainSystem.State validCheck() {
        ExecuteChecker executeChecker = new ExecuteChecker();
        return executeChecker.execute("HandleError");
    }
}

