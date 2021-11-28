/*
Milestone 3

Group 17:
Trong Nguyen 100848232
Francisco De Grano 101147447
Ibrahim Almalki 101142978
Elisha Catherasoo 101148507

Professor: Babak Esfandiari
TA: Michael Vezina

Due: 11/22/2021
 */

package monopoly17;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

/**
 * This class represents the Monopoly GUI.
 * @author Trong Nguyen, Francisco De Grano, Ibrahim Almalki, & Elisha Catherasoo
 */
public class MonopolyGUI extends JPanel {
    public static int MIN_PLAYERS = 2;
    public static int MAX_PLAYERS = 6;

    private final Monopoly monopoly;
    private GameBoardGUI gameBoard;
    private int currentPlayerOrder;
    private int currentSquareNumber;
    private final ArrayList<PlayerGUI> playersGUI = new ArrayList<>();
    private DiceGUI die1;
    private DiceGUI die2;
    private Boolean isDouble = false;
    private int doubles = 0;

    private JFrame frame;
    private JPanel playerInitPanel;                                     // Panel for making the players
    private JPanel startPanel;                                          // Panel for the main starting page
    private JPanel monopolyPanel;                                       // Panel for the actual Monopoly game
    private final JPanel switchPanels = new JPanel(new CardLayout());   // Used for switching between panels
    private final LinkedList<Player> playersList;                       // The list of players
    private JButton startButton;
    private JButton playButton;
    private JButton addPlayer;
    private JButton addCPUPlayer;
    private JTextField playerNameInput;
    private JPanel playerNameList;
    private Font playerFont;
    private JPanel titleBackground;
    private JPanel messagePanel;

