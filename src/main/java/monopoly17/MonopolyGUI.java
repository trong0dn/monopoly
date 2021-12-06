/*
Milestone 4

Group 17:
Trong Nguyen 100848232
Francisco De Grano 101147447
Ibrahim Almalki 101142978
Elisha Catherasoo 101148507

Professor: Babak Esfandiari
TA: Michael Vezina

Due: 12/06/2021
 */

package monopoly17;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import static monopoly17.GameBoard.BOARD_SIZE;

/**
 * This class represents the Monopoly GUI.
 * @author Trong Nguyen, Francisco De Grano, Ibrahim Almalki, & Elisha Catherasoo
 */
public class MonopolyGUI extends JPanel {
    private Monopoly monopoly;
    private ArrayList<PlayerGUI> playersGUI;                            // List of player token positions
    private LinkedList<Player> playersList;                             // The list of players
    private boolean isDouble;
    private int currentPlayerOrder;
    private int currentSquareNumber;
    private int doubles = 0;

    // GameBoard panel
    private GameBoardGUI gameBoardGUI;
    private DiceGUI die1;
    private DiceGUI die2;

    // Gameplay panel
    private JPanel playerAssetsPanel;
    private JLayeredPane rightLayeredPane;
    private JLayeredPane leftLayeredPane;
    private JTextArea panelPlayerTextArea;
    private final CardLayout cardLayout = new CardLayout();
    private static JTextArea infoConsole;
    private JButton buttonRollDice;
    private JButton buttonNextTurn;
    private JButton buttonPayRent;
    private JButton buttonBuy;
    private JButton buttonBuyHouse;
    private JButton buttonRunCPU;
    private JButton buttonPayBail;

    public final Color[] playerTokenColors = {
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
        this.monopoly = new Monopoly();
        this.playersGUI = monopoly.getPlayerGUI();
        this.playersList = monopoly.getPlayers();
        this.currentPlayerOrder = monopoly.getCurrentPlayerOrder();
        this.currentSquareNumber = monopoly.getCurrentSquareNumber();
        this.gameBoardGUI = monopoly.getGameBoardGUI();
        this.isDouble = monopoly.isBankrupt();
        this.monopoly.play();                               // Determines the winners and losers
        this.leftLayeredPane = new JLayeredPane();
    }

    /**
     * Set up the monopoly board components.
     */
    public void setupMonopolyBoard() {
        setupBoard();
        setupDice();
        initPlayerToken();
        setupPlayerStatusWindow();
        setupConsoleLog();
        setupMonopolyButtons();
    }

    /**
     * Set the players list.
     * @param players    LinkedList<Player>
     */
    public void setPlayers(LinkedList<Player> players) {
        playersList.addAll(players);
    }

    /**
     * Get the game board GUI.
     * @return  GameBoardGUI
     */
    public GameBoardGUI getGameBoardGUI() {
        return this.gameBoardGUI;
    }

    /**
     * Export the Saved game file.
     */
    public void saveGame() {
        monopoly.setCurrentPlayerOrder(currentPlayerOrder);
        monopoly.setCurrentSquareNumber(currentSquareNumber);
        monopoly.setGameBoardGUI(gameBoardGUI);
        monopoly.exportGame(monopoly);
        JOptionPane.showMessageDialog(null, "Game has been saved");
    }

    /**
     * Set the current game play state.
     * @param newMonopoly   Monopoly
     * @return              MonopolyGUI
     */
    public MonopolyGUI setGame(Monopoly newMonopoly) {
        this.newGame();
        this.monopoly = newMonopoly;
        this.gameBoardGUI = monopoly.getGameBoardGUI();
        this.playersGUI = monopoly.getPlayerGUI();
        this.playersList = monopoly.getPlayers();
        this.currentPlayerOrder = monopoly.getCurrentPlayerOrder();
        this.currentSquareNumber = monopoly.getCurrentSquareNumber();

        setupBoard();
        setupDice();
        setPlayerTokens();
        setupPlayerStatusWindow();
        setupConsoleLog();
        setupMonopolyButtons();

        CardLayout cardLayout = (CardLayout) playerAssetsPanel.getLayout();
        cardLayout.show(playerAssetsPanel, String.valueOf(currentPlayerOrder));

        JOptionPane.showMessageDialog(null, "Game has been loaded");

        return this;
    }

