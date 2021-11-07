import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private int numOfPlayers;
    private LinkedList<Player> playerList;
    private JButton startButton;
    private JButton playButton;
    private JMenuBar playerMakeMenu;
    private JMenu addPlayer;
    private JMenuItem addPlayerItem;
    private GridBagConstraints gbagConstraintsTitle;
    private GridBagConstraints gbagConstraintsStartButton;
    private GridBagConstraints gbagConstraintsPlayerName;
    private GridBagConstraints gbagConstraintsPlayButton;

    /**
     * Initialize MonopolyController
     */
    public MonopolyController(){
        this.frame = new JFrame("MONOPOLY"); // used for testing buttons
        this.playerInitPanel = new JPanel(new GridBagLayout());
        this.startPanel = new JPanel(new GridBagLayout());
        this.monopolyPanel = new JPanel();
        this.switchPanels = new JPanel(new CardLayout());
        this.playerList = new LinkedList<>();
        this.startButton = new JButton("Start Game");
        this.playButton = new JButton("Play The Game");
        this.playerMakeMenu = new JMenuBar();
        this.addPlayer = new JMenu("Add Player");
        this.addPlayerItem = new JMenuItem("Add Player");
        this.gbagConstraintsTitle = new GridBagConstraints();
        this.gbagConstraintsStartButton = new GridBagConstraints();
        this.gbagConstraintsPlayerName = new GridBagConstraints();
        this.gbagConstraintsPlayButton = new GridBagConstraints();
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
     * Get the list of players.
     * @return      LinkedList<Player>
     */
    public LinkedList<Player> getPlayerList(){
        return playerList;
    }

    /**
     * Get the menu item that adds a new player.
     * @return      JMenuItem
     */
    public JMenuItem getAddPlayerItem(){
        addPlayerItem.addActionListener(e -> {
            if(playerList.size() < 8) {
                JPanel panel = new JPanel(new GridLayout(1, 2));
                JLabel playerName = new JLabel("Enter Player Name: ");
                JTextField playerNameInput = new JTextField();
                panel.add(playerName);
                panel.add(playerNameInput);
                JOptionPane.showMessageDialog(playerInitPanel, panel);
                Player newPlayer = new HumanPlayer(playerNameInput.getText());
                playerList.add(newPlayer);

                gbagConstraintsPlayerName.gridx = 1;
                gbagConstraintsPlayerName.gridy = playerList.indexOf(newPlayer);
                gbagConstraintsPlayerName.insets = new Insets(0, 0, 30, 0);

                Font font = new Font("Lucida Grande", Font.BOLD, 60);
                JLabel title = new JLabel("MONOPOLY!");
                title.setFont(font);

                // Add the new player to the player panel
                Font playerFont = new Font("Lucida Grande", Font.PLAIN, 30);
                JLabel newPlayerLabel = new JLabel("Player " + (playerList.indexOf(newPlayer) + 1) + ": " + newPlayer.name());
                newPlayerLabel.setFont(playerFont);
                newPlayerLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                playerInitPanel.add(newPlayerLabel, gbagConstraintsPlayerName);

                if (playerList.size() >= 2 && playerList.size() <= 8) {
                    playButton.setEnabled(true);
                }
            } else {
                JOptionPane.showMessageDialog(playerInitPanel, "You can't have more than 8 players.\nPress Play Game!");
            }
        });

        return addPlayerItem;
    }

    /**
     * This will change to the player initialization panel
     * @return button
     */
    public JButton startButton(){
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
     * this creates the players when a new game has begun
     * asks for number of players
     * asks for name
     * displays image icons for players to select from.
     */
    public void makePlayers(){
        //get number of players
        numOfPlayers = Integer.parseInt(JOptionPane.showInputDialog("Number of players: "));
        //get player names
        for (int i = 0; i<numOfPlayers; i++){
            String name = JOptionPane.showInputDialog("Player " + i + "'s Name: ");
            //playerList.add(name);
        }
    }

    /**
     * Play the game after making all the players.
     * @return button
     */
    public JButton playButton(){
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
        JLabel title = new JLabel("MONOPOLY!");
        title.setFont(font);
        title.setOpaque(true);
        title.setBackground(Color.RED);
        title.setForeground(Color.WHITE);

        // GridBagConstraints for the start panel
        gbagConstraintsTitle.gridx = 1;
        gbagConstraintsTitle.gridy = 1;
        gbagConstraintsTitle.insets = new Insets(0, 0, 20, 0);

        gbagConstraintsStartButton.gridx = 1;
        gbagConstraintsStartButton.gridy = 2;
        gbagConstraintsStartButton.insets = new Insets(40, 0, 0, 0);

        // GridBagConstraints for the play button
        gbagConstraintsPlayButton.gridx = 1;
        gbagConstraintsPlayButton.gridy = 8;
        gbagConstraintsPlayButton.insets = new Insets(60, 0, 0, 0);

        // set the frame, panels and buttons
        frame.setBounds(100, 100, 450, 300);
        frame.setSize(1080,710);

        startPanel.setSize(new Dimension(250, 250));
        startPanel.setBackground(new Color(50, 255, 155));
        startPanel.setBorder(new LineBorder(Color.WHITE, 10, true));

        playerInitPanel.setSize(new Dimension(250, 250));
        playerInitPanel.setBackground(Color.white);

        monopolyPanel.setSize(new Dimension(250, 250));
        monopolyPanel.setBackground(Color.white);

        startButton.setPreferredSize(new Dimension(175, 50));
        playButton.setPreferredSize(new Dimension(175, 50));
        playButton.setEnabled(false);

        // add the buttons, panels and labels to the frame
        startPanel.add(title, gbagConstraintsTitle);
        startPanel.add(startButton(), gbagConstraintsStartButton);

        playerInitPanel.add(playButton(), gbagConstraintsPlayButton);

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

    public static void main(String[] args) {
        MonopolyController mc = new MonopolyController();
        mc.displayGUI();
    }
}
