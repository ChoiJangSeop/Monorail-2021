package board;

// singleton
public class Board {

    private static Board instance = null;
    private Board() {}

    public static Board getInstance() {
        if ( instance == null) {
            return new Board();
        }
        return instance;
    }

    public void pushTile(int type, int x, int y) {}

    public void popTile() {}
}