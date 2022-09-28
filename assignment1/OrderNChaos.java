//package assignment1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderNChaos extends Boardgame{

    public OrderNChaos() {
        super(new Player[]{
                new Player("Order", new HashMap<>(){{
                    put("X", Integer.MAX_VALUE);
                    put("O", Integer.MAX_VALUE);
                }}),
                new Player("Chaos", new HashMap<>(){{
                    put("X", Integer.MAX_VALUE);
                    put("O", Integer.MAX_VALUE);
                }})
        }, 6, 6, false);
    }

    @Override
    public int getState() {
        Pair shape = board.getShape();
        List<Move[]> ls = new ArrayList<>();
        // row
        for(int i=0;i<shape.m;i++) {
            ls.add(board.getRow(i));
        }
        //  col
        for(int i=0;i<shape.n;i++) {
            ls.add(board.getCol(i));
        }
        // diagonal
        ls.addAll(board.getDiagonals());
        for(Move[] mv : ls){
            int res = this.check5OInARow(mv);
            if(res!=STATE_CONTINUE){
                return res;
            }
        }
        if(!board.hasFreeSpace()){
            return STATE_DRAW;
        }
        return STATE_CONTINUE;
    }

    private int check5OInARow(Move[] moves){
        Move last = null;
        int cnt = 0;
        for (Move move : moves) {
            if (move==null || !move.equals(last) ||move.getPiece().equals("O")) {
                last = move;
                cnt = 0;
            }
            cnt += 1;
            if (cnt >= 5) {
                return move.getPlayerno();
            }
        }
        return STATE_CONTINUE;
    }
}
