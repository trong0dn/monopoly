/**
 * This class represents all the utility square tiles on the game board.
 */
public class Utility implements Square {
    private final int COST = 150;
    private final int position;
    private final String name;
    private Utility other;
    private int numOwned;
    private Player owner;
    private boolean owned;
    private final RollDice dice;

    public Utility(int position, String name) {
        this.position = position;
        this.name = name;
        this.dice = new RollDice();
    }

    /**
     * Set the other utility.
     * @param other     Utility
     */
    public void setOther(Utility other) {
        this.other = other;
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
        numOwned = 1;
        for (Square sq : player.properties()) {
            if (sq instanceof Utility) {
                numOwned++;
            }
        }
    }

    @Override
    public int rent(int roll) {
        if (roll == 0) {
            roll = dice.rollDice().value;
        }
        int ONE = 4;
        int TWO = 10;

        if (owner.equals(other.owner())) {
            return TWO * roll;
        } else {
            return ONE * roll;
        }
    }

    @Override
    public Player owner() {
        return owner;
    }
}
