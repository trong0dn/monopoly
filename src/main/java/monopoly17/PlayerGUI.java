package monopoly17;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * This class represents the GUI for player on the game board.
 * @author Trong Nguyen
 */
public class PlayerGUI extends JPanel implements Serializable {
    private final Player player;
    private final int playerNumber;
    private static int totalPlayers = 0;
    private int currentSquareNumber = 0;

    private final int[] xLocationsOfPlayer = {550, 495, 445, 395, 345, 295, 245, 195, 145, 95, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 95, 145, 195, 245, 295, 345, 395, 445, 495, 550,
            550, 550, 550, 550, 550, 550, 550, 550, 550};

    private final int[] yLocationsOfPlayer = {620, 620, 620, 620, 620, 620, 620, 620, 620, 620, 620,
            525, 475, 425, 375, 325, 275, 225, 175, 125,
            20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20,
            125, 175, 225, 275, 325, 375, 425, 475, 525};

    /**
     * Constructor for PlayerGUI
     * @param color         Color, the color for the player
     * @param playerName    String, the player's name
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
     * Reset totalPlayer counter for new game.
     */
    public void resetTotalPlayers() {
        totalPlayers = 0;
    }

    /**
     * Move the player based on their dice roll value.
     * @param diceValue     int, the player's roll value
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

    /**
     * Move the player to a specific position.
     * @param position      int
     */
    public void moveTo(int position) {
        currentSquareNumber = position;

        this.setLocation((15+this.playerNumber*5+xLocationsOfPlayer[position]), yLocationsOfPlayer[position]);
    }

    /**
     * Get the player's properties.
     * @return      Collection<Square>
     */
    public Collection<Square> getProperties() {
        return this.player.properties();
    }

    /**
     * Get the amount of money a player has.
     * @return      int
     */
    public int getPlayerMoney() {
        return this.player.getMoney();
    }

    /**
     * Get the current player.
     * @return      Player
     */
    public Player getPlayer() { return this.player; }

    /**
     * Method for player to collect addition money.
     * @param money     int, the money to be exchanged
     */
    public void exchangeMoney(int money) {
        this.player.exchangeMoney(money);
    }

    /**
     * Get the player's number.
     * @return      int
     */
    public int getCurrentSquareNumber() {
        return this.currentSquareNumber;
    }
}
