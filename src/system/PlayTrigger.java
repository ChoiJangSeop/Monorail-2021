package system;

import java.util.*;
import play.*;

public class PlayTrigger {

    public void trig (Player p, String message, List<Integer> arg) {
        
        switch (message) {
            case "PutTile" :
                p.setPlayStrategy(new PutTile(arg.get(0), arg.get(1), arg.get(2)));
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
        }
    }

    public void trig (Player p, String message) {
        
        switch (message) {
            case "TurnEnd" :
                p.setPlayStrategy(new EndTurn());
                p.playTurn();
                break;
            
            case "ImpossibleDeclare" :
                p.setPlayStrategy(new DeclareImp());
                p.playTurn();
                break;
        }
    }
}