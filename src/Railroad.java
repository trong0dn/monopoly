public class Railroad implements Square {
    private final int COST = 200;
    private final String name;
    private final int position;
    private final Railroad[] others = new Railroad[3];
    private int numOwned;
    private HumanPlayer owner;
    private boolean owned;

    public Railroad(int position, String name) {
        this.position = position;
        this.name = name;
    }

    private void updateOwners() {
        numOwned = 1;
        for (Railroad r : others) {
            if (r.isOwned() && r.owner().equals(owner)) {
                numOwned++;
            }
        }
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
        return false;
    }

    @Override
    public int cost() {
        return this.COST;
    }

    @Override
    public void purchase(HumanPlayer player) {
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
}
