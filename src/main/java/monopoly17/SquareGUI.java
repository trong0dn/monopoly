package monopoly17;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.geom.AffineTransform;

import static monopoly17.GameBoard.BOARD_SIZE;

/**
 * This class every square GUI.
 * @author Trong Nguyen
 */
public class SquareGUI extends JPanel {
    private final String squareName;
    private int squareNum;
    private static int total = 0;

    /**
     * Constructor for SquareGUI.
     * @param x                 int, x-axis of the Square
     * @param y                 int, y-axis of the Square
     * @param width             int, width of the Square
     * @param height            int, height of the Square
     * @param squareName        String, the name of the Square
     * @param rotationDegree    int, the rotation of the Square
     */
    public SquareGUI(int x, int y, int width, int height, String squareName, int rotationDegree) {
        this.squareNum = total;
        total++;
        this.squareName = squareName;
        this.setBorder(new LineBorder(Color.BLACK));
        this.setBounds(x, y, width, height);
        this.setLayout(null);
        rotation(rotationDegree);
    }

    /**
     * Rotate the labels on a square.
     * @param degree    int, the degree of rotation of the label
     */
    private void rotation(int degree) {
        JLabel labelName;
        if (degree == 0) {
            labelName = new JLabel(squareName);
        } else {
            // Rotate labels on square
            // Sourced https://www.daniweb.com/programming/software-development/threads/390060/rotate-jlabel-or-image-in-label
            labelName = new JLabel(squareName) {
                protected void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    AffineTransform aT = g2.getTransform();
                    Shape oldShape = g2.getClip();
                    double x = getWidth()/2.0;
                    double y = getHeight()/2.0;
                    aT.rotate(Math.toRadians(degree), x, y);
                    g2.setTransform(aT);
                    g2.setClip(oldShape);
                    super.paintComponent(g);
                }
            };
        }
        GameBoard gameBoard = new GameBoard();
        squareNum %= BOARD_SIZE;
        // See the full name of the property, it's cost, and it's rent
        if (gameBoard.square(squareNum) instanceof Property || gameBoard.square(squareNum) instanceof Railroad){
            labelName.setToolTipText("<html>" + squareName + "<br>" + "Cost: $"
                    + gameBoard.square(squareNum).cost() + "<br>" + "Rent: $"
                    + gameBoard.square(squareNum).rent(0));
        } else {
            labelName.setToolTipText(squareName);
        }

        labelName.setBounds(0, 0, this.getWidth(), this.getHeight());
        labelName.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
        labelName.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(labelName);
    }

    /**
     * Method that gives each set of properties their own color.
     * @param g     Graphics
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.squareNum == 1 || this.squareNum == 3) {
            g.drawRect(0, 0, this.getWidth(), 20);
            g.setColor(new Color(150,75,0));
            g.fillRect(0, 0, this.getWidth(), 20);
        }
        if (this.squareNum == 6 || this.squareNum == 8 || this.squareNum == 9) {
            g.drawRect(0, 0, this.getWidth(), 20);
            g.setColor(new Color(114, 188, 212));
            g.fillRect(0, 0, this.getWidth(), 20);
        }
        if (this.squareNum == 11 || this.squareNum == 13 || this.squareNum == 14) {
            g.drawRect(this.getWidth()-20, 0, 20, this.getHeight());
            g.setColor(new Color(199,21,133));
            g.fillRect(this.getWidth()-20, 0, 20, this.getHeight());
        }
        if (this.squareNum == 16 || this.squareNum == 18 || this.squareNum == 19) {
            g.drawRect(this.getWidth()-20, 0, 20, this.getHeight());
            g.setColor(new Color(255,140,0));
            g.fillRect(this.getWidth()-20, 0, 20, this.getHeight());
        }
        if (this.squareNum == 21 || this.squareNum == 23 || this.squareNum == 24) {
            g.drawRect(0, this.getHeight()-20, this.getWidth(), 20);
            g.setColor(new Color(255,0,0));
            g.fillRect(0, this.getHeight()-20, this.getWidth(), 20);
        }
        if (this.squareNum == 26 || this.squareNum == 27 || this.squareNum == 29) {
            g.drawRect(0, this.getHeight()-20, this.getWidth(), 20);
            g.setColor(new Color(255,255,0));
            g.fillRect(0, this.getHeight()-20, this.getWidth(), 20);
        }
        if (this.squareNum == 31 || this.squareNum == 32 || this.squareNum == 34) {
            g.drawRect(0, 0, 20, this.getHeight());
            g.setColor(new Color(34,139,34));
            g.fillRect(0, 0, 20, this.getHeight());
        }
        if (this.squareNum == 37 || this.squareNum == 39) {
            g.drawRect(0, 0, 20, this.getHeight());
            g.setColor(new Color(0,0,255));
            g.fillRect(0, 0, 20, this.getHeight());
        }
    }
}
