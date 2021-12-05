package monopoly17;

import java.util.Collection;

/**
 * Interface for the players on a monopoly board.
 * @author Francisco De Grano & Trong Nguyen
 */
public interface Player {
    int PASS_GO_COLLECT = 200;
    /**
     * Movement of square tiles for a player.
     * @param numTiles  int
     */
    void move(int numTiles);

    /**
     * Movement to a different position on the board.
     * @param newPosition  int
     */
    void moveTo(int newPosition);

    /**
     * Get the position of the player.
     * @return  int
     */
    int getPosition();

    /**
     * Set if the player is in jail pr not.
     * @param bool      boolean
     */
    void setInJail(boolean bool);

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
    boolean inputBool(GameState state);

    /**
     * Player inputs an integer response.
     * @param state     Monopoly.GameState
     * @return          int
     */
    int inputInt(GameState state);

    /**
     * Player inputs a two choice decision.
     * @param state     Monopoly.GameState
     * @param choices   String[]
     * @return          int
     */
    int inputDecision(GameState state, String[] choices);

    /**
     * Add another turn that the player is in jail.
     */
    void addJailTurn();

    /**
     * Get how many turn the player has been in jail.
     * @param turns     int
     */
    void setJailTurns(int turns);

    /**
     * Get the number of turns the player has been in jail.
     * @return          int
     */
    int getJailTurns();

    /**
     * Get the property based on the name.
     * @param propName  String
     * @return          Property
     */
    Property getProperty(String propName);
}
