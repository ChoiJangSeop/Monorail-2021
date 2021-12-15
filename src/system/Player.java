package system;

import play.PlayStrategy;

public class Player {
    private String name;
    private PlayStrategy playStrategy;

    public Player(String name) { this.name = name; }
    public String getName() { return this.name; }

    public void playTurn() { 
        this.playStrategy.play();
        this.playStrategy.validCheck(); 
    }

    public void setPlayStrategy(PlayStrategy playStrategy) {
        this.playStrategy = playStrategy;
    }
}