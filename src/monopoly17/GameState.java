package monopoly17;

import java.util.LinkedList;

/**
 * Track the game state.
 */
public class GameState {
    public Monopoly.DecisionState decisionState;
    public LinkedList<Player> players;
    public GameBoard gameBoard;
    public Player currentPlayer;
}