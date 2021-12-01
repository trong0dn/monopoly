package monopoly17;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * This class represents the GUI for the dice.
 * @author Trong Nguyen
 */
public class DiceGUI extends JPanel {
    private final RollDice rollDice = new RollDice();
    private int faceValue;

    /**
     * Constructor for DiceGUI.
     * @param x         int, x-axis of the Dice
     * @param y         int, y-axis of the Dice
     * @param width     int, width of the Dice
     * @param height    int, height of the Dice
     */
    public DiceGUI(int x, int y, int width, int height) {
        this.setBorder(new LineBorder(new Color(0, 0, 0)));
        this.setBounds(x, y, width, height);
        this.faceValue = rollDice.rollDice().dieValue1;
    }

    /**
     * Adds the number of dots on the dice depending on the roll.
     * @param g     Graphics
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = 5;
        int height = 5;
        if (faceValue == 1) {
            g.fillOval(getWidth()/2 - 5/2, getHeight()/2 - 5/2, width, height);
        }
        else if (faceValue == 2) {
            g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, width, height);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15, width, height);
        }
        else if (faceValue == 3) {
            g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, width, height);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15, width, height);
            g.fillOval(getWidth()/2 - 5/2, getHeight()/2 - 5/2, width, height);
        }
        else if (faceValue == 4) {
            g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, width, height);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15, width, height);
            g.fillOval(getWidth()/2 - 15, getHeight()/2 - 15, width, height);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 + 10, width, height);
        }
        else if (faceValue == 5) {
            g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, width, height);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15, width, height);
            g.fillOval(getWidth()/2 - 15, getHeight()/2 - 15, width, height);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 + 10, width, height);
            g.fillOval(getWidth()/2 - 5/2, getHeight()/2 - 5/2, width, height);
        }
        else {
            g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, width, height);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15, width, height);
            g.fillOval(getWidth()/2 - 15, getHeight()/2 - 15, width, height);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 + 10, width, height);
            g.fillOval(getWidth()/2 - 15, getHeight()/2 - 5/2, width, height);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 5/2, width, height);
        }
    }

    /**
     * Roll the dice.
     */
    public void rollDice() {
        this.faceValue = rollDice.rollDice().dieValue1;
        repaint();
    }

    /**
     * Get the value to be shown on the dice.
     * @return      int
     */
    public int getFaceValue() {
        return this.faceValue;
    }
}
