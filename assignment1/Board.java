package assignment1;
public class Board {

    Move[][] moves;

    public Board(int x, int y){
    }

    public void resizeBoard(int nx, int ny){

    }

    public Move[][] getBoard(){
        return this.moves;
    }

    public boolean setMove(Move move, int mNew, int nNew){
        // returns false if point x,y already has a move
        
        int m = this.moves.length;
        int n = this.moves[0].length;
        if(mNew<0 || mNew >=m || nNew<0 || nNew >= n){
            return false;
        }
        if(this.moves[mNew][nNew]!=null){
            return false;
        }
        this.moves[mNew][nNew] = move;
        return true;
    }
}
