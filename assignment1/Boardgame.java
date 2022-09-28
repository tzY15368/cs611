//package assignment1;

import java.util.InputMismatchException;
import java.util.Scanner;



public abstract class Boardgame {
    protected Board board;
    private Playable[] players;
    private int roundno;
    private int gameCount;
    protected final int STATE_DRAW = -2;
    protected final int STATE_CONTINUE = -1;

    public Boardgame(Playable[] players, int x, int y, boolean resizable) {
        if (players.length < 2) {
            System.out.println("invalid players" + players.length);
            System.exit(-1);
        }
        this.board = new Board(x, y, resizable);
        this.players = players;
    }

    private int evalState() {
        int c = this.getState();
        if (c != STATE_DRAW || !board.getResizable()) {
            return c;
        }
        return c;
        // attempt to resize the board
        // try {
        //     Scanner s = Prompt.input("Draw, enter [] [] to resize or any other to end");
        //     int nm = s.nextInt();
        //     int nn = s.nextInt();
        //     boolean res = board.resizeBoard(nm, nn);
        //     if (!res) {
        //         Prompt.say("board resize failed");
        //         return STATE_DRAW;
        //     } else {
        //         Prompt.say("board size is now " + board.getShape());
        //         return STATE_CONTINUE;
        //     }
        // } catch (InputMismatchException ime) {
        //     Prompt.say("invalid input");
        //     return STATE_DRAW;
        // }
    }

    public abstract int getState();

    public void printBoard() {
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

    public int runRound() {
        this.roundno++;
        for (Playable player : this.players) {
            while (true) {
                try {
                    //Prompt.say(board.toString());
                    printBoard();
                    Move move = player.getPiece();
                    move.setRound(roundno);
                    if (!this.board.addMove(move)) {
                        Prompt.say("Board: move failed:" + move);
                        continue;
                    }

                    printBoard();
                    int winner = this.evalState();

                    if (winner != STATE_CONTINUE) {
                        return winner;
                    }
                    break;
                } catch (Exception e) {
                    Prompt.reset();
                    Prompt.say("invalid params:" + e);
                }
            }
        }
        return STATE_CONTINUE;
    }

    public String gameSummary() {
        StringBuilder s = new StringBuilder();
        s.append(String.format("Summary:\n\tOut of %d rounds,", this.gameCount));
        for (Playable p : this.players) {
            s.append(String.format("\n %s won [%d] games; ", p.getPrintName(), p.getWin()));
        }
        return s.toString();
    }

    public void runGame() {
        Prompt.say("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Prompt.say("Welcome to " + this.getClass().getSimpleName() + "...");
        while (true) {
            Prompt.say("---------------------------");
            try {
                if(board.getResizable()){
                    Scanner s = Prompt.input("enter [int] [int] to resize or any string to stick to default" + board.getShape());
                    Prompt.say("new m and new n must be greater or equal to original (default)");
                    int nm = s.nextInt();
                    int nn = s.nextInt();
                    boolean res = board.resizeBoard(nm, nn);
                    if (!res) {
                        Prompt.say("board resize failed, shape: " + board.getShape());
                    } else {
                        Prompt.say("board size is now " + board.getShape());
                    }
                }   
                
            } catch (InputMismatchException ime) {
                Prompt.say("invalid input, fallingback to default" + board.getShape());
            }
            try {
                Prompt.say("starting in 1 sec");
                Thread.sleep(1000);
            } catch (InterruptedException ie){
                System.exit(2);
            }
            Prompt.say("Initialized board:");
            board.reset();
            while (true) {
                int state = this.runRound();
                switch (state) {
                    case STATE_CONTINUE:
                        Prompt.say("next round..");
                        break;
                    case STATE_DRAW:
                        Prompt.say("Game ended with a draw");
                        break;
                    default:
                        for (Playable player : players) {
                            if (player.getId() == state) {
                                player.win();
                                Prompt.say("winner is " + player.getPrintName());
                                break;
                            }
                        }

                }
                if (state != STATE_CONTINUE) break;
            }
            gameCount++;

            Scanner s = Prompt.input("enter Y to play next game");
            String in = s.nextLine().strip();
            if (!in.equals("Y")) {
                Prompt.say(this.gameSummary());
                Prompt.say("Bye.");
                return;
            }

        }
    }
}
