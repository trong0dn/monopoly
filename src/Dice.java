/**
 * Interface for dice.
 * @author Trong Nguyen
 */
public interface Dice {

    /**
     * Get number of dice.
     */
    int getNumDice();

    /**
     * Get number of sides per die.
     */
    int getNumSides();

    /**
     * Roll dice method.
     */
    Roll rollDice();

    /**
     * Class represents rolling two dice.
     */
    class Roll {
        public int value;
        public boolean isDouble;
    }
}
