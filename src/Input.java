import java.util.Scanner;

/**
 * This class defines all the methods to handle user inputs.
 */
public class Input {
    private final Scanner scanner;

    public Input() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Handle String inputs.
     * @return  String
     */
    public String inputString() {
        return this.scanner.nextLine();
    }

    /**
     * Handle boolean inputs.
     * @return  boolean
     */
    public boolean inputBool() {
        return inputDecision(new String[] {"Yes", "No"}) == 0;
    }

    /**
     * Handle decision between two choices.
     * @param choices   String[]
     * @return          int
     */
    public int inputDecision(String[] choices) {
        while (true) {
            String input = inputString();
            for (int i = 0; i < choices.length; i++) {
                if (input.equalsIgnoreCase(choices[i]) || input.equalsIgnoreCase(choices[i].substring(0, 1))) {
                    return i;
                }
            }
            System.out.println("Please enter a valid choice.");
        }
    }

    /**
     * Handle integer inputs.
     * @return  int
     */
    public int inputInt() {
        while (true) {
            int value;
            try {
                value = Integer.parseInt(inputString());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
                continue;
            }
            return value;
        }
    }

    /**
     * Handle player name input upon game initialization.
     * @param players   Iterable<Player>
     * @param notPlayable   Player
     * @return  Player
     */
    public Player inputPlayer(Iterable<Player> players, Player notPlayable) {
        Player player = null;
        do {
            String playerName = inputString();
            for (Player p : players) {
                if (playerName.equals(p.name())) {
                    player = p;
                }
            }
            if (player == null) {
                System.out.println("Invalid player. Please enter another name.");
            } else if (notPlayable != null && player.name().equals(notPlayable.name())) {
                System.out.println("Not allowed to choose this player. Select another.");
                player = null;
            }
        } while (player == null);
        return player;
    }
}
