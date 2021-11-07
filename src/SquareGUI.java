import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * GUI for Individual Squares in Monopoly
 */
public class SquareGUI extends JPanel {
    private final String squareName;
    private final int squareNum;
    static int total = 0;

    /**
     * Get the Square info.
     * @param x int, x-position
     * @param y int, y-position
     * @param width int
     * @param height int
     * @param squareName String, corresponding name of square in game
     * @param rotationDegree inr,
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
     * Rotates the squares/tiles so that they can be turned on their sides.
     * @param degree, int
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
        labelName.setBounds(0, 0, this.getWidth(), this.getHeight());
        labelName.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
        labelName.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(labelName);
    }

    /**
     * Painting specific squares on game board.
     * @param g Graphics
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
