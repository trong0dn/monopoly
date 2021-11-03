import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class MonopolyGUI extends JFrame {
    private Monopoly monopoly = new Monopoly();
    private GameBoardGUI gameBoard;
    static JTextArea infoConsole;
    static int nowPlaying = 0;
    ArrayList<PlayerGUI> players = new ArrayList<>();
    private DiceGUI die1;
    private DiceGUI die2;
    PlayerGUI player1;
    PlayerGUI player2;
    JPanel boxPanel;
    JLayeredPane layeredPane;
    JPanel rightPanel;
    JTextArea panelPlayerTextArea;
    CardLayout cardLayout = new CardLayout();
    JPanel playerAssetsPanel;
    JButton buttonRollDice;
    JButton buttonNextTurn;
    JButton buttonPayRent;
    JButton buttonBuy;
    Boolean doubleDiceForPlayer1 = false;
    Boolean doubleDiceForPlayer2 = false;

    public MonopolyGUI() {
        setupFrame();
        setupBoard();
        setupDice();
        setupButtons();
        setupPlayerToken();
        setupPlayerStatusWindow();
        setupConsoleLog();
    }

    private void setupFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setSize(1080,860);
        boxPanel = new JPanel();
        boxPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(boxPanel);
        boxPanel.setLayout(null);
    }

    private void setupBoard() {
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
        gameBoard.setBackground(new Color(50, 255, 155));
        layeredPane.add(gameBoard, Integer.valueOf(0));
    }

    private void setupDice() {
        // Add dice graphics
        die1 = new DiceGUI(350, 450, 40, 40);
        layeredPane.add(die1, Integer.valueOf(1));

        die2 = new DiceGUI(400, 450, 40, 40);
        layeredPane.add(die2, Integer.valueOf(1));
    }

    private void setupButtons() {
        // Add dice button ActionListener
        buttonRollDice = buttonRollDice();
        buttonRollDice.setBounds(80, 410, 250, 50);
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
    }

    private void setupPlayerToken() {
        // Add Players tokens
        player1 = new PlayerGUI(Color.RED);
        players.add(player1);
        layeredPane.add(player1, Integer.valueOf(1));

        player2 = new PlayerGUI(Color.BLUE);
        players.add(player2);
        layeredPane.add(player2, Integer.valueOf(1));
    }

    private void setupPlayerStatusWindow() {
        // Add player status panel
        playerAssetsPanel = new JPanel();
        playerAssetsPanel.setBounds(80, 30, 250, 190);
        playerAssetsPanel.setLayout(cardLayout);
        rightPanel.add(playerAssetsPanel);

        JPanel player1StatusPanel = playerStatusPanel(1, Color.RED);
        JPanel player2StatusPanel = playerStatusPanel(2, Color.BLUE);

        playerAssetsPanel.add(player1StatusPanel, "1");
        playerAssetsPanel.add(player2StatusPanel, "2");
    }

    private void setupConsoleLog() {
        // Add console log panel
        JPanel consolePanel = new JPanel();
        consolePanel.setBounds(80, 310, 250, 70);
        consolePanel.setLayout(null);
        rightPanel.add(consolePanel);

        infoConsole = new JTextArea();
        infoConsole.setColumns(20);
        infoConsole.setRows(5);
        infoConsole.setBounds(5, 5, 240, 60);
        infoConsole.setLineWrap(true);
        infoConsole.setEditable(false);
        infoConsole.setText("Player 1 starts the game! \nClicking Roll Dice!");
        consolePanel.add(infoConsole);
    }

    private JButton buttonRollDice() {
        buttonRollDice = new JButton("Roll Dice");
        buttonRollDice.addActionListener(e -> {
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

            // Roll double, player rolls again
            if (doubleDiceForPlayer1 || doubleDiceForPlayer2) {
                infoConsole.setText("Click Next Turn to allow player "+ (nowPlaying==0 ? 1 : 2) +" to Roll Dice!");
                buttonNextTurn.setEnabled(false);
            } else {
                infoConsole.setText("Click Next Turn to allow player "+ (nowPlaying==0 ? 2 : 1) +" to Roll Dice!");
                buttonRollDice.setEnabled(false);
                buttonNextTurn.setEnabled(true);
            }
            layeredPane.remove(gameBoard);
            layeredPane.add(gameBoard, Integer.valueOf(0));

            updatePlayerStatusTextArea();
        });
        return buttonRollDice;
    }

    private JButton buttonNextTurn() {
        buttonNextTurn = new JButton("Next Turn");
        buttonNextTurn.addActionListener(e -> {
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

            updatePlayerStatusTextArea();
            cardLayout.show(playerAssetsPanel,String.valueOf(nowPlaying==0 ? 1 : 2));
            infoConsole.setText("It's now player "+ (nowPlaying==0 ? 1 : 2) +"'s turn!");
        });
        return buttonNextTurn;
    }

    private JButton buttonBuy() {
        buttonBuy = new JButton("Buy Property");
        buttonBuy.addActionListener(e -> infoConsole.setText("You bought something"));
        return buttonBuy;
    }

    private JButton buttonPayRent() {
        buttonPayRent = new JButton("Pay Rent");
        buttonPayRent.addActionListener(e -> infoConsole.setText("You paid rent!"));
        return buttonPayRent;
    }

    private JPanel playerStatusPanel(int playerNumber, Color color) {
        JPanel panelPlayer = new JPanel();
        panelPlayer.setBackground(color);
        panelPlayer.setLayout(null);

        JLabel panelPlayerTitle = new JLabel("Player " + playerNumber + " Status");
        panelPlayerTitle.setForeground(Color.WHITE);
        panelPlayerTitle.setHorizontalAlignment(SwingConstants.CENTER);
        panelPlayerTitle.setBounds(0, 5, 240, 15);
        panelPlayer.add(panelPlayerTitle);

        this.panelPlayerTextArea = new JTextArea();
        this.panelPlayerTextArea.setBounds(10, 35, 230, 150);
        panelPlayer.add(this.panelPlayerTextArea);
        updatePlayerStatusTextArea();
        return panelPlayer;
    }

    private void updatePlayerStatusTextArea() {
        //StringBuilder output = new StringBuilder();
        //int playerMoney = monopoly.getGameState().currentPlayer.getMoney();
        //Collection<Square> properties = monopoly.getGameState().currentPlayer.properties();
        //output.append("Current Balance: ").append(playerMoney);
        //output.append("Property titles owned:\n");
        //for (Square sq : properties) {
         //   output.append(">>> ").append(sq.name()).append("\n");
        //}
        String output = "It's now player "+ (nowPlaying==0 ? 1 : 2) +"'s turn!";
        this.panelPlayerTextArea.setText(output);
    }

    public static void main(String[] args) {
        MonopolyGUI frame = new MonopolyGUI();
        frame.setVisible(true);
    }
}
