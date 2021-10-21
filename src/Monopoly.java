import java.util.ArrayList;
import java.util.Scanner;

public class Monopoly {
    private final ArrayList<Player> playerList;
    private final boolean success;
    private GameBoard gameBoard;
    private Input userInput;


    public Monopoly(boolean success) {
        this.playerList = new ArrayList<>();
        this.success = success;
        this.gameBoard = new GameBoard();
        this.userInput = new Input();
    }

    /**
     * enum decision state
     */
    public enum DecisionState {
        NONE, BUY_HOUSE, BUY_PROPERTY, SELL_PROPERTY, SELL_HOUSE, FUNDS, PAY_RENT, RECEIVE_RENT
    }


    /**
     * @return the number of player
     * adds new players to a list.
     */
    public int numOfPlayers (){
        /*do {
            System.out.print("How many players would like to play (min 2): ");
            try {
                num = input.nextInt();
                success = true;
            } catch (Exception e) {
                System.out.println("Wrong input type (only integers)");
                success = false;
                input.next();
            }
        }while (!success);*/

        Input userInput = new Input();
        int num = userInput.numOfPlayers();

        Scanner input = new Scanner(System.in);
        /*
        Scanner input = new Scanner(System.in);
        System.out.print("How many players would like to play (min 2, max 8): ");
        int num = input.nextInt();

        while (num < 2 || num > 8) {
            System.out.println("Try Again! You must have a min of 2 and max of 8 players: ");
            num = input.nextInt();
        }

         */
       // System.out.println("there are: " + num + " players");
        String newPlayerName;
        //input.nextLine();
        for (int i =1; i<num+1; i++){
            System.out.print("Player #" + i + ", enter your username: ");
            newPlayerName = input.nextLine();
            HumanPlayer newPlayer = new HumanPlayer(newPlayerName);
            playerList.add(newPlayer);

        }
        System.out.println("\nthere are: " + num+" players, with the usernames: ");
        for (Player p: playerList){
            System.out.println(p.name());
        }

        return num;
    }


    /**
     * @param player Player
     * @param square Square
     */
    public void handleSquare(Player player, Square square){
        //another player owns this, you must pay rent
        if (square.isOwned()){
            player.payRent(square);
            //make player that owns it receive rent
        }
        else if (square.isOwnable()){
            player.buyProperty(square);
        }
    }


    /**
     * pay rent
     * buy houses
     */
    public void handleSquare(){
        //
    }

    public void gamePlay(){
        numOfPlayers();

        for(int i = 0; i < playerList.size(); i++){
            System.out.println(playerList.get(i).name() + "'s Turn");

            // show position of player at the beginning of their turn
            int position = playerList.get(i).getPosition();

            gameBoard.makeSquare(position);
            Square square = gameBoard.square(position);

            //System.out.println(square.name()); // when uncommented it is showing a null pointer exception

            userInput.playerTurn();
        }

    }

    public static void main(String[] args) {
        RollDice rollDice = new RollDice();
        System.out.println(rollDice.rollDice().value);
        System.out.println(rollDice.rollDice().isDouble);

        Monopoly m = new Monopoly(true);

        m.gamePlay();

    }
}
