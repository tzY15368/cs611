//package assignment1;


public class Move {
    private int playerno;
    private final String piece;
    private int round;
    public Point point;

    public Move(int playerno, String piece, Point pt) {
        this.playerno = playerno;
        this.piece = piece;
        this.point = pt;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public String getPiece() {
        return this.piece;
    }

    public int getPlayerno() {
        return this.playerno;
    }

    public void setPlayerno(int po) {
        this.playerno = po;
    }

    ;

    public String toString() {
        return String.format("Move[%s] player=%d round=%d point=%s", piece, playerno, round, point);
    }

    public boolean equals(Move other) {
        if (other == null) return false;
        return this.getPiece().equals(other.getPiece());
    }
}
