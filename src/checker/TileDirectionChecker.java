package checker;

import board.Board;
import system.MainSystem;
import board.Tile;

import java.util.List;
import java.util.Stack;
import java.util.ArrayList;

public class TileDirectionChecker extends Checker {
    // COMPLETE 타일 방향 예외처리 작성
    @Override
    public MainSystem.State check() {
        List<Tile> curTiles = Board.getInstance().getTurnUseTiles();

        boolean flag1 = true;
        boolean flag2 = true;
        
        // 가로
        for (int i=1; i<curTiles.size(); ++i) {
            boolean temp = false;
            for (int j=0; j<i; ++j) {
                int xi = curTiles.get(i).getX();
                int yi = curTiles.get(i).getY();
                int xj = curTiles.get(j).getX();
                int yj = curTiles.get(j).getY();
                
                if (xi == xj && (yi + 1 == yj || yi - 1 == yj)) temp = true;
            }
            if (!temp) flag2 = false;
        }

        // 세로
        for (int i=1; i<curTiles.size(); ++i) {
            boolean temp = false;
            for (int j=0; j<i; ++j) {
                int xi = curTiles.get(i).getX();
                int yi = curTiles.get(i).getY();
                int xj = curTiles.get(j).getX();
                int yj = curTiles.get(j).getY();
                
                if (yi == yj && (xi + 1 == xj || xi - 1 == xj)) temp = true;
            }
            if (!temp) flag1 = false;
        }

        if (flag1 || flag2) {
            return MainSystem.State.NONE;
        } else {
            return MainSystem.State.TILE_DIRECTION_ERROR;
        }


    }
}