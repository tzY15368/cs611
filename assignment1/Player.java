package assignment1;

import java.util.HashMap;
import java.util.Map;

public class Player {
    private int id;
    private Map<String, Integer> pieces;

    public Player(int id, Map<String, Integer> pieces) {
        this.pieces = pieces;
        this.id = id;
    }

    public Move getPiece() throws Exception{
        if(!this.pieces.containsKey(piece)){
            throw new Exception("player: invalid piece:"+piece);
        }
        int piece_cnt = this.pieces.get(piece);
        if(piece_cnt <= 0){
            throw new Exception("player: no more piece "+piece+" available");
        }
        this.pieces.put(piece, piece_cnt-1);
        String in = Prompt.input("input: [piece] [m] [n]");
        Move mv = new Move(this.id, piece, roundno, new Point());

        return mv;
    }
}
