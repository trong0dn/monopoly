import static org.junit.Assert.*;

public class HumanPlayerTest {
    private final HumanPlayer humanPlayer = new HumanPlayer("tester");
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
     * tests moving the player
     */
    @org.junit.Test
    public void moveTest() {
        position = 0;
        money =1500;
        humanPlayer.move(5);
        assertEquals(5, humanPlayer.getPosition() );

        //passed go, collect $200
        humanPlayer.moveTo(2);
        assertEquals(2, humanPlayer.getPosition());
        assertEquals(1700, humanPlayer.getMoney());
    }

/*

    @org.junit.Test
    public void addProperty() {
        humanPlayer.addProperty(oldkent);
        assertEquals(oldkent, humanPlayer.properties());
    }


    @org.junit.Test
    public void removeProperty() {
        humanPlayer.addProperty(oldkent);
        humanPlayer.removeProperty(oldkent);
        assertEquals(null, humanPlayer.properties());


    }
*/

}