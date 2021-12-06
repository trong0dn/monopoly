package monopoly17;

import java.io.Serializable;

/**
 * This class represents the tax square tiles on the game board.
 * @author Trong Nguyen & Elisha Catherasoo
 */
public class Taxes implements Square, Serializable {
    public static final int INCOME_TAX_POSITION = 4;
    public static final int SUPER_TAX_POSITION = 38;
    public static final int FIX_INCOME_TAX = 200;
    public static final int FIX_SUPER_TAX = 100;

    private final int position;
    private final String name;
    private final int fixTax;
    private final double varTax;

    /**
     * Initialize Taxes.
     * @param position int
     * @param name String
     */
    public Taxes(int position, String name) {
        this.position = position;
        this.name = name;
        if (name.equals(JsonParse.parseJSON(INCOME_TAX_POSITION, String.valueOf(Versions.UK)))) {
            fixTax = FIX_INCOME_TAX;        // Pay 200
            varTax = 0.1;                   // Pay 10% of total money
        } else {
            fixTax = FIX_SUPER_TAX;         // Pay 100
            varTax = 0;
        }
    }

    /**
     * Get the fix tax value.
     * @return  int
     */
    public int getTax() {
        return fixTax;
    }

    /**
     * Get the variable tax value.
     * @param value int
     * @return  int
     */
    public int getTax(int value) {
        if (varTax == 0) {
            return fixTax;
        } else {
            return (int) (value * varTax);
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
     * @param player    Player
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
     * @return  Player
     */
    @Override
    public Player owner() {
        return null;
    }
}