    // Visuals and Buttons for monopolyPanel
    private JLayeredPane rightLayeredPane;
    private JPanel playerAssetsPanel;
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
        initFrame();
        initPanelComponents();
        setupSwitchPanel();
        monopoly = new Monopoly();
        GameState gameState = new GameState();
        gameState.players = new LinkedList<>();
        this.playersList = gameState.players;
        monopoly.play();        // Determines the winners and losers
    }

    /**
     * Initialize the frame and it's panels.
     */
    private void initFrame() {
        frame = new JFrame("MONOPOLY");
        playerInitPanel = new JPanel(new GridBagLayout());
        startPanel = new JPanel(new GridBagLayout());
        monopolyPanel = new JPanel();
    }

    /**
     * Initialize the components in the panels.
     */
    private void initPanelComponents() {
        startButton = new JButton("Start Game");
        playButton = new JButton("Play Game!");
        addPlayer = new JButton("Add Player");
        addCPUPlayer = new JButton("Add CPU Player");
        playerNameInput = new JTextField("");
        playerNameList = new JPanel(new GridLayout(0,2));
        playerFont = new Font("Lucida Grande", Font.PLAIN, 20);
        titleBackground = new JPanel();
        messagePanel = new JPanel();
    }

    /**
     * Setup the panels in the switch panel.
     */
    private void setupSwitchPanel() {
        Font font = new Font("Lucida Grande", Font.BOLD, 60);

        titleBackground.setPreferredSize(new Dimension(450, 90));
        titleBackground.setBackground(Color.RED);

        setupPanels();
        setupLayouts();

        // Starting page label
        JLabel title = new JLabel("MONOPOLY!");
        title.setFont(font);
        title.setOpaque(true);
        title.setBackground(Color.RED);
        title.setForeground(Color.WHITE);
        titleBackground.add(title);

        // Player initialization label
        JLabel message = new JLabel("Enter Player name in text box then click Add Player (2-6 players)");
        messagePanel.add(message);

        switchPanels.add(startPanel, "StartPanel");
        switchPanels.add(playerInitPanel, "PlayerInitializePanel");
        switchPanels.add(monopolyPanel, "MonopolyPanel");

        frame.add(switchPanels);
    }

    /**
     * Set up the panels.
     */
    private void setupPanels() {
        frame.setBounds(100, 100, 450, 300);
        frame.setSize(1080,710);

        startPanel.setSize(new Dimension(250, 250));
        startPanel.setBackground(new Color(50, 255, 155));
        startPanel.setBorder(new LineBorder(Color.WHITE, 10, true));

        playerInitPanel.setSize(new Dimension(250, 250));
        playerInitPanel.setBackground(new Color(50, 255, 155));
        playerInitPanel.setBorder(new LineBorder(Color.WHITE, 10, true));

        monopolyPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        monopolyPanel.setLayout(null);
        monopolyPanel.setSize(new Dimension(250, 250));
        monopolyPanel.setBackground(Color.white);

        playerNameList.setPreferredSize(new Dimension(400, 240));
        playerNameList.setBackground(Color.RED);

        setupButtons();
    }

    /**
     * Set up the buttons not in the game.
     */
    private void setupButtons() {
        startButton.setPreferredSize(new Dimension(175, 50));
        playerNameInput.setPreferredSize(new Dimension(175, 50));
        addPlayer.setPreferredSize(new Dimension(175, 50));
        addCPUPlayer.setPreferredSize(new Dimension(175, 50));
        playButton.setPreferredSize(new Dimension(175, 50));
        playButton.setEnabled(false);
    }

    /**
     * Set up the layouts used.
     */
    private void setupLayouts() {
        // GridBagConstraints
        GridBagConstraints gbagConstraintsTitle = new GridBagConstraints();
        gbagConstraintsTitle.gridx = 1;
        gbagConstraintsTitle.gridy = 1;
        gbagConstraintsTitle.insets = new Insets(0, 0, 20, 0);

        GridBagConstraints gbagConstraintsStartButton = new GridBagConstraints();
        gbagConstraintsStartButton.gridx = 1;
        gbagConstraintsStartButton.gridy = 2;
        gbagConstraintsStartButton.insets = new Insets(40, 0, 0, 0);

        GridBagConstraints gbagConstraintsPlayerNameList = new GridBagConstraints();
        gbagConstraintsPlayerNameList.gridx = 1;
        gbagConstraintsPlayerNameList.gridy = 1;
        gbagConstraintsPlayerNameList.gridwidth = 2;
        gbagConstraintsPlayerNameList.gridheight = 7;
        gbagConstraintsPlayerNameList.insets = new Insets(0, 0, 10, 0);

        GridBagConstraints gbagConstraintsPlayerNameInput = new GridBagConstraints();
        gbagConstraintsPlayerNameInput.gridx = 1;
        gbagConstraintsPlayerNameInput.gridy = 8;
        gbagConstraintsPlayerNameInput.gridwidth = 2;
        gbagConstraintsPlayerNameInput.insets = new Insets(5, 0, 10, 0);

        GridBagConstraints gbagConstraintsAddPlayerButton = new GridBagConstraints();
        gbagConstraintsAddPlayerButton.gridx = 1;
        gbagConstraintsAddPlayerButton.gridy = 9;
        gbagConstraintsAddPlayerButton.gridwidth = 1;
        gbagConstraintsAddPlayerButton.insets = new Insets(0, 15, 20, 0);

        GridBagConstraints gbagConstraintsAddCPUPlayerButton = new GridBagConstraints();
        gbagConstraintsAddCPUPlayerButton.gridx = 2;
        gbagConstraintsAddCPUPlayerButton.gridy = 9;
        gbagConstraintsAddCPUPlayerButton.gridwidth = 2;
        gbagConstraintsAddCPUPlayerButton.insets = new Insets(0, 0, 20, 0);

        GridBagConstraints gbagConstraintsPlayButton = new GridBagConstraints();
        gbagConstraintsPlayButton.gridx = 1;
        gbagConstraintsPlayButton.gridy = 10;
        gbagConstraintsPlayButton.gridwidth = 2;
        gbagConstraintsPlayButton.insets = new Insets(20, 0, 0, 0);

        GridBagConstraints gbagConstraintsMessage = new GridBagConstraints();
        gbagConstraintsMessage.gridx = 1;
        gbagConstraintsMessage.gridy = 12;
        gbagConstraintsMessage.gridwidth = 2;
        gbagConstraintsMessage.insets = new Insets(40, 0, 0, 0);

        // Add the buttons, panels and labels to the frame
        startPanel.add(titleBackground, gbagConstraintsTitle);
        startPanel.add(startButton(), gbagConstraintsStartButton);

        playerInitPanel.add(playerNameList, gbagConstraintsPlayerNameList);
        playerInitPanel.add(playerNameInput, gbagConstraintsPlayerNameInput);
        playerInitPanel.add(addPlayerButton(), gbagConstraintsAddPlayerButton);
        playerInitPanel.add(addCPUPlayer(), gbagConstraintsAddCPUPlayerButton);
        playerInitPanel.add(playButton(), gbagConstraintsPlayButton);
        playerInitPanel.add(messagePanel, gbagConstraintsMessage);
    }

    /**
     * Set up the layout for the board.
     */
    private void setupBoard() {
        monopolyPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        monopolyPanel.setLayout(null);

        // Add right panel
        rightLayeredPane = new JLayeredPane();
        rightLayeredPane.setBackground(Color.LIGHT_GRAY);
        rightLayeredPane.setBorder(new LineBorder(new Color(0, 0, 0)));
        rightLayeredPane.setBounds(680, 5, 430, 670);
        rightLayeredPane.setLayout(null);
        monopolyPanel.add(rightLayeredPane);

        leftLayeredPane = new JLayeredPane();
        leftLayeredPane.setBorder(new LineBorder(new Color(0, 0, 0)));
        leftLayeredPane.setBounds(5, 5, 670, 670);
        monopolyPanel.add(leftLayeredPane);

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
    private void setupMonopolyButtons() {
        // Add roll dice button
        buttonRollDice = buttonRollDice();
        buttonRollDice.setBounds(80, 420, 250, 40);
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

        // Add buyHouse Button
        buttonBuyHouse = buttonBuyHouse();
        buttonBuyHouse.setBounds(80, 520, 115, 40);
        buttonBuyHouse.setEnabled(false);
        rightLayeredPane.add(buttonBuyHouse);

        // Add RunCPU Button
        buttonRunCPU = buttonRunCPU();
        buttonRunCPU.setBounds(80, 570, 115, 40);
        buttonRunCPU.setEnabled(false);
        rightLayeredPane.add(buttonRunCPU);

        // Add next turn button
        buttonNextTurn = buttonNextTurn();
        buttonNextTurn.setBounds(215, 570, 115, 40);
        buttonNextTurn.setEnabled(false);
        rightLayeredPane.add(buttonNextTurn);

        // Add pay bail button
        buttonPayBail = buttonPayBail();
        buttonPayBail.setBounds(215, 520, 115, 40);
        buttonPayBail.setEnabled(false);
        rightLayeredPane.add(buttonPayBail);
    }

    /**
     * Create a JButton for adding a new Human Player.
     * @return      JButton
     */
    public JButton addPlayerButton() {
        addPlayer.addActionListener(e -> {
            if (playersList.size() < MAX_PLAYERS && playerNameInput.getText().matches(".*\\w.*")) {
                // Make the panel to get the username
                Player newPlayer = new HumanPlayer(playerNameInput.getText());
                addNewPlayerPanel(newPlayer);
            } else if (!playerNameInput.getText().matches(".*\\w.*")) { // if the text box is empty/all whitespace
                JOptionPane.showMessageDialog(playerInitPanel, "Type a name in the text box!");
            }
            else {
                JOptionPane.showMessageDialog(playerInitPanel, "You can't have more than 6 players.\nPress Play Game!");
            }
        });
        return addPlayer;
    }

    /**
     * Create a JButton for adding a new CPU Player.
     * @return      JButton
     */
    public JButton addCPUPlayer() {
        addCPUPlayer.addActionListener(e -> {
            if (playersList.size() < MAX_PLAYERS && playerNameInput.getText().matches(".*\\w.*")) {
                // Make the panel to get the username
                Player newPlayer = new CPUPlayer(playerNameInput.getText());
                addNewPlayerPanel(newPlayer);
            } else if (!playerNameInput.getText().matches(".*\\w.*")) { // if the text box is empty/all whitespace
                JOptionPane.showMessageDialog(playerInitPanel, "Type a name in the text box!");
            }
            else {
                JOptionPane.showMessageDialog(playerInitPanel, "You can't have more than 6 players.\nPress Play Game!");
            }
        });
        return addCPUPlayer;
    }

    /**
     * Creates a panel to display the players added to the game.
     * @param newPlayer     Player
     */
    private void addNewPlayerPanel(Player newPlayer) {
        playersList.add(newPlayer);
        // Add the new player to the player panel
        JLabel playerNumber = new JLabel();
        playerNumber.setFont(playerFont);
        playerNumber.setOpaque(true);
        playerNumber.setBackground(playerTokenColors[playersList.indexOf(newPlayer)]);
        playerNumber.setForeground(Color.WHITE);

        JLabel newPlayerLabel = new JLabel();
        newPlayerLabel.setFont(playerFont);
        newPlayerLabel.setOpaque(true);
        newPlayerLabel.setBackground(playerTokenColors[playersList.indexOf(newPlayer)]);
        newPlayerLabel.setForeground(Color.WHITE);

        playerNumber.setText("Player " + (playersList.indexOf(newPlayer) + 1) + ": ");
        newPlayerLabel.setText(newPlayer.name());

        JPanel tempPanelNumber = new JPanel();
        tempPanelNumber.setPreferredSize(new Dimension(150, 40));
        tempPanelNumber.setBackground(playerTokenColors[playersList.indexOf(newPlayer)]);
        tempPanelNumber.add(playerNumber);

        JPanel tempPanelName = new JPanel();
        tempPanelName.setPreferredSize(new Dimension(250, 40));
        tempPanelName.setBackground(playerTokenColors[playersList.indexOf(newPlayer)]);
        tempPanelName.add(newPlayerLabel);

        // Add player name to panel
        playerNameList.add(tempPanelNumber);
        playerNameList.add(tempPanelName);
        playerNameList.revalidate();
        playerNameList.repaint();

        if (playersList.size() >= MIN_PLAYERS && playersList.size() <= MAX_PLAYERS) {
            playButton.setEnabled(true);
        }
        // Set text box empty after player has been added
        playerNameInput.setText("");
    }

    /**
     * This will change to the player initialization panel.
     * @return JButton
     */
    public JButton startButton() {
        startButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) (switchPanels.getLayout());
            cl.show(switchPanels, "PlayerInitializePanel");
        });
        return startButton;
    }

    /**
     * Play the game after making all the players.
     * @return JButton
     */
    public JButton playButton() {
        playButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) (switchPanels.getLayout());
            cl.show(switchPanels, "MonopolyPanel");

            setupBoard();
            setupDice();
            setupPlayerToken();
            setupPlayerStatusWindow();
            setupConsoleLog();
            setupMonopolyButtons();
        });
        return playButton;
    }

    /**
     * Give each player their own color.
     */
    private void setupPlayerToken() {
        for (int i = 0; i < playersList.size(); i++) {
            PlayerGUI playerGUI = new PlayerGUI(playerTokenColors[i], playersList.get(i).name());
            playersGUI.add(playerGUI);
            leftLayeredPane.add(playerGUI, Integer.valueOf(1));
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
        infoConsole.setText("Player 1 starts the game! \nClick Roll Dice!");
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
     * @return      JButton
     */
    private JButton buttonRollDice() {
        buttonRollDice = new JButton("Roll Dice");
        buttonRollDice.addActionListener(e -> rollDiceLogic());
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

            handleCPUNextTurn();
            updatePlayerStatusTextArea();
        });
        return buttonNextTurn;
    }

    /**
     * Handles when it is the CPU Player's next turn.
     */
    public void handleCPUNextTurn() {
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
            handlePayRent();
            handlePlayerRollDoubles();
            updatePlayerStatusTextArea();
        });
        return buttonPayRent;
    }

    /**
     * Handle paying rent for a property.
     */
    public void handlePayRent() {
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
    }

    /**
     * Handle when a Human Player roll doubles.
     */
    public void handlePlayerRollDoubles() {
        if (isDouble) {
            buttonRollDice.setEnabled(true);
            buttonNextTurn.setEnabled(false);
        } else {
            buttonNextTurn.setEnabled(true);
        }
        buttonBuyHouse.setEnabled(false);
    }

    /**
     * Allows user to buy house when they own a full set of properties
     * @return JButton
     */
    private JButton buttonBuyHouse() {
        buttonBuyHouse = new JButton("Buy House");
        buttonBuyHouse.addActionListener(f-> {
            JPanel panel = new JPanel(new GridLayout(0, 4));
            for (Square sq : playersGUI.get(currentPlayerOrder).getPlayer().properties()) {
                Property property;
                if (sq instanceof Property) {
                    property = (Property) sq;
                    if (property.isMonopoly()) {    // If all property of type is owned
                        JButton propButton = new JButton(property.name());
                        // Press the button to get a house for the property selected
                        propButton.addActionListener(e-> {
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
                        });
                        panel.add(propButton);
                    }
                }
            }
            JOptionPane.showMessageDialog(rightLayeredPane, panel, "Current houses you can purchase", JOptionPane.INFORMATION_MESSAGE);
        });
        return buttonBuyHouse;
    }

    /**
     * Creates a button to perform all activities related to the CPU player - automated.
     * @return  JButton
     */
    private JButton buttonRunCPU() {
        buttonRunCPU = new JButton("CPU's Turn");
        buttonRunCPU.addActionListener(e-> {
            handleCPUButtons();
            rollDiceLogic();
            handleCPUSquare();
        });
        return buttonRunCPU;
    }

    /**
     * Handles the button logic during CPU Player's turn.
     */
    public void handleCPUButtons() {
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
    public void handleCPUSquare() {
        int roll = die1.getFaceValue() + die2.getFaceValue();
        if (playersList.get(currentPlayerOrder) instanceof CPUPlayer) {
            PlayerGUI currentPlayer = this.playersGUI.get(currentPlayerOrder);
            Square currentSquare = this.gameBoard.getSquare(currentSquareNumber);
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

            // CPU rolling doubles
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
     * @return  JButton
     */
    private JButton buttonPayBail() {
        buttonPayBail = new JButton("Pay Bail");
        buttonPayBail.addActionListener(e -> {
            PlayerGUI currentPlayer = this.playersGUI.get(currentPlayerOrder);

            currentPlayer.getPlayer().setJailTurns(monopoly.leaveJail(currentPlayer.getPlayer()));

            infoConsole.setText("You paid $50 to get out of jail\n");
            infoConsole.append("You are now out of Jail!");

            buttonRunCPU.setEnabled(false);
            buttonRollDice.setEnabled(false);
            buttonBuyHouse.setEnabled(false);
            buttonPayRent.setEnabled(false);
            buttonBuy.setEnabled(false);
            buttonPayBail.setEnabled(false);
            buttonNextTurn.setEnabled(true);

            updatePlayerStatusTextArea();
        });

        return buttonPayBail;
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

        Square currentSquare = this.gameBoard.getSquare(10); //If in Jail

        if (currentPlayer.getPlayer().getJailTurns() == 0) {
            currentSquareNumber = (this.playersGUI.get(currentPlayerOrder).getCurrentSquareNumber() + diceValue) % 40;
            currentPlayer.move(diceValue);
            currentSquare = this.gameBoard.getSquare(currentSquareNumber);
        }

        // Swing concurrency thread correction for layeredPane flickering
        leftLayeredPane.remove(gameBoard);
        leftLayeredPane.add(gameBoard, Integer.valueOf(0));

        int prevSquare = currentSquareNumber - diceValue;

        infoConsole.setText(""); // in case player didn't pass go

        // Pass Go
        if (currentSquareNumber < 12 && prevSquare < 0) {
            infoConsole.setText("You passed Go! You get $200!\n");
        }

        // When a player lands on a square
        if (currentSquare.isOwnable() && !currentSquare.isOwned()) {
            infoConsole.append("You landed on " + currentSquare.name() +
                    "\nProperty Cost: $" + currentSquare.cost());
            isRollDouble(currentPlayerOrder);
            buttonBuy.setEnabled(true);
            buttonBuyHouse.setEnabled(true);
            // If square is already owned
        } else if (currentSquare.isOwnable()) {
            // Player lands on their own property
            if (currentSquare.owner().name().equals(currentPlayer.getPlayer().name())) {
                buttonBuy.setEnabled(false);
                buttonPayRent.setEnabled(false);
                buttonBuyHouse.setEnabled(true);
                infoConsole.append("You landed on " + currentSquare.name()
                        + "\nYou already own " + currentSquare.name());
                // Player lands on own property owned by another player
            } else if (currentSquare instanceof Property) {
                infoConsole.append("Property: You landed on:" + currentSquare.name() +
                        "\nRent: $" + currentSquare.rent(diceValue));
                buttonPayRent.setEnabled(true);
                buttonRollDice.setEnabled(false);
                buttonNextTurn.setEnabled(false);
                buttonBuy.setEnabled(false);
                buttonBuyHouse.setEnabled(false);
                // Player lands on owned railroad
            } else if (currentSquare instanceof Railroad) {
                infoConsole.append("Station: You landed on " + currentSquare.name() +
                        "\nRent: $" + (2*currentSquare.rent(diceValue)));
                buttonPayRent.setEnabled(true);
                buttonRollDice.setEnabled(false);
                buttonNextTurn.setEnabled(false);
                buttonBuy.setEnabled(false);
                buttonBuyHouse.setEnabled(false);
                // Player lands on owned utility
            } else if (currentSquare instanceof Utility) {
                infoConsole.append("Utility: You landed on " + currentSquare.name() +
                        "\nRent: $" + currentSquare.rent(diceValue));
                buttonPayRent.setEnabled(true);
                buttonRollDice.setEnabled(false);
                buttonNextTurn.setEnabled(false);
                buttonBuy.setEnabled(false);
                buttonBuyHouse.setEnabled(false);
            }
        } else {
            // Player lands on tax square
            if (currentSquare instanceof Taxes) {
                infoConsole.append("Taxes: You landed on " + currentSquare.name() +
                        "\nTax: $" + ((Taxes) currentSquare).getTax());
                buttonPayRent.setEnabled(true);
                buttonRollDice.setEnabled(false);
                buttonNextTurn.setEnabled(false);
                buttonBuy.setEnabled(false);
            } else if(currentSquare instanceof Jail) {
                if(((Jail) currentSquare).getType() == Jail.JailType.GOTO_JAIL) {
                    buttonRunCPU.setEnabled(false);
                    buttonRollDice.setEnabled(false);
                    buttonBuyHouse.setEnabled(false);
                    buttonPayRent.setEnabled(false);
                    buttonBuy.setEnabled(false);
                    buttonPayBail.setEnabled(false);
                    buttonNextTurn.setEnabled(true);
                    //buttonGoToJail.setEnabled(true);
                    infoConsole.setText("You have landed on:\n" + currentSquare.name());
                    infoConsole.append("\nYou are now in Jail.");

                    ArrayList<Integer> positionAndJailTurns = monopoly.handleSquare(currentPlayer.getPlayer(), currentSquare,
                            diceValue);

                    int newJailPosition = positionAndJailTurns.get(0);
                    int jailTurns = positionAndJailTurns.get(1);

                    currentPlayer.moveTo(newJailPosition);
                    currentPlayer.getPlayer().setJailTurns(jailTurns);

                } else if(currentPlayer.getPlayer().getJailTurns() > 0) {
                    ArrayList<Integer> positionAndJailTurns = monopoly.handleSquare(currentPlayer.getPlayer(), currentSquare,
                            diceValue);

                    int newJailPosition = positionAndJailTurns.get(0);
                    int jailTurns = positionAndJailTurns.get(1);

                    currentPlayer.moveTo(newJailPosition);
                    currentPlayer.getPlayer().setJailTurns(jailTurns);

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
                } else {
                    infoConsole.setText("Non-purchasable: You landed on: \n" + currentSquare.name());
                    isRollDouble(currentPlayerOrder);
                }
            } else {
                // FREE PARKING, CHANCE, COMMUNITY CHEST
                infoConsole.append("Non-purchasable: You landed on: \n" + currentSquare.name());
                isRollDouble(currentPlayerOrder);
                buttonBuy.setEnabled(false);
                buttonPayRent.setEnabled(false);
            }
            buttonBuyHouse.setEnabled(false);
        }
        updatePlayerStatusTextArea();
    }

    /**
     * Display the game.
     */
    public void displayGUI(){
        // Frame does not close immediately when trying to quit
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit?")
                        == JOptionPane.OK_OPTION) {
                    frame.setVisible(false);
                    frame.dispose();
                }
            }
        });
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MonopolyGUI().displayGUI();
    }
}
