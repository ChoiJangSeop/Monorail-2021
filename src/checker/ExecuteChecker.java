package checker;

import system.System;
import java.util.*;

public class ExecuteChecker {

    public void execute(String action) {
        CheckerFactory checkerFactory = new CheckerFactory();
        List<System.State> result = new ArrayList<>();

        switch(action) {
            case "PutTile": 
                result.add(checkerFactory.createChecker("NumTile").check());    
                result.add(checkerFactory.createChecker("TileConnect").check());
                result.add(checkerFactory.createChecker("RailConnect").check());
                break;                
            
            case "EndTurn": 
                result.add(checkerFactory.createChecker("AllConnect").check());
                break;
            
            case "HandleError": 
                result.add(checkerFactory.createChecker("NumTile").check());    
                result.add(checkerFactory.createChecker("TileConnect").check());
                result.add(checkerFactory.createChecker("RailConnect").check());
                break;
            
            // detail

        }

        // TODO result 정리 코드
        result = result.stream().filter(s -> s != System.State.NONE).toList();

        if (result.size() == 0) {
            System.getInstance().endAction(System.State.NONE);
        } else {
            System.getInstance().endAction(result.get(0));
        }
    }
}