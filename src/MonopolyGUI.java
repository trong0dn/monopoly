import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MonopolyGUI extends JFrame {
    private GameBoardGUI gameBoard;
    static JTextArea infoConsole;
    static int nowPlaying = 0;
    ArrayList<PlayerGUI> players = new ArrayList<>();
    DiceGUI die1;
    DiceGUI die2;
    Boolean doubleDiceForPlayer1 = false;
    Boolean doubleDiceForPlayer2 = false;
    PlayerGUI player1;
    PlayerGUI player2;
    CardLayout c1 = new CardLayout();
    JTextArea panelPlayer1TextArea;
    JTextArea panelPlayer2TextArea;
    JLayeredPane layeredPane;
    JButton buttonRollDice;
    JButton buttonNextTurn;
    JButton buttonPayRent;
    JButton buttonBuy;

    public MonopolyGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setSize(1080,860);
        JPanel boxPanel = new JPanel();
        boxPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(boxPanel);
        boxPanel.setLayout(null);

        // Add right panel
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.LIGHT_GRAY);
        rightPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        rightPanel.setBounds(675, 5, 420, 670);
        boxPanel.add(rightPanel);
        rightPanel.setLayout(null);

        layeredPane = new JLayeredPane();
        layeredPane.setBorder(new LineBorder(new Color(0, 0, 0)));
        layeredPane.setBounds(5, 5, 650+20, 650+20);
        boxPanel.add(layeredPane);

        // Add game board to panel
        gameBoard = new GameBoardGUI(5,5,650+10,650+10);
        gameBoard.setBackground(new Color(51, 255, 153));
        layeredPane.add(gameBoard, Integer.valueOf(0));

        // Add dice graphics
        die1 = new DiceGUI(350, 450, 40, 40);
        layeredPane.add(die1, Integer.valueOf(1));

        die2 = new DiceGUI(400, 450, 40, 40);
        layeredPane.add(die2, Integer.valueOf(1));

        // Add dice ActionListener
        JButton buttonRollDice = buttonRollDice();
        buttonRollDice.setBounds(80, 410, 246, 50);
        rightPanel.add(buttonRollDice);

        // Add Players tokens
        player1 = new PlayerGUI(1, Color.RED);
        players.add(player1);
        layeredPane.add(player1, Integer.valueOf(1));

        player2 = new PlayerGUI(2, Color.BLUE);
        players.add(player2);
        layeredPane.add(player2, Integer.valueOf(1));
    }

    private JButton buttonRollDice() {
        buttonRollDice = new JButton("Roll Dice");
        buttonRollDice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                die1.rollDice();
                die2.rollDice();
            }
        });
        return buttonRollDice;
    }


    public static void main(String[] args) {
        MonopolyGUI frame = new MonopolyGUI();
        frame.setVisible(true);
    }
}
