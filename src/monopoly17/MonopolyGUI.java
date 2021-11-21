/*
Milestone 2

Group 17:
Trong Nguyen 100848232
Francisco De Grano 101147447
Ibrahim Almalki 101142978
Elisha Catherasoo 101148507

Professor: Babak Esfandiari
TA: Michael Vezina

Due: 11/08/2021
 */

package monopoly17;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

/**
 * This class represents the Monopoly GUI.
 * @author Trong Nguyen, Francisco De Grano, Ibrahim Almalki, & Elisha Catherasoo
 */
public class MonopolyGUI extends JPanel {
    private final Monopoly monopoly;
    private final MonopolyController controller = new MonopolyController();
    private final LinkedList<Player> playersList;
    private GameBoardGUI gameBoard;
    private static JTextArea infoConsole;
    private int currentPlayerOrder;
    private int currentSquareNumber;
    private final ArrayList<PlayerGUI> playersGUI = new ArrayList<>();
    private DiceGUI die1;
    private DiceGUI die2;
    private Boolean isDouble = false;
    private int doubles = 0;
    private JPanel boxPanel;
    private JLayeredPane rightLayeredPane;
    private JPanel playerAssetsPanel;
    private JLayeredPane leftLayeredPane;
    private JTextArea panelPlayerTextArea;
    private final CardLayout cardLayout = new CardLayout();
    private JButton buttonRollDice;
    private JButton buttonNextTurn;
    private JButton buttonPayRent;
    private JButton buttonBuy;
    private JButton buttonBuyHouse; // Add buy house button
    private JButton buttonRunCPU;
    private boolean firstRoll = true;

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

    /**
     * Constructor for MonopolyGUI.
     */
    public MonopolyGUI() {

        monopoly = new Monopoly();
        Monopoly.GameState gameState = new Monopoly.GameState();
        gameState.players = new LinkedList<>();
        playersList = gameState.players;
        playersList.add(new HumanPlayer(""));
        setupBoard();
        setupDice();
        setupRollButton();
        setupPlayerToken();
        setupPlayerStatusWindow();
        setupConsoleLog();
        initController();
        monopoly.play();
    }

