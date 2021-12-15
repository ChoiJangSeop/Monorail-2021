package checker;

import system.System;

public class ExecuteChecker {

    public void execute(String action) {
        CheckerFactory checkerFactory = new CheckerFactory();
        System.State result = System.State.NONE;

        switch(action) {
            case "PutTile": 
                result = checkerFactory.createChecker("OneTileChecker").check();
                // detail
                break;                
            
            case "EndTurn":
                result = checkerFactory.createChecker("FullTileChecker").check();
                // detail
                break;
            
            case "HandleError":
                result = checkerFactory.createChecker("OneTileChecker").check();
                // detail
                break;
            
            // detail

        }

        System.getInstance().endAction(result);
    }
}