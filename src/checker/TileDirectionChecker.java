package checker;

import board.Board;
import system.MainSystem;
import board.Tile;

import java.util.List;
import java.util.Stack;
import java.util.ArrayList;

public class TileDirectionChecker extends Checker {
    // TODO 타일 방향 예외처리 작성
    @Override
    public MainSystem.State check() {
        return MainSystem.State.NONE;
    }
}