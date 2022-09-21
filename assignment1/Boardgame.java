package assignment1;

public abstract class Boardgame {
    private Board board;
    private int playerCount;
    private boolean boardResizable;

    public Boardgame(int playerCount, int x, int y, boolean resizeable){
        this.board = new Board(x,y);
        this.boardResizable = resizeable;
        this.playerCount = playerCount;
    }

    public abstract void runGame(int gameCount);
}
