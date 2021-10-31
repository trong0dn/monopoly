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
    JPanel playerAssetsPanel;
    ArrayList<PlayerGUI> players = new ArrayList<>();
    DiceGUI die1;
    DiceGUI die2;
    PlayerGUI player1;
    PlayerGUI player2;
    CardLayout c1 = new CardLayout();
    JTextArea panelPlayer1TextArea;
    JTextArea panelPlayer2TextArea;
    JLayeredPane layeredPane;
    JPanel rightPanel;
    JButton buttonRollDice;
    JButton buttonNextTurn;
    JButton buttonPayRent;
    JButton buttonBuy;
    Boolean doubleDiceForPlayer1 = false;
    Boolean doubleDiceForPlayer2 = false;

    public MonopolyGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setSize(1080,860);
        JPanel boxPanel = new JPanel();
        boxPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(boxPanel);
        boxPanel.setLayout(null);

        // Add right panel
        rightPanel = new JPanel();
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

        // Add dice button ActionListener
        buttonRollDice = buttonRollDice();
        buttonRollDice.setBounds(80, 410, 246, 50);
        rightPanel.add(buttonRollDice);

        // Add buy button
        buttonBuy = buttonBuy();
        buttonBuy.setBounds(81, 478, 117, 29);
        rightPanel.add(buttonBuy);

        // Add pay rent button
        buttonPayRent = buttonPayRent();
        buttonPayRent.setBounds(210, 478, 117, 29);
        rightPanel.add(buttonPayRent);

        // Add next turn button
        buttonNextTurn = buttonNextTurn();
        buttonNextTurn.setBounds(81, 519, 246, 53);
        rightPanel.add(buttonNextTurn);

        // Add Players tokens
        player1 = new PlayerGUI(1, Color.RED);
        players.add(player1);
        layeredPane.add(player1, Integer.valueOf(1));

        player2 = new PlayerGUI(2, Color.BLUE);
        players.add(player2);
        layeredPane.add(player2, Integer.valueOf(1));

        // Add console log panel
        JPanel consolePanel = new JPanel();
        consolePanel.setBounds(81, 312, 246, 68);
        rightPanel.add(consolePanel);
        consolePanel.setLayout(null);

        // Add player status panel
        JPanel playerAssetsPanel = new JPanel();
        playerAssetsPanel.setBounds(81, 28, 246, 189);
        rightPanel.add(playerAssetsPanel);
        playerAssetsPanel.setLayout(c1);

        // Player 1 status panel
        JPanel panelPlayer1 = new JPanel();
        panelPlayer1.setBackground(Color.RED);
        playerAssetsPanel.add(panelPlayer1, "1");
        panelPlayer1.setLayout(null);

        JLabel panelPlayer1Title = new JLabel("Player 1 All Wealth");
        panelPlayer1Title.setForeground(Color.WHITE);
        panelPlayer1Title.setHorizontalAlignment(SwingConstants.CENTER);
        panelPlayer1Title.setBounds(0, 6, 240, 16);
        panelPlayer1.add(panelPlayer1Title);

        panelPlayer1TextArea = new JTextArea();
        panelPlayer1TextArea.setBounds(10, 34, 230, 149);
        panelPlayer1.add(panelPlayer1TextArea);

        JPanel panelPlayer2 = new JPanel();
        panelPlayer2.setBackground(Color.BLUE);
        playerAssetsPanel.add(panelPlayer2, "2");
        panelPlayer2.setLayout(null);
        c1.show(playerAssetsPanel, ""+nowPlaying);

        JLabel panelPlayer2Title = new JLabel("Player 2 All Wealth");
        panelPlayer2Title.setForeground(Color.WHITE);
        panelPlayer2Title.setHorizontalAlignment(SwingConstants.CENTER);
        panelPlayer2Title.setBounds(0, 6, 240, 16);
        panelPlayer2.add(panelPlayer2Title);

        panelPlayer2TextArea = new JTextArea();
        panelPlayer2TextArea.setBounds(10, 34, 230, 149);
        panelPlayer2.add(panelPlayer2TextArea);

        //updatePanelPlayer1TextArea();
        //updatePanelPlayer2TextArea();

        // Info console log
        infoConsole = new JTextArea();
        infoConsole.setColumns(20);
        infoConsole.setRows(5);
        infoConsole.setBounds(6, 6, 234, 56);
        consolePanel.add(infoConsole);
        infoConsole.setLineWrap(true);
        infoConsole.setText("Player 1 starts the game, clicking Roll Dice!");
    }

    private JButton buttonRollDice() {
        buttonRollDice = new JButton("Roll Dice");
        buttonRollDice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonNextTurn.setEnabled(true);
                if (nowPlaying == 0) {
                    die1.rollDice();
                    die2.rollDice();
                    doubleDiceForPlayer1 = die1.getFaceValue() == die2.getFaceValue();
                    int diceValue = die1.getFaceValue() + die2.getFaceValue();
                    player1.move(diceValue);
                }
                else {
                    die1.rollDice();
                    die2.rollDice();
                    doubleDiceForPlayer2 = die1.getFaceValue() == die2.getFaceValue();
                    int diceValue = die1.getFaceValue() + die2.getFaceValue();
                    player2.move(diceValue);

                }

                buttonRollDice.setEnabled(false);
                if(doubleDiceForPlayer1 || doubleDiceForPlayer2) {
                    infoConsole.setText("Click Next Turn to allow player "+ (nowPlaying==0 ? 1 : 2) +" to Roll Dice!");
                } else {
                    infoConsole.setText("Click Next Turn to allow player "+ (nowPlaying==0 ? 2 : 1) +" to Roll Dice!");
                }
                layeredPane.remove(gameBoard);
                layeredPane.add(gameBoard, Integer.valueOf(0));

                //updatePanelPlayer1TextArea();
                //updatePanelPlayer2TextArea();
            }
        });
        return buttonRollDice;
    }

    private JButton buttonNextTurn() {
        buttonNextTurn = new JButton("Next Turn");
        buttonNextTurn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoConsole.setText("Next Turn!");
                buttonRollDice.setEnabled(true);
                if(nowPlaying == 0 && doubleDiceForPlayer1) {
                    doubleDiceForPlayer1 = false;
                } else if(nowPlaying == 1 && doubleDiceForPlayer2) {
                    doubleDiceForPlayer2 = false;
                } else if(!doubleDiceForPlayer1 && !doubleDiceForPlayer2) {
                    nowPlaying = (nowPlaying + 1) % 2;
                }
                buttonNextTurn.setEnabled(false);

                //c1.show(playerAssetsPanel, ""+(nowPlaying==0 ? 1 : 2)); // maps 0 to 1 and 1 to 2
                //updatePanelPlayer1TextArea();
                //updatePanelPlayer2TextArea();
                infoConsole.setText("It's now player "+(nowPlaying==0 ? 1 : 2)+"'s turn!");
            }
        });
        return buttonNextTurn;
    }

    private JButton buttonBuy() {
        buttonBuy = new JButton("Buy Property");
        buttonBuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoConsole.setText("You bought something");
            }
        });
        return buttonBuy;
    }

    private JButton buttonPayRent() {
        buttonPayRent = new JButton("Pay Rent");
        buttonPayRent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoConsole.setText("You paid rent!");
            }
        });
        return buttonPayRent;
    }

    public static void main(String[] args) {
        MonopolyGUI frame = new MonopolyGUI();
        frame.setVisible(true);
    }
}
