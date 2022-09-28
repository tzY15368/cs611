//package assignment1;

import java.util.*;

public class TikTakToe extends Boardgame {

    public TikTakToe() {
        super(new Playable[]{
                new Player("x-holder", new HashMap<>() {{
                    put("X", Integer.MAX_VALUE);
                }}),
                new Team("Team-o-holder", new Player[]{
                        new Player("alice", new HashMap<>() {{
                            put("O", Integer.MAX_VALUE);
                        }}),
                        new Player("bob", new HashMap<>() {{
                            put("O", Integer.MAX_VALUE);
                        }}

                        )
                })
        }, 3, 3, true);

    }

    @Override
    public int getState() {
        Pair shape = board.getShape();
        List<Move[]> ls = new ArrayList<>();
        // row
        for (int i = 0; i < shape.m; i++) {
            ls.add(board.getRow(i));
        }
        //  col
        for (int i = 0; i < shape.n; i++) {
            ls.add(board.getCol(i));
        }
        // diagonal
        ls.addAll(board.getDiagonals());
        for (Move[] mv : ls) {
            int res = this.checkSameMoves(mv);
            if (res != STATE_CONTINUE) {
                return res;
            }
        }
        // check no NULL in moves
        if (!board.hasFreeSpace()) {
            return STATE_DRAW;
        }
        return STATE_CONTINUE;
    }


    public int checkSameMoves(Move[] moves) {
        Move prev = moves[0];
        if (prev == null) return STATE_CONTINUE;
        for (int i = 1; i < moves.length; i++) {
            if (moves[i] == null || !prev.getPiece().equals(moves[i].getPiece())) {
                return STATE_CONTINUE;
            }
        }
        return prev.getPlayerno();
    }
}
