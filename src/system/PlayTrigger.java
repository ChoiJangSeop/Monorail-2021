package system;

import java.util.*;
import play.*;

public class PlayTrigger {

    public MainSystem.State trig (Player p, String message, List<Integer> arg) {

        switch (message) {
            case "PutTile" : 
                if (arg.size() == 3 && arg.get(2) >= 0) { 
                    p.setPlayStrategy(new PutTile(arg)); 
                } else { p.setPlayStrategy(new HandleError("InputArgError")); }
                break;
            
            
            
            default :
                p.setPlayStrategy(new HandleError("InputArgError"));
                break;
        }

        return p.playTurn();
    }

    
    public MainSystem.State trig(Player p, String message, String arg) {
        
        switch (message) {
            
            case "HandleError" :
                p.setPlayStrategy(new HandleError(arg));
                break;

            default :
                p.setPlayStrategy(new HandleError("InputArgError"));
                break;
        }

        return p.playTurn();
    }

    public MainSystem.State trig (Player p, String message) {
        
        switch (message) {
            case "EndGame" :
                p.setPlayStrategy(new EndGame());
                break;

            case "ImpossibleDeclare" :
                p.setPlayStrategy(new DeclareImp());
                break;
            
            case "TurnEnd" :
                p.setPlayStrategy(new EndTurn());
                break;
            
            default :
                p.setPlayStrategy(new HandleError("InputArgError"));
                break;
        }

        return p.playTurn();
    }
}