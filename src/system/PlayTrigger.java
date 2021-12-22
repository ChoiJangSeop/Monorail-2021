package system;

import java.util.*;
import play.*;

public class PlayTrigger {

    public void trig (Player p, String message, List<Integer> arg) {

        
        
        switch (message) {
            case "PutTile" : 
                if (arg.size() == 3 && arg.get(2) >= 0) { 
                    p.setPlayStrategy(new PutTile(arg)); 
                } else { p.setPlayStrategy(new HandleError("InputArgError")); }
                p.playTurn();
                break;
            
            case "TurnEnd" :
                p.setPlayStrategy(new EndTurn());
                p.playTurn();
                break;
            
            case "ImpossibleDeclare" :
                p.setPlayStrategy(new DeclareImp());
                p.playTurn();
                break;
            
            default :
                p.setPlayStrategy(new HandleError("InputArgError"));
                p.playTurn();
                break;
        }
    }

    
    public void trig(Player p, String message, String arg) {
        
        switch (message) {
            
            case "HandleError" :
                p.setPlayStrategy(new HandleError(arg));
                p.playTurn();
                break;

            default :
                p.setPlayStrategy(new HandleError("InputArgError"));
                p.playTurn();
                break;
        }
    }

    public void trig (Player p, String message) {
        
        switch (message) {
            case "EndGame" :
                break;

            case "ImpossibleDeclare" :
                p.setPlayStrategy(new DeclareImp());
                p.playTurn();
                break;
            
            default :
                p.setPlayStrategy(new HandleError("InputArgError"));
                p.playTurn();
                break;
        }
    }
}