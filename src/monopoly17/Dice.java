package monopoly17;

/**
 * Interface for dice.
 * @author Trong Nguyen
 */
public interface Dice {

    /**
     * Get the number of dice.
     * @return  int
     */
    int getNumDice();

    /**
     * Get the number of sides per dice.
     * @return  int
     */
    int getNumSides();

    /**
     * Method invoked when dice are rolled.
     * @return  Roll
     */
    Roll rollDice();

    /**
     * Represents a dice roll.
     */
    class Roll {
        public int dieValue1;
        public int dieValue2;
        public int value;
        public boolean isDouble;
    }
}
