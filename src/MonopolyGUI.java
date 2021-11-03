import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class MonopolyGUI extends JFrame {
    private final Monopoly.GameState gameState;
    private final LinkedList<Player> playersList;
    private final int numPlayers = 2;
    private GameBoardGUI gameBoard;
    static JTextArea infoConsole;
    private int currentPlayerOrder;
    private final ArrayList<PlayerGUI> playersGUI = new ArrayList<>();
    private DiceGUI die1;
    private DiceGUI die2;
    private JPanel rightPanel;
    private JPanel playerAssetsPanel;
    private JLayeredPane layeredPane;
    private JTextArea panelPlayerTextArea;
    private final CardLayout cardLayout = new CardLayout();
    private JButton buttonRollDice;
    private JButton buttonNextTurn;
    private JButton buttonPayRent;
    private JButton buttonBuy;
    private Boolean doubleDiceForPlayer1 = false;
    private Boolean doubleDiceForPlayer2 = false;

    private final Color[] playerTokenColors = {
            Color.RED,
            Color.BLUE,
            Color.GREEN,
            Color.ORANGE,
            Color.YELLOW,
            Color.MAGENTA,
            Color.GRAY,
            Color.PINK
    };

    public MonopolyGUI() {
        gameState = new Monopoly.GameState();
        playersList = new LinkedList<>();
        init();
        setupFrame();
        setupBoard();
        setupDice();
        setupButtons();
        setupPlayerToken();
        setupPlayerStatusWindow();
        setupConsoleLog();
    }

    public void init() {
        playersList.add(new HumanPlayer("Alice"));
        playersList.add(new HumanPlayer("Bert"));
        playersList.add(new HumanPlayer("Candice"));
        playersList.add(new HumanPlayer("Donald"));
        playersList.add(new HumanPlayer("Elenore"));
        playersList.add(new HumanPlayer("Frank"));
        playersList.add(new HumanPlayer("George"));
        playersList.add(new HumanPlayer("Harry"));
    }

    private void setupFrame() {
        setTitle("MONOPOLY!");
        setBounds(100, 100, 450, 300);
        setSize(1080,860);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setupBoard() {
        JPanel boxPanel = new JPanel();
        boxPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(boxPanel);
        boxPanel.setLayout(null);

        // Add right panel
        rightPanel = new JPanel();
        rightPanel.setBackground(Color.LIGHT_GRAY);
        rightPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        rightPanel.setBounds(675, 5, 430, 670);
        rightPanel.setLayout(null);
        boxPanel.add(rightPanel);

        layeredPane = new JLayeredPane();
        layeredPane.setBorder(new LineBorder(new Color(0, 0, 0)));
        layeredPane.setBounds(5, 5, 670, 670);
        boxPanel.add(layeredPane);

        // Add game board to right panel
        gameBoard = new GameBoardGUI(5,5,670,670);
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
        // Add dice button
        buttonRollDice = buttonRollDice();
        buttonRollDice.setBounds(80, 410, 250, 50);
        rightPanel.add(buttonRollDice);

        // Add buy button
        buttonBuy = buttonBuy();
        buttonBuy.setBounds(80, 470, 115, 40);
        rightPanel.add(buttonBuy);

        // Add pay rent button
        buttonPayRent = buttonPayRent();
        buttonPayRent.setBounds(215, 470, 115, 40);
        rightPanel.add(buttonPayRent);

        // Add next turn button
        buttonNextTurn = buttonNextTurn();
        buttonNextTurn.setBounds(80, 520, 250, 50);
        rightPanel.add(buttonNextTurn);
    }

    private void setupPlayerToken() {
        for (int i = 0; i < numPlayers; i++) {
            PlayerGUI playerGUI = new PlayerGUI(playerTokenColors[i], playersList.get(i).name());
            playersGUI.add(playerGUI);
            layeredPane.add(playerGUI, Integer.valueOf(1));
        }
    }

    private void setupPlayerStatusWindow() {
        // Add player status panel
        playerAssetsPanel = new JPanel();
        playerAssetsPanel.setBounds(80, 40, 250, 200);
        playerAssetsPanel.setLayout(cardLayout);
        rightPanel.add(playerAssetsPanel);

        for (int i = 0; i < numPlayers; i++) {
            JPanel playerStatusPanel = playerStatusPanel(i+1, playerTokenColors[i]);
            playerAssetsPanel.add(playerStatusPanel, String.valueOf(i+1));
        }
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
            die1.rollDice();
            die2.rollDice();
            doubleDiceForPlayer1 = die1.getFaceValue() == die2.getFaceValue();
            int diceValue = die1.getFaceValue() + die2.getFaceValue();
            playersGUI.get(currentPlayerOrder).move(diceValue);

            int tempCurrentPlayer = currentPlayerOrder + 1;
            // Roll double, player rolls again
            if (doubleDiceForPlayer1 || doubleDiceForPlayer2) {
                infoConsole.setText("Doubles! Click Roll Dice again player " + tempCurrentPlayer);
                buttonNextTurn.setEnabled(false);
            } else {
                infoConsole.setText("Click Next Turn to allow player " + (tempCurrentPlayer % numPlayers + 1) + " to Roll Dice!");
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
            if (currentPlayerOrder == 0 && doubleDiceForPlayer1) {
                doubleDiceForPlayer1 = false;
            } else if (currentPlayerOrder == 1 && doubleDiceForPlayer2) {
                doubleDiceForPlayer2 = false;
            } else if(!doubleDiceForPlayer1 && !doubleDiceForPlayer2) {
                currentPlayerOrder = (currentPlayerOrder + 1) % numPlayers;
            }
            buttonNextTurn.setEnabled(false);

            updatePlayerStatusTextArea();
            cardLayout.show(playerAssetsPanel, String.valueOf(currentPlayerOrder+1));
            infoConsole.setText("Console: It's now player "+ (currentPlayerOrder+1) +"'s turn!");
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

        panelPlayerTextArea = new JTextArea();
        panelPlayerTextArea.setBounds(10, 35, 230, 150);
        panelPlayerTextArea.setEditable(false);
        panelPlayer.add(panelPlayerTextArea);
        updatePlayerStatusTextArea();
        return panelPlayer;
    }

    private void updatePlayerStatusTextArea() {
        StringBuilder output = new StringBuilder();
        int playerMoney = playersGUI.get(currentPlayerOrder).getMoney();
        Collection<Square> properties = playersGUI.get(currentPlayerOrder).getProperties();
        output.append("Current Balance: ").append(playerMoney).append("\n");
        output.append("Property titles owned:\n");
        for (Square sq : properties) {
            output.append(">>> ").append(sq.name()).append("\n");
        }
        panelPlayerTextArea.setText(output.toString());
    }

    public static void main(String[] args) {
        MonopolyGUI frame = new MonopolyGUI();
        frame.setVisible(true);
    }
}
