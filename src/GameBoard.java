import java.util.ArrayList;
import java.util.HashMap;

public class GameBoard {
    private HashMap<Integer, Property> propertyLayout;
    private ArrayList<Player> players;

    public GameBoard() {
        this.propertyLayout = new HashMap<>();
        this.players = new ArrayList<>();
    }
}
