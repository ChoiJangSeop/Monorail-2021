package system;

import board.*;
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
        if (state == System.State.GAME_END) {
            // 게임종료
        }
        else if (state == System.State.TURN_END) {
            curPlayer = (curPlayer + 1) % 2;
        }
        else if (
            state == System.State.NO_TILE_ERROR ||
            state == System.State.OVER_TILE_ERROR ||
            state == System.State.TILE_CONNECT_ERROR
        ) {
            beginAction("HandleError");
        }

    }

    public void beginAction(String message, List<Integer> args) {
        PlayTrigger playTrigger = new PlayTrigger();
        playTrigger.trig(players.get(curPlayer), message, args);
    }

    public void beginAction(String message) {
        PlayTrigger playTrigger = new PlayTrigger();
        playTrigger.trig(players.get(curPlayer), message);
    }



}