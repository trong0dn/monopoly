import org.junit.Test;

import static org.junit.Assert.*;

public class MonopolyTest {
    private Monopoly monopoly = new Monopoly();
    private Monopoly.GameState gameState;
    private final Player humanPlayer = new HumanPlayer("tester");
    private final HumanPlayer humanPlayer2 = new HumanPlayer("tester2");
    private String playerName;
    private int money;
    private int position;
    int rent = 2;
    int oneHouse = 10;
    int twoHouse = 30;
    int threeHouse = 90;
    int fourHouse = 160;
    int hotel = 250;
    int propertyCost = 60;
    int houses = 50;
    private final Square oldkent = new Property(position, "OLD KENT ROAD",
            rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);


    /**
     * tests the buyProprty method.
     *
     */
    @Test
    public void buyProperty() {
        monopoly.buyProperty(humanPlayer, oldkent);
        assertEquals(oldkent, humanPlayer.properties().toArray()[0]);

        oldkent.isOwned();
        monopoly.buyProperty(humanPlayer, oldkent);
        assertEquals(oldkent, humanPlayer.properties().toArray()[0]);

    }



}