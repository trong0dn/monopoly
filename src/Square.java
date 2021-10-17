/**
 * Interface for the square tiles on a monopoly board.
 */
public interface Square {
    int position();
    String name();
    boolean isOwnable();
    boolean isOwned();
    int cost();
    void purchase(Player player);
    int rent(int val);
    Player owner();
    String toString();
}
