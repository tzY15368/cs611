package assignment1;

import java.util.Map;
import java.util.Scanner;

public class Player {
    private int id;
    private int winCount;
    private Map<String, Integer> pieces;

    public Player(int id, Map<String, Integer> pieces) {
        this.pieces = pieces;
        this.id = id;
    }

    public void win(){
        this.winCount++;
    }

    public int getId(){
        return this.id;
    }

    public int getWin(){
        return this.winCount;
    }

    private String strf(String format, Object... args){
        return String.format("Player["+this.id+"]: "+format, args);
    }

    public Move getPiece() throws Exception{
        Scanner s = Prompt.input(this.strf("input: [m] [n] [piece]"));
        int m = s.nextInt();
        int n = s.nextInt();
        String piece = s.nextLine().strip();
        //Prompt.say(this.strf("got m=%d n=%d piece=%s", m, n ,piece));
        if(!this.pieces.containsKey(piece)){
            throw new Exception(this.strf("invalid piece: %s, valid piece %s", piece, this.pieces.keySet()));
        }
        int piece_cnt = this.pieces.get(piece);
        if(piece_cnt <= 0){
            throw new Exception(this.strf("no more piece "+piece+" available"));
        }
        this.pieces.put(piece, piece_cnt-1);
        Move mv = new Move(this.id, piece, new Point(m, n));

        return mv;
    }
}
