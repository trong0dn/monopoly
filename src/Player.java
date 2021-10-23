import java.util.Collection;

/**
 * Interface for the players on a monopoly board.
 */
public interface Player {

    /**
     * Movement of square tiles a player.
     * @param numTiles  int
     */
    void move(int numTiles);

    /**
     * Movement position of a player.
     * @param position  int
     */
    void moveTo(int position);

    /**
     * Get the position of the player.
     * @return  int
     */
    int getPosition();

    /**
     * Properties owned by the player.
     * @return  Collection<Square>
     */
    Collection<Square> properties();

    /**
     * Get the name of the player.
     * @return  String
     */
    String name();

    /**
     * Get the amount of money owned by a player.
     * @return  int
     */
    int getMoney();

    /**
     * Method for player to collect addition money.
     * @param money int
     */
    void exchangeMoney(int money);

    /**
     * Method when a player buys a property.
     * @param square    Square
     */
    void addProperty(Square square);

    /**
     * Method when a player sells a property.
     * @param square    Square
     */
    void removeProperty(Square square);

    /**
     * Player inputs a boolean response.
     * @param state     Monopoly.GameState
     * @return          boolean
     */
    boolean inputBool(Monopoly.GameState state);

    /**
     * Player inputs an integer response.
     * @param state     Monopoly.GameState
     * @return          int
     */
    int inputInt(Monopoly.GameState state);

    /**
     * Player inputs a two choice decision.
     * @param state     Monopoly.GameState
     * @param choices   String[]
     * @return          int
     */
    int inputDecision(Monopoly.GameState state, String[] choices);
}
