public class Monopoly {

    public static void main(String[] args) {
        RollDice rollDice = new RollDice();
        System.out.println(rollDice.rollDice().value);
        System.out.println(rollDice.rollDice().isDouble);
    }
}
