/**
 * This class represents all the railroad square tiles on the game board.
 */
public class Railroad implements Square {
    private final int COST = 200;
    private final int position;
    private final String name;
    private final Railroad[] others;
    private int numOwned;
    private Player owner;
    private boolean owned;

    public Railroad(int position, String name) {
        this.position = position;
        this.name = name;
        this.others = new Railroad[3];
    }

    @Override
    public int position() {
        return this.position;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public boolean isOwnable() {
        return true;
    }

    @Override
    public boolean isOwned() {
        return owned;
    }

    @Override
    public int cost() {
        return this.COST;
    }

    @Override
    public void purchase(Player player) {
        owned = true;
        owner = player;
        updateOwners();
    }

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

    @Override
    public Player owner() {
        return owner;
    }

    /**
     * Create railroad groupings.
     * @param a    Railroad
     * @param b    Railroad
     * @param c    Railroad
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
