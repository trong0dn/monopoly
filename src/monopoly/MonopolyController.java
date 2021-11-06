package monopoly;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

/**
 * This class represents all the controllers used in the monopoly.Monopoly GUI
 */
public class MonopolyController {
    private JFrame frame; // used for testing buttons
    private JPanel playerInitPanel; // used to test buttons
    private JPanel startPanel; // Panel for the main starting page
    private JPanel monopolyPanel; // Panel for the actual monopoly.Monopoly game
    private JPanel switchPanels; // Used for switching the panels
    private Monopoly.GameState gameState;
    private JLabel playerName;
    private int numOfPlayers;
    private LinkedList<String> playerList;



    /**
     * Initialize monopoly.MonopolyController
     */
    public MonopolyController(){
        this.frame = new JFrame(); // used for testing buttons
        this.playerInitPanel = new JPanel();
        this.startPanel = new JPanel();
        this.monopolyPanel = new JPanel();
        this.switchPanels = new JPanel(new CardLayout());
        this.playerName = new JLabel();
        this.numOfPlayers = 2;
        this.playerList = new LinkedList<>();
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
        JButton button = new JButton("Start Game");

        button.addActionListener(e -> {
            CardLayout cl = (CardLayout) (switchPanels.getLayout());
            cl.show(switchPanels, "PlayerInitializePanel");
        });
        return button;

    }

    /**
     * this creates the players when a new game has begun
     * asks for number of players
     * asks for name
     * displays image icons for players to select from.
     * return void
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
     * Buy the property the player has landed on.
     * @return button
     */
    public JButton buyButton() {
        JButton button = new JButton("Buy");

        button.addActionListener(e -> {
            // TODO get the position of the current player and buy the property
            //monopoly.Square[] square = gameState.gameBoard.getBoard();
            //buyProperty(gameState.currentPlayer, square[gameState.currentPlayer.getPosition()]);
        });
        return button;
    }

    /**
     * Roll the dice when button is pressed
     * @return button
     */
    public JButton rollButton(){
        JButton button = new JButton("Roll monopoly.Dice");

        button.addActionListener(e -> {
            // TODO Roll dice and move player to the right square
            //turn();
        });
        return button;
    }

    /**
     * The monopoly.Monopoly GUI
     */
    public void displayGUI() { // used for testing buttons
        JFrame frame = new JFrame("MONOPOLY");
        JLabel playerInitLabel = new JLabel("This is the panel for creating the players");
        JLabel monopolyLabel = new JLabel("This is the panel for the game");

        getStartPanel().setPreferredSize(new Dimension(250, 250));
        getStartPanel().setBackground(Color.white);

        getPlayerInitPanel().setPreferredSize(new Dimension(250, 250));
        getPlayerInitPanel().setBackground(Color.white);

        // add the buttons and panels to the frame
        getStartPanel().add(startButton());

        getPlayerInitPanel().add(playerInitLabel);
        getPlayerInitPanel().add(playButton());

        getMonopolyPanel().add(monopolyLabel);
        getMonopolyPanel().add(rollButton());

        getSwitchPanels().add(getStartPanel(), "StartPanel");
        getSwitchPanels().add(getPlayerInitPanel(), "PlayerInitializePanel");
        getSwitchPanels().add(getMonopolyPanel(), "MonopolyPanel");

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

        frame.pack();
        frame.setVisible(true);
    }



    public static void main(String[] args) {
        MonopolyController mc = new MonopolyController();
        mc.makePlayers();
        mc.displayGUI();
    }
}
