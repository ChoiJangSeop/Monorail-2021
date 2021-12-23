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
         
        // COMPLETE remove rail img in GUI => 실행 결과를 GUI에게 리턴해 리턴 결과로 GUI레벨에서 처리함.
        // TODO pop up the error windows
        

        switch (message)  {
            case "ZeroTileError" :
                System.out.println("ZeroTileError");
                new ErrorWindow();
                break;

            case "OverTileError" :
                new ErrorWindow();         
                Board.getInstance().popTile();
              
                break;

            case "NoTileError" :
                new ErrorWindow();   
                Board.getInstance().popTile();
            
                break;

            case "TileConnectError" :
                new ErrorWindow();
                Board.getInstance().popTile();
             
                break;
            
            case "RailConnectError" :
                new ErrorWindow();  
                Board.getInstance().popTile();
                break;
            
            case "TileDirectionError" :
                new ErrorWindow();
                Board.getInstance().popTile();
                break;
        }
    }

    @Override
    public MainSystem.State validCheck() {
        ExecuteChecker executeChecker = new ExecuteChecker();
        return executeChecker.execute("HandleError");
    }
}

