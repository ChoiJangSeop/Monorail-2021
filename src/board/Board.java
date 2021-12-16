package board;

import java.util.Stack;
// singleton
public class Board {

    private static Board instance = null;

    private Tile[][] board = new Tile[17][17];
    private Stack<Tile> tileLog = new Stack<>();
    private int restTile = 16;
    private int useTile;
    

    private Board() {
        for (int i=0; i<17; ++i) {
            for (int j=0; j<17; ++j) {
                this.board[i][j] = new Tile(Tile.Type.NONE, i, j);
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
    public void pushTile(Tile.Type type, int x, int y) {
        board[x][y].setType(type);
        tileLog.push(new Tile(type, x, y));
        restTile--; useTile++;
    }

    public void popTile() {
        Tile popTile = tileLog.peek();
        
        int x = popTile.getX();
        int y = popTile.getY();
        
        board[x][y].setType(Tile.Type.NONE);
        tileLog.pop();
        restTile++; useTile--;
    }
    
    // useTile checking
    public int getRestTile() { return this.restTile; }
    public int getUseTile() { return this.useTile; }
    public void initUseTile() { this.useTile = 0; }


    // connect checking
    public boolean isTileConnect() {
        int posX = tileLog.peek().getX();
        int posY = tileLog.peek().getY();
        
        int[] diffX = { -1, 1, 0, 0 };
        int[] diffY = { 0, 0, 1, -1 };

        for (int i=0; i<4; ++i) {
            if (posX + diffX[i] >= 0 && posX + diffX[i] < 17 && posY + diffY[i] >= 0 && posY + diffY[i] < 17) {
                if (board[posX + diffX[i]][posY + diffY[i]].getRight() != Tile.Connect.DEFAULT) return true; 
            }
        }
        return false;
    }

    public boolean isRailConnect() {
        int posX = tileLog.peek().getX();
        int posY = tileLog.peek().getY();
        
        if (posX+1 < 17 &&
            board[posX+1][posY].getUp() != Tile.Connect.DEFAULT && 
            board[posX+1][posY].getUp() != board[posX][posY].getDown()) return false;

        if (posX-1 >= 0 && 
            board[posX-1][posY].getDown() != Tile.Connect.DEFAULT && 
            board[posX-1][posY].getDown() != board[posX][posY].getUp()) return false;

        if (posY+1 < 17 &&
            board[posX][posY+1].getLeft() != Tile.Connect.DEFAULT && 
            board[posX][posY+1].getLeft() != board[posX][posY].getRight()) return false;
        
        if (posY-1 >= 0 &&
            board[posX][posY-1].getRight() != Tile.Connect.DEFAULT && 
            board[posX][posY-1].getRight() != board[posX][posY-1].getLeft()) return false; 
        
        return true;
    }

    public boolean isAllConnect() {
        Stack<Tile> tmpTileLog = new Stack<>();
        boolean ret = true;

        while (!tileLog.empty()) {
            tmpTileLog.push(tileLog.peek());

            int posX = tileLog.peek().getX();
            int posY = tileLog.peek().getY();

            if (board[posX][posY].getLeft() == Tile.Connect.ABLE) {
                if (posY-1 < 0) { ret = false; break; }
                if (board[posX][posY-1].getRight() != Tile.Connect.ABLE) { ret = false; break; }
            }

            if (board[posX][posY].getRight() == Tile.Connect.ABLE) {
                if (posY+1 >= 17) { ret = false; break; }
                if (board[posX][posY+1].getLeft() != Tile.Connect.ABLE) { ret = false; break; }
            }

            if (board[posX][posY].getUp() == Tile.Connect.ABLE) {
                if (posX-1 < 0) { ret = false; break; }
                if (board[posX-1][posY].getDown() != Tile.Connect.ABLE) { ret = false; break; }
            }

            if (board[posX][posY].getDown() == Tile.Connect.ABLE) {
                if (posX+1 >= 17) { ret = false; break; }
                if (board[posX+1][posY].getUp() != Tile.Connect.ABLE) { ret = false; break; }
            }
            tileLog.pop();
        }

        while (!tmpTileLog.empty()) {
            tileLog.push(tmpTileLog.peek());
            tmpTileLog.pop();
        }

        return ret;
    }
}