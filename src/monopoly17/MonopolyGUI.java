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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;

import static monopoly17.GameBoard.BOARD_SIZE;
import static monopoly17.Monopoly.MAX_PLAYERS;
import static monopoly17.Monopoly.MIN_PLAYERS;

/**
 * This class represents the Monopoly GUI.
 * @author Trong Nguyen, Francisco De Grano, Ibrahim Almalki, & Elisha Catherasoo
 */
public class MonopolyGUI extends JFrame {
    public static final int MONOPOLY_IDX = 0;
    public static final int PLAYER_GUI_IDX = 1;
    public static final int PLAYER_LIST_IDX = 2;

    private Monopoly monopoly;
    private ArrayList<PlayerGUI> playersGUI;
    private LinkedList<Player> playersList;                             // The list of players
    private int currentPlayerOrder;
    private int currentSquareNumber;
    private Boolean isDouble = false;
    private int doubles = 0;

    private GameBoardGUI gameBoardGUI;
    private DiceGUI die1;
    private DiceGUI die2;

    private JPanel playerInitPanel;                                     // Panel for making the players
    private JPanel startPanel;                                          // Panel for the main starting page
    private JPanel monopolyPanel;                                       // Panel for the actual Monopoly game
    private final JPanel switchPanels = new JPanel(new CardLayout());   // Used for switching between panels
    private JButton startButton;
    private JButton playButton;
    private JButton addPlayer;
    private JButton addCPUPlayer;
    private JTextField playerNameInput;
    private JPanel playerNameList;
    private JPanel titleBackground;
    private JPanel messagePanel;
    private Font playerFont;

