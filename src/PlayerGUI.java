import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PlayerGUI extends JPanel {

    private int playerNumber;
    JLabel labelPlayerNumber;
    static int totalPlayers = 0;
    static HashMap<Integer, Integer> ledger= new HashMap<>();

    private int currentSquareNumber = 0;
    private ArrayList<Integer> titleDeeds = new ArrayList<>(); // squares that the player has
    private int wallet = 3200; // initial money

    public ArrayList<Integer> getTitleDeeds() {
        return titleDeeds;
    }

    public int getWallet() {
        return wallet;
    }

    public void withdrawFromWallet(int withdrawAmount) {
        if(withdrawAmount > wallet) {
            setVisible(false);
            System.out.println("Player "+ playerNumber + " went bankrupt!");
        } else {
            wallet -= withdrawAmount;
        }
    }

    public void depositToWallet(int depositAmount) {
        wallet += depositAmount;
        System.out.println("Payday for player "+getPlayerNumber()+". You earned $200!");
    }

    public int getCurrentSquareNumber() {
        return currentSquareNumber;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public boolean hasTitleDeed(int squareNumber) {
        return titleDeeds.contains(squareNumber);
    }

    public void buyTitleDeed(int squareNumber) {
        if(ledger.containsKey(squareNumber)) {
            System.out.println("It's already bought by someone. You cannot by here.");
        } else {
            titleDeeds.add(this.getCurrentSquareNumber());
            ledger.put(squareNumber, this.getPlayerNumber()); // everytime a player buys a title deed, it is written in ledger, for example square 1 belongs to player 2

        }
    }

    public PlayerGUI(int playerNumber, Color color) {
        this.playerNumber = playerNumber;
        this.setBackground(color);
        labelPlayerNumber = new JLabel(""+playerNumber);
        labelPlayerNumber.setFont(new Font("Lucida Grande", Font.BOLD, 15));
        labelPlayerNumber.setForeground(Color.WHITE);
        this.add(labelPlayerNumber);
        this.setBounds((this.playerNumber*20+xLocationsOfPlayer[0]), yLocationsOfPlayer[0], 18, 28);
        totalPlayers++;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    int[] xLocationsOfPlayer = {550, 495, 445, 395, 345, 295, 245, 195, 145, 95, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 95, 145, 195, 245, 295, 345, 395, 445, 495, 550,
            550, 550, 550, 550, 550, 550, 550, 550, 550};

    int[] yLocationsOfPlayer = {620, 620, 620, 620, 620, 620, 620, 620, 620, 620, 620,
            525, 475, 425, 375, 325, 275, 225, 175, 125,
            20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20,
            125, 175, 225, 275, 325, 375, 425, 475, 525};

    public void move(int diceValue) {
        GameBoard gameBoard = new GameBoard();
        int size = gameBoard.size()-1;

        if (currentSquareNumber + diceValue > size) {
            // Pass go, collect 200.
        }
        int targetSquare = (currentSquareNumber + diceValue) % size;
        currentSquareNumber = targetSquare;

        this.setLocation((this.playerNumber*20+xLocationsOfPlayer[targetSquare]), yLocationsOfPlayer[targetSquare]);
    }
}