    /**
     * Update player token to loaded position.
     */
    private void setPlayerTokens() {
        PlayerGUI playerGUI;
        for (PlayerGUI gui : playersGUI) {
            playerGUI = gui;
            playerGUI.moveTo(gui.getCurrentSquareNumber());
            leftLayeredPane.add(playerGUI, Integer.valueOf(1));
        }
    }

    /**
     * Creates a new game.
     */
    public void newGame() {
        this.monopoly = new Monopoly();
        this.playersGUI = new ArrayList<>();
        this.playersList = new LinkedList<>();
        this.gameBoardGUI = new GameBoardGUI(5,5,670,670);
        this.currentPlayerOrder = 0;
        this.currentSquareNumber = 0;
        isDouble = false;
        doubles = 0;
    }

    /**
     * Set up the layout for the board.
     */
    public void setupBoard() {
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setLayout(null);
        this.setSize(new Dimension(1080,740));
        this.setBackground(Color.white);

        // Add right panel
        rightLayeredPane = new JLayeredPane();
        rightLayeredPane.setBackground(Color.LIGHT_GRAY);
        rightLayeredPane.setBorder(new LineBorder(new Color(0, 0, 0)));
        rightLayeredPane.setBounds(680, 5, 430, 670);
        rightLayeredPane.setLayout(null);
        this.add(rightLayeredPane);

        //leftLayeredPane = new JLayeredPane();
        leftLayeredPane.setBorder(new LineBorder(new Color(0, 0, 0)));
        leftLayeredPane.setBounds(5, 5, 670, 670);
        this.add(leftLayeredPane);

        // Add game board to left panel
        gameBoardGUI.setBackground(new Color(50, 255, 155));
        leftLayeredPane.add(gameBoardGUI, Integer.valueOf(0));
    }

    /**
     * Set game board GUI.
     * @param gameBoardGUI   GameBoardGUI
     */
    public void setGameBoardGUI(GameBoardGUI gameBoardGUI) {
        this.gameBoardGUI = gameBoardGUI;
    }

    /**
     * Set up the dice positions.
     */
    public void setupDice() {
        die1 = new DiceGUI(350, 450, 40, 40);
        leftLayeredPane.add(die1, Integer.valueOf(1));

        die2 = new DiceGUI(400, 450, 40, 40);
        leftLayeredPane.add(die2, Integer.valueOf(1));
    }

    /**
     * Set up the positions of the buttons.
     */
    public void setupMonopolyButtons() {
        // Add roll dice button
        buttonRollDice = new JButton("Roll Dice");
        buttonRollDice.addActionListener(this::rollDiceAction);
        buttonRollDice.setBounds(80, 420, 250, 40);
        rightLayeredPane.add(buttonRollDice);

        // Add buy button
        buttonBuy = new JButton("Buy Property");
        buttonBuy.addActionListener(this::buyAction);
        buttonBuy.setBounds(80, 470, 115, 40);
        buttonBuy.setEnabled(false);
        rightLayeredPane.add(buttonBuy);

        // Add pay rent button
        buttonPayRent = new JButton("Pay Rent");
        buttonPayRent.addActionListener(this::payRentAction);
        buttonPayRent.setBounds(215, 470, 115, 40);
        buttonPayRent.setEnabled(false);
        rightLayeredPane.add(buttonPayRent);

        // Add buyHouse Button
        buttonBuyHouse = new JButton("Buy House");
        buttonBuyHouse.addActionListener(this::buyHouseAction);
        buttonBuyHouse.setBounds(80, 520, 115, 40);
        buttonBuyHouse.setEnabled(false);
        rightLayeredPane.add(buttonBuyHouse);

        // Add RunCPU Button
        buttonRunCPU = new JButton("CPU's Turn");
        buttonRunCPU.addActionListener(this::runCPUAction);
        buttonRunCPU.setBounds(80, 570, 115, 40);
        buttonRunCPU.setEnabled(false);
        rightLayeredPane.add(buttonRunCPU);

        // Add next turn button
        buttonNextTurn = new JButton("Next Turn");
        buttonNextTurn.addActionListener(this::nextTurnAction);
        buttonNextTurn.setBounds(215, 570, 115, 40);
        buttonNextTurn.setEnabled(false);
        rightLayeredPane.add(buttonNextTurn);

        // Add pay bail button
        buttonPayBail = new JButton("Pay Bail");
        buttonPayBail.addActionListener(this::payBailAction);
        buttonPayBail.setBounds(215, 520, 115, 40);
        buttonPayBail.setEnabled(false);
        rightLayeredPane.add(buttonPayBail);
    }

