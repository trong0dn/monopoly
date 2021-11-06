package monopoly;

/**
 * This class represents the jail square tile on the board game.
 * @author Trong Nguyen
 */
public class Jail implements Square {
    private final int position;
    private final String name;
    private final JailType type;

    /**
     * Initialize monopoly.Jail,
     * @param position int
     * @param name     String
     * @param type     JailType
     */
    public Jail(int position, String name, JailType type) {
        this.position = position;
        this.name = name;
        this.type = type;
    }

    /**
     * Get the position of the square tile.
     * @return  int
     */
    @Override
    public int position() {
        return this.position;
    }

    /**
     * Get the name of the square tile.
     * @return  String
     */
    @Override
    public String name() {
        return this.name;
    }

    /**
     * If the square tile can be owned or not.
     * @return  boolean
     */
    @Override
    public boolean isOwnable() {
        return false;
    }

    /**
     * If the square tile is owned.
     * @return  boolean
     */
    @Override
    public boolean isOwned() {
        return false;
    }


    /**
     * Get the cost of the square tile.
     * @return  int
     */
    @Override
    public int cost() {
        return 0;
    }


    /**
     * Method invoked when a square tile is being purchased.
     * @param player    monopoly.Player
     */
    @Override
    public void purchase(Player player) {
    }

    /**
     * Rent value of the square tile.
     * @param value     int
     * @return  int
     */
    @Override
    public int rent(int value) {
        return 0;
    }

    /**
     * Return owner of the square tile.
     * @return  monopoly.Player
     */
    @Override
    public Player owner() {
        return null;
    }

    /**
     * The various monopoly.Jail type states.
     */
    public enum JailType {
        JUST_VISITING, IN_JAIL, GOTO_JAIL
    }
}
