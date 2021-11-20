package monopoly17;

import java.util.*;

/**
 * This is a class representation of a human player of this game.
 * @author Francisco De Grano & Ibrahim Almalki
 */
public class HumanPlayer implements Player {
    private final Input input;
    private final Collection<Square> properties;
    private final String playerName;
    private int money;
    private int position;
    private int jailTurns;
    private boolean inJail;

    /**
     * Initialize HumanPlayer.
     * @param playerName String
     */
    public HumanPlayer(String playerName) {
        this.input = new Input();
        this.properties = new LinkedList<>();
        this.playerName = playerName;
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
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * Method for player to collect addition money.
     * @param money int
     */
    @Override
    public void exchangeMoney(int money) {
        this.money += money;
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
     * Input value for boolean entries.
     * @param state Monopoly.GameState
     * @return boolean
     */
    @Override
    public boolean inputBool(Monopoly.DecisionState state) {
        return this.input.inputBool();
    }

    /**
     * Input value for integer entries.
     * @param state Monopoly.GameState
     * @return int
     */
    @Override
    public int inputInt(Monopoly.DecisionState state) {
        return this.input.inputInt();
    }

    /**
     * Input value for two choices.
     * @param state   Monopoly.GameState
     * @param choices String[]
     * @return int
     */
    @Override
    public int inputDecision(Monopoly.DecisionState state, String[] choices) {
        return this.input.inputDecision(choices);
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
}