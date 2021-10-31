import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MonopolyGUI extends JFrame {
    private GameBoardGUI gameBoard;

    public MonopolyGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setSize(1080,860);
        JPanel boxPanel = new JPanel();
        boxPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(boxPanel);
        boxPanel.setLayout(null);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBorder(new LineBorder(new Color(0, 0, 0)));
        layeredPane.setBounds(5, 5, 650+20, 650+20);
        boxPanel.add(layeredPane);

        gameBoard = new GameBoardGUI(5,5,650+10,650+10);
        gameBoard.setBackground(new Color(51, 255, 153));
        layeredPane.add(gameBoard, String.valueOf(0));

    }

    public static void main(String[] args) {
        MonopolyGUI frame = new MonopolyGUI();
        frame.setVisible(true);
    }
}
