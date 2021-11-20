package monopoly17;

import java.util.Collection;
import java.util.LinkedList;

public class AIPlayer implements Player{

    private final Input input;
    private final Collection<Square> properties;
    private final String CPUName;
    private int money;
    private int position;

    public AIPlayer(String CPUName){
        this.input = new Input();
        this.properties = new LinkedList<>();
        this.CPUName = CPUName;
        this.money = 1500;
        this.position = 0;
    }

    /**
     * Movement of square tiles for a player.
     *
     * @param numTiles int
     */
    @Override
    public void move(int numTiles) {
        this.position += numTiles;
        int BOARD_SIZE = 40;
        if (position >= BOARD_SIZE) {
            position -= BOARD_SIZE;
            exchangeMoney(200);

        }
    }

    /**
     * Movement to a different position on the board.
     *
     * @param newPosition int
     */
    @Override
    public void moveTo(int newPosition) {
        if (newPosition < position) {
            exchangeMoney(200);
        }
        position = newPosition;
    }

    /**
     * Get the position of the player.
     *
     * @return int
     */
    @Override
    public int getPosition() {
        return this.position;

    }

    /**
     * Set if the player is in jail pr not.
     *
     * @param bool boolean
     */
    @Override
    public void setInJail(boolean bool) {
        return;
    }

    /**
     * Properties owned by the player.
     *
     * @return Collection<Square>
     */
    @Override
    public Collection<Square> properties() {
        return new LinkedList<>(properties);

    }

    /**
     * Get the name of the CPU player.
     *
     * @return String
     */
    @Override
    public String name() {
        return this.CPUName;

    }

    /**
     * Get the amount of money owned by a player.
     *
     * @return int
     */
    @Override
    public int getMoney() {
        return this.money;

    }

    /**
     * Method for player to collect addition money.
     *
     * @param money int
     */
    @Override
    public void exchangeMoney(int money) {
        this.money += money;

    }

    /**
     * Method when a player buys a property.
     *
     * @param square Square
     */
    @Override
    public void addProperty(Square square) {
        if (!square.isOwnable()) {
            throw new IllegalArgumentException("This property can not be purchased");
        }
        else {
            properties.add(square);
            square.purchase(this);
        }
    }

    /**
     * Method when a player sells a property.
     *
     * @param square Square
     */
    @Override
    public void removeProperty(Square square) {
        properties.remove(square);
        money += square.cost();
    }

    /**
     * Player inputs a boolean response.
     *
     * @param state Monopoly
     * @return boolean
     */
    @Override
    public boolean inputBool(Monopoly.DecisionState state) {
        return false;
    }

    /**
     * Player inputs an integer response.
     *
     * @param state Monopoly
     * @return int
     */
    @Override
    public int inputInt(Monopoly.DecisionState state) {
        return 0;
    }

    /**
     * Player inputs a two choice decision.
     *
     * @param state   Monopoly
     * @param choices String[]
     * @return int
     */
    @Override
    public int inputDecision(Monopoly.DecisionState state, String[] choices) {
        return 0;
    }

    /**
     * Get how many turn the player has been in jail.
     *
     * @param turns int
     */
    @Override
    public void setJailTurns(int turns) {

    }

    /**
     * Get the number of turns the player has been in jail.
     *
     * @return int
     */
    @Override
    public int getJailTurns() {
        return 0;
    }

    /**
     * Player inputs a boolean response.
     *
     * @param state Monopoly.GameState
     * @return boolean
     */

    public boolean inputBool(Monopoly.GameState state) {
        return this.input.inputBool();
    }

    /**
     * Player inputs an integer response.
     *
     * @param state Monopoly.GameState
     * @return int
     */

    public int inputInt(Monopoly.GameState state) {
        return this.input.inputInt();
    }

    /**
     * Player inputs a two choice decision.
     *
     * @param state   Monopoly.GameState
     * @param choices String[]
     * @return int
     */

    public int inputDecision(Monopoly.GameState state, String[] choices) {
        return this.input.inputDecision(choices);

    }
}