    // Visuals and Buttons for monopolyPanel
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
        this.monopoly = new Monopoly();
        this.playersGUI = new ArrayList<>();
        this.playersList = monopoly.getPlayers();
        this.monopoly.play();        // Determines the winners and losers
        displayGUI();
    }

    /**
     * Initialize the frame and it's panels.
     */
    private void initFrame() {
        this.setTitle("MONOPOLY");
        playerInitPanel = new JPanel(new GridBagLayout());
        startPanel = new JPanel(new GridBagLayout());
        monopolyPanel = new JPanel();

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem saveMenuItem = new JMenuItem("Save Game");
        JMenuItem loadMenuItem = new JMenuItem("Load Game");
        JMenuItem newMenuItem = new JMenuItem("New Game");

        saveMenuItem.addActionListener(this::saveGame);
        loadMenuItem.addActionListener(this::loadGame);
        newMenuItem.addActionListener(this::newGame);

        menu.add(saveMenuItem);
        menu.add(loadMenuItem);
        menu.add(newMenuItem);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);
    }

    /**
     * Export the Saved game file.
     * @param actionEvent   ActionEvent
     */
    public void saveGame(ActionEvent actionEvent) {
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(MONOPOLY_IDX, monopoly);
        arrayList.add(PLAYER_GUI_IDX, playersGUI);
        arrayList.add(PLAYER_LIST_IDX, playersList);

        monopoly.exportGame(arrayList);

        JOptionPane.showMessageDialog(null, "Game has been saved");
    }

    /**
     * Load the current game play state.
     * @param actionEvent   ActionEvent
     */
    public void loadGame(ActionEvent actionEvent) {
        setGame(Objects.requireNonNull(monopoly.importGame()));
    }

    /**
     * Set the current game play state.
     * @param arrayList     ArrayList<Object>
     */
    @SuppressWarnings("unchecked")
    public void setGame(ArrayList<Object> arrayList) {
        CardLayout cl = (CardLayout) (switchPanels.getLayout());
        cl.show(switchPanels, "MonopolyPanel");
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.set(i, arrayList.get(i));
        }
        this.monopoly = (Monopoly) arrayList.get(MONOPOLY_IDX);
        this.playersGUI = (ArrayList<PlayerGUI>) arrayList.get(PLAYER_GUI_IDX);
        this.playersList = (LinkedList<Player>) arrayList.get(PLAYER_LIST_IDX);

        setupBoard();
        setupDice();
        setPlayerTokens();
        setupPlayerStatusWindow();
        setupConsoleLog();
        setupMonopolyButtons();
    }

    /**
     * Update player token to loaded position.
     */
    private void setPlayerTokens() {
        PlayerGUI playerGUI = null;
        for (PlayerGUI gui : playersGUI) {
            playerGUI = gui;
            playerGUI.moveTo(gui.getCurrentSquareNumber());
            leftLayeredPane.add(playerGUI, Integer.valueOf(1));
        }
        assert playerGUI != null;
        playerGUI.resetTotalPlayers();
    }

    /**
     * Creates a new game.
     * @param actionEvent   ActionEvent
     */
    private void newGame(ActionEvent actionEvent) {
        CardLayout cl = (CardLayout) (switchPanels.getLayout());
        cl.show(switchPanels, "StartPanel");
        this.monopoly = new Monopoly();
        this.playersGUI = new ArrayList<>();
        this.playersList = new LinkedList<>();
        this.currentPlayerOrder = 0;
        this.currentSquareNumber = 0;
        isDouble = false;
        doubles = 0;
        initFrame();
        initPanelComponents();
        setupSwitchPanel();
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
     * Set up the panels in the switch panel.
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

        this.add(switchPanels);
    }

    /**
     * Set up the panels.
     */
    private void setupPanels() {
        this.setBounds(100, 100, 450, 300);
        this.setSize(1080,740);

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

        startButton.addActionListener(this::startButton);
        addPlayer.addActionListener(this::addPlayerButton);
        addCPUPlayer.addActionListener(this::addCPUPlayerButton);
        playButton.addActionListener(this::playButton);
    }

    /**
     * Set up the layouts used.
     */
    private void setupLayouts() {
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
        startPanel.add(startButton, gbagConstraintsStartButton);

        playerInitPanel.add(playerNameList, gbagConstraintsPlayerNameList);
        playerInitPanel.add(playerNameInput, gbagConstraintsPlayerNameInput);
        playerInitPanel.add(addPlayer, gbagConstraintsAddPlayerButton);
        playerInitPanel.add(addCPUPlayer, gbagConstraintsAddCPUPlayerButton);
        playerInitPanel.add(playButton, gbagConstraintsPlayButton);
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
        gameBoardGUI = new GameBoardGUI(5,5,670,670);
        gameBoardGUI.setBackground(new Color(50, 255, 155));
        leftLayeredPane.add(gameBoardGUI, Integer.valueOf(0));
    }

    /**
     * Set up the dice positions.
     */
    private void setupDice() {
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
        buttonRollDice = new JButton("Roll Dice");
        buttonRollDice.addActionListener(this::buttonRollDice);
        buttonRollDice.setBounds(80, 420, 250, 40);
        rightLayeredPane.add(buttonRollDice);

        // Add buy button
        buttonBuy = new JButton("Buy Property");
        buttonBuy.addActionListener(this::buttonBuy);
        buttonBuy.setBounds(80, 470, 115, 40);
        buttonBuy.setEnabled(false);
        rightLayeredPane.add(buttonBuy);

        // Add pay rent button
        buttonPayRent = new JButton("Pay Rent");
        buttonPayRent.addActionListener(this::buttonPayRent);
        buttonPayRent.setBounds(215, 470, 115, 40);
        buttonPayRent.setEnabled(false);
        rightLayeredPane.add(buttonPayRent);

        // Add buyHouse Button
        buttonBuyHouse = new JButton("Buy House");
        buttonBuyHouse.addActionListener(this::buttonBuyHouse);
        buttonBuyHouse.setBounds(80, 520, 115, 40);
        buttonBuyHouse.setEnabled(false);
        rightLayeredPane.add(buttonBuyHouse);

        // Add RunCPU Button
        buttonRunCPU = new JButton("CPU's Turn");
        buttonRunCPU.addActionListener(this::buttonRunCPU);
        buttonRunCPU.setBounds(80, 570, 115, 40);
        buttonRunCPU.setEnabled(false);
        rightLayeredPane.add(buttonRunCPU);

        // Add next turn button
        buttonNextTurn = new JButton("Next Turn");
        buttonNextTurn.addActionListener(this::buttonNextTurn);
        buttonNextTurn.setBounds(215, 570, 115, 40);
        buttonNextTurn.setEnabled(false);
        rightLayeredPane.add(buttonNextTurn);

        // Add pay bail button
        buttonPayBail = new JButton("Pay Bail");
        buttonPayBail.addActionListener(this::buttonPayBail);
        buttonPayBail.setBounds(215, 520, 115, 40);
        buttonPayBail.setEnabled(false);
        rightLayeredPane.add(buttonPayBail);
    }

    /**
     * Create a JButton for adding a new Human Player.
     * @param actionEvent ActionEvent
     */
    private void addPlayerButton(ActionEvent actionEvent) {
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
    }

    /**
     * Create a JButton for adding a new CPU Player.
     * @param actionEvent ActionEvent
     */
    private void addCPUPlayerButton(ActionEvent actionEvent) {
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
    }

    /**
     * Creates a panel to display the players added to the game.
     * @param newPlayer     Player
     */
    private void addNewPlayerPanel(Player newPlayer) {
        playersList.add(newPlayer);
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
        playerNameInput.setText("");
    }

    /**
     * This will change to the player initialization panel.
     * @param actionEvent ActionEvent
     */
    private void startButton(ActionEvent actionEvent) {
        CardLayout cl = (CardLayout) (switchPanels.getLayout());
        cl.show(switchPanels, "PlayerInitializePanel");
    }

    /**
     * Play the game after making all the players.
     * @param actionEvent ActionEvent
     */
    private void playButton(ActionEvent actionEvent) {
        CardLayout cl = (CardLayout) (switchPanels.getLayout());
        cl.show(switchPanels, "MonopolyPanel");

        setupBoard();
        setupDice();
        setupPlayerToken();
        setupPlayerStatusWindow();
        setupConsoleLog();
        setupMonopolyButtons();
    }

    /**
     * Give each player their own color.
     */
    private void setupPlayerToken() {
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
    private void setupConsoleLog() {
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
     * @param actionEvent ActionEvent
     */
    private void buttonRollDice(ActionEvent actionEvent) {
        rollDiceLogic();
    }

    /**
     * Gives the turn to the next player.
     * @param actionEvent ActionEvent
     */
    private void buttonNextTurn(ActionEvent actionEvent) {
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
    private void buttonBuy(ActionEvent actionEvent) {
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
    private void buttonPayRent(ActionEvent actionEvent) {
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
    private void buttonBuyHouse(ActionEvent actionEvent) {
        JPanel panel = new JPanel(new GridLayout(0, 4));
        for (Square sq : playersGUI.get(currentPlayerOrder).getPlayer().properties()) {
            Property property;
            if (sq instanceof Property) {
                property = (Property) sq;
                if (property.isMonopoly()) {
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
    }

    /**
     * Creates a button to perform all activities related to the CPU player.
     * @param actionEvent ActionEvent
     */
    private void buttonRunCPU(ActionEvent actionEvent) {
        rollDiceLogic();
        handleCPUSquare();
        handleCPUButtons();
    }

    /**
     * Handles the button logic during CPU Player's turn.
     */
    private void handleCPUButtons() {
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
    private void buttonPayBail(ActionEvent actionEvent) {
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
        Square currentSquare = this.gameBoardGUI.getSquare(SquareInfo.SQUARE_10.getPosition()); // If in Jail

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
        if (currentSquareNumber < SquareInfo.SQUARE_12.getPosition() && prevSquare < SquareInfo.SQUARE_0.getPosition()) {
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
                buttonPayRent.setEnabled(true);
                buttonRollDice.setEnabled(false);
                buttonNextTurn.setEnabled(false);
                buttonBuy.setEnabled(false);
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
     * Handles button logic for GUI when player interacts with Jail.
     */
    private void handleButtonsGoToJail() {
        buttonRunCPU.setEnabled(false);
        buttonRollDice.setEnabled(false);
        buttonBuyHouse.setEnabled(false);
        buttonPayRent.setEnabled(false);
        buttonBuy.setEnabled(false);
        buttonPayBail.setEnabled(false);
        buttonNextTurn.setEnabled(true);
    }

    /**
     * Handles GUI when a player interacts with Jail square.
     * @param currentPlayer     PlayerGUI
     * @param currentSquare     Square
     * @param diceValue         int
     */
    private void handleJail(PlayerGUI currentPlayer, Square currentSquare, int diceValue) {
        if(((Jail) currentSquare).getType() == Jail.JailType.GOTO_JAIL) {
            handleButtonsGoToJail();
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

    /**
     * Display the game.
     */
    private void displayGUI() {
        // Frame does not close immediately when trying to quit
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                if (JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?")
                        == JOptionPane.OK_OPTION) {
                    setVisible(false);
                    dispose();
                }
            }
        });
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MonopolyGUI();
    }
}
