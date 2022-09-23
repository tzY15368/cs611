package assignment1;

class Point {
    public int m;
    public int n;

    public Point(int m, int n){
        this.m = m;
        this.n = n;
    }
}

public class Move {
    private int playerno;
    private String piece;
    private int round;
    private Point point;

    public Move(int playerno, String piece, int roundno, Point pt){
        this.playerno = playerno;
        this.piece = piece;
        this.round = roundno;
        this.point = pt;
    }
}
