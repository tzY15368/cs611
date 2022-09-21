package assignment1;

import java.util.HashMap;
import java.util.Map;

public class Player {
    int id;
    Map<Piece, Integer> pieces;
    public Player(int id, Map<Piece, Integer> pieces){
        this.pieces = pieces;
        this.id = id;
    }

    public Move getPiece(String piece, int roundno) throws Exception{
        if(!this.pieces.containsKey(piece)){
            throw new Exception("player: invalid piece:"+piece);
        }
        int piece_cnt = this.pieces.get(piece);
        if(piece_cnt <= 0){
            throw new Exception("player: no more piece "+piece+" available");
        }
        this.pieces.put(piece, piece_cnt-1);
        return new Move(this.id, piece, roundno);
    }
}