    /**
     * Give each player their own color.
     */
    public void initPlayerToken() {
        PlayerGUI playerGUI = null;
        for (int i = 0; i < playersList.size(); i++) {

            playerGUI = new PlayerGUI(playerTokenColors[i], playersList.get(i).name());
            playersGUI.add(playerGUI);
            leftLayeredPane.add(playerGUI, Integer.valueOf(1));
        }
        assert playerGUI != null;
        playerGUI.resetTotalPlayers();
    }

    /**
     * Gives the players instructions.
     */
    public void setupConsoleLog() {
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
        infoConsole.setText("Starts the game! \nClick Roll Dice!");
        consolePanel.add(infoConsole);
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
    public void setupPlayerStatusWindow() {
        playerAssetsPanel = new JPanel();
        playerAssetsPanel.setBounds(80, 40, 250, 250);
        playerAssetsPanel.setLayout(cardLayout);

        for (int i = 0; i < playersList.size(); i++) {
            JPanel playerStatusPanel = playerStatusPanel(i+1, playerTokenColors[i]);
            playerAssetsPanel.add(playerStatusPanel, String.valueOf(i));
        }

        panelPlayerTextArea = new JTextArea();
        panelPlayerTextArea.setBounds(90, 70, 230, 210);
        panelPlayerTextArea.setEditable(false);

        rightLayeredPane.add(playerAssetsPanel, String.valueOf(1));
        rightLayeredPane.add(panelPlayerTextArea, String.valueOf(2));

        updatePlayerStatusTextArea();
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
     * @param actionEvent ActionEvent
     */
    private void rollDiceAction(ActionEvent actionEvent) {
        rollDiceLogic();
    }

    /**
     * Gives the turn to the next player.
     * @param actionEvent ActionEvent
     */
    private void nextTurnAction(ActionEvent actionEvent) {
        infoConsole.setText("Next Turn!\n");
        if (isDouble) {
            isDouble = false;
        }
        currentPlayerOrder = (currentPlayerOrder + 1) % playersList.size();
        int currentPlayerIndex = (currentPlayerOrder % playersList.size()) + 1;
        currentPlayerOrder %= playersList.size();
        cardLayout.show(playerAssetsPanel, String.valueOf(currentPlayerOrder));
        infoConsole.append("It's now player "+ playersList.get(currentPlayerIndex - 1).name() +"'s turn!\n");

        handleCPUTurn();
        updatePlayerStatusTextArea();
    }

    /**
     * Handles when it is the CPU Player's next turn.
     */
    private void handleCPUTurn() {
        PlayerGUI currentPlayer = this.playersGUI.get(currentPlayerOrder);
        if (playersList.get(currentPlayerOrder) instanceof CPUPlayer) {
            buttonRunCPU.setEnabled(true);
            buttonRollDice.setEnabled(false);
        } else {
            buttonRunCPU.setEnabled(false);
            buttonRollDice.setEnabled(true);
        }
        buttonNextTurn.setEnabled(false);
        buttonPayRent.setEnabled(false);
        buttonBuy.setEnabled(false);
        buttonBuyHouse.setEnabled(false);

        if (currentPlayer.getPlayer().getJailTurns() > 0) {
            buttonPayBail.setEnabled(true);
        }
        buttonPayBail.setEnabled(false);
    }

    /**
     * Buys the property that the player landed on.
     * @param actionEvent ActionEvent
     */
    private void buyAction(ActionEvent actionEvent) {
        PlayerGUI currentPlayer = this.playersGUI.get(currentPlayerOrder);
        Square currentSquare = this.gameBoardGUI.getSquare(currentSquareNumber);
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
    }

    /**
     * Pays the rent that the player landed on.
     * @param actionEvent ActionEvent
     */
    private void payRentAction(ActionEvent actionEvent) {
        handlePayRent();
        handlePlayerRollDoubles();
        updatePlayerStatusTextArea();
    }

    /**
     * Handle paying rent for a property.
     */
    private void handlePayRent() {
        PlayerGUI currentPlayer = this.playersGUI.get(currentPlayerOrder);
        Square currentSquare = this.gameBoardGUI.getSquare(currentSquareNumber);
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
    }

    /**
     * Handle when a Human Player roll doubles.
     */
    private void handlePlayerRollDoubles() {
        if (isDouble) {
            buttonRollDice.setEnabled(true);
            buttonNextTurn.setEnabled(false);
        } else {
            buttonNextTurn.setEnabled(true);
        }
        buttonBuyHouse.setEnabled(false);
    }

    /**
     * Allows user to buy house when they own a full set of properties.
     * @param actionEvent ActionEvent
     */
    private void buyHouseAction(ActionEvent actionEvent) {
        JPanel panel = new JPanel(new GridLayout(0, 4));
        for (Square sq : playersGUI.get(currentPlayerOrder).getPlayer().properties()) {
            Property property;
            if (sq instanceof Property && ((Property) sq).isMonopoly()) {
                property = (Property) sq;
                JButton propButton = new JButton(property.name());
                // Press the button to get a house for the property selected
                propButton.addActionListener(this::propertyButtonAction);
                panel.add(propButton);
            }
        }
        JOptionPane.showMessageDialog(rightLayeredPane, panel, "Current houses you can purchase", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Allows the user to buy a house for the property they select.
     * @param actionEvent   ActionEvent
     */
    private void propertyButtonAction(ActionEvent actionEvent) {
        String propName = ((JButton)actionEvent.getSource()).getText();
        Property property = playersGUI.get(currentPlayerOrder).getPlayer().getProperty(propName);

        boolean purchased = monopoly.buyHouses(playersGUI.get(currentPlayerOrder).getPlayer(), property);
        if (purchased) {
            infoConsole.setText("Bought House for $" + property.getHouseCost());
            if (property.getBuildings() == 5) {
                infoConsole.append("\nYou have a hotel for " + property.name());
            } else {
                infoConsole.append("\nYou have " + property.getBuildings() + " houses for " + property.name());
            }
        } else {
            infoConsole.setText("You can't afford anymore houses for " + property.name());
        }
        updatePlayerStatusTextArea();
    }

    /**
     * Creates a button to perform all activities related to the CPU player.
     * @param actionEvent ActionEvent
     */
    private void runCPUAction(ActionEvent actionEvent) {
        rollDiceLogic();
        handleCPUSquare();
        handleButtons();
    }

    /**
     * Handles the button logic during CPU Player's turn.
     */
    private void handleButtons() {
        buttonRunCPU.setEnabled(false);
        buttonRollDice.setEnabled(false);
        buttonBuyHouse.setEnabled(false);
        buttonPayRent.setEnabled(false);
        buttonBuy.setEnabled(false);
        buttonPayBail.setEnabled(false);
        buttonNextTurn.setEnabled(true);
    }

    /**
     * Handle square when CPU Player has landed.
     */
    private void handleCPUSquare() {
        int roll = die1.getFaceValue() + die2.getFaceValue();
        if (playersList.get(currentPlayerOrder) instanceof CPUPlayer) {
            PlayerGUI currentPlayer = this.playersGUI.get(currentPlayerOrder);
            Square currentSquare = this.gameBoardGUI.getSquare(currentSquareNumber);
            // CPU paying rent
            if (currentSquare.isOwnable() && currentSquare.isOwned() && currentPlayer.getPlayer() != currentSquare.owner()) {
                infoConsole.setText("You paid rent on:\n" + currentSquare.name() +
                        "\nRent cost: " + currentSquare.rent(roll));
                if (currentSquare instanceof Railroad) {
                    infoConsole.setText("You paid rent on:\n" + currentSquare.name() +
                            "\nRent cost: " + (2*currentSquare.rent(roll)));
                }
            }
            // CPU buying property
            else if (currentSquare.isOwnable() && !currentSquare.isOwned() && currentPlayer.getPlayerMoney() >= currentSquare.cost()) {
                infoConsole.setText("You bought property:\n" + currentSquare.name() +
                        "\nPurchase cost: " + currentSquare.cost());
            } else if (currentPlayer.getPlayerMoney() <= currentSquare.cost()) {
                infoConsole.setText("You don't have enough money to buy: \n" + currentSquare.name());
            }
            monopoly.handleSquare(currentPlayer.getPlayer(), currentSquare, roll);
            updatePlayerStatusTextArea();
            handleCPURollDouble();
        }
    }

    /**
     * Handle cases dealing when CPU Player roll doubles.
     */
    private void handleCPURollDouble() {
        if (isDouble) {
            buttonRunCPU.setEnabled(true);
            buttonNextTurn.setEnabled(false);
        } else {
            buttonNextTurn.setEnabled(true);
        }
    }

    /**
     * Allows the player to bail out of jail.
     * @param actionEvent ActionEvent
     */
    private void payBailAction(ActionEvent actionEvent) {
        PlayerGUI currentPlayer = this.playersGUI.get(currentPlayerOrder);
        currentPlayer.getPlayer().setJailTurns(monopoly.leaveJail(currentPlayer.getPlayer()));

        infoConsole.setText("You paid $50 to get out of jail\n");
        infoConsole.append("You are now out of Jail!");

        handleButtons();
        updatePlayerStatusTextArea();
    }

    /**
     * Logic of the dice rolling without the button creation.
     */
    private void rollDiceLogic() {
        die1.rollDice();
        die2.rollDice();
        isDouble = die1.getFaceValue() == die2.getFaceValue();
        int diceValue = die1.getFaceValue() + die2.getFaceValue();
        PlayerGUI currentPlayer = this.playersGUI.get(currentPlayerOrder);
        Square currentSquare = this.gameBoardGUI.getSquare(Jail.IN_JAIL); // If in Jail

        if (currentPlayer.getPlayer().getJailTurns() == 0) {
            currentSquareNumber = (this.playersGUI.get(currentPlayerOrder).getCurrentSquareNumber() + diceValue) % BOARD_SIZE;
            currentPlayer.move(diceValue);
            currentSquare = this.gameBoardGUI.getSquare(currentSquareNumber);
        }

        // Swing concurrency thread correction for layeredPane flickering
        leftLayeredPane.remove(gameBoardGUI);
        leftLayeredPane.add(gameBoardGUI, Integer.valueOf(0));

        int prevSquare = currentSquareNumber - diceValue;

        infoConsole.setText(""); // Player did not pass go

        // Pass Go
        if (currentSquareNumber < 12 && prevSquare < 0) {
            infoConsole.setText("You passed Go! You get $200!\n");
        }
        handleSquareGUI(currentPlayer, currentSquare, diceValue);
        updatePlayerStatusTextArea();
    }

    /**
     * Handles the GUI display when a player lands on a square.
     * @param currentPlayer     PlayerGUI
     * @param currentSquare     Square
     * @param diceValue         int
     */
    private void handleSquareGUI(PlayerGUI currentPlayer, Square currentSquare, int diceValue) {
        if (currentSquare.isOwnable() && !currentSquare.isOwned()) {
            infoConsole.append("You landed on " + currentSquare.name() +
                    "\nProperty Cost: $" + currentSquare.cost());
            isRollDouble(currentPlayerOrder);
            buttonBuy.setEnabled(true);
            buttonBuyHouse.setEnabled(true);
        } else if (currentSquare.isOwnable()) {
            if (currentSquare.owner().name().equals(currentPlayer.getPlayer().name())) {
                buttonBuy.setEnabled(false);
                buttonPayRent.setEnabled(false);
                buttonBuyHouse.setEnabled(true);
                infoConsole.append("You landed on " + currentSquare.name()
                        + "\nYou already own " + currentSquare.name());
            } else if (currentSquare instanceof Property || currentSquare instanceof Utility) {
                infoConsole.append("Property: You landed on:" + currentSquare.name() +
                        "\nRent: $" + currentSquare.rent(diceValue));
                handleButtonsSpecialSquares();
            } else if (currentSquare instanceof Railroad) {
                infoConsole.append("Station: You landed on " + currentSquare.name() +
                        "\nRent: $" + (2*currentSquare.rent(diceValue)));
                handleButtonsSpecialSquares();
            }
        } else {
            if (currentSquare instanceof Taxes) {
                infoConsole.append("Taxes: You landed on " + currentSquare.name() +
                        "\nTax: $" + ((Taxes) currentSquare).getTax());
                handleButtonsSpecialSquares();
            } else if(currentSquare instanceof Jail) {
                handleJail(currentPlayer, currentSquare, diceValue);
            } else {
                infoConsole.append("Non-purchasable: You landed on: \n" + currentSquare.name());
                isRollDouble(currentPlayerOrder);
                buttonBuy.setEnabled(false);
                buttonPayRent.setEnabled(false);
            }
            buttonBuyHouse.setEnabled(false);
        }
    }

    /**
     * Handles button logic for GUI when player interacts with Railroads, Utilities, or Property.
     */
    private void handleButtonsSpecialSquares() {
        buttonPayRent.setEnabled(true);
        buttonRollDice.setEnabled(false);
        buttonNextTurn.setEnabled(false);
        buttonBuy.setEnabled(false);
        buttonBuyHouse.setEnabled(false);
    }

    /**
     * Handles GUI when a player interacts with Jail square.
     * @param currentPlayer     PlayerGUI
     * @param currentSquare     Square
     * @param diceValue         int
     */
    private void handleJail(PlayerGUI currentPlayer, Square currentSquare, int diceValue) {
        if(((Jail) currentSquare).getType() == Jail.JailType.GOTO_JAIL) {
            handleButtons();
            infoConsole.setText("You have landed on:\n" + currentSquare.name());
            infoConsole.append("\nYou are now in Jail.");
            updateJailTurn(currentPlayer, currentSquare, diceValue);
        } else if(currentPlayer.getPlayer().getJailTurns() > 0) {
            updateJailTurn(currentPlayer, currentSquare, diceValue);
            handleRollJail(currentPlayer);
        } else {
            infoConsole.setText("Non-purchasable: You landed on: \n" + currentSquare.name());
            isRollDouble(currentPlayerOrder);
        }
    }

    /**
     * Update the jail turns for a player.
     * @param currentPlayer     PlayerGUI
     * @param currentSquare     Square
     * @param diceValue         int
     */
    private void updateJailTurn(PlayerGUI currentPlayer, Square currentSquare, int diceValue) {
        ArrayList<Integer> positionAndJailTurns = monopoly.handleSquare(currentPlayer.getPlayer(), currentSquare,
                diceValue);

        int newJailPosition = positionAndJailTurns.get(0);
        int jailTurns = positionAndJailTurns.get(1);

        currentPlayer.moveTo(newJailPosition);
        currentPlayer.getPlayer().setJailTurns(jailTurns);
    }

    /**
     * Handles when a player attempts to roll to get out of jail.
     * @param currentPlayer     PlayerGUI
     */
    private void handleRollJail(PlayerGUI currentPlayer) {
        if(isDouble) {
            buttonRollDice.setEnabled(true);
            buttonNextTurn.setEnabled(false);
            infoConsole.setText("You rolled doubles. You are now out of jail!\nRoll again!");
        } else if (currentPlayer.getPlayer().getJailTurns() == 0) {
            buttonRollDice.setEnabled(false);
            buttonNextTurn.setEnabled(true);
            infoConsole.setText("You did not roll doubles.\n");
            infoConsole.append("You have been in Jail for 3 turns.\nYou are now out of jail\n");
        } else {
            buttonRollDice.setEnabled(false);
            buttonNextTurn.setEnabled(true);
            buttonPayBail.setEnabled(true);
            infoConsole.setText("You did not roll doubles.\nYou are still in Jail\n");
            infoConsole.append("Bail out of Jail for $50 by pressing Pay Bail");
        }
    }
}
