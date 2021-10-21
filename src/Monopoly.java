import java.util.*;

public class Monopoly {
    private RollDice rollDice;
    private GameState gameState;
    private boolean isBankrupt;

    public Monopoly() {
        this.rollDice = new RollDice();
        this.isBankrupt = false;
        this.gameState = new GameState();
        gameState.players = new LinkedList<>();
        gameState.decisionState = DecisionState.NONE;
        gameState.gameBoard = new GameBoard();
        gameState.currentPlayer = null;
        initialize();
    }

    /**
     * enum decision state
     */
    public enum DecisionState {
        NONE, BUY_HOUSE, BUY_PROPERTY, SELL_PROPERTY, SELL_HOUSE, FUNDS, PAY_RENT, RECEIVE_RENT
    }

    /**
     * Track the game state.
     */
    public class GameState {
        public DecisionState decisionState;
        public Queue<Player> players;
        public GameBoard gameBoard;
        public Player currentPlayer;
        public int counter = 0;
    }

    public void play() {
        while (gameState.players.size() > 1) {
            try {
                gameState.currentPlayer = gameState.players.remove();
                turn();
                if (!isBankrupt) {
                    gameState.players.add(gameState.currentPlayer);
                }
                isBankrupt = false;
            } catch (NoSuchElementException e) {
                System.err.println("Game failed to be initialized");
                return;
            } finally {
                printState();
            }
        }
        Player winner = gameState.players.remove();
        System.out.println("Monopoly winner: " + winner.name());
    }

    public void initialize() {

    public void turn() {
        // Roll Dice
        // Move
        // handleSquare
        // addition
    }


    public void printState() {

    }

    public void handleSquare(Player player, Square square, int roll) {
        boolean owned = square.isOwned();
        boolean ownable = square.isOwnable();

        if (!owned && ownable) {
            //unowned(player, square);
        } else if (square instanceof Taxes) {
            //payTax
        } else if (square instanceof  Jail) {
            // jail
        }
    }

    public static void main(String[] args) {
        Monopoly monopoly = new Monopoly();
        monopoly.play();
    }
}
