package monopoly17;

import java.util.Collection;
import java.util.LinkedList;

public class CPUPlayer implements Player{

    private final Input input;
    private final Collection<Square> properties;
    private final String CPUName;
    private int money;
    private int position;

    public CPUPlayer(String CPUName){
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
        return 0;
    }

    /**
     * Properties owned by the player.
     *
     * @return Collection<Square>
     */
    @Override
    public Collection<Square> properties() {
        return null;
    }

    /**
     * Get the name of the player.
     *
     * @return String
     */
    @Override
    public String name() {
        return null;
    }

    /**
     * Get the amount of money owned by a player.
     *
     * @return int
     */
    @Override
    public int getMoney() {
        return 0;
    }

    /**
     * Method for player to collect addition money.
     *
     * @param money int
     */
    @Override
    public void exchangeMoney(int money) {

    }

    /**
     * Method when a player buys a property.
     *
     * @param square Square
     */
    @Override
    public void addProperty(Square square) {

    }

    /**
     * Method when a player sells a property.
     *
     * @param square Square
     */
    @Override
    public void removeProperty(Square square) {

    }

    /**
     * Player inputs a boolean response.
     *
     * @param state Monopoly.GameState
     * @return boolean
     */
    @Override
    public boolean inputBool(Monopoly.GameState state) {
        return false;
    }

    /**
     * Player inputs an integer response.
     *
     * @param state Monopoly.GameState
     * @return int
     */
    @Override
    public int inputInt(Monopoly.GameState state) {
        return 0;
    }

    /**
     * Player inputs a two choice decision.
     *
     * @param state   Monopoly.GameState
     * @param choices String[]
     * @return int
     */
    @Override
    public int inputDecision(Monopoly.GameState state, String[] choices) {
        return 0;
    }
}
