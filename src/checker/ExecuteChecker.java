package checker;

import system.MainSystem;
import java.util.*;

public class ExecuteChecker {

    // COMPLETE 같은 자리에 타일을 다시 놓을때 생기는 예외처리 => GUI 단계에서 처리

    public MainSystem.State execute(String action) {
        CheckerFactory checkerFactory = new CheckerFactory();
        List<MainSystem.State> result = new ArrayList<>();

        // COMPLETE 타일 일자로 놓는거 체킹 필요! => 체커 도입으로 일단은해결/보드에 타일리턴 메소드도 추가됨.
        switch(action) {
            case "PutTile": 
                // 메세지 우선순위에 맞게 작성
                result.add(checkerFactory.createChecker("UseTile").check());
                result.add(checkerFactory.createChecker("RestTile").check());    
                result.add(checkerFactory.createChecker("TileConnect").check());
                result.add(checkerFactory.createChecker("RailConnect").check());
                result.add(checkerFactory.createChecker("TileDirection").check());
                break;                
            
            case "EndTurn":
                result.add(checkerFactory.createChecker("UseTile").check());             
                result.add(checkerFactory.createChecker("AllConnect").check());
                break;
            
            case "HandleError": 
                result.add(checkerFactory.createChecker("TileConnect").check());
                result.add(checkerFactory.createChecker("RailConnect").check());
                result.add(checkerFactory.createChecker("TileDirection").check()); 
                break;
            
            case "ImpPutTile":
                result.add(checkerFactory.createChecker("RestTile").check());
                result.add(checkerFactory.createChecker("TileConnect").check());
                result.add(checkerFactory.createChecker("RailConnect").check());
                break;
        }

        // COMPLETE result 정리 코드 
        result = result.stream().filter(s -> s != MainSystem.State.NONE).toList();

        if (result.size() == 0) {
            return MainSystem.State.NONE;
        } else {
            return result.get(0);
        }
    }
}