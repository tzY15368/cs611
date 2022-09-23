package assignment1;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Boardgame {
    protected Board board;
    private Player[] players;
    private int roundno;
    private int gameCount;

    public Boardgame(Player[] players, int x, int y, boolean resizable){
        if(players.length < 2){
            System.out.println("invalid players"+players.length);
            System.exit(-1);
        }
        this.board = new Board(x,y, resizable);
        this.players = players;
    }

    public void printBoard(){
        try {
            String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.println("\033c");
            }
        } catch (Exception exception) {
            //  Handle exception.
        }
        Prompt.say(board.toString());
    }

    public abstract int evalState();

    public int runRound(){
        this.roundno++;
        for(Player player : this.players){
            while(true){
                try {
                    //Prompt.say(board.toString());
                    printBoard();
                    Move move = player.getPiece();
                    move.setRound(roundno);
                    if(!this.board.addMove(move)){
                        Prompt.say("Board: move failed:"+move);
                        continue;
                    }

                    printBoard();
                    int winner = this.evalState();

                    if(winner!=-1){
                        return winner;
                    }
                    break;
                }
                catch (Exception e) {
                    Prompt.reset();
                    Prompt.say("invalid params:"+e);
                }
            }
        }
        return -1;
    }

    public String gameSummary(){
        StringBuilder s = new StringBuilder();
        s.append(String.format("Summary:\n\tOut of %d rounds,", this.gameCount));
        for(Player p : this.players){
            s.append(String.format("\nPlayer[%d] won [%d] games; ", p.getId(), p.getWin()));
        }
        return s.toString();
    }

    public void runGame(){
        Prompt.say("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Prompt.say("Welcome to "+this.getClass().getSimpleName()+"...");
        while(true){
            Prompt.say("---------------------------");
            Prompt.say("Initialized board:");
            board.reset();
            while(true){
                int winner = this.runRound();
                if(winner!=-1){
                    if(winner==-2){
                        Prompt.say("Game ended with a draw");
                    } else {
                        Prompt.say("winner is "+winner);
                        players[winner].win();
                    }
                    break;
                }
                Prompt.say("next round..");
            }
            gameCount++;

            Scanner s = Prompt.input("enter Y to play next game");
            s.reset();
            String in = s.nextLine().strip();
            if(!in.equals(new String("Y"))){
                Prompt.say(this.gameSummary());
                Prompt.say("Bye.");
                return;
            }

        }
    }
}
