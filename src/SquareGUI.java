import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class SquareGUI extends JPanel {
    private String squareName;
    private JLabel labelName;
    private int squareNum;

    public SquareGUI(int x, int y, int width, int height, String squareName, int rotationDegree) {
        this.setBorder(new LineBorder(Color.BLACK));
        this.setBounds(x,y, width, height);
        this.squareName = squareName;
        rotation(rotationDegree);
    }

    private void rotation(int degree) {
        if (degree == 0) {
            this.labelName = new JLabel(squareName);
            this.labelName.setFont(new Font("Lucide Grande", Font.PLAIN, 12));
            this.labelName.setHorizontalAlignment(SwingConstants.CENTER);
            this.labelName.setBounds(0, 20, this.getWidth(), 20);
            this.add(labelName);
        } else {
            // Rotate the square
            labelName = new JLabel(squareName) {
                protected void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D)g;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    AffineTransform aT = g2.getTransform();
                    Shape oldshape = g2.getClip();
                    double x = getWidth()/2.0;
                    double y = getHeight()/2.0;
                    aT.rotate(Math.toRadians(degree), x, y);
                    g2.setTransform(aT);
                    g2.setClip(oldshape);
                    super.paintComponent(g);
                }
            };
            if (degree == 90) {
                labelName.setBounds(20, 0, this.getWidth(), this.getHeight());
            }
            if (degree == -90) {
                labelName.setBounds(-10, 0, this.getWidth(), this.getHeight());
            }
            if (degree == 180) {
                labelName.setBounds(0, 0, this.getWidth(), this.getHeight());
            }
            if (degree == 135 || degree == -135 || degree == -45 || degree == 45) {
                labelName.setBounds(0, 0, this.getWidth(), this.getHeight());
            }
            labelName.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
            labelName.setHorizontalAlignment(SwingConstants.CENTER);

            this.add(labelName);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.squareNum == 1 || this.squareNum == 3) {
            g.drawRect(0, this.getHeight() - 20, this.getWidth(), 20);
            g.setColor(new Color(128,117,90));
            g.fillRect(0, this.getHeight() - 20, this.getWidth(), 20);
        }
        if (this.squareNum == 6 || this.squareNum == 8 || this.squareNum == 9) {
            g.drawRect(0, 0, 20, this.getHeight());
            g.setColor(Color.PINK);
            g.fillRect(0, 0, 20, this.getHeight());
        }
        if (this.squareNum == 11 || this.squareNum == 13 || this.squareNum == 14) {
            g.drawRect(0, 0, this.getWidth(), 20);
            g.setColor(Color.ORANGE);
            g.fillRect(0, 0, this.getWidth(), 20);
        }
        if (this.squareNum == 16 || this.squareNum == 17 || this.squareNum == 19) {
            g.drawRect(this.getWidth() - 20, 0, 20, this.getHeight());
            g.setColor(Color.GREEN);
            g.fillRect(this.getWidth() - 20, 0, 20, this.getHeight());
        }
    }
}
