import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents all the controllers used in the Monopoly GUI
 */
public class MonopolyController {
    private JFrame frame; // used for testing buttons
    private JPanel playerInitPanel; // used to test buttons
    private JPanel startPanel; // Panel for the main starting page
    private JPanel monopolyPanel; // Panel for the actual Monopoly game
    private JPanel switchPanels; // Used for switching the panels
    private Monopoly.GameState gameState;

    /**
     * Initialize MonopolyController
     */
    public MonopolyController(){
        this.frame = new JFrame(); // used for testing buttons
        this.playerInitPanel = new JPanel();
        this.startPanel = new JPanel();
        this.monopolyPanel = new JPanel();
        this.switchPanels = new JPanel(new CardLayout());
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
            //Square[] square = gameState.gameBoard.getBoard();
            //buyProperty(gameState.currentPlayer, square[gameState.currentPlayer.getPosition()]);
        });
        return button;
    }

    /**
     * Roll the dice when button is pressed
     * @return button
     */
    public JButton rollButton(){
        JButton button = new JButton("Roll Dice");

        button.addActionListener(e -> {
            // TODO Roll dice and move player to the right square
            //turn();
        });
        return button;
    }

    /**
     * Add property to player's hand and change owner of the property.
     * @param player    Player
     * @param square    Square
     */
    public void buyProperty(Player player, Square square) {
        if (player == null || square == null) return;
        if (!square.isOwnable()) return;
        player.addProperty(square);
        square.purchase(player);
    }
}