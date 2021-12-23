package board;

import java.util.Stack;
import java.util.*;
// singleton
public class Board {

    private static Board instance = new Board();

    private TileState.Connect horizontalLine[][] = new TileState.Connect[18][18];
    private TileState.Connect verticalLine[][] = new TileState.Connect[18][18];
    private Stack<Tile> tileLog = new Stack<>();
    private int restTile;
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
        return instance;
    }

    public void initBoard() {
        List<Integer> type = new ArrayList<>();
        type.add(0); type.add(0); type.add(1); type.add(1);

        instance.pushTile(5, 7, type);
        instance.pushTile(5, 8, type);
        
        this.useTile = 0;
        this.restTile = 16;
    }

    // pop and push tile
    public void pushTile(int x, int y, List<Integer> type) {
        tileLog.push(new Tile(x, y, type));
        
        // up
        horizontalLine[x][y] = TileState.pushState(horizontalLine[x][y], type.get(0));
        // down
        horizontalLine[x+1][y]= TileState.pushState(horizontalLine[x+1][y], type.get(1));
        // left
        verticalLine[x][y] = TileState.pushState(verticalLine[x][y], type.get(2));
        // right
        verticalLine[x][y+1] = TileState.pushState(verticalLine[x][y+1], type.get(3));

        useTile++; restTile--;
    }

    public void popTile() {
        
        int x = tileLog.peek().getX();
        int y = tileLog.peek().getY();
        List<Integer> type = tileLog.peek().getType();

        tileLog.pop();

        // up
        horizontalLine[x][y] = TileState.popState(horizontalLine[x][y], type.get(0));
        
        // down
        horizontalLine[x+1][y] = TileState.popState(horizontalLine[x+1][y], type.get(1));
        // left
        verticalLine[x][y] = TileState.popState(verticalLine[x][y], type.get(2));
        // right
        verticalLine[x][y+1] = TileState.popState(verticalLine[x][y+1], type.get(3));

        restTile++; useTile--;
    }
    
    // useTile checking
    public int getRestTile() { return this.restTile; }
    public int getUseTile() { return this.useTile; }
    public void initUseTile() { this.useTile = 0; }

    // direction checking
    public List<Tile> getTurnUseTiles() {
        List<Tile> ret = new ArrayList<>();
        Stack<Tile> temp = new Stack<>();

        for (int i=0; i<this.useTile; ++i) {
            temp.push(this.tileLog.peek());
            ret.add(this.tileLog.peek());
            this.tileLog.pop();
        }

        for (int i=0; i<this.useTile; ++i) {
            tileLog.push(temp.peek());
            temp.pop();
        }

        return ret;
    }

    // connecting checking
    public List<TileState.Connect> getAdjacnetState() {
        List<TileState.Connect> ret = new ArrayList<>();
        if (tileLog.empty()) { return ret; }

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
        if (tileLog.empty()) { return ret; }
        ret.add(horizontalLine[x][y]);
        ret.add(horizontalLine[x+1][y]);
        ret.add(verticalLine[x][y]);
        ret.add(verticalLine[x][y+1]);
        
        return ret;
    } 

}