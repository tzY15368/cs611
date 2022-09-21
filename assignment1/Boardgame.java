package assignment1;

public abstract class Boardgame {
    private Board board;
    private boolean boardResizable;
    private Player[] players;

    public void allocatePieces( ){

    }

    public Boardgame(Player[] players, int x, int y, boolean resizable){
        if(players.length < 2){
            System.out.println("invalid players"+players.length);
            System.exit(-1);
        }
        this.board = new Board(x,y);
        this.boardResizable = resizable;
        this.players = players;

    }

    public abstract void runGame(int gameCount);
}
