import monopoly17.HumanPlayer;
import monopoly17.Property;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testing Property class.
 * @author Ibrahim Almalki
 * Modified by Trong Nguyen
 */
public class PropertyTest {
    private final HumanPlayer humanPlayer = new HumanPlayer("Tester1");
    private final HumanPlayer humanPlayer2 = new HumanPlayer("Tester2");
    private int position1;
    private int rent = 2;
    private int oneHouse = 10;
    private int twoHouse = 30;
    private int threeHouse = 90;
    private int fourHouse = 160;
    private int hotel = 250;
    private int propertyCost = 60;
    private int houses = 50;
    private final Property oldKent = new Property(position1, "OLD KENT ROAD",
            rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);

    int position2;
    private final Property whitechapel = new Property(position2, "WHITECHAPEL ROAD",
                        4, 20, 60, 180, 320, 450, 60, 50);

    /**
     * Test the name of the property is correct.
     */
    @Test
    public void testName() {
        Assert.assertEquals("OLD KENT ROAD", oldKent.name());
        Assert.assertEquals("WHITECHAPEL ROAD", whitechapel.name());
    }

    /**
     * Test the cost of the property is correct.
     */
    @Test
    public void testCost() {
        Assert.assertEquals(60, oldKent.cost());
        Assert.assertEquals(60, whitechapel.cost());
    }

    /**
     * Test the purchase method, which assigns the owner of the property.
     */
    @Test
    public void testPurchase() {
        oldKent.purchase(humanPlayer);
        assertTrue(oldKent.isOwned());
        Assert.assertEquals(humanPlayer, oldKent.owner());

        // whitechapel is not owned
        assertFalse(whitechapel.isOwned());
        // whitechapel does not have an owner
        assertNull(whitechapel.owner());
    }

    /**
     * Test the rent method which assigns the rent to the players.
     */
    @Test
    public void testRent() {
        // Player purchases oldKent, therefore the rent on the property is $2
        humanPlayer.addProperty(oldKent);
        oldKent.purchase(humanPlayer);
        Assert.assertEquals(2, oldKent.rent(3));

        // Property is not owned, so there is no rent on the property
        Assert.assertEquals(4, whitechapel.rent(3));
    }

    /**
     * Tests the updateMonopoly function and checks if properties owned belong in a set.
     */
    @Test
    public void testUpdateMonopoly() {
        // Only one property is owned
        humanPlayer.addProperty(oldKent);
        oldKent.purchase(humanPlayer);
        humanPlayer.addProperty(whitechapel);
        whitechapel.purchase(humanPlayer);
        assertFalse(oldKent.isMonopoly());

        // Both property sets owned
        humanPlayer.addProperty(oldKent);
        oldKent.purchase(humanPlayer);
        oldKent.setMonopoly();
        humanPlayer.addProperty(whitechapel);
        whitechapel.purchase(humanPlayer);
        assertTrue(oldKent.isMonopoly());

        // Both properties are owned, but by different players
        humanPlayer.addProperty(oldKent);
        oldKent.purchase(humanPlayer);
        humanPlayer2.addProperty(whitechapel);
        whitechapel.purchase(humanPlayer2);
        assertFalse(oldKent.isMonopoly());
    }
}