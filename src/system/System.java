package system;

import java.util.*;

public class System {
    public static enum State { 
        NONE,
        TURN_END, 
        GAME_END, 
        TILE_CONNECT_ERROR,  
        NO_TILE_ERROR,
        ZERO_TILE_ERROR,
        OVER_TILE_ERROR  
    };

    private static System instance = null;
    
    int curPlayer = 0;
    private List<Player> players = new ArrayList<Player>();

    private System() {
        players.add(new Player("P1"));
        players.add(new Player("P2"));
    }

    public static System getInstance() {
        if (instance == null) {
            instance = new System();
        }
        return instance;
    }   

    

    public void endAction(System.State state) {

        switch (state) {
            case GAME_END :
                beginAction("EndGame");
                break;
            
            case TURN_END :
                curPlayer = (curPlayer + 1) % 2;
                Lock.getInstance().openLock();
                break;

            case ZERO_TILE_ERROR :
                beginAction("HandleError", "ZeroTileError");
                break;
            
            case NO_TILE_ERROR :
                beginAction("HandleError", "NoTileError");
                break;

            case OVER_TILE_ERROR :
                beginAction("HandleError", "OverTileError");
                break;
            
            case TILE_CONNECT_ERROR :
                beginAction("HandleError", "TileConnectError");
                break;
            
            case NONE :
                Lock.getInstance().openLock();
                break;
        }
       

    }

    public void userBeginAction(String message, List<Integer> args) {
        if (Lock.getInstance().getLock()) {
            Lock.getInstance().closeLock();
            PlayTrigger playTrigger = new PlayTrigger();
            playTrigger.trig(players.get(curPlayer), message, args);
        }
        
    }

    // no user input
    public void beginAction(String message, String arg) {
        PlayTrigger playTrigger = new PlayTrigger();
        playTrigger.trig(players.get(curPlayer), message, arg);
    }

    // needs user input
    public void beginAction(String message) {
        PlayTrigger playTrigger = new PlayTrigger();
        playTrigger.trig(players.get(curPlayer), message);
    }
}