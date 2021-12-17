package board;

import java.util.Stack;
import java.util.*;
// singleton
public class Board {

    private static Board instance = null;

    private TileState.Connect horizontalLine[][] = new TileState.Connect[18][18];
    private TileState.Connect verticalLine[][] = new TileState.Connect[18][18];
    private Stack<Tile> tileLog = new Stack<>();
    private int restTile = 16;
    private int useTile;
    

    private Board() {
        for (int i=0; i<18; ++i) {
            for (int j=0; j<18; ++j) {
                horizontalLine[i][j] = TileState.Connect.DEFAULT;
                verticalLine[i][j] = TileState.Connect.DEFAULT;
            }
        }
    }

    public static Board getInstance() {
        if ( instance == null) {
            return new Board();
        }
        return instance;
    }

    // pop and push tile
    public void pushTile(int x, int y, List<Integer> type) {
        tileLog.push(new Tile(x, y, type));

        // up
        TileState.pushState(horizontalLine[x][y], type.get(0));
        // down
        TileState.pushState(horizontalLine[x+1][y], type.get(1));
        // left
        TileState.pushState(verticalLine[x][y], type.get(2));
        // right
        TileState.pushState(verticalLine[x][y+1], type.get(3));

        useTile++; restTile--;
    }

    public void popTile() {
        int x = tileLog.peek().getX();
        int y = tileLog.peek().getY();
        List<Integer> type = tileLog.peek().getType();

        tileLog.pop();

        // up
        TileState.popState(horizontalLine[x][y], type.get(0));
        // down
        TileState.popState(horizontalLine[x+1][y], type.get(1));
        // left
        TileState.popState(verticalLine[x][y], type.get(2));
        // right
        TileState.popState(verticalLine[x][y+1], type.get(3));

        restTile++; useTile--;
    }
    
    // useTile checking
    public int getRestTile() { return this.restTile; }
    public int getUseTile() { return this.useTile; }
    public void initUseTile() { this.useTile = 0; }

    // connecting checking
    public List<TileState.Connect> getAdjacnetState() {
        List<TileState.Connect> ret = new ArrayList<>();

        int x = tileLog.peek().getX();
        int y = tileLog.peek().getY();

        ret.add(horizontalLine[x][y]);
        ret.add(horizontalLine[x+1][y]);
        ret.add(verticalLine[x][y]);
        ret.add(verticalLine[x][y+1]);
        
        return ret;
    }
    
    public List<TileState.Connect> getAdjacnetState(int x, int y) {
        List<TileState.Connect> ret = new ArrayList<>();
        
        ret.add(horizontalLine[x][y]);
        ret.add(horizontalLine[x+1][y]);
        ret.add(verticalLine[x][y]);
        ret.add(verticalLine[x][y+1]);
        
        return ret;
    } 

}