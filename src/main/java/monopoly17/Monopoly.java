package monopoly17;

import java.io.*;
import java.util.*;

/**
 * This class represents the user interface for the Monopoly board game.
 * @author Trong Nguyen, Francisco De Grano, Ibrahim Almalki, & Elisha Catherasoo
 */
public class Monopoly implements Serializable {
    public static final String FILENAME = "monopoly17.txt";
    private final RollDice rollDice;
    private final GameState gameState;
    private boolean isBankrupt;
    public static int MIN_PLAYERS = 2;
    public static int MAX_PLAYERS = 6;

    /**
     * Constructor for Monopoly.
     */
    public Monopoly() {
        this.rollDice = new RollDice();
        this.isBankrupt = false;
        gameState = new GameState();
        gameState.players = new LinkedList<>();
        gameState.playersGUI = new ArrayList<>();
        gameState.gameBoardGUI = new GameBoardGUI(5,5,670,670);
        gameState.decisionState = DecisionState.NONE;
        gameState.gameBoard = new GameBoard();
        gameState.currentPlayer = null;
        gameState.currentPlayerOrder = 0;
        gameState.currentSquareNumber = 0;
    }

    /**
     * Get the list of Players in the game state.
     * @return  LinkedList<Player>
     */
    public LinkedList<Player> getPlayers() {
        return gameState.players;
    }

    /**
     * Get the playerGUI, tokens on the game board in the game state.
     * @return  ArrayList<PlayerGUI>
     */
    public ArrayList<PlayerGUI> getPlayerGUI() {
        return gameState.playersGUI;
    }

    /**
     * Get the current player order.
     * @return  int
     */
    public int getCurrentPlayerOrder() {
        return gameState.currentPlayerOrder;
    }

    /**
     * Get the current square number.
     * @return  int
     */
    public int getCurrentSquareNumber() {
        return gameState.currentSquareNumber;
    }

    /**
     * Set the current player order.
     * @param i int
     */
    public void setCurrentPlayerOrder(int i) {
        gameState.currentPlayerOrder = i;
    }

    /**
     * Set the current square number.
     * @param i int
     */
    public void setCurrentSquareNumber(int i) {
        gameState.currentSquareNumber = i;
    }

    /**
     * Get the game board view.
     * @return  GameBoardGUI
     */
    public GameBoardGUI getGameBoardGUI() {
        return this.gameState.gameBoardGUI;
    }

    /**
     * Set the game board view.
     * @param gameBoardGUI  GameBoardGUI
     */
    public void setGameBoardGUI(GameBoardGUI gameBoardGUI) {
        this.gameState.gameBoardGUI = gameBoardGUI;
    }

    /**
     * Different decision states during a player's turn.
     */
    public enum DecisionState {
        NONE, BUY_PROPERTY, BUY_HOUSE, TURN_ACTION, TAX, IN_JAIL
    }

    /**
     * Play loop that requests for user inputs until there is a winner.
     */
    public void play() {
        while (gameState.players.size() > 1) {
            try {
                gameState.currentPlayer = gameState.players.remove();
                // turn();
                if (!isBankrupt) {
                    gameState.players.add(gameState.currentPlayer);
                }
                isBankrupt = false;
            } catch (NoSuchElementException e) {
                System.err.println("Game failed to be initialized");
                return;
            }
        }
        try {
            Player winner = gameState.players.remove();
            System.out.println("Monopoly winner: " + winner.name());
        } catch (NoSuchElementException ignored) {
            // Since when the game first start there are no players.
        }
    }

    /**
     * Prints the state of the players name, current balance and their properties owned.
     */
    public void printState() {
        Player player = gameState.currentPlayer;

        System.out.println("-----------------------------------------------");
        System.out.println("Player name: " + player.name());
        System.out.println("Current balance: $" + player.getMoney());
        System.out.println("Current position: " + gameState.gameBoard.square(player.getPosition()).name());
        System.out.println("Properties owned: ");

        for (Square s: player.properties()){
            System.out.println(">>> " + s.name());
        }
        System.out.println("-----------------------------------------------");
    }

    /**
     * Handle how a player interacts with the square tile they land upon.
     * @param player    Player
     * @param square    Square
     * @param roll      int
     * @return          ArrayList<Integer>
     */
    public ArrayList<Integer> handleSquare(Player player, Square square, int roll) {
        boolean owned = square.isOwned();
        boolean ownable = square.isOwnable();
        if (square instanceof  Jail) {
            jailAction(player, (Jail) square);
        }
        if (!owned && ownable) {
            unowned(player, square);
        } else if (owned) {
            owned(player, square, roll);
        } else if (square instanceof Taxes) {
            payTax(player, (Taxes) square, square);
        } else if (square instanceof Railroad) {
            railroadMove(player);
        }

        ArrayList<Integer> positionJailTurns = new ArrayList<>();
        positionJailTurns.add(player.getPosition());
        positionJailTurns.add(player.getJailTurns());

        return positionJailTurns;
    }

    /**
     * Method to allow players to buy house for properties.
     * @param player  Player
     *
     */
    public boolean buyHouses(Player player, Property property){
        // Check if property is a monopoly or a hotel
        if (property.getBuildings() == 5 || !property.isMonopoly()){
            System.out.println("You already have a hotel");
            return false;
        }
        // Check if player has enough money
        if (player.getMoney() < property.getHouseCost()){
            System.out.println("You don't have enough money to buy a house");
            return false;
        }
        // Create case for when the player needs to build house following even rule
        if (!property.evenRule()){
            System.out.println("You have to follow the Even Rule");
            return false;
        }
        property.build();
        player.exchangeMoney(property.getHouseCost() * -1);
        System.out.println("House Purchased $" + property.getHouseCost());
        return true;
    }

    /**
     * Landing on an unowned square, the player may choose to buy the square.
     * @param player    Player
     * @param square    Square
     */
    public void unowned(Player player, Square square) {
        int cost = square.cost();

        if (player.getMoney() < cost) {
            System.out.println("You can not afford to purchase " + square.name());
            return;
        }

        boolean noMoney = false;
        System.out.println("You have purchase " + square.name() + " for $" + cost);
        gameState.decisionState = DecisionState.BUY_PROPERTY;
        if (player.getMoney() < cost) {
            noMoney = true;
            System.out.println("You do not have sufficient funds for this transaction.\n You currently have $"
                    + gameState.currentPlayer.getMoney());
        }
        //if (player.inputBool(gameState)) {
        if (!noMoney) {
            player.exchangeMoney(-1 * cost);
            buyProperty(player, square);
        }
        //}
    }

    /**
     * Add property to player's hand and change owner of the property.
     * @param player    Player
     * @param square    Square
     */
    public void buyProperty(Player player, Square square) {
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
    public void owned(Player player, Square square, int roll) {
        int rent = square.rent(roll);
        if (square instanceof Utility) {
            // Increase rent depends on roll value
            rent = square.rent(roll);
        }
        else if (square instanceof Railroad) {
            // Railroad cost double if owned
            rent *= 2;
        }
        Player owner = square.owner();
        if (player.name().equals(owner.name())) {
            System.out.println("You own " + square.name());
            return;
        }

        boolean noMoney = false;
        System.out.println("You have landed on the " + square.name() + " and must pay $" + rent + " in rent.");
        if (player.getMoney() < rent) {
            noMoney = true;
            System.out.println("You do not have sufficient funds for this transaction");
            isBankrupt = true;
        }
        if (!noMoney) {
            player.exchangeMoney(-1 * rent);
            owner.exchangeMoney(rent);
        }
    }

    /**
     * Handle Tax square via paying the taxes.
     * @param player    Player
     * @param tax       Taxes
     * @param square    Square
     */
    public void payTax(Player player, Taxes tax, Square square) {
        int cost;
        gameState.decisionState = DecisionState.TAX;
        // Income Tax square
        cost = tax.getTax();
        System.out.println("You have landed on " + square.name() + " and must pay " + cost + " in Taxes.");
        if (player.getMoney() < cost) {
            System.out.println("You have insufficient funds.");
        } else {
            player.exchangeMoney(cost * -1);
        }
    }

    /**
     * Method features for moving player using owned railroads.
     * @param player    Player
     */
    public void railroadMove(Player player) {
        int position = player.getPosition();
        // Iterate from current position to the end
        for (int i = position; i < gameState.gameBoard.size(); i++) {
            if (gameState.gameBoard.square(i) instanceof Railroad) {
                if (gameState.gameBoard.square(i).owner().equals(player)) {
                    player.moveTo(i);
                    return;
                }
                return;
            }
        }
        // Iterate from start to current position
        for (int i = 0; i < position; i++) {
            if (gameState.gameBoard.square(i) instanceof Railroad) {
                if (gameState.gameBoard.square(i).owner().equals(player)) {
                    player.moveTo(i);
                    return;
                }
                return;
            }
        }
        throw new RuntimeException("Railroads don't exist on the board");
    }

    /**
     * Gives player an action if they land on GO TO JAIL.
     * @param player    Player
     * @param jail      Jail
     */
    public void jailAction(Player player, Jail jail) {
        Jail.JailType type = jail.getType();
        if (type == Jail.JailType.GOTO_JAIL) {
            System.out.println("You have landed on GO TO JAIL. You are now in Jail.");
            goToJail(player);
        } else if(player.getJailTurns() > 0) {
            inJail(player);
        }
    }

    /**
     * Action when player is currently in jail.
     * @param player    Player
     */
    private void inJail(Player player) {
        if (rollDice.rollDice().isDouble) {
            System.out.println("You have rolled doubles. You are now out of Jail.\nRoll again!\n");
            player.setJailTurns(0);
        } else {
            int MAX_JAIL_TURNS = 3;
            if(player.getJailTurns() == MAX_JAIL_TURNS) {
                System.out.println("You have been in Jail for 3 turns. You are now out of Jail.\n");
                player.setJailTurns(0);
            } else {
                System.out.println("You have not rolled doubles. You are still in Jail.");
                player.addJailTurn();
            }
        }
    }

    /**
     * Action when player lands on Go To Jail
     * @param player    Player
     */
    private void goToJail(Player player) {
        System.out.println("Go to Jail!");
        player.moveTo(Jail.IN_JAIL);
        player.addJailTurn();
    }


    /**
     * Method for leaving jail, if the player is in jail on their turn.
     * @param player    Player
     * @return          int
     */
    public int leaveJail(Player player) {
        System.out.println("You have paid Bail!");
        int JAIL_COST = 50;
        if (player.getMoney() >= JAIL_COST) {
            player.exchangeMoney(JAIL_COST * -1);
        }
        player.setJailTurns(0);

        return player.getJailTurns();

    }

    /**
     * Return whether a player is bankrupt or not.
     * @return      boolean
     */
    public boolean isBankrupt() {
        return isBankrupt;
    }

    /**
     * Export the game state using serialization.
     * @param monopoly Monopoly
     */
    public void exportGame(Monopoly monopoly) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(FILENAME);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(monopoly);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Import the saved contents of the game file.
     * @return Monopoly
     */
    public Monopoly importGame() {
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