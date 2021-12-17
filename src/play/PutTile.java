package play;

import board.*;
import checker.*;
import java.util.*;

public class PutTile implements PlayStrategy {
    int x, y;
    List<Integer> connectable;

    // TODO eidt input
    public PutTile(List<Integer> args) {
        this.x = args.get(0);
        this.y = args.get(1);

        for (int i=0; i<args.size(); ++i) {
            if (i >= 2 && i < 6)  {
                connectable.add(args.get(i));
            } 
        }
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