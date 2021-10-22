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

    /**
     * Initializes game starting conditions
     * gets the number of players participating
     * gets the name of the players
     * prints them out at the end
     */
    public void initialize(Input input) {
        System.out.print("How many players would like to play?");
        int numPlayers = input.inputInt();
        while (numPlayers < 2 || numPlayers > 8) {
            System.out.println("Try Again! You must have a min of 2 and max of 8 players: ");
            numPlayers = input.inputInt();
        }

        for (int i = 0; i< numPlayers; i++) {
            System.out.print("Player #" + i + ": Enter your character name: ");
            String playerName = input.inputString();
            HumanPlayer newPlayer = new HumanPlayer(playerName);
            gameState.players.add(newPlayer);

        }
        // Print all the player names
        System.out.println("\n There are: " + numPlayers + " players, with the usernames: ");
        for (Player p: gameState.players) {
            System.out.println(p.name());
        }
    }

    public void turn() {
        // Roll Dice
        // Move
        // handleSquare
        // addition
    }


    /**
     * prints the  players name, current balance and their properties owned
     */
    public void printState() {
        Player player = gameState.currentPlayer;

        System.out.println("Player name: " + player.name());
        System.out.println("Current balance: $" + player.getMoney());
        System.out.println("Current position: " + player.getPosition()); //not sure if this will work
        System.out.println("Properties owned: ");

        for (Square s: player.properties()){
            System.out.println(s.name());
        }
        //add info about houses, jail, etc later

    }

    /**
     * @param player the player
     * @param square the square (property)
     * @param roll the roll (will use later)
     */
    public void handleSquare(Player player, Square square, int roll) {
        boolean owned = square.isOwned();
        boolean ownable = square.isOwnable();

        //empty purchasable square
        if (!owned && ownable) {
            player.addProperty(square);
        }

        else if (owned) {
            payRent(square);
            square.owner().exchangeMoney(square.rent(0));
        }

        else if (square instanceof Taxes) {
            //payTax
        }

        else if (square instanceof  Jail) {
            // jail
        }
        //might need to add cases for utilities and railroads?
    }

    private void buyProperty(Player player, Square square) {
        if (player == null || square == null) return;
        if (!square.isOwnable()) return;
        player.addProperty(square);
        square.purchase(player);
    }

//    public void payRent(Square square){
//        Property property = (Property) square;
//        if (pla- property.rent(0) < 0){
//            System.out.println("cannot pay rent on this property, current balance: $" + money + ". Property rent: $" + property.rent(0));
//        }
//        else{
//            money -= property.rent(0);
//        }
//    }


    public static void main(String[] args) {
        Monopoly monopoly = new Monopoly();
        monopoly.play();
    }
}