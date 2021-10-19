import java.util.*;

public class HumanPlayer implements Player {
    private final int BOARD_SIZE = 40;
    private final Queue<Square> properties;
    private final String playerName;
    private int money;
    private int position;


    public HumanPlayer(String playerName) {
        this.properties = new LinkedList<Square>();
        this.playerName = playerName;
        this.money = 1500;
        this.position = 0;
    }

    @Override
    public void move(int numTiles) {
        this.position += numTiles;
        if (position >= BOARD_SIZE) {

        }
    }

    @Override
    public void moveTo(int position) {

    }

    @Override
    public int getPosition() {
        return 0;
    }

    @Override
    public Iterable<Square> properties() {
        return null;
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public int getMoney() {
        return 0;
    }

    @Override
    public void collectMoney(int money) {

    }

    @Override
    public void buyProperty(Square square) {

    }

    @Override
    public void sellProperty(Square square) {

    }
}