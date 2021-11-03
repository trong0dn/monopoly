import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class PlayerGUI extends JPanel {
    private HumanPlayer player;
    private final int playerNumber;
    static int totalPlayers = 0;
    private int currentSquareNumber = 0;

    private final int[] xLocationsOfPlayer = {550, 495, 445, 395, 345, 295, 245, 195, 145, 95, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 95, 145, 195, 245, 295, 345, 395, 445, 495, 550,
            550, 550, 550, 550, 550, 550, 550, 550, 550};

    private final int[] yLocationsOfPlayer = {620, 620, 620, 620, 620, 620, 620, 620, 620, 620, 620,
            525, 475, 425, 375, 325, 275, 225, 175, 125,
            20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20,
            125, 175, 225, 275, 325, 375, 425, 475, 525};

    public PlayerGUI(Color color, String playerName) {
        player = new HumanPlayer(playerName);
        totalPlayers++;
        this.playerNumber = totalPlayers;
        this.setBackground(color);
        JLabel labelPlayerNumber = new JLabel(String.valueOf(playerNumber));
        labelPlayerNumber.setFont(new Font("Lucida Grande", Font.BOLD, 15));
        labelPlayerNumber.setForeground(Color.WHITE);
        this.add(labelPlayerNumber);
        this.setBounds((this.playerNumber*20+xLocationsOfPlayer[0]), yLocationsOfPlayer[0], 18, 28);
    }

    public void move(int diceValue) {
        GameBoard gameBoard = new GameBoard();
        int size = gameBoard.size()-1;

        if (currentSquareNumber + diceValue > size) {
            exchangeMoney(200);
        }
        int targetSquare = (currentSquareNumber + diceValue) % size;
        currentSquareNumber = targetSquare;

        this.setLocation((this.playerNumber*20+xLocationsOfPlayer[targetSquare]), yLocationsOfPlayer[targetSquare]);
    }

    public Collection<Square> getProperties() {
        return this.player.properties();
    }

    public int getMoney() {
        return this.player.getMoney();
    }

    public void exchangeMoney(int money) {
        this.player.exchangeMoney(money);
    }

    public int getCurrentSquareNumber() {
        return this.currentSquareNumber;
    }

    public int getPlayerNumber() {
        return this.playerNumber;
    }
}
