import java.util.*;

/**
 * This class representation of a human player of this game.
 */
public class HumanPlayer implements Player {
    private final Collection<Square> properties;
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
            addMoney(200);
        }
    }

    @Override
    public void moveTo(int newPosition) {
        if (newPosition < position) {
            addMoney(200);
        }
        position = newPosition;
    }

    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public Collection<Square> properties() {
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
    public void addMoney(int money) {
        this.money += money;
    }

    @Override
    public void addProperty(Square square) {
        Property property = (Property) square;
        if (!square.isOwnable()) {
            throw new IllegalArgumentException("This property can not be purchased");
        } else {
            properties.add(square);
            square.purchase(this);
            money =- property.cost();
        }
    }

    @Override
    public void removeProperty(Square square) {
        properties.remove(square);
        money += square.cost();
    }

    /**
     * @param square
     *  pays rent
     */
    public void payRent(Square square){
        Property property = (Property) square;
        if (money - property.rent(0) < 0){
            System.out.println("cannot pay rent on this property, current balance: $" + money + ". Property rent: $" + property.rent(0));
        }
        else{
            money -= property.rent(0);
        }
    }


}