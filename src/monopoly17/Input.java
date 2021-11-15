package monopoly17;

import java.util.Scanner;

/**
 * This class defines all the methods to handle user inputs.
 * @author Elisha Catherasoo & Trong Nguyen
 */
public class Input {
    private final Scanner scanner;

    /**
     * Constructor for Input.
     */
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
}
