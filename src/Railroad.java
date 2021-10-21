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

    public int getRent() {
        return 0;
    }

    public int getPropertyCost() {
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
     * @param r1    Railroad
     * @param r2    Railroad
     * @param r3    Railroad
     */
    public void setOthers(Railroad r1, Railroad r2, Railroad r3) {
        others[0] = r1;
        others[1] = r2;
        others[2] = r3;
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
