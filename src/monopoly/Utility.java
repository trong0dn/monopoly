package monopoly;

/**
 * This class represents all the utility square tiles on the game board.
 * @author Trong Nguyen
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

    /**
     * Initialize monopoly.Utility.
     * @param position int
     * @param name     String
     */
    public Utility(int position, String name) {
        this.position = position;
        this.name = name;
        this.dice = new RollDice();
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
        numOwned = 1;
        for (Square sq : player.properties()) {
            if (sq instanceof Utility) {
                numOwned++;
            }
        }
    }


    /**
     * Rent value of the square tile.
     * @param roll     int
     * @return  int
     */
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

    /**
     * Return owner of the square tile.
     * @return  monopoly.Player
     */
    @Override
    public Player owner() {
        return owner;
    }

    /**
     * Set the other utility.
     * @param other     monopoly.Utility
     */
    public void setGroup(Utility other) {
        this.other = other;
    }
}
