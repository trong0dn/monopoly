import java.util.*;
// Francisco Comment
public class Monopoly {
    private final RollDice rollDice;
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
        Input input = new Input();
        initializePlayers(input);
    }

    /**
     * Different decision states during a player's turn.
     */
    public enum DecisionState {
        NONE, BUY_PROPERTY, SELL_PROPERTY, BUY_HOUSE, SELL_HOUSE, TURN_ACTION
    }

    /**
     * Track the game state.
     */
    public class GameState {
        public DecisionState decisionState;
        public Queue<Player> players;
        public GameBoard gameBoard;
        public Player currentPlayer;
    }

    /**
     * Play loop that requests for user inputs until there is a winner.
     */
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
     * Initializes game starting conditions with players.
     */
    public void initializePlayers(Input input) {
        // Ask user for number of players participating
        System.out.println("How many players would like to play?");
        int numPlayers = input.inputInt();
        while (numPlayers < 2 || numPlayers > 8) {
            System.out.println("Try Again! You must have a min of 2 and max of 8 players: ");
            numPlayers = input.inputInt();
        }
        // Ask user to input name of players
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Player #" + i + ": Enter your character name: ");
            String playerName = input.inputString();
            HumanPlayer newPlayer = new HumanPlayer(playerName);
            gameState.players.add(newPlayer);
        }
        // Print all the player names
        System.out.println("\nThere are: " + numPlayers + " players, with the usernames: ");
        for (Player p: gameState.players) {
            System.out.println(">>> " + p.name());
        }
    }

    public void turn() {
        System.out.println("It's " + gameState.currentPlayer.name() + "'s turn");
        int countRollDoubles = 0;
        while (true) {
            //TODO If player is in jail, they have to try to get out
            Dice.Roll roll = rollDice.rollDice();
            if (roll.isDouble) {
                countRollDoubles++;
                //TODO Check rules on rolling doubles
            }
            //TODO Player can leave jail if they roll doubles
            if (countRollDoubles == 3) {
                //TODO Goto_Jail
                break;
            }
            System.out.print("You rolled a [" + roll.dieValue1 + "][" + roll.dieValue2 + "]");
            if (roll.isDouble) {
                System.out.println(" (double)");
            }
            Square[] square = gameState.gameBoard.getBoard();
            System.out.println(" and landed on " + square[(gameState.currentPlayer.getPosition() + roll.value) % 40].name());
            gameState.currentPlayer.move(roll.value);
            handleSquare(gameState.currentPlayer, square[gameState.currentPlayer.getPosition()], roll.value);
            }
        boolean playerAction = true;
        while (playerAction && !isBankrupt) {
            System.out.println("Would you like to perform any additional actions this turn?");
            System.out.println("Select one of the following options:");
            //TODO Buy/Sell houses
            System.out.println("1) Pass my turn.");
            gameState.decisionState = DecisionState.TURN_ACTION;
            int choice = gameState.currentPlayer.inputInt(gameState);

            //TODO Switch-case for more additional player options
            if (choice == 1) {
                playerAction = false;
            } else {
                System.out.println("Please choose a valid option.");
            }
        }
        System.out.println();
    }

    /**
     * Prints the state of the players name, current balance and their properties owned.
     */
    public void printState() {
        Player player = gameState.currentPlayer;

        System.out.println("Player name: " + player.name());
        System.out.println("Current balance: $" + player.getMoney());
        System.out.println("Current position: " + player.getPosition());
        //TODO Fix this to get square tile name position maybe using toString
        System.out.println("Properties owned: ");

        for (Square s: player.properties()){
            System.out.println(s.name());
        }
        //TODO Additional information about houses, jail, etc., later
    }

    /**
     * Handle how a player interacts with the square tile they land upon.
     * @param player    Player
     * @param square    Square
     * @param roll      int
     */
    public void handleSquare(Player player, Square square, int roll) {
        boolean owned = square.isOwned();
        boolean ownable = square.isOwnable();

        if (!owned && ownable) {
            unowned(player, square);
        }
        else if (owned) {
            owned(player, square, roll);
        }
        //else if (square instanceof Taxes) { }
            //TODO Deal with Tax squares
        //else if (square instanceof  Jail) { }
            //TODO Deal with Jail square
    }

    /**
     * Landing on an unowned square, the player may choose to buy the square.
     * @param player    Player
     * @param square    Square
     */
    private void unowned(Player player, Square square) {
        int cost = square.cost();

        if (player.getMoney() < cost) { //TODO Create method to get total value player of available assets
            System.out.println(" You can not afford to purchase " + square.name());
        }

        boolean noMoney = false;
        System.out.println("Would you like to purchase " + square.name() + " for " + cost + " (Yes/No)?");
        gameState.decisionState = DecisionState.BUY_PROPERTY;
        if (player.getMoney() < cost) {
            noMoney = true;
            System.out.println("You do not have sufficient funds for this transaction");
        }
        if (player.inputBool(gameState)) {
            if (!noMoney) {
                player.exchangeMoney(-1 * cost);
                buyProperty(player, square);
            }
            //TODO else trade assets for money
        }
    }

    /**
     * Add property to player's hand and change owner of the property.
     * @param player    Player
     * @param square    Square
     */
    private void buyProperty(Player player, Square square) {
        if (player == null || square == null) return;
        if (!square.isOwnable()) return;
        player.addProperty(square);
        square.purchase(player);
    }

    /**
     * Landing on an owned square, the player must pay rent.
     * @param player    Player
     * @param square    Square
     * @param roll      int
     */
    private void owned(Player player, Square square, int roll) {
        int rent = square.rent(roll);
        if (square instanceof Utility) {
            // Increase rent depends on roll value
            rent = square.rent(roll);
        }
        else if (square instanceof Railroad) {
            //TODO Check rules on railroad rent
            rent *= 2;
        }
        Player owner = square.owner();
        if (player.name().equals(owner.name())) { return; }

        boolean noMoney = false;
        System.out.println("You have landed on the " + square.name() + " and must pay" + rent + " in rent.");
        if (player.getMoney() < rent) {
            noMoney = true;
            System.out.println("You do not have sufficient funds for this transaction");
        }
        if (!noMoney) {
            player.exchangeMoney(-1 * rent);
            owner.exchangeMoney(rent);
        }
        //TODO else trade assets for money
    }

    public static void main(String[] args) {
        Monopoly monopoly = new Monopoly();
        monopoly.play();
    }
}