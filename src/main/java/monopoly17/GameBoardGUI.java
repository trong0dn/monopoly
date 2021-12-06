package monopoly17;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

/**
 * This class creates the GUI for the game board
 * @author Trong Nguyen
 */
public class GameBoardGUI extends JPanel {
    private GameBoard gameBoard;
    private Versions language;

    /**
     * Constructor for GameBoardGUI.
     * @param x         int, x-axis of the GameBoard
     * @param y         int, y-axis of the GameBoard
     * @param width     int, width of the GameBoard
     * @param height    int, height of the GameBoard
     */
    public GameBoardGUI(int x, int y, int width, int height) {
        this.setBorder(new LineBorder(Color.BLACK));
        this.setBounds(x, y, width, height);
        this.language = Versions.US;
        this.setLayout(null);
    }

    /**
     * Sets the international version.
     * @param language  Versions, the enum of the international version
     */
    public void setVersion(Versions language){
        this.gameBoard = new GameBoard(language);
        this.language = language;
        initializeSquarePanels();
    }

    /**
     * Get the Veriosn enum and change to string.
     * @return  Versions, the string value of the Versions enum
     */
    public Versions getVersion(){
        return language;
    }

    /**
     * Get the square based the squareNumber.
     * @param squareNumber  int, the square position on the game board
     * @return              Square
     */
    public Square getSquare(int squareNumber) {
        return this.gameBoard.square(squareNumber);
    }

    /**
     * Initialize the Square Panels (ie. properties, utilities, go, jail,etc.)
     */
    private void initializeSquarePanels() {
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
        JLabel centerLabel = new JLabel("MONOPOLY") {
            // Rotate labels on square
            // Sourced https://www.daniweb.com/programming/software-development/threads/390060/rotate-jlabel-or-image-in-label
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
        centerLabel.setBounds(200, 280, 265, 55);
        this.add(centerLabel);
    }
}
