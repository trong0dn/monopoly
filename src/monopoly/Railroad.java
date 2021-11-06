package monopoly;

/**
 * This class represents all the railroad square tiles on the game board.
 * @author Trong Nguyen
 */
public class Railroad implements Square {
    private final int COST = 200;
    private final int position;
    private final String name;
    private final Railroad[] others;
    private int numOwned;
    private Player owner;
    private boolean owned;

    /**
     * Initialize monopoly.Railroad.
     * @param position int
     * @param name     String
     */
    public Railroad(int position, String name) {
        this.position = position;
        this.name = name;
        this.others = new Railroad[3];
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
        return true;
    }


    /**
     * If the square tile is owned.
     * @return  boolean
     */
    @Override
    public boolean isOwned() {
        return owned;
    }


    /**
     * Get the cost of the square tile.
     * @return  int
     */
    @Override
    public int cost() {
        return this.COST;
    }

    /**
     * Method invoked when a square tile is being purchased.
     * @param player    monopoly.Player
     */
    @Override
    public void purchase(Player player) {
        owned = true;
        owner = player;
        updateOwners();
    }

    /**
     * Rent value of the square tile.
     * @param value     int
     * @return  int
     */
    @Override
    public int rent(int value) {
        updateOwners();
        return switch (numOwned) {
            case 1 -> 25;
            case 2 -> 50;
            case 3 -> 100;
            case 4 -> 200;
            default -> 0;
        };
    }

    /**
     * Return owner of the square tile.
     * @return  monopoly.Player
     */
    @Override
    public Player owner() {
        return owner;
    }

    /**
     * Create railroad groupings.
     * @param a   - monopoly.Railroad
     * @param b   - monopoly.Railroad
     * @param c   - monopoly.Railroad
     */
    public void setGroup(Railroad a, Railroad b, Railroad c) {
        this.others[0] = a;
        this.others[1] = b;
        this.others[2] = c;
    }

    /**
     * Updates the number of owned railroads.
     */
    private void updateOwners() {
        numOwned = 1;
        for (Railroad r : others) {
            if (r.isOwned() && r.owner().equals(owner)) {
                numOwned++;
            }
        }
    }
}
