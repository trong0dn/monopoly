import java.util.Scanner;

public class Input {
    Scanner sc = new Scanner(System.in);
    String userInput;
    int numInput;

    /**
     * Initialize Input.
     */
    public Input() {
        this.userInput = "";
        this.numInput = 2;
    }

    /**
     * Get the action of the player through user input
     *
     */
    public void playerTurn(){
        System.out.println("Type a command (None, Buy House, Buy Property, Sell Property, Sell House, Funds, Pay Rent: ");
        userInput = sc.nextLine();
        switch(userInput) {
            case "None":
                break;
            case "Buy House":
                break;
            case "Buy Property":
                break;
            case"Sell Property":
                break;
            case "Sell House":
                break;
            case "Funds":
                break;
            case "Pay Rent":
                break;
            default:
                System.out.println("Not an action. Try again");
        }
    }

    /**
     * Get the number of players through user input.
     *
     * @return int
     */
    public int numOfPlayers() {
        System.out.println("How many players? You can choose from 2 to 8 players: ");
        numInput = sc.nextInt();
        while(numInput < 2 || numInput > 8){
            System.out.println("You need a number of 2 to 8 player. Try again.");
            numInput = sc.nextInt();
        }
        return numInput;
    }

}
