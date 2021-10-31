import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class DiceGUI extends JPanel {
    RollDice rollDice = new RollDice();
    int faceValue;

    public DiceGUI(int x, int y, int width, int height) {
        this.setBorder(new LineBorder(new Color(0, 0, 0)));
        this.setBounds(x, y, width, height);
        this.faceValue = rollDice.rollDice().dieValue1;;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (faceValue == 1) {
            g.fillOval(getWidth()/2 - 5/2, getHeight()/2 - 5/2, 5, 5);
        }
        else if (faceValue == 2) {
            g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15, 5, 5);
        }
        else if (faceValue == 3) {
            g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15, 5, 5);
            g.fillOval(getWidth()/2 - 5/2, getHeight()/2 - 5/2, 5, 5);
        }
        else if (faceValue == 4) {
            g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15, 5, 5);
            g.fillOval(getWidth()/2 - 15, getHeight()/2 - 15, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 + 10, 5, 5);
        }
        else if (faceValue == 5) {
            g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15, 5, 5);
            g.fillOval(getWidth()/2 - 15, getHeight()/2 - 15, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 + 10, 5, 5);
            g.fillOval(getWidth()/2 - 5/2, getHeight()/2 - 5/2, 5, 5);
        }
        else {
            g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15, 5, 5);
            g.fillOval(getWidth()/2 - 15, getHeight()/2 - 15, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 + 10, 5, 5);
            g.fillOval(getWidth()/2 - 15, getHeight()/2 - 5/2, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 5/2, 5, 5);
        }
    }

    public void rollDice() {
        this.faceValue = rollDice.rollDice().dieValue1;
        repaint();
    }
}
