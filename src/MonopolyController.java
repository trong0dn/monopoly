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
    private JLabel playerName;
    private int numOfPlayers;
    private LinkedList<String> playerList;
    private JButton startButton;

    /**
     * Initialize MonopolyController
     */
    public MonopolyController(){
        this.frame = new JFrame(); // used for testing buttons
        this.playerInitPanel = new JPanel();
        this.startPanel = new JPanel(new GridBagLayout());
        this.monopolyPanel = new JPanel();
        this.switchPanels = new JPanel(new CardLayout());
        this.playerName = new JLabel();
        this.numOfPlayers = 2;
        this.playerList = new LinkedList<>();
        this.startButton = new JButton("Start Game");
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
     * This will change to the player initialization panel
     * @return button
     */
    public JButton startButton(){
        startButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) (switchPanels.getLayout());
            cl.show(switchPanels, "PlayerInitializePanel");
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
            playerList.add(name);
        }
    }

    /**
     * Play the game after making all the players.
     * @return button
     */
    public JButton playButton(){
        JButton button = new JButton("Play The Game");

        button.addActionListener(e -> {
            CardLayout cl = (CardLayout) (switchPanels.getLayout());
            cl.show(switchPanels, "MonopolyPanel");
        });
        return button;

    }

    /**
     * The Monopoly GUI
     */
    public void displayGUI() { // used for testing buttons
        JFrame frame = new JFrame("MONOPOLY");

        // make the main title
        Font font = new Font("Lucida Grande", Font.BOLD, 60);
        JLabel title = new JLabel("MONOPOLY!");
        title.setFont(font);
        title.setOpaque(true);
        title.setBackground(Color.RED);
        title.setForeground(Color.WHITE);

        // GridBagConstraints for the starting page elements
        GridBagConstraints gbagConstraintsTitle = new GridBagConstraints();
        gbagConstraintsTitle.gridx = 1;
        gbagConstraintsTitle.gridy = 1;
        gbagConstraintsTitle.insets = new Insets(0, 0, 20, 0);

        GridBagConstraints gbagConstraintsStartButton = new GridBagConstraints();
        gbagConstraintsStartButton.gridx = 1;
        gbagConstraintsStartButton.gridy = 2;
        gbagConstraintsStartButton.insets = new Insets(40, 0, 0, 0);

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

        // add the buttons, panels and labels to the frame
        startPanel.add(title, gbagConstraintsTitle);
        startPanel.add(startButton(), gbagConstraintsStartButton);

        playerInitPanel.add(playButton());

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
