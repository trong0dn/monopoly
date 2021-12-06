package monopoly17;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Track the game state.
 * @author Trong Nguyen
 */
public class GameState implements Serializable {
    public Monopoly.DecisionState decisionState;
    public LinkedList<Player> players;
    public ArrayList<PlayerGUI> playersGUI;
    public GameBoardGUI gameBoardGUI;
    public GameBoard gameBoard;
    public Player currentPlayer;
    public int currentPlayerOrder;
    public int currentSquareNumber;
}