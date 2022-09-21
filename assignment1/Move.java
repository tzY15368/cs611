package assignment1;

public class Move {
    private int playerno;
    private String piece;
    private int round;
    public int m;
    public int n;

    public Move(int playerno, String piece, int roundno){
        this.playerno = playerno;
        this.piece = piece;
        this.round = roundno;
    }
}
