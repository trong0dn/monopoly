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
     * initiliazes the start of the games
     * gets the number of players participating
     * gets the name of the players
     * prints them out at the end
     */
    public void initialize() {
        Scanner input = new Scanner(System.in);
        System.out.print("How many players would like to play (min 2, max 8): ");
        int num = input.nextInt();

        while (num < 2 || num > 8) {
            System.out.println("Try Again! You must have a min of 2 and max of 8 players: ");
            num = input.nextInt();
        }
        // System.out.println("there are: " + num + " players");
        String newPlayerName;
        input.nextLine();
        for (int i =1; i<num+1; i++){
            System.out.print("Player #" + i + ", enter your username: ");
            newPlayerName = input.nextLine();
            HumanPlayer newPlayer = new HumanPlayer(newPlayerName);
            gameState.players.add(newPlayer);

        }
        //print all the player names
        System.out.println("\nthere are: " + num+" players, with the usernames: ");
        for (Player p: gameState.players){
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
            player.buyProperty(square);
        }

        else if (owned){
            player.payRent(square);
            square.owner().addMoney(square.getRent());
        }

        else if (square instanceof Taxes) {
            //payTax
        }

        else if (square instanceof  Jail) {
            // jail
        }
        //might need to add cases for utilities and railroads?
    }

    public static void main(String[] args) {
        Monopoly monopoly = new Monopoly();
        monopoly.play();
    }
}
