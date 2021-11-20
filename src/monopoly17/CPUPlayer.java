package monopoly17;

import java.util.Collection;
import java.util.LinkedList;

/**
 * This class represents the CPU player of this game.
 * @author Trong Nguyen
 */
public class CPUPlayer implements Player {
    private final Collection<Square> properties;
    private final String playerName;
    private int money;
    private int position;
    private int jailTurns;
    private boolean inJail;

    /**
     * Constructor for CPU Player.
     * @param number    int
     */
    public CPUPlayer(int number) {
        this.properties = new LinkedList<>();
        this.playerName = "CPU Player " + number;
        this.money = 1500;
        this.position = 0;
        this.jailTurns = 0;
        this.inJail = false;
    }

    /**
     * Movement of the player.
     * @param numTiles int
     */
    @Override
    public void move(int numTiles) {
        this.position += numTiles;
        int BOARD_SIZE = 40;
        if (position >= BOARD_SIZE && !inJail) {
            position -= BOARD_SIZE;
            exchangeMoney(200);
        }
    }

    /**
     * Passing the "GO" square giving the player $200
     * @param newPosition   int
     */
    @Override
    public void moveTo(int newPosition) {
        if (newPosition < position) {
            exchangeMoney(200);
        }
        position = newPosition;
    }

    /**
     * Get the position of the player
     * @return  int
     */
    @Override
    public int getPosition() {
        return this.position;
    }

    /**
     * Set if the player is in jail or not.
     * @param bool      boolean
     */
    @Override
    public void setInJail(boolean bool) {
        inJail = bool;
    }

    /**
     * Properties owned by the player.
     * @return  Collection<Square>
     */
    @Override
    public Collection<Square> properties() {
        return  this.properties;
    }

    /**
     * Get the name of the player.
     * @return  String
     */
    @Override
    public String name() {
        return this.playerName;
    }

    /**
     * Get the amount of money owned by a player.
     * @return  int
     */
    @Override
    public int getMoney() {
        return this.money;
    }

    /**
     * Set the amount of money a player has
     * @param money int
     */
    @Override
    public void exchangeMoney(int money) {
        this.money = money;
    }

    /**
     * Method when a player buys a property.
     * @param square    Square
     */
    @Override
    public void addProperty(Square square) {
        if (!square.isOwnable()) {
            throw new IllegalArgumentException("This property can not be purchased");
        } else {
            this.properties.add(square);
            square.purchase(this);
        }
    }

    /**
     * Method when a player to sells a property.
     * @param square Square
     */
    @Override
    public void removeProperty(Square square) {
        this.properties.remove(square);
        this.money += square.cost();
    }

    @Override
    public boolean inputBool(Monopoly.DecisionState state) {
        switch (state) {
            case BUY_PROPERTY:
                return handleBuyProperty();
            case SELL_PROPERTY:
                return handleSellProperty();
            case BUY_HOUSE:
                return handleBuyHouse();
            case SELL_HOUSE:
                return handleSellHouse();
            case TURN_ACTION:
                return handleTurnAction();
            default:
                throw new IllegalArgumentException("No implementation exists for the CPU player bool input");
        }
    }

    @Override
    public int inputInt(Monopoly.DecisionState state) {
        throw new IllegalArgumentException("No implementation use case for CPU to input integer");
    }

    @Override
    public int inputDecision(Monopoly.DecisionState state, String[] choices) {
        // CPU Player always selects true
        return 0;
    }

    /**
     * Get how many turn the player has been in jail.
     * @param turns     int
     */
    @Override
    public void setJailTurns(int turns) {
        this.jailTurns = turns;
    }

    /**
     * Get the number of turns the player has been in jail.
     * @return          int
     */
    @Override
    public int getJailTurns() {
        return this.jailTurns;
    }

    public boolean handleBuyProperty() {
        return false;
    }

    public boolean handleSellProperty() {
        return false;
    }

    public boolean handleBuyHouse() {
        return false;
    }

    public boolean handleSellHouse() {
        return false;
    }

    public boolean handleTurnAction() {
        return true;
    }
}
