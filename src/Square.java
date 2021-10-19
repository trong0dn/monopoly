/**
 * Interface for the square tiles on a monopoly board.
 */
public interface Square {
    int position();
    String name();
    boolean isOwnable();
    boolean isOwned();
    int cost();
    void purchase(HumanPlayer player);
    int rent(int value);
    Player owner();
    String toString();
}
