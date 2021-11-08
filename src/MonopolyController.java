import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

/**
 * This class represents all the controllers used in the Monopoly GUI
 * @author Elisha Catherasoo & Ibrahim Almalki
 */
public class MonopolyController{
    private JFrame frame;
    private JPanel playerInitPanel; // Panel for making the players
    private JPanel startPanel; // Panel for the main starting page
    private JPanel monopolyPanel; // Panel for the actual Monopoly game
    private JPanel switchPanels; // Used for switching the panels
    private LinkedList<Player> playerList;
    private JButton startButton;
    private JButton playButton;
    private JMenuBar playerMakeMenu;
    private JMenu addPlayer;
    private JMenuItem addPlayerItem;
    private GridBagConstraints gbagConstraintsPlayerName;

    /**
     * Initialize MonopolyController
     */
    public MonopolyController() {
        this.frame = new JFrame("MONOPOLY"); // used for testing buttons
        this.playerInitPanel = new JPanel(new GridBagLayout());
        this.startPanel = new JPanel(new GridBagLayout());
        this.monopolyPanel = new JPanel();
        this.switchPanels = new JPanel(new CardLayout());
        this.playerList = new LinkedList<>();
        this.startButton = new JButton("Start Game");
        this.playButton = new JButton("Play Game!");
        this.playerMakeMenu = new JMenuBar();
        this.addPlayer = new JMenu("Add Player");
        this.addPlayerItem = new JMenuItem("Add Player");
        this.gbagConstraintsPlayerName = new GridBagConstraints();
    }

    /**
     * Get the frame.
     * @return JFrame
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Get the player initialization panel.
     * @return JPanel
     */
    public JPanel getPlayerInitPanel() {
        return playerInitPanel;
    }

    /**
     * Get the starting panel.
     * @return JPanel
     */
    public JPanel getStartPanel() {
        return startPanel;
    }

    /**
     *  Get the monopoly panel.
     * @return JPanel
     */
    public JPanel getMonopolyPanel() {
        return monopolyPanel;
    }

    /**
     *  Get the panel that switches between the startPanel, playerInitPanel and monopolyPanel
     * @return JPanel
     */
    public JPanel getSwitchPanels() {
        return switchPanels;
    }

    /**
     * Get the list of players.
     * @return      LinkedList<Player>
     */
    public LinkedList<Player> getPlayerList(){
        return playerList;
    }

    /**
     * Set the frame.
     * @param frame JFrame
     */
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    /**
     * Set the monopoly panel.
     * @param monopolyPanel JPanel
     */
    public void setMonopolyPanel(JPanel monopolyPanel) {
        this.monopolyPanel = monopolyPanel;
    }

    /**
     * Set the player initialization panel.
     * @param playerInit JPanel
     */
    public void setPlayerInitPanel(JPanel playerInit) {
        this.playerInitPanel = playerInit;
    }

    /**
     * Set the starting panel.
     * @param startPanel JPanel
     */
    public void setStartPanel(JPanel startPanel) {
        this.startPanel = startPanel;
    }

    /**
     * Set the switching panel.
     * @param switchPanels JPanel
     */
    public void setSwitchPanels(JPanel switchPanels) {
        this.switchPanels = switchPanels;
    }

    /**
     * Get the menu item that adds a new player.
     * @return      JMenuItem
     */
    public JMenuItem getAddPlayerItem() {
        addPlayerItem.addActionListener(e -> {
            if(playerList.size() < 4) {
                // Make the panel to get the user name
                JPanel panel = new JPanel(new GridLayout(1, 2));
                JLabel playerName = new JLabel("Enter Player Name: ");
                JTextField playerNameInput = new JTextField();
                panel.add(playerName);
                panel.add(playerNameInput);
                JOptionPane.showMessageDialog(playerInitPanel, panel);
                Player newPlayer = new HumanPlayer(playerNameInput.getText());

                playerList.add(newPlayer);

                // Constraints for the player number
                GridBagConstraints gbagConstraintsPlayerNumber = new GridBagConstraints();
                gbagConstraintsPlayerNumber.gridx = 1;
                gbagConstraintsPlayerNumber.gridy = playerList.indexOf(newPlayer);
                gbagConstraintsPlayerNumber.insets = new Insets(0, 0, 20, 0);

                // Constraint for player name
                gbagConstraintsPlayerName.gridx = 2;
                gbagConstraintsPlayerName.gridy = playerList.indexOf(newPlayer);
                gbagConstraintsPlayerName.insets = new Insets(0, 0, 20, 0);

                // Add the new player to the player panel
                Font playerFont = new Font("Lucida Grande", Font.PLAIN, 20);
                JLabel playerNumber = new JLabel("Player " + (playerList.indexOf(newPlayer) + 1 + ": "));
                JLabel newPlayerLabel = new JLabel(newPlayer.name());

                // Set look of player names
                playerNumber.setFont(playerFont);
                playerNumber.setOpaque(true);
                playerNumber.setBackground(Color.RED);
                playerNumber.setForeground(Color.WHITE);

                newPlayerLabel.setFont(playerFont);
                newPlayerLabel.setOpaque(true);
                newPlayerLabel.setBackground(Color.RED);
                newPlayerLabel.setForeground(Color.WHITE);

                JPanel tempPanelNumber = new JPanel();
                tempPanelNumber.setPreferredSize(new Dimension(150, 40));
                tempPanelNumber.setBackground(Color.RED);
                tempPanelNumber.add(playerNumber);

                JPanel tempPanelName = new JPanel();
                tempPanelName.setPreferredSize(new Dimension(250, 40));
                tempPanelName.setBackground(Color.RED);
                tempPanelName.add(newPlayerLabel);

                // Add player name to panel
                playerInitPanel.add(tempPanelNumber, gbagConstraintsPlayerNumber);
                playerInitPanel.add(tempPanelName, gbagConstraintsPlayerName);

                if (playerList.size() >= 2 && playerList.size() <= 4) {
                    playButton.setEnabled(true);
                }
            } else {
                JOptionPane.showMessageDialog(playerInitPanel, "You can't have more than 4 players.\nPress Play Game!");
            }
        });
        return addPlayerItem;
    }

