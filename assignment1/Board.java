package assignment1;


import java.util.ArrayList;
import java.util.List;

class Pair {
    public int m;
    public int n;
    public Pair(int m, int n){
        this.m = m;
        this.n = n;
    }
    public String toString(){
        return "Pair<"+m+","+n+">";
    }
}

class Board {

    private Move[][] moves;
    private final boolean resizable;
    private final int maxX = 10;
    private final int maxY = 10;

    public Board(int x, int y, boolean resizable){
        x = Math.max(1,x);
        y = Math.max(1,y);
        this.resizable = resizable;
        this.moves = new Move[x][y];
    }

    public void reset(){
        this.moves = new Move[moves.length][moves[0].length];
    }

    public Pair getShape(){
        return new Pair(moves.length, moves[0].length);
    }

    public List<Move[]> getDiagonals(){
        List<Move[]> res = new ArrayList<>();
        Pair shape = this.getShape();
        if(shape.m < shape.n){

            for(int diff=0;diff<=(shape.n-shape.m);diff++){
                Move[] mvs = new Move[shape.m];
                Move[] mvs2 = new Move[shape.m];
                mvs[0] = moves[0][0];
                mvs2[0] = moves[0][shape.m-1];
                for(int i=1;i<shape.m;i++){
                    mvs[i] = moves[i][i+diff];
                    mvs2[i] = moves[i][shape.m-1-i+diff];
                }
                res.add(mvs);
                res.add(mvs2);
            }
        } else {
            for(int diff=0;diff<=(shape.m-shape.n);diff++){
                Move[] mvs = new Move[shape.n];
                Move[] mvs2 = new Move[shape.n];
                mvs[0] = moves[0][0];
                mvs2[0] = moves[0][shape.n-1];
                for(int i=1;i<shape.n;i++){
                    mvs[i] = moves[i+diff][i];
                    mvs2[i] = moves[shape.n-1-i+diff][i];
                }
                res.add(mvs);
                res.add(mvs2);
            }
        }
        return res;
    }

    public boolean hasFreeSpace(){
        Pair shape = this.getShape();
        for(int i=0;i<shape.m;i++){
            for(int j=0;j<shape.n;j++){
                if(moves[i][j]==null)return true;
            }
        }
        return false;
    }

    public Move[] getRow(int rowIndex){
        return this.moves[rowIndex];
    }

    public Move[] getCol(int colIndex){
        Move[] res = new Move[moves.length];
        for(int i=0;i<res.length;i++){
            res[i] = moves[i][colIndex];
        }
        return res;
    }

    public boolean resizeBoard(int nx, int ny){
        if(!this.resizable){
            Prompt.say("Warning: board not resizable");
            return false;
        }
        int m = moves.length;
        int n = moves[0].length;
        if(nx>=maxX || ny >= maxY || nx <= moves.length || ny <= moves[0].length){
            Prompt.say("invalid resize args"+nx+" "+ny);
            return false;
        }
        Move[][] newMoves = new Move[nx][ny];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                newMoves[i][j] = moves[i][j];
            }
        }
        this.moves = newMoves;
        return true;
    }

    public String toString(){
        String s = "";
        for(int i=0;i<moves.length;i++){

            for(int j=0;j<moves[0].length;j++){
                s += "" + (moves[i][j]==null?"_":moves[i][j].getPiece()) + " ";
            }
            s += "\n";
        }
        return s;
    }

    public boolean addMove(Move move){
        // validate board
        int mNew = move.point.m;
        int nNew = move.point.n;
        int m = this.moves.length;
        int n = this.moves[0].length;
        if(mNew<0 || mNew >=m || nNew<0 || nNew >= n){
            return false;
        }
        // put things in board
        if(this.moves[mNew][nNew]!=null){
            return false;
        }
        this.moves[mNew][nNew] = move;
        return true;
    }
}
