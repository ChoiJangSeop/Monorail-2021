package system;

import java.util.*;

import board.Board;

public class MainSystem {
    public static enum State { 
        NONE,
        TURN_END, 
        GAME_END, 
        IMP_MODE,
        TILE_CONNECT_ERROR,
        RAIL_CONNECT_ERROR,  
        NO_TILE_ERROR,
        ZERO_TILE_ERROR,
        OVER_TILE_ERROR  
    };

    private static MainSystem instance = null;
    
    int curPlayer = 0;
    private List<Player> players = new ArrayList<Player>();
    private Lock lock = new Lock();

    private MainSystem() {
        players.add(new Player("P1"));
        players.add(new Player("P2"));
    }

    public static MainSystem getInstance() {
        if (instance == null) {
            instance = new MainSystem();
        }
        return instance;
    }   

    

    private void endAction(MainSystem.State state) {

        switch (state) {
            case NONE :
                lock.openLock();
                break;

            case TURN_END :
                curPlayer = (curPlayer + 1) % 2;
                Board.getInstance().initUseTile();
                lock.openLock();
                break;
            
            case IMP_MODE :
                curPlayer = (curPlayer + 1) % 2;
                lock.openLock();
                break;
            // BUG hava a leg to complete game
            case GAME_END :
                System.out.println(curPlayer+1 + "P is winner!");
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
            
            case RAIL_CONNECT_ERROR :
                beginAction("HandleError", "RailConnectError");
            
        }
       

    }

    public boolean userBeginAction(String message, List<Integer> args) {
        if (lock.getLock()) {
            lock.closeLock();
            PlayTrigger playTrigger = new PlayTrigger();
            State endState = playTrigger.trig(players.get(curPlayer), message, args);
            
            boolean ret;

            if (endState == State.NONE || endState == State.TURN_END || endState == State.GAME_END) { ret = true; } 
            else { ret = false; }

            endAction(endState);
            return ret;
        }
        
        return false;
    }

    public boolean userBeginAction(String message) {
        
        if (lock.getLock()) {
            lock.closeLock();
            PlayTrigger playTrigger = new PlayTrigger();
            State endState = playTrigger.trig(players.get(curPlayer), message);
            
            boolean ret;

            if (endState == State.NONE || endState == State.TURN_END || endState == State.GAME_END) { ret = true; } 
            else { ret = false; }

            endAction(endState);
            return ret;
        }

        return false;
    }

    // no user input
    public void beginAction(String message, String arg) {
        PlayTrigger playTrigger = new PlayTrigger();
        State endState = playTrigger.trig(players.get(curPlayer), message, arg);
        endAction(endState);
    }

    // needs user input
    public void beginAction(String message) {
        PlayTrigger playTrigger = new PlayTrigger();
        State endState = playTrigger.trig(players.get(curPlayer), message);
        endAction(endState);
    }
}