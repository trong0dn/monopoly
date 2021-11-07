import org.junit.Test;

import static org.junit.Assert.*;

public class MonopolyTest {
    private Monopoly monopoly;
    private Monopoly.GameState gameState;
    private final HumanPlayer humanPlayer = new HumanPlayer("tester");
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



    @Test
    public void handleSquare() {
       monopoly.handleSquare(humanPlayer, oldkent, 3);


    }

    @Test
    public void buyProperty() {
        monopoly.buyProperty(humanPlayer, oldkent);
        assertEquals(true, humanPlayer.properties().contains(oldkent));

    }

    @Test
    public void owned() {
        monopoly.buyProperty(humanPlayer2, oldkent);
        monopoly.owned(humanPlayer, oldkent, 3);
        assertEquals( 1498, humanPlayer.getMoney() );

    }

    @Test
    public void unowned() {
        monopoly.unowned(humanPlayer, oldkent);
        assertEquals( 1440, humanPlayer.getMoney() );

    }

}