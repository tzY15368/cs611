package assignment1;
import java.util.HashMap;
public class TikTakToe extends Boardgame{

    public TikTakToe() {
        super(new Player[]{
            new Player(0, new HashMap<>(){{
                put("X", Integer.MAX_VALUE);
                put("O", Integer.MAX_VALUE);
            }}),
            new Player(1, new HashMap<>())
        }, 3, 3, true);
        
    }
    
}
