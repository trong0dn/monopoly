package monopoly17;

/**
 * Interface for the square tiles on a monopoly board.
 * @author Trong Nguyen, Francisco De Grano, Ibrahim Almalki, & Elisha Catherasoo
 */
public interface Square {

    /**
     * Get the position of the square tile.
     * @return  int
     */
    int position();

    /**
     * Get the name of the square tile.
     * @return  String
     */
    String name();

    /**
     * If the square tile can be owned or not.
     * @return  boolean
     */
    boolean isOwnable();

    /**
     * If the square tile is owned.
     * @return  boolean
     */
    boolean isOwned();

    /**
     * Get the cost of the square tile.
     * @return  int
     */
    int cost();

    /**
     * Method invoked when a square tile is being purchased.
     * @param player    Player
     */
    void purchase(Player player);

    /**
     * Rent value of the square tile.
     * @param value     int
     * @return  int
     */
    int rent(int value);

    /**
     * Return owner of the square tile.
     * @return  Player
     */
    Player owner();

    /**
     * toString method of a square tile.
     * @return  String
     */
    String toString();
}
