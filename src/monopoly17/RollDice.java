package monopoly17;

import java.io.Serializable;
import java.util.Random;

/**
 * Class represents rolling dice.
 * @author Trong Nguyen
 */
public class RollDice implements Dice, Serializable {
    private final int NUM_DICE;     // number of dice
    private final int NUM_SIDES;    // number of sides per die

    /**
     * Initialize RollDice.
     */
    public RollDice() {
        this.NUM_DICE = 2;
        this.NUM_SIDES = 6;
    }

    /**
     * Get the number of dice.
     * @return  int
     */
    @Override
    public int getNumDice() {
        return this.NUM_DICE;
    }

    /**
     * Get the number of sides per dice.
     * @return  int
     */
    @Override
    public int getNumSides() {
        return this.NUM_SIDES;
    }

    /**
     * Method invoked when dice are rolled.
     * @return  Roll
     */
    @Override
    public Roll rollDice() {
        Roll roll = new Roll();
        int die1 = new Random().nextInt(this.NUM_SIDES) + 1;
        int die2 = new Random().nextInt(this.NUM_SIDES) + 1;
        roll.dieValue1 = die1;
        roll.dieValue2 = die2;
        roll.value = die1 + die2;
        roll.isDouble = die1 == die2;
        return roll;
    }
}
