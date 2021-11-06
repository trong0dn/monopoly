package monopoly;

/**
 * This class represents the tax square tiles on the game board.
 * @author Trong Nguyen
 */
public class Taxes implements Square {
    private final int position;
    private final String name;
    private final int tax;

    /**
     * Initialize monopoly.Taxes.
     * @param position int
     * @param name     String
     */
    public Taxes(int position, String name) {
        this.position = position;
        this.name = name;
        if (this.name.equals("Income Tax")) {
            this.tax = 200;
        } else {
            this.tax = 100;
        }
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
     * Get the tax.
     * @return  int
     */
    public int getTax() {
        return this.tax;
    }
}
