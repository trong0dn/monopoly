import monopoly17.HumanPlayer;
import monopoly17.Property;
import monopoly17.Square;
import org.junit.Assert;

/**
 * Testing HumanPlayer class.
 * @author Ibrahim Almalki
 * Modified by Trong Nguyen
 */
public class HumanPlayerTest {
    private final HumanPlayer humanPlayer = new HumanPlayer("Tester");
    private int position;
    private int rent = 2;
    private int oneHouse = 10;
    private int twoHouse = 30;
    private int threeHouse = 90;
    private int fourHouse = 160;
    private int hotel = 250;
    private int propertyCost = 60;
    private int houses = 50;
    private final Square oldKent = new Property(position, "OLD KENT ROAD",
                            rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);

    /**
     * Test the player name is returned correctly.
     */
    @org.junit.Test
    public void testName() {
        Assert.assertEquals("Tester", humanPlayer.name());
    }


    /**
     * Test moving the player a specified number of tiles.
     */
    @org.junit.Test
    public void testMove() {
        position = 0;
        humanPlayer.setMoney(1500);
        humanPlayer.move(5);
        Assert.assertEquals(5, humanPlayer.getPosition() );

        // Player passed go, collect $200 - do one lap around the board
        humanPlayer.move(43);
        Assert.assertEquals(8, humanPlayer.getPosition());
        Assert.assertEquals(1700, humanPlayer.getMoney());

        // Player did not pass go, do not collect $200
        humanPlayer.setMoney(1500);
        humanPlayer.move(4);
        Assert.assertEquals(12, humanPlayer.getPosition());
        Assert.assertEquals(1500, humanPlayer.getMoney());
    }

    /**
     * Test moving the player to a specified tile.
     */
    @org.junit.Test
    public void testMoveTo() {
        position = 0;
        humanPlayer.setMoney(1500);
        humanPlayer.moveTo(5);
        Assert.assertEquals(5, humanPlayer.getPosition() );

        // Player passed go, collected $200
        humanPlayer.moveTo(2);
        Assert.assertEquals(2, humanPlayer.getPosition());
        Assert.assertEquals(1700, humanPlayer.getMoney());

        // Player did not pass go, do not collect $200
        humanPlayer.setMoney(1500);
        humanPlayer.moveTo(4);
        Assert.assertEquals(4, humanPlayer.getPosition());
        Assert.assertEquals(1500, humanPlayer.getMoney());
    }

    /**
     * Test exchanging money for paying rent, purchasing properties.
     */
    @org.junit.Test
    public void testExchangeMoney() {
        humanPlayer.setMoney(1500);
        humanPlayer.exchangeMoney(100);
        Assert.assertEquals(1600, humanPlayer.getMoney());

        humanPlayer.setMoney(1500);
        humanPlayer.exchangeMoney(0);
        Assert.assertEquals(1500, humanPlayer.getMoney());

        // Remove money (pay rent)
        humanPlayer.setMoney(1500);
        humanPlayer.exchangeMoney(-100);
        Assert.assertEquals(1400, humanPlayer.getMoney());
    }

    /**
     * Test adding properties.
     */
    @org.junit.Test
    public void testAddProperty() {
        // Add a property
        humanPlayer.addProperty(oldKent);
        Assert.assertEquals(oldKent, humanPlayer.properties().toArray()[0]);

        // Add a property that is not ownable
        humanPlayer.removeProperty(oldKent);
        oldKent.isOwned();
        humanPlayer.addProperty(oldKent);
        Assert.assertEquals(oldKent, humanPlayer.properties().toArray()[0]);

        // Add a property that is ownable
        humanPlayer.removeProperty(oldKent);
        oldKent.isOwnable();
        humanPlayer.addProperty(oldKent);
        Assert.assertEquals(oldKent, humanPlayer.properties().toArray()[0]);
    }

    /**
     * Test removing properties.
     */
    @org.junit.Test
    public void testRemoveProperty() {
        // Remove a property
        humanPlayer.setMoney(1500);
        humanPlayer.addProperty(oldKent);
        humanPlayer.exchangeMoney(-60);
        humanPlayer.removeProperty(oldKent);
        Assert.assertEquals(0,  humanPlayer.properties().toArray().length);

        // Testing that play should get the money back when selling property
        humanPlayer.setMoney(1500);
        humanPlayer.addProperty(oldKent);
        humanPlayer.exchangeMoney(-60);
        humanPlayer.removeProperty(oldKent);
        Assert.assertEquals(1500,  humanPlayer.getMoney());
    }
}