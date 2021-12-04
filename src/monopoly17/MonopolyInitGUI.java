package monopoly17;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.LinkedList;

import static monopoly17.Monopoly.MAX_PLAYERS;
import static monopoly17.Monopoly.MIN_PLAYERS;

public class MonopolyInitGUI extends JFrame {
    private MonopolyGUI monopolyGUI = new MonopolyGUI();
    private Monopoly monopoly = new Monopoly();

    private JButton startButton;
    private JButton usVersionButton;
    private JButton ukVersionButton;
    private JButton playButton;
    private JButton addPlayer;
    private JButton addCPUPlayer;
    private JTextField playerNameInput;
    private JPanel playerNameList;
    private JPanel titleBackground;
    private JPanel messagePanel;
    private Font playerFont;

    private JPanel playerInitPanel;                                     // Panel for making the players
    private JPanel startPanel;                                          // Panel for the main starting page
    private JPanel versionsPanel;
    private JPanel monopolyPanel;                                       // Panel for the actual Monopoly game
    private final JPanel switchPanels = new JPanel(new CardLayout());   // Used for switching between panels

    private LinkedList<Player> playersList;                             // The list of players

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

    public MonopolyInitGUI() {
        playersList = new LinkedList<>();

        initFrame();
        initPanelComponents();
        setupSwitchPanel();

        displayGUI();
    }

    /**
     * Initialize the frame and it's panels.
     */
    private void initFrame() {
        this.setTitle("MONOPOLY");
        playerInitPanel = new JPanel(new GridBagLayout());
        startPanel = new JPanel(new GridBagLayout());
        versionsPanel = new JPanel(new GridBagLayout());
        monopolyPanel = new JPanel();

        this.setBounds(100, 100, 450, 300);
        this.setSize(1080,740);

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
     * Get the starting panel.
     * @return  JPanel
     */
    public JPanel getStartPanel() {
        return startPanel;
    }

    /**
     * Get the player initialization panel.
     * @return JPanel
     */
    private JPanel getPlayerInitPanel() {
        return playerInitPanel;
    }

    /**
     * Get the list of players.
     * @return  JPanel
     */
    private LinkedList<Player> getPlayersList() {
        return playersList;
    }

    /**
     * Get the monopoly panel.
     * @return  JPanel
     */
    public JPanel getMonopolyPanel() {
        return monopolyPanel;
    }

    public JPanel getSwitchPanels() {
        return switchPanels;
    }

    /**
     * Initialize the components in the panels.
     */
    private void initPanelComponents() {
        startButton = new JButton("Start Game");
        usVersionButton = new JButton("US Version");
        ukVersionButton = new JButton("UK Version");
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
        switchPanels.add(versionsPanel, "VersionsPanel");
        switchPanels.add(playerInitPanel, "PlayerInitializePanel");
        switchPanels.add(monopolyPanel, "MonopolyPanel");

        this.add(switchPanels);
    }

    /**
     * Set up the panels.
     */
    private void setupPanels() {
        startPanel.setSize(new Dimension(250, 250));
        startPanel.setBackground(new Color(50, 255, 155));
        startPanel.setBorder(new LineBorder(Color.WHITE, 10, true));

        versionsPanel.setSize(new Dimension(250, 250));
        versionsPanel.setBackground(new Color(50, 255, 155));
        versionsPanel.setBorder(new LineBorder(Color.WHITE, 10, true));

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
        usVersionButton.setPreferredSize(new Dimension(175, 50));
        ukVersionButton.setPreferredSize(new Dimension(175, 50));
        playButton.setEnabled(false);

        startButton.addActionListener(this::startButton);
        usVersionButton.addActionListener(this::usVersionButton);
        ukVersionButton.addActionListener(this::ukVersionButton);
        addPlayer.addActionListener(this::addPlayerAction);
        addCPUPlayer.addActionListener(this::addPlayerAction);
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

        GridBagConstraints gbagConstraintUsVersion = new GridBagConstraints();
        gbagConstraintUsVersion.gridx = 1;
        gbagConstraintUsVersion.gridy = 0;
        gbagConstraintUsVersion.gridwidth = 2;
        gbagConstraintUsVersion.insets = new Insets(20, 0, 10, 0);

        GridBagConstraints gbagConstraintUkVersion = new GridBagConstraints();
        gbagConstraintUkVersion.gridx = 1;
        gbagConstraintUkVersion.gridy = 1;
        gbagConstraintUkVersion.gridwidth = 2;
        gbagConstraintUkVersion.insets = new Insets(10, 0, 20, 0);

        // Add the buttons, panels and labels to the frame
        startPanel.add(titleBackground, gbagConstraintsTitle);
        startPanel.add(startButton, gbagConstraintsStartButton);

        versionsPanel.add(usVersionButton, gbagConstraintUsVersion);
        versionsPanel.add(ukVersionButton, gbagConstraintUkVersion);

        playerInitPanel.add(playerNameList, gbagConstraintsPlayerNameList);
        playerInitPanel.add(playerNameInput, gbagConstraintsPlayerNameInput);
        playerInitPanel.add(addPlayer, gbagConstraintsAddPlayerButton);
        playerInitPanel.add(addCPUPlayer, gbagConstraintsAddCPUPlayerButton);
        playerInitPanel.add(playButton, gbagConstraintsPlayButton);
        playerInitPanel.add(messagePanel, gbagConstraintsMessage);
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
        cl.show(switchPanels, "VersionsPanel");
    }

    /**
     * Makes Monopoly use the US version.
     * @param actionEvent ActionEvent
     */
    private void usVersionButton(ActionEvent actionEvent) {
        CardLayout cl = (CardLayout) (switchPanels.getLayout());
        cl.show(switchPanels, "PlayerInitializePanel");
    }

    /**
     * Makes Monopoly use the US version.
     * @param actionEvent ActionEvent
     */
    private void ukVersionButton(ActionEvent actionEvent) {
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

        monopolyGUI.setPlayers(playersList);

        monopolyGUI.setupMonopolyBoard();

        monopolyPanel.add(monopolyGUI);
    }

    /**
     * Create a JButton for adding a new Player.
     * @param actionEvent ActionEvent
     */
    private void addPlayerAction(ActionEvent actionEvent) {
        if (playersList.size() < MAX_PLAYERS && playerNameInput.getText().matches(".*\\w.*")) {
            // Make the panel to get the username
            Player newPlayer;
            if (actionEvent.getActionCommand().equals("Add Player")) {
                newPlayer = new HumanPlayer(playerNameInput.getText());
            } else {
                newPlayer = new CPUPlayer(playerNameInput.getText());
            }
            addNewPlayerPanel(newPlayer);
        } else if (!playerNameInput.getText().matches(".*\\w.*")) { // if the text box is empty/all whitespace
            JOptionPane.showMessageDialog(playerInitPanel, "Type a name in the text box!");
        }
        else {
            JOptionPane.showMessageDialog(playerInitPanel, "You can't have more than 6 players.\nPress Play Game!");
        }
    }

    /**
     * Export the Saved game file.
     * @param actionEvent   ActionEvent
     */
    public void saveGame(ActionEvent actionEvent) {
        monopoly.setCurrentPlayerOrder(monopolyGUI.getCurrentPlayerOrder());
        monopoly.setCurrentSquareNumber(monopolyGUI.getCurrentSquareNumber());
        monopoly.exportGame(monopoly);
        System.out.println(monopolyGUI.getCurrentPlayerOrder());
        System.out.println(monopolyGUI.getCurrentSquareNumber());

        JOptionPane.showMessageDialog(null, "Game has been saved");
    }

    /**
     * Load the current game play state.
     * @param actionEvent   ActionEvent
     */
    public void loadGame(ActionEvent actionEvent) {
        setGame(monopoly.importGame());
    }

    /**
     * Set the current game play state.
     * @param newMonopoly Monopoly
     */
    public void setGame(Monopoly newMonopoly) {
        CardLayout cl = (CardLayout) (switchPanels.getLayout());
        cl.show(switchPanels, "MonopolyPanel");

        monopolyGUI.setMonopoly(newMonopoly);
        monopolyGUI.setPlayersGUI(monopoly.getPlayerGUI());
        monopolyGUI.setPlayersList(monopoly.getPlayers());
        monopolyGUI.setCurrentPlayerOrder(monopoly.getCurrentPlayerOrder());
        monopolyGUI.setCurrentSquareNumber(monopoly.getCurrentSquareNumber());

        monopolyGUI.setupBoard();
        monopolyGUI.setupDice();
        monopolyGUI.setPlayerTokens();
        monopolyGUI.setupPlayerStatusWindow();
        monopolyGUI.setupConsoleLog();
        monopolyGUI.setupMonopolyButtons();

        CardLayout cardLayout = (CardLayout) monopolyGUI.getPlayerAssetsPanel().getLayout();
        cardLayout.show(monopolyGUI.getPlayerAssetsPanel(), String.valueOf(monopoly.getCurrentPlayerOrder()));

        JOptionPane.showMessageDialog(null, "Game has been loaded");
    }

    /**
     * Creates a new game.
     * @param actionEvent   ActionEvent
     */
    void newGame(ActionEvent actionEvent) {
        CardLayout cl = (CardLayout) (switchPanels.getLayout());
        cl.show(switchPanels, "StartPanel");
        this.monopoly = new Monopoly();

        monopolyGUI.emptyPlayersGUI();

        while (!playersList.isEmpty()) {
            playersList.removeFirst();
        }

        monopolyGUI.emptyPlayersList();
        monopolyGUI.setCurrentPlayerOrder(0);
        monopolyGUI.setCurrentSquareNumber(0);
        monopolyGUI.setIsDouble(false);
        monopolyGUI.setDoubles(0);

        initPanelComponents();
        setupSwitchPanel();
        setupPanels();
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
        new MonopolyInitGUI();
    }
}