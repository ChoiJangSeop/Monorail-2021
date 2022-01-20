package play;

import checker.ExecuteChecker;

import board.Board;
import gui.AlertWindow;
import system.MainSystem;

public class HandleError implements PlayStrategy {

    private String message;

    public HandleError(String message) {
        this.message = message;
    }

    @Override
    public void play() {
         
        // COMPLETE remove rail img in GUI => 실행 결과를 GUI에게 리턴해 리턴 결과로 GUI레벨에서 처리함.

        
        // TODO: how to send error message to client?
        switch (message)  {
            case "ZeroTileError" :
                System.out.println("ZeroTileError");
                new AlertWindow("error", "더 이상 남은 타일이 없습니다.");
                break;

            case "OverTileError" :
                new AlertWindow("error","철로타일은 최대 3개까지만 사용 가능합니다");
                Board.getInstance().popTile();
                break;

            case "NoTileError" :
                new AlertWindow("error","철로타일을 1개 이상 놓아주세요");
                Board.getInstance().popTile();
                break;

            case "TileConnectError" :
                new AlertWindow("error","타일이 기존 타일과 연결되어야 합니다");
                Board.getInstance().popTile();
             
                break;
            
            case "RailConnectError" :
                new AlertWindow("error","잘못된 철로 연결입니다");
                Board.getInstance().popTile();
                break;
            
            case "TileDirectionError" :
                new AlertWindow("error","철로타일을 한방향으로 놓아주세요");
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

