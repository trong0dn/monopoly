import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class GameBoardGUI extends JPanel {
    private ArrayList<SquareGUI> squareGUIs = new ArrayList<>();
    private GameBoard gameBoard;
    private int WIDTH = 80;
    private int HEIGHT = 80;

    public GameBoardGUI(int x, int y, int width, int height) {
        this.setBorder(new LineBorder(Color.BLACK));
        this.setBounds(x, y, width, height);
        initializeSquarePanels(width, height);
    }

    private void initializeSquarePanels(int width, int height) {
        int count = 0;
        for (Square sq : gameBoard.getBoard()) {
            count++;
            // Squares on the bottom
            if (count <= 10) {
                int x = 10-count;
                int y = 10;
                SquareGUI squareGUI = new SquareGUI(x, y, WIDTH, HEIGHT, sq.name(), 0);
                squareGUIs.add(squareGUI);
            }
            // Squares on the left
            else if (count <= 20) {
                int x = 0;
                int y = 20-count;
                SquareGUI squareGUI = new SquareGUI(x, y, WIDTH, HEIGHT, sq.name(), 90);
            }
            // Squares on the top
            else if (count <= 30) {
                int x = count-20;
                int y = 0;
                SquareGUI squareGUI = new SquareGUI(x, y, WIDTH, HEIGHT, sq.name(), 180);
            }
            // Squares on the right
            else if (count < 40){
                int x = 10;
                int y = count- 30;
                SquareGUI squareGUI = new SquareGUI(x, y, WIDTH, HEIGHT, sq.name(), -90);
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