    /**
     * Set up the layout for the board.
     */
    private void setupBoard() {
        boxPanel = new JPanel();
        boxPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
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

    /**
     * Set up the dice positions.
     */
    private void setupDice() {
        // Add dice graphics
        die1 = new DiceGUI(350, 450, 40, 40);
        leftLayeredPane.add(die1, Integer.valueOf(1));

        die2 = new DiceGUI(400, 450, 40, 40);
        leftLayeredPane.add(die2, Integer.valueOf(1));
    }

    /**
     * Set up the positions of the buttons.
     */
    private void setupRollButton() {
        // Add roll dice button
        buttonRollDice = buttonRollDice();
        buttonRollDice.setBounds(80, 410, 250, 50);
        rightLayeredPane.add(buttonRollDice);
    }

    /**
     * Set up the positions of the buttons.
     */
    private void setupButtons() {
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

        // Add buyHouse Button
        buttonBuyHouse = buttonBuyHouse();
        buttonBuyHouse.setBounds(80, 520, 115, 40);
        buttonBuyHouse.setEnabled(true);
        rightLayeredPane.add(buttonBuyHouse);

        // Add RunCPU Button
        buttonRunCPU = buttonRunCPU();
        buttonRunCPU.setBounds(215, 520, 115, 40);
        buttonRunCPU.setEnabled(true);
        rightLayeredPane.add(buttonRunCPU);

        // Add next turn button
        buttonNextTurn = buttonNextTurn();
        buttonNextTurn.setBounds(80, 570, 250, 50);
        buttonNextTurn.setEnabled(false);
        rightLayeredPane.add(buttonNextTurn);
    }

    /**
     * Give each player their own color.
     */
    private void setupPlayerToken() {
        if (!firstRoll) {
            for (int i = 0; i < playersList.size(); i++) {
                PlayerGUI playerGUI = new PlayerGUI(playerTokenColors[i], playersList.get(i).name());
                playersGUI.add(playerGUI);
                leftLayeredPane.add(playerGUI, Integer.valueOf(1));
            }
        }
    }

    /**
     * Gives the players instructions.
     */
    private void setupConsoleLog() {
        // Add console log panel
        JPanel consolePanel = new JPanel();
        consolePanel.setBounds(80, 300, 250, 100);
        consolePanel.setBackground(Color.BLACK);
        consolePanel.setLayout(null);
        rightLayeredPane.add(consolePanel, String.valueOf(1));

        infoConsole = new JTextArea();
        infoConsole.setColumns(20);
        infoConsole.setRows(5);
        infoConsole.setBounds(5, 5, 240, 90);
        infoConsole.setLineWrap(true);
        infoConsole.setEditable(false);
        infoConsole.setText("Player 1 starts the game! \nClicking Roll Dice!");
        consolePanel.add(infoConsole);
    }

    /**
     * Initialize the controllers.
     */
    private void initController() {
        controller.setMonopolyPanel(boxPanel);
        controller.displayGUI();
    }

    /**
     * Displays the player information.
     * @param playerNumber  int, the player number
     * @param color         Color, the color of the player
     * @return              JPanel
     */
    private JPanel playerStatusPanel(int playerNumber, Color color) {
        JPanel panelPlayer = new JPanel();
        panelPlayer.setBackground(color);
        panelPlayer.setLayout(null);
        JLabel panelPlayerTitle = new JLabel("Player " + playersList.get(playerNumber-1).name() + " Status");
        panelPlayerTitle.setForeground(Color.WHITE);
        panelPlayerTitle.setHorizontalAlignment(SwingConstants.CENTER);
        panelPlayerTitle.setBounds(0, 5, 240, 15);
        panelPlayer.add(panelPlayerTitle);
        return panelPlayer;
    }

    /**
     * Gives each player their own distinct color.
     */
    private void setupPlayerStatusWindow() {
        // Add player status panel
        playerAssetsPanel = new JPanel();
        playerAssetsPanel.setBounds(80, 40, 250, 250);
        playerAssetsPanel.setLayout(cardLayout);
        rightLayeredPane.add(playerAssetsPanel, String.valueOf(1));

        for (int i = 0; i < playersList.size(); i++) {
            JPanel playerStatusPanel = playerStatusPanel(i+1, playerTokenColors[i]);
            playerAssetsPanel.add(playerStatusPanel, String.valueOf(i));
        }

        panelPlayerTextArea = new JTextArea();
        panelPlayerTextArea.setBounds(90, 70, 230, 210);
        panelPlayerTextArea.setEditable(false);
        rightLayeredPane.add(panelPlayerTextArea, String.valueOf(2));
        if(!firstRoll){
            updatePlayerStatusTextArea();
        }
    }

    /**
     * Update the player information shown.
     */
    private void updatePlayerStatusTextArea() {
        StringBuilder output = new StringBuilder();
        PlayerGUI currentPlayer =  playersGUI.get(currentPlayerOrder);
        int playerMoney = currentPlayer.getPlayerMoney();
        Collection<Square> properties = currentPlayer.getProperties();
        output.append("Current Balance: $").append(playerMoney).append("\n");
        output.append("Property titles owned:\n");
        for (Square sq : properties) {
            output.append("> ").append(sq.name()).append("\n");

        }
        panelPlayerTextArea.setText(output.toString());
    }

    /**
     * Update the player list after the user inputs the names in the player initialization panel.
     */
    private void updatePlayerList() {
        for(int i = 0; i < controller.getPlayerList().size(); i++) {
            if(i == 0){
                playersList.set(0,controller.getPlayerList().get(i));
            } else {
                playersList.add(controller.getPlayerList().get(i));
            }
        }
    }

    /**
     * Check if the roll is a double and change the visibility of the buttons.
     * @param currentPlayerOrder    int, the index of the current player
     */
    private void isRollDouble(int currentPlayerOrder) {
        int nextPlayerIndex = (currentPlayerOrder + 1) % playersList.size();
        if (isDouble && doubles < 3) { // A player can not have more than 3 rolls
            infoConsole.append("\nDoubles! Click Roll Dice again, player " + playersList.get(currentPlayerOrder).name());
            buttonNextTurn.setEnabled(false);
            doubles++;
        } else {
            infoConsole.append("\nClick Next Turn to allow player " + playersList.get(nextPlayerIndex).name() + " to Roll Dice");
            buttonRollDice.setEnabled(false);
            buttonNextTurn.setEnabled(true);
            doubles = 0;
        }
    }

    /**
     * Rolls the dice.
     * @return      JButton
     */
    private JButton buttonRollDice() {
        buttonRollDice = new JButton("Roll Dice");

        buttonRollDice.addActionListener(e -> {
            if (firstRoll) {
                updatePlayerList();
                firstRoll = false;
                setupButtons();
                setupPlayerToken();
                setupPlayerStatusWindow();
            }

            die1.rollDice();
            die2.rollDice();
            isDouble = die1.getFaceValue() == die2.getFaceValue();
            int diceValue = die1.getFaceValue() + die2.getFaceValue();
            PlayerGUI currentPlayer = this.playersGUI.get(currentPlayerOrder);
            currentSquareNumber = (this.playersGUI.get(currentPlayerOrder).getCurrentSquareNumber() + diceValue) % 40;

            if(currentPlayer.getPlayer().getJailTurns() > 0) {
                infoConsole.setText("You are in Jail\n");
                if (isDouble) {
                    infoConsole.setText("You rolled doubles\n");
                    infoConsole.append("You are now out of Jail\n");
                    infoConsole.append("Roll again!");

                    currentPlayer.getPlayer().setInJail(false);
                    currentPlayer.getPlayer().setJailTurns(0);

                    buttonRollDice.setEnabled(true);
                    buttonNextTurn.setEnabled(false);
                } else if (currentPlayer.getPlayer().getJailTurns() == 3) {
                    infoConsole.setText("You did not roll doubles\n");
                    infoConsole.append("This is your third turn in jail\n");
                    infoConsole.append("You are now out of Jail\n");

                    currentPlayer.getPlayer().setInJail(false);
                    currentPlayer.getPlayer().setJailTurns(0);

                    buttonRollDice.setEnabled(false);
                    buttonNextTurn.setEnabled(true);
                } else {
                    infoConsole.setText("You did not roll doubles\n");
                    infoConsole.append("This is turn " + currentPlayer.getPlayer().getJailTurns() + " in jail\n");

                    buttonRollDice.setEnabled(false);
                    buttonNextTurn.setEnabled(true);
                    currentPlayer.getPlayer().setJailTurns(currentPlayer.getPlayer().getJailTurns() + 1);
                }

                buttonPayRent.setEnabled(false);
                buttonBuy.setEnabled(false);
            } else if(currentSquareNumber == 30) {
                currentPlayer.move(diceValue);
                Square currentSquare = this.gameBoard.getSquare(currentSquareNumber);
                infoConsole.setText("You landed on " + currentSquare.name());
                infoConsole.append("\nYou are now in Jail");
                currentPlayer.move(20);
                currentPlayer.getPlayer().setJailTurns(1);
                buttonPayRent.setEnabled(false);
                buttonRollDice.setEnabled(false);
                buttonBuy.setEnabled(false);
                buttonNextTurn.setEnabled(true);
            } else {
                currentPlayer.move(diceValue);
                Square currentSquare = this.gameBoard.getSquare(currentSquareNumber);
                // Swing concurrency thread correction for layeredPane flickering
                leftLayeredPane.remove(gameBoard);
                leftLayeredPane.add(gameBoard, Integer.valueOf(0));

                int prevSquare = currentSquareNumber - diceValue;

                infoConsole.setText(""); // in case player didn't pass go

                // Pass Go
                if (currentSquareNumber < 12 && prevSquare < 0) {
                    infoConsole.setText("You passed Go! You get $200!\n");
                }

                // Button logic
                if (currentSquare.isOwnable() && !currentSquare.isOwned()) {
                    infoConsole.append("You landed on " + currentSquare.name() +
                            "\nProperty Cost: $" + currentSquare.cost());
                    isRollDouble(currentPlayerOrder);
                    buttonBuy.setEnabled(true);
                    // If square is already owned
                } else if (currentSquare.isOwnable()) {
                    // Player lands on their own property
                    if (currentSquare.owner().name().equals(currentPlayer.getPlayer().name())) {
                        buttonBuy.setEnabled(false);
                        buttonPayRent.setEnabled(false);
                        infoConsole.append("You landed on " + currentSquare.name()
                                + "\nYou already own " + currentSquare.name());
                        // Player lands on own property owned by another player
                    } else if (currentSquare instanceof Property) {
                        infoConsole.append("Property: You landed on:" + currentSquare.name() +
                                "\nRent: $" + currentSquare.rent(diceValue));
                        buttonPayRent.setEnabled(true);
                        buttonRollDice.setEnabled(false);
                        buttonNextTurn.setEnabled(false);
                        // Player lands on owned railroad
                    } else if (currentSquare instanceof Railroad) {
                        infoConsole.append("Station: You landed on " + currentSquare.name() +
                                "\nRent: $" + (2*currentSquare.rent(diceValue)));
                        buttonPayRent.setEnabled(true);
                        buttonRollDice.setEnabled(false);
                        buttonNextTurn.setEnabled(false);
                    } else if (currentSquare instanceof Utility) {
                        infoConsole.append("Utility: You landed on " + currentSquare.name() +
                                "\nRent: $" + currentSquare.rent(diceValue));
                        buttonPayRent.setEnabled(true);
                        buttonRollDice.setEnabled(false);
                        buttonNextTurn.setEnabled(false);
                    }
                    isRollDouble(currentPlayerOrder);
                } else {
                    // Player lands on tax square
                    if (currentSquare instanceof Taxes) {
                        infoConsole.append("Taxes: You landed on " + currentSquare.name() +
                                "\nTax: $" + ((Taxes) currentSquare).getTax());
                        buttonPayRent.setEnabled(true);
                        buttonRollDice.setEnabled(false);
                        buttonNextTurn.setEnabled(false);
                    } else {
                        infoConsole.append("Non-purchasable: You landed on a property: \n" + currentSquare.name());
                        isRollDouble(currentPlayerOrder);
                        buttonBuy.setEnabled(false);
                        buttonPayRent.setEnabled(false);
                    }
                }
            }
            updatePlayerStatusTextArea();
        });
        return buttonRollDice;
    }

    /**
     * Gives the turn to the next player.
     * @return      JButton
     */
    private JButton buttonNextTurn() {
        buttonNextTurn = new JButton("Next Turn");
        buttonNextTurn.addActionListener(e -> {
            infoConsole.setText("Next Turn!\n");
            if (isDouble) {
                isDouble = false;
            }
            currentPlayerOrder = (currentPlayerOrder + 1) % playersList.size();
            int currentPlayerIndex = (currentPlayerOrder % playersList.size()) + 1;
            currentPlayerOrder %= playersList.size();
            cardLayout.show(playerAssetsPanel, String.valueOf(currentPlayerOrder));
            infoConsole.append("It's now player "+ playersList.get(currentPlayerIndex - 1).name() +"'s turn!\n");
            buttonRollDice.setEnabled(true);
            buttonBuy.setEnabled(false);
            buttonPayRent.setEnabled(false);
            buttonNextTurn.setEnabled(false);
            updatePlayerStatusTextArea();
        });
        return buttonNextTurn;
    }

    /**
     * Buys the property that the player landed on.
     * @return      JButton
     */
    private JButton buttonBuy() {
        buttonBuy = new JButton("Buy Property");
        buttonBuy.addActionListener(e -> {
            PlayerGUI currentPlayer = this.playersGUI.get(currentPlayerOrder);
            Square currentSquare = this.gameBoard.getSquare(currentSquareNumber);
            int roll = die1.getFaceValue() + die2.getFaceValue();
            if (currentSquare.isOwnable() && !currentSquare.isOwned() && currentPlayer.getPlayerMoney() >= currentSquare.cost()) {
                infoConsole.setText("You bought property:\n" + currentSquare.name() +
                        "\nPurchase cost: " + currentSquare.cost());
            } else {
                infoConsole.setText("You don't have enough money to buy: \n" + currentSquare.name());
            }
            monopoly.handleSquare(currentPlayer.getPlayer(), currentSquare, roll);
            buttonBuy.setEnabled(false);
            updatePlayerStatusTextArea();
        });
        return buttonBuy;
    }

    /**
     * Pays the rent that the player landed on.
     * @return      JButton
     */
    private JButton buttonPayRent() {
        buttonPayRent = new JButton("Pay Rent");
        buttonPayRent.addActionListener(e -> {
            PlayerGUI currentPlayer = this.playersGUI.get(currentPlayerOrder);
            Square currentSquare = this.gameBoard.getSquare(currentSquareNumber);
            int roll = die1.getFaceValue() + die2.getFaceValue();
            monopoly.handleSquare(currentPlayer.getPlayer(), currentSquare, roll);
            if (currentSquare.isOwnable() && currentSquare.isOwned()) {
                infoConsole.setText("You paid rent on:\n" + currentSquare.name() +
                        "\nRent cost: " + currentSquare.rent(roll));
                if (currentSquare instanceof Railroad) {
                    infoConsole.setText("You paid rent on:\n" + currentSquare.name() +
                            "\nRent cost: " + (2*currentSquare.rent(roll)));
                }
            }
            buttonPayRent.setEnabled(false);

            if(isDouble){
                buttonRollDice.setEnabled(true);
                buttonNextTurn.setEnabled(false);
            } else {
                buttonNextTurn.setEnabled(true);
            }
            updatePlayerStatusTextArea();
        });
        return buttonPayRent;
    }

    /**
     * Allows user to buy house when they own a full set of properties
     * @return JButton
     */
    private JButton buttonBuyHouse(){
        buttonBuyHouse = new JButton("Buy House");
        buttonBuyHouse.addActionListener(f->{
            JPanel panel = new JPanel(new GridLayout(0, 4));
            for (Square sq : playersGUI.get(currentPlayerOrder).getPlayer().properties()){
                Property property;
                if (sq instanceof Property){
                    property = (Property) sq;

                    if (property.isMonopoly()){
                        JButton propButton = new JButton(property.name());
                        propButton.addActionListener(e-> {
                            monopoly.buyHouses(playersGUI.get(currentPlayerOrder).getPlayer(), property);
                            System.out.println("House Purchased");
                            infoConsole.setText("Bought House for " + property.getHouseCost());
                            /*
                            Display dots when player buys house on property.
                             */
                        });

                        panel.add(propButton);

                    }
                }

            }
            JOptionPane.showMessageDialog(rightLayeredPane, panel);

        });

        return buttonBuyHouse;

    }

    private JButton buttonRunCPU(){
        buttonRunCPU = new JButton("CPU's Turn");
        buttonRunCPU.addActionListener(f-> {
            PlayerGUI currentPlayer = playersGUI.get(currentPlayerOrder);
            Square currentSquare = this.gameBoard.getSquare(currentSquareNumber);

            //dice setup
            die1.rollDice();
            die2.rollDice();
            int roll = die1.getFaceValue() + die2.getFaceValue();

            //grey out the buttons
            buttonRollDice.setEnabled(false);
            buttonBuyHouse.setEnabled(false);
            buttonPayRent.setEnabled(false);
            buttonBuy.setEnabled(false);
            buttonNextTurn.setEnabled(true);

            if (currentPlayer.getPlayer() instanceof CPUPlayer) {

                //paying rent
                if (currentSquare.isOwnable() && currentSquare.isOwned()) {
                    infoConsole.setText("You paid rent on:\n" + currentSquare.name() +
                            "\nRent cost: " + currentSquare.rent(roll));
                }
                monopoly.handleSquare(currentPlayer.getPlayer(), currentSquare, roll);
                buttonPayRent.setEnabled(false);
                updatePlayerStatusTextArea();


                //buying property
                if (currentSquare.isOwnable() && !currentSquare.isOwned() && currentPlayer.getPlayerMoney() >= currentSquare.cost()) {
                    infoConsole.setText("You bought property:\n" + currentSquare.name() +
                            "\nPurchase cost: " + currentSquare.cost());
                } else {
                    infoConsole.setText("You don't have enough money to buy: \n" + currentSquare.name());
                }
                monopoly.handleSquare(currentPlayer.getPlayer(), currentSquare, roll);
                buttonBuy.setEnabled(false);
                updatePlayerStatusTextArea();


                //buy house
                for (Square sq : playersGUI.get(currentPlayerOrder).getPlayer().properties()) {
                    Property property;
                    if (sq instanceof Property) {
                        property = (Property) sq;

                        if (property.isMonopoly()) {
                            monopoly.buyHouses(playersGUI.get(currentPlayerOrder).getPlayer(), property);
                            System.out.println("House Purchased");
                            infoConsole.setText("Bought House for " + property.getHouseCost());
                        /*
                        Display dots when player buys house on property.
                         */

                        }
                        updatePlayerStatusTextArea();
                    }
                }
            }
        });
        return buttonRunCPU;
    }

    public static void main(String[] args) {
        new MonopolyGUI();
    }
}
