package board;

import java.util.*;
public class Tile {

    private List<Integer> type;
    private int x, y;

    public Tile(int x, int y, List<Integer> type) {
        this.x = x;
        this.y = y;
        this.type = type;
    } 
    
    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public List<Integer> getType() { return this.type; } 
}