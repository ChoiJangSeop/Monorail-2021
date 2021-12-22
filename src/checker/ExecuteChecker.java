package checker;

import system.MainSystem;
import java.util.*;

public class ExecuteChecker {

    public void execute(String action) {
        CheckerFactory checkerFactory = new CheckerFactory();
        List<MainSystem.State> result = new ArrayList<>();

        switch(action) {
            case "PutTile": 
                // 메세지 우선순위에 맞게 작성
                result.add(checkerFactory.createChecker("NumTile").check());    
                result.add(checkerFactory.createChecker("TileConnect").check());
                result.add(checkerFactory.createChecker("RailConnect").check());
                break;                
            
            case "EndTurn": 
                result.add(checkerFactory.createChecker("AllConnect").check());
                break;
            
            case "HandleError": 
                result.add(checkerFactory.createChecker("TileConnect").check());
                result.add(checkerFactory.createChecker("RailConnect").check());
                break;
            
            // detail

        }

        // TODO result 정리 코드
        result = result.stream().filter(s -> s != MainSystem.State.NONE).toList();

        if (result.size() == 0) {
            System.out.println("none");
            MainSystem.getInstance().endAction(MainSystem.State.NONE);
        } else {
            System.out.println("error");
            MainSystem.getInstance().endAction(result.get(0));
        }
    }
}