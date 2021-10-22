import javax.swing.plaf.IconUIResource;
import java.util.Scanner;

public class Input {
    private final Scanner scanner;

    public Input() {
        this.scanner = new Scanner(System.in);
    }

    public String inputString() {
        return this.scanner.nextLine();
    }

    //Input input = new Input();
    //input.inputBool(new String[]{"Yes", "No"});
    public boolean inputBool(String[] choices) {
        while (true) {
            String input = inputString();
            for (int i = 0; i < choices.length; i++) {
                if (input.equalsIgnoreCase(choices[i]) || input.equalsIgnoreCase(choices[i].substring(0, 1))) {
                    return i == 0;
                }
            }
            System.out.println("Please enter a valid choice.");
        }
    }

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
