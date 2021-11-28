package monopoly17;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Track the game state.
 * @author Trong Nguyen
 */
public class GameState implements Serializable {
    private static final String FILENAME = "monopoly17.txt";

    public Monopoly.DecisionState decisionState;
    public LinkedList<Player> players;
    public ArrayList<PlayerGUI> playersGUI;
    public GameBoard gameBoard;
    public Player currentPlayer;

    /**
     * Export the AddressBook to TXT file.
     */
    public void saveGame() {
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(FILENAME);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Import the AddressBook contents of a .txt file.
     * @return  AddressBook
     */
    public static Monopoly loadGame() {
        try {
            FileInputStream fileInputStream = new FileInputStream(FILENAME);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (Monopoly) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}