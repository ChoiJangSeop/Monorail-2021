package play;

import system.MainSystem.State;

import checker.ExecuteChecker;

import java.util.List;
import java.util.ArrayList;

public class ImpPutTile implements PlayStrategy {
    List<Integer> args = new ArrayList<>();

    public ImpPutTile(List<Integer> args) {
        this.args = args;
    }

    @Override
    public void play() {
        PlayStrategy putTile = new PutTile(args);
        putTile.play();
    }

    @Override
    public State validCheck() {
        // BUG pop up the error window over system wants
        ExecuteChecker executeChecker = new ExecuteChecker();
        State result = executeChecker.execute("ImpPutTile");
        return result;
    }
}