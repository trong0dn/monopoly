package monopoly17;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;

import static monopoly17.GameBoard.BOARD_SIZE;

/**
 * This class represents the CPU player of this game.
 * @author Ibrahim Almalki & Trong Nguyen
 */
public class CPUPlayer implements Player, Serializable {
    private final Collection<Square> properties;
    private final String playerName;
    private int money;
    private int position;
    private int jailTurns;
    private boolean inJail;

    /**
     * Constructor for CPU Player.
     * @param name    String
     */
    public CPUPlayer(String name) {
        this.properties = new LinkedList<>();
        this.playerName = "CPU " + name;
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
        if (position >= BOARD_SIZE && !inJail) {
            position -= BOARD_SIZE;
            exchangeMoney(PASS_GO_COLLECT);
        }
    }

    /**
     * Passing the "GO" square giving the player $200
     * @param newPosition   int
     */
    @Override
    public void moveTo(int newPosition) {
        if (newPosition < position) {
            exchangeMoney(PASS_GO_COLLECT);
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

    /**
     * Outputs a boolean value for the decisions states available
     * @param state Monopoly.GameState
     * @return boolean
     */
    @Override
    public boolean inputBool(GameState state) {
        return switch (state.decisionState) {
            case NONE -> handleNone();
            case BUY_PROPERTY -> handleBuyProperty();
            case BUY_HOUSE -> handleBuyHouse();
            case TURN_ACTION -> handleTurnAction();
            case TAX -> handleIncomeTax();
            case IN_JAIL -> false;
        };
    }

    /**
     * Input value for integer entries.
     * @param state Monopoly.GameState
     * @return int
     */
    @Override
    public int inputInt(GameState state) {
        throw new IllegalArgumentException("No implementation use case for CPU to input integer");
    }

    /**
     * CPU Player chooses true for choices.
     * @param state     Monopoly.GameState
     * @param choices   String[]
     * @return          int
     */
    @Override
    public int inputDecision(GameState state, String[] choices) {
        // CPU Player always selects true
        return 0;
    }

    @Override
    public void addJailTurn() {
        jailTurns++;
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
     * @return     int
     */
    @Override
    public int getJailTurns() {
        return this.jailTurns;
    }

    /**
     * Get the property based on the name.
     * @param propName  String
     * @return          Property
     */
    @Override
    public Property getProperty(String propName) {
        for(Square prop: properties){
            if(propName.equals(prop.name())) {
                return (Property) prop;
            }
        }
        return null;
    }

    /**
     * CPU PLayer handling next turn.
     * @return  boolean
     */
    public boolean handleNone() {
        // Always choose an action over passing their turn.
        return false;
    }

    /**
     * CPU Player handling buying a property.
     * @return  boolean
     */
    public boolean handleBuyProperty() {
        // Always purchases the property when possible.
        return true;
    }

    /**
     * CPU Player handling buying a house.
     * @return  boolean
     */
    public boolean handleBuyHouse() {
        // Always buy house when possible.
        return true;
    }

    /**
     * CPU Player handling rolling the dice.
     * @return  boolean
     */
    public boolean handleTurnAction() {
        // Always roll dice to move.
        return true;
    }

    /**
     * CPU Player handling income tax.
     * @return  boolean
     */
    public boolean handleIncomeTax() {
        // Always pay taxes
        return true;
    }
}
