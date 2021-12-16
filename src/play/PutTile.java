package play;

import board.*;
import checker.*;

public class PutTile implements PlayStrategy {
    int x, y;
    int type;

    public PutTile(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    @Override
    public void play() {
        //Board.getInstance().pushTile(type, x, y);
    } 

    @Override
    public void validCheck() {
        ExecuteChecker executeChecker = new ExecuteChecker();
        executeChecker.execute("PutTile");
    }
}