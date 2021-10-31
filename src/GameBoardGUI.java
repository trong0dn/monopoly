import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class GameBoardGUI extends JPanel {
    private ArrayList<SquareGUI> squareGUIs = new ArrayList<>();
    private GameBoard gameBoard;
    private int WIDTH = 80;
    private int HEIGHT = 80;

    public GameBoardGUI(int x, int y, int width, int height) {
        this.setBorder(new LineBorder(Color.BLACK));
        this.setBounds(x, y, width, height);
        this.gameBoard = new GameBoard();
        this.setLayout(null);
        initializeSquarePanels(width, height);
    }

    private void initializeSquarePanels(int width, int height) {
        ArrayList<String> squareNames = new ArrayList<>();
        for (Square sq : gameBoard.getBoard()) {
            squareNames.add(sq.name());
        }

        SquareGUI square;
        int xOffset = 5;
        int yOffset = 5;
        int maxX = 550;
        int maxY = 550;
        int x = xOffset + maxX;
        int y = yOffset + maxY;

        // Create right-bottom corner square: "GO"
        square = new SquareGUI(x, y,100,100, squareNames.get(0),-45);
        this.add(square);

        // Create bottom squares
        for (int i = 0; i < 9; i++) {
            x -= 50;
            square = new SquareGUI(x, y,50,100, squareNames.get(i+1),0);
            this.add(square);
        }

        // Create left-bottom corner square: "JAIL"
        square = new SquareGUI(xOffset, y,100,100, squareNames.get(10),45);
        this.add(square);

        // Create left squares
        for (int i = 0; i < 9; i++) {
            y -= 50;
            square = new SquareGUI(xOffset, y,100,50, squareNames.get(i+11),90);
            this.add(square);
        }

        // Create top-left corner square: "FREE PARKING"
        square = new SquareGUI(xOffset, yOffset,100,100, squareNames.get(20),135);
        this.add(square);

        // Create top squares
        for (int i = 0; i < 9; i++) {
            square = new SquareGUI(x, yOffset,50,100, squareNames.get(i+21),180);
            x += 50;
            this.add(square);
        }

        // Create top-right corner square: "GO TO JAIL"
        square = new SquareGUI(555,5,100,100, squareNames.get(30),-135);
        this.add(square);

        // Create right squares
        for (int i = 0; i < 9; i++) {
            square = new SquareGUI(x, y,100,50, squareNames.get(i+31),-90);
            y += 50;
            this.add(square);
        }

        // Create center of the game board
        JLabel centerLabel = new JLabel("MONOPOLY"){
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D)g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                AffineTransform aT = g2.getTransform();
                Shape oldShape = g2.getClip();
                double x = getWidth()/2.0;
                double y = getHeight()/2.0;
                aT.rotate(Math.toRadians(-45), x, y);
                g2.setTransform(aT);
                g2.setClip(oldShape);
                super.paintComponent(g);
            }
        };
        centerLabel.setForeground(Color.WHITE);
        centerLabel.setBackground(Color.RED);
        centerLabel.setOpaque(true);
        centerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        centerLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
        centerLabel.setBounds(200, 280, 263, 55);
        this.add(centerLabel);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
