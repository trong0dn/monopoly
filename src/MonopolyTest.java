import org.junit.Test;

import static org.junit.Assert.*;

public class MonopolyTest {

    @Test
    public void play() {
    }

    @Test
    public void initializePlayers() {
    }

    @Test
    public void turn() {
    }

    @Test
    public void handleSquare() {
    }

    @Test
    public void buyProperty() {
    }

    /*
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class MonopolyGUI extends JFrame {
    private final Monopoly monopoly;
    private final LinkedList<Player> playersList;
    private final int numPlayers = 4;
    private GameBoardGUI gameBoard;
    static JTextArea infoConsole;
    private int currentPlayerOrder;
    private int currentSquareNumber;
    private final ArrayList<PlayerGUI> playersGUI = new ArrayList<>();
    private DiceGUI die1;
    private DiceGUI die2;
    private Boolean isDouble = false;
    private JLayeredPane rightLayeredPane;
    private JPanel playerAssetsPanel;
    private JLayeredPane leftLayeredPane;
    private JTextArea panelPlayerTextArea;
    private final CardLayout cardLayout = new CardLayout();
    private JButton buttonRollDice;
    private JButton buttonNextTurn;
    private JButton buttonPayRent;
    private JButton buttonBuy;

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
        monopoly = new Monopoly();
        playersList = new LinkedList<>();
        init();
        setupFrame();
        setupBoard();
        setupDice();
        setupButtons();
        setupPlayerToken();
        setupPlayerStatusWindow();
        setupConsoleLog();
        setVisible(true);
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
        setSize(1080,710);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setupBoard() {
        JPanel boxPanel = new JPanel();
        boxPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(boxPanel);
        boxPanel.setLayout(null);

        // Add right panel
        rightLayeredPane = new JLayeredPane();
        rightLayeredPane.setBackground(Color.LIGHT_GRAY);
        rightLayeredPane.setBorder(new LineBorder(new Color(0, 0, 0)));
        rightLayeredPane.setBounds(680, 5, 430, 670);
        rightLayeredPane.setLayout(null);
        boxPanel.add(rightLayeredPane);

        leftLayeredPane = new JLayeredPane();
        leftLayeredPane.setBorder(new LineBorder(new Color(0, 0, 0)));
        leftLayeredPane.setBounds(5, 5, 670, 670);
        boxPanel.add(leftLayeredPane);

        // Add game board to right panel
        gameBoard = new GameBoardGUI(5,5,670,670);
        gameBoard.setBackground(new Color(50, 255, 155));
        leftLayeredPane.add(gameBoard, Integer.valueOf(0));
    }

    private void setupDice() {
        // Add dice graphics
        die1 = new DiceGUI(350, 450, 40, 40);
        leftLayeredPane.add(die1, Integer.valueOf(1));

        die2 = new DiceGUI(400, 450, 40, 40);
        leftLayeredPane.add(die2, Integer.valueOf(1));
    }

    private void setupButtons() {
        // Add roll dice button
        buttonRollDice = buttonRollDice();
        buttonRollDice.setBounds(80, 410, 250, 50);
        rightLayeredPane.add(buttonRollDice);

        // Add buy button
        buttonBuy = buttonBuy();
        buttonBuy.setBounds(80, 470, 115, 40);
        buttonBuy.setEnabled(false);
        rightLayeredPane.add(buttonBuy);

        // Add pay rent button
        buttonPayRent = buttonPayRent();
        buttonPayRent.setBounds(215, 470, 115, 40);
        buttonPayRent.setEnabled(false);
        rightLayeredPane.add(buttonPayRent);

        // Add next turn button
        buttonNextTurn = buttonNextTurn();
        buttonNextTurn.setBounds(80, 520, 250, 50);
        buttonNextTurn.setEnabled(false);
        rightLayeredPane.add(buttonNextTurn);
    }

    private void setupPlayerToken() {
        for (int i = 0; i < numPlayers; i++) {
            PlayerGUI playerGUI = new PlayerGUI(playerTokenColors[i], playersList.get(i).name());
            playersGUI.add(playerGUI);
            leftLayeredPane.add(playerGUI, Integer.valueOf(1));
        }
    }

    private void setupConsoleLog() {
        // Add console log panel
        JPanel consolePanel = new JPanel();
        consolePanel.setBounds(80, 310, 250, 70);
        consolePanel.setBackground(Color.BLACK);
        consolePanel.setLayout(null);
        rightLayeredPane.add(consolePanel, String.valueOf(1));

        infoConsole = new JTextArea();
        infoConsole.setColumns(20);
        infoConsole.setRows(5);
        infoConsole.setBounds(5, 5, 240, 60);
        infoConsole.setLineWrap(true);
        infoConsole.setEditable(false);
        infoConsole.setText("Player 1 starts the game! \nClicking Roll Dice!");
        consolePanel.add(infoConsole);
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
        return panelPlayer;
    }

    private void setupPlayerStatusWindow() {
        // Add player status panel
        playerAssetsPanel = new JPanel();
        playerAssetsPanel.setBounds(80, 40, 250, 250);
        playerAssetsPanel.setLayout(cardLayout);
        rightLayeredPane.add(playerAssetsPanel, String.valueOf(1));

        for (int i = 0; i < numPlayers; i++) {
            JPanel playerStatusPanel = playerStatusPanel(i+1, playerTokenColors[i]);
            playerAssetsPanel.add(playerStatusPanel, String.valueOf(i));
        }

        panelPlayerTextArea = new JTextArea();
        panelPlayerTextArea.setBounds(90, 70, 230, 210);
        panelPlayerTextArea.setEditable(false);
        rightLayeredPane.add(panelPlayerTextArea, String.valueOf(2));
        updatePlayerStatusTextArea();
    }

    private void updatePlayerStatusTextArea() {
        StringBuilder output = new StringBuilder();
        PlayerGUI currentPlayer =  playersGUI.get(currentPlayerOrder);
        int playerMoney = currentPlayer.getPlayerMoney();
        Collection<Square> properties = currentPlayer.getProperties();
        output.append("Current Balance: ").append(playerMoney).append("\n");
        output.append("Property titles owned:\n");
        for (Square sq : properties) {
            output.append(">>> ").append(sq.name()).append("\n");
        }
        panelPlayerTextArea.setText(output.toString());
    }

    private JButton buttonRollDice() {
        buttonRollDice = new JButton("Roll Dice");
        buttonRollDice.addActionListener(e -> {
            die1.rollDice();
            die2.rollDice();
            isDouble = die1.getFaceValue() == die2.getFaceValue();
            int diceValue = die1.getFaceValue() + die2.getFaceValue();
            PlayerGUI currentPlayer = this.playersGUI.get(currentPlayerOrder);
            currentPlayer.move(diceValue);
            currentSquareNumber = this.playersGUI.get(currentPlayerOrder).getCurrentSquareNumber();
            int currentPlayerIndex = (currentPlayerOrder + 1) + numPlayers;
            Square currentSquare = this.gameBoard.getSquare(currentSquareNumber);
            // Roll double, player rolls again
            if (isDouble) {
                infoConsole.setText("You landed on " + currentSquare.name() +
                        "\nProperty Cost: " + currentSquare.cost() +
                        "\nDoubles! Click Roll Dice again, player " + currentPlayerIndex);
                buttonNextTurn.setEnabled(false);
            } else {
                infoConsole.setText("You landed on " + currentSquare.name() +
                        "\nProperty cost: " + currentSquare.cost() +
                        "\nClick Next Turn to allow player " + (currentPlayerIndex % numPlayers + 1) + " to Roll Dice!");
                buttonRollDice.setEnabled(false);
                buttonNextTurn.setEnabled(true);
            }
            // Swing concurrency thread correction for layeredPane flickering
            leftLayeredPane.remove(gameBoard);
            leftLayeredPane.add(gameBoard, Integer.valueOf(0));
            // Button logic
            if (currentSquare.isOwnable() && !currentSquare.isOwned()) {
                buttonBuy.setEnabled(true);
            } else if (currentSquare.isOwnable()) {
                if (currentSquare.owner().name().equals(currentPlayer.getPlayer().name())) {
                    buttonBuy.setEnabled(false);
                    buttonPayRent.setEnabled(false);
                    infoConsole.setText("You already own " + currentSquare.name() +
                            "\nClick Next Turn to allow player " + (currentPlayerIndex % numPlayers + 1) + " to Roll Dice!");
                } else {
                    buttonPayRent.setEnabled(true);
                }
            } else {
                infoConsole.setText("You landed on a non-purchasable property \n" + currentSquare.name() +
                        "\nClick Next Turn to allow player " + (currentPlayerIndex % numPlayers + 1) + " to Roll Dice!");
                buttonBuy.setEnabled(false);
                buttonPayRent.setEnabled(false);
            }
            updatePlayerStatusTextArea();
        });
        return buttonRollDice;
    }

    private JButton buttonNextTurn() {
        buttonNextTurn = new JButton("Next Turn");
        buttonNextTurn.addActionListener(e -> {
            infoConsole.setText("Next Turn!");
            if (isDouble) {
                isDouble = false;
            }
            currentPlayerOrder = (currentPlayerOrder % numPlayers) + 1;
            int currentPlayerIndex = (currentPlayerOrder % numPlayers) + 1;
            currentPlayerOrder %= numPlayers;
            cardLayout.show(playerAssetsPanel, String.valueOf(currentPlayerOrder));
            infoConsole.setText("It's now player "+ (currentPlayerIndex) +"'s turn!");
            buttonRollDice.setEnabled(true);
            buttonBuy.setEnabled(false);
            buttonPayRent.setEnabled(false);
            buttonNextTurn.setEnabled(false);
            updatePlayerStatusTextArea();
        });
        return buttonNextTurn;
    }

    private JButton buttonBuy() {
        buttonBuy = new JButton("Buy Property");
        buttonBuy.addActionListener(e -> {
            PlayerGUI currentPlayer = this.playersGUI.get(currentPlayerOrder);
            Square currentSquare = this.gameBoard.getSquare(currentSquareNumber);
            int roll = die1.getFaceValue() + die2.getFaceValue();
            if (currentSquare.isOwnable() && !currentSquare.isOwned()) {
                infoConsole.setText("You bought property:\n" + currentSquare.name() +
                        "\nPurchase cost: " + currentSquare.cost());
            }
            monopoly.handleSquare(currentPlayer.getPlayer(), currentSquare, roll);
            buttonBuy.setEnabled(false);
            buttonPayRent.setEnabled(false);
            updatePlayerStatusTextArea();
        });
        return buttonBuy;
    }

    private JButton buttonPayRent() {
        buttonPayRent = new JButton("Pay Rent");
        buttonPayRent.addActionListener(e -> {
            PlayerGUI currentPlayer = this.playersGUI.get(currentPlayerOrder);
            Square currentSquare = this.gameBoard.getSquare(currentSquareNumber);
            int roll = die1.getFaceValue() + die2.getFaceValue();
            if (currentSquare.isOwnable() && currentSquare.isOwned()) {
                infoConsole.setText("You paid rent on:\n" + currentSquare.name() +
                        "\nRent cost: " + currentSquare.rent(roll));
            }
            monopoly.handleSquare(currentPlayer.getPlayer(), currentSquare, roll);
            buttonBuy.setEnabled(false);
            buttonPayRent.setEnabled(false);
            updatePlayerStatusTextArea();
        });
        return buttonPayRent;
    }

    public static void main(String[] args) {
        new MonopolyGUI();
    }
}

     */

}