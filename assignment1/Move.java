package assignment1;

class Point {
    public int m;
    public int n;

    public Point(int m, int n){
        this.m = m;
        this.n = n;
    }

    public String toString(){
        return String.format("Point[m=%s, n=%s]", m, n);
    }
}

public class Move {
    private int playerno;
    private String piece;
    private int round;
    public Point point;

    public Move(int playerno, String piece, Point pt){
        this.playerno = playerno;
        this.piece = piece;
        this.point = pt;
    }

    public void setRound(int round){
        this.round = round;
    }

    public String getPiece(){
        return this.piece;
    }

    public int getPlayerno(){
        return this.playerno;
    }

    public String toString(){
        return String.format("Move[%s] player=%d round=%d point=%s", piece, playerno, round, point);
    }
}
