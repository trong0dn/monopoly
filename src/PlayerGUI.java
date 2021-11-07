import javax.swing.*;
import java.awt.*;
import java.util.Collection;

/**
 * GUI for Player in Game
 */
public class PlayerGUI extends JPanel {
    private HumanPlayer player;
    private final int playerNumber;
    static int totalPlayers = 0;
    private int currentSquareNumber = 0;

    // Potential X positions on the board for the player
    private final int[] xLocationsOfPlayer = {550, 495, 445, 395, 345, 295, 245, 195, 145, 95, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 95, 145, 195, 245, 295, 345, 395, 445, 495, 550,
            550, 550, 550, 550, 550, 550, 550, 550, 550};

    // Potential Y positions on the board for the player
    private final int[] yLocationsOfPlayer = {620, 620, 620, 620, 620, 620, 620, 620, 620, 620, 620,
            525, 475, 425, 375, 325, 275, 225, 175, 125,
            20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20,
            125, 175, 225, 275, 325, 375, 425, 475, 525};

    /*
    Each X Position has a Y Position, (550, 620)
     */

    /**
     * Display Player, Color + Name
     * @param color, color
     * @param playerName , String
     */
    public PlayerGUI(Color color, String playerName) {
        player = new HumanPlayer(playerName);
        totalPlayers++;
        this.playerNumber = totalPlayers;
        this.setBackground(color);
        JLabel labelPlayerNumber = new JLabel(String.valueOf(playerNumber));
        labelPlayerNumber.setFont(new Font("Lucida Grande", Font.BOLD, 15));
        labelPlayerNumber.setForeground(Color.WHITE);
        this.add(labelPlayerNumber);
        this.setBounds((15+this.playerNumber*5+xLocationsOfPlayer[0]), yLocationsOfPlayer[0], 18, 28);
    }

    /**
     * Move function
     * @param diceValue, int
     */
    public void move(int diceValue) {
        GameBoard gameBoard = new GameBoard();
        int size = gameBoard.size()-1;

        if (currentSquareNumber + diceValue >= size) {
            this.exchangeMoney(200);
        }
        int targetSquare = (currentSquareNumber + diceValue) % size;
        currentSquareNumber = targetSquare;

        this.setLocation((15+this.playerNumber*5+xLocationsOfPlayer[targetSquare]), yLocationsOfPlayer[targetSquare]);
    }

    public Collection<Square> getProperties() {
        return this.player.properties();
    }

    public int getPlayerMoney() {
        return this.player.getMoney();
    }

    public Player getPlayer() { return this.player; }

    public void exchangeMoney(int money) {
        this.player.exchangeMoney(money);
    }

    public int getCurrentSquareNumber() {
        return this.currentSquareNumber;
    }
}
