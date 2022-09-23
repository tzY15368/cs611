package assignment1;
import java.util.*;

public class TikTakToe extends Boardgame{

    public TikTakToe() {
        super(new Player[]{
            new Player(0, new HashMap<>(){{
                put("X", Integer.MAX_VALUE);
            }}),
            new Player(1, new HashMap<>(){{
                put("O", Integer.MAX_VALUE);
            }})
        }, 3, 3, true);
        
    }

    @Override
    public int evalState() {
        int c = checkState();
        Prompt.say("got state "+c);
        if(c == -2){
            // resize or draw
            Scanner s = Prompt.input("Draw, enter [] [] to resize or any other to end");
            Prompt.reset();
            try {
                int nm = s.nextInt();
                int nn = s.nextInt();
                boolean res = board.resizeBoard(nm, nn);
                if(!res){
                    Prompt.say("board resize failed, this is a draw");
                    return -2;
                } else {
                    Prompt.say("board size is now "+board.getShape() );
                }
            } catch (InputMismatchException ime){
                return -2;
            }
            return -1;
        } else {
            return c;
        }
    }

    private int checkState(){
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
            int res = this.checkSameMoves(mv);
            if(res!=-1){
                return res;
            }
        }
        // check no NULL in moves
        if(!board.hasFreeSpace()){
            return -2;
        }
        return -1;
    }


    public int checkSameMoves(Move[] moves){
        Move prev = moves[0];
        if(prev==null)return -1;
        for(int i=1;i<moves.length;i++){
            if(moves[i]==null || !prev.getPiece().equals(moves[i].getPiece())){
                return -1;
            }
        }
        return prev.getPlayerno();
    }
}