    /**
     * This will change to the player initialization panel
     * @return JButton
     */
    public JButton startButton() {
        startButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) (switchPanels.getLayout());
            cl.show(switchPanels, "PlayerInitializePanel");

            // add a menu bar for adding players
            getAddPlayerItem();
            addPlayer.add(addPlayerItem);
            playerMakeMenu.add(addPlayer);
            frame.setJMenuBar(playerMakeMenu);
        });
        return startButton;
    }

    /**
     * Play the game after making all the players.
     * @return button
     */
    public JButton playButton() {
        playButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) (switchPanels.getLayout());
            cl.show(switchPanels, "MonopolyPanel");

            // remove the menu bar for the game
            frame.setJMenuBar(null);
        });
        return playButton;
    }

    /**
     * The Monopoly GUI
     */
    public void displayGUI() { // used for testing buttons
        // make the main title
        Font font = new Font("Lucida Grande", Font.BOLD, 60);
        JPanel titleBackground = new JPanel();
        titleBackground.setPreferredSize(new Dimension(450, 90));
        titleBackground.setBackground(Color.RED);

        JLabel title = new JLabel("MONOPOLY!");
        title.setFont(font);
        title.setOpaque(true);
        title.setBackground(Color.RED);
        title.setForeground(Color.WHITE);
        titleBackground.add(title);

        // GridBagConstraints for the start panel
        GridBagConstraints gbagConstraintsTitle = new GridBagConstraints();
        gbagConstraintsTitle.gridx = 1;
        gbagConstraintsTitle.gridy = 1;
        gbagConstraintsTitle.insets = new Insets(0, 0, 20, 0);

        GridBagConstraints gbagConstraintsStartButton = new GridBagConstraints();
        gbagConstraintsStartButton.gridx = 1;
        gbagConstraintsStartButton.gridy = 2;
        gbagConstraintsStartButton.insets = new Insets(40, 0, 0, 0);

        // GridBagConstraints for the play button
        GridBagConstraints gbagConstraintsPlayButton = new GridBagConstraints();
        gbagConstraintsPlayButton.gridx = 1;
        gbagConstraintsPlayButton.gridy = 8;
        gbagConstraintsPlayButton.gridwidth = 2;
        gbagConstraintsPlayButton.insets = new Insets(60, 0, 0, 0);

        GridBagConstraints gbagConstraintsMessage = new GridBagConstraints();
        gbagConstraintsMessage.gridx = 1;
        gbagConstraintsMessage.gridy = 12;
        gbagConstraintsMessage.gridwidth = 2;
        gbagConstraintsMessage.insets = new Insets(40, 0, 0, 0);

        // set the frame, panels and buttons
        frame.setBounds(100, 100, 450, 300);
        frame.setSize(1080,710);

        startPanel.setSize(new Dimension(250, 250));
        startPanel.setBackground(new Color(50, 255, 155));
        startPanel.setBorder(new LineBorder(Color.WHITE, 10, true));

        playerInitPanel.setSize(new Dimension(250, 250));
        playerInitPanel.setBackground(new Color(50, 255, 155));
        playerInitPanel.setBorder(new LineBorder(Color.WHITE, 10, true));

        monopolyPanel.setSize(new Dimension(250, 250));
        monopolyPanel.setBackground(Color.white);

        startButton.setPreferredSize(new Dimension(175, 50));
        playButton.setPreferredSize(new Dimension(175, 50));
        playButton.setEnabled(false);

        JPanel messagePanel = new JPanel();
        JLabel message = new JLabel("Click Add Player on top right menu bar (need 2-4 players)");
        messagePanel.add(message);

        // add the buttons, panels and labels to the frame
        startPanel.add(titleBackground, gbagConstraintsTitle);
        startPanel.add(startButton(), gbagConstraintsStartButton);
        playerInitPanel.add(playButton(), gbagConstraintsPlayButton);
        playerInitPanel.add(messagePanel, gbagConstraintsMessage);

        getSwitchPanels().add(startPanel, "StartPanel");
        getSwitchPanels().add(playerInitPanel, "PlayerInitializePanel");
        getSwitchPanels().add(monopolyPanel, "MonopolyPanel");

        frame.add(getSwitchPanels());

        // frame doesn't close immediately when trying to quit
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
}
