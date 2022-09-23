package assignment1;

import java.util.HashMap;

public class OrderNChaos extends Boardgame{

    public OrderNChaos() {
        super(new Player[]{
                new Player(0, new HashMap<>(){{
                    put("X", Integer.MAX_VALUE);
                }}),
                new Player(1, new HashMap<>(){{
                    put("O", Integer.MAX_VALUE);
                }})
        }, 6, 6, false);
    }

    @Override
    public int evalState() {
        return 1;
    }
}
