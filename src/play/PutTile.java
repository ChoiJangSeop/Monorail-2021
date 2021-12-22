package play;

import board.*;
import checker.*;
import java.util.*;

import system.MainSystem;

public class PutTile implements PlayStrategy {
    int x, y;
    List<Integer> connectable = new ArrayList<>();

    // COMPLETE eidt input
    public PutTile(List<Integer> args) {
        this.x = args.get(0);
        this.y = args.get(1);

        switch(args.get(2)) {
            case 0 :
                this.connectable.add(0);
                this.connectable.add(0);
                this.connectable.add(1);
                this.connectable.add(1);
                break;

            case 1 :
                this.connectable.add(1);
                this.connectable.add(1);
                this.connectable.add(0);
                this.connectable.add(0);
                break;
            
            case 2 :
                this.connectable.add(1);
                this.connectable.add(0);
                this.connectable.add(0);
                this.connectable.add(1);
                break;

            case 3 :
                this.connectable.add(1);
                this.connectable.add(0);
                this.connectable.add(1);
                this.connectable.add(0);
                break;

            case 4 :
                this.connectable.add(0);
                this.connectable.add(1);
                this.connectable.add(1);
                this.connectable.add(0);
                break;
            
            case 5 :
                this.connectable.add(0);
                this.connectable.add(1);
                this.connectable.add(0);
                this.connectable.add(1);
                break;
        }
    }

    @Override
    public void play() {
        Board.getInstance().pushTile(x, y, connectable);
    } 

    @Override
    public MainSystem.State validCheck() {
        ExecuteChecker executeChecker = new ExecuteChecker();
        return executeChecker.execute("PutTile");
    }
}