/*
Change test
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * GameBoard Class
 */
public class GameBoard {
    private HashMap<Integer, Property> propertyLayout;
    private ArrayList<String> players;

    public GameBoard() {
        this.propertyLayout = new HashMap<>();
        this.players = new ArrayList<>();
    }

    /**
     * Get the position of the player
     *
     * @param player Player
     * @return int
     */
    public int getPosition(Player player){
        return player.getPosition();
    }

    /**
     * Create a new property to be added to propertyLayout
     *
     * @param name String
     * @param rent int
     * @param price int
     */
    private void createProperty(String name, int rent, int price, int position){
        Property newProperty = new Property(name, rent, price, false);
        propertyLayout.put(position, newProperty);
    }

    /**
     * Get the number of players
     *
     * @return Integer
     */
    public Integer getNumPlayers() {
        return players.size();
    }

    /**
     * Remove a player that is bankrupt
     *
     * @param playerCharacter String
     */
    public void removePlayers(String playerCharacter) {
        players.remove(playerCharacter);
    }

    /**
     * Roll a random number from 1 to 12
     *
     * @return int
     */
    public int rollDice() {
        return new Random().nextInt(12) + 1;
    }

    /**
     * Change the player's position
     *
     * @param player Player
     */
    public void playerMovement(Player player) {
    int newPosition = (player.getPosition() + rollDice()) % 22;

        player.setPosition(newPosition);
    }

    /**
     * The text-based playable version of Monopoly. You can buy and sell properties, pay rent and become bankrupt
     */
    public void play(){

    }

    public static void main(String[] args) {
        GameBoard game = new GameBoard();

        game.play();
    }
}
