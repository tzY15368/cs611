package assignment1;

public abstract class Boardgame {
    private Board board;
    private boolean boardResizable;
    private Player[] players;

    public Boardgame(Player[] players, int x, int y, boolean resizable){
        if(players.length < 2){
            System.out.println("invalid players"+players.length);
            System.exit(-1);
        }
        this.board = new Board(x,y);
        this.boardResizable = resizable;
        this.players = players;
    }

    public void PrintBoard(){
        System.out.println("print...");
    }

    public abstract int evalState();

    public int runRound(){
        for(Player player : this.players){
            while(true){
                try {
                    Move move = player.getPiece();
                    // validate board
                    // put things in board
                    int state = this.evalState();
                    if(state!=-1){
                        return state;
                    }
                    break;
                } catch (Exception e) {
                    // TODO: handle exception
                    continue;
                }
            }
        }
        return -1;
    }

    public void runGame(int gameCount){
        while(gameCount!=0){
            Prompt.say("welcome...");
            while(true){
                int winner = this.runRound();
                if(winner!=-1){
                    Prompt.say("winner is"+winner);
                    break;
                }
                Prompt.say("next round..");
            }
        }
    }
}
