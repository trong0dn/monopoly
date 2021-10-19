import java.util.*;

public class HumanPlayer implements Player {
    private final Queue<Square> properties;
    private final String playerName;
    private int money;
    private int position;


    public HumanPlayer(String playerName) {
        this.properties = new LinkedList<>();
        this.playerName = playerName;
        this.money = 1500;
        this.position = 0;
    }

    @Override
    public void move(int numTiles) {
        this.position += numTiles;
        int BOARD_SIZE = 40;
        if (position >= BOARD_SIZE) {
            position -= BOARD_SIZE;
            collectMoney(200);
        }
    }

    @Override
    public void moveTo(int newPosition) {
        if (newPosition < position) {
            collectMoney(200);
        }
        position = newPosition;
    }

    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public Queue<Square> properties() {
        return new LinkedList<>(properties);
    }

    @Override
    public String name() {
        return this.playerName;
    }

    @Override
    public int getMoney() {
        return this.money;
    }

    @Override
    public void collectMoney(int money) {
        this.money += money;
    }

    @Override
    public void buyProperty(Square square) {
        if (!square.isOwnable()) {
            throw new IllegalArgumentException("This property can not be purchased");
        } else {
            properties.add(square);
            square.purchase(this);
        }
    }

    @Override
    public void sellProperty(Square square) {
        properties.remove(square);
        //gain money
    }
}