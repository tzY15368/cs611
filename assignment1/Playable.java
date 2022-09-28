//package assignment1;

import java.util.Map;
import java.util.Scanner;

public interface Playable {
    Move getPiece() throws Exception;

    void win();

    int getWin();

    int getId();

    String getName();

    String getPrintName();
}



abstract class Participant {
    private int id;
    private int winCount;
    protected final String name;
    private static int maxId = 0;

    private static int assignId() {
        maxId++;
        return maxId;
    }

    public Participant(String name) {
        this.id = Participant.assignId();
        this.winCount = 0;
        this.name = name;
    }

    public int getWin() {
        return this.winCount;
    }

    public void win() {
        this.winCount++;
    }

    public int getId() {
        return this.id;
    }

    ;

    public String getName() {
        return this.name;
    }

    ;

    public abstract String getPrintName();

    public abstract Move getPiece() throws Exception;
}

class Team extends Participant implements Playable {
    private Player[] players;
    private int rrCount = 0;

    public Team(String name, Player[] players) {
        super(name);
        this.players = players;
    }

    @Override
    public Move getPiece() throws Exception {
        Prompt.say(getPrintName() + ": sending " + players[rrCount % players.length].getPrintName());
        Move m = players[rrCount % players.length].getPiece();
        m.setPlayerno(this.getId());
        rrCount++;
        return m;
    }

    @Override
    public String getPrintName() {
        StringBuilder sb = new StringBuilder("<Team[");
        for (Player p : this.players) {
            sb.append(p.getPrintName());
            sb.append(", ");
        }
        sb.append("]>");
        return sb.toString();
    }
}

class Player extends Participant implements Playable {
    private Map<String, Integer> pieces;

    public Player(String name, Map<String, Integer> pieces) {
        super(name);
        this.pieces = pieces;
    }

    @Override
    public String getPrintName() {
        return "<Player[" + this.getName() + "]>";
    }

    @Override
    public Move getPiece() throws Exception {
        String basePrompt = this.getPrintName() + ": input: [m] [n]";
        String piece = null;
        if (this.pieces.size() == 1) {
            piece = (String) this.pieces.keySet().toArray()[0];
            basePrompt += String.format(" (piece: %s)", piece);
        } else {
            basePrompt += " [piece]";
        }
        Scanner s = Prompt.input(basePrompt);
        int m = s.nextInt();
        int n = s.nextInt();
        if (this.pieces.size() != 1) {
            piece = s.nextLine().strip();
        }
        if (!this.pieces.containsKey(piece)) {
            throw new Exception(this.getPrintName() +
                    String.format(": invalid piece: %s, valid piece %s", piece, this.pieces.keySet()));
        }
        int piece_cnt = this.pieces.get(piece);
        if (piece_cnt <= 0) {
            throw new Exception(this.getPrintName() + ": no more piece " + piece + " available");
        }
        this.pieces.put(piece, piece_cnt - 1);
        Move mv = new Move(this.getId(), piece, new Point(m, n));

        return mv;
    }
}
