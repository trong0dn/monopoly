import org.junit.Test;

import static org.junit.Assert.*;

public class PropertyTest {
    private final HumanPlayer humanPlayer = new HumanPlayer("test");
    private final HumanPlayer humanPlayer2 = new HumanPlayer("test2");
    Property property;
    private int position1;
    int rent = 2;
    int oneHouse = 10;
    int twoHouse = 30;
    int threeHouse = 90;
    int fourHouse = 160;
    int hotel = 250;
    int propertyCost = 60;
    int houses = 50;
    private final Property oldkent = new Property(position1, "OLD KENT ROAD",
            rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);

    int position2;
    private final Property whitechapel = new Property(position2, "WHITECHAPEL ROAD",
                        4, 20, 60, 180, 320, 450, 60, 50);


    /**
     * tests the name of the property is correct
     */
    @Test
    public void name() {
        assertEquals("OLD KENT ROAD", oldkent.name());
        assertEquals("WHITECHAPEL ROAD", whitechapel.name());
    }


    /**
     * tests the cost of the property is correct
     */
    @Test
    public void cost() {
        assertEquals(60, oldkent.cost());
        assertEquals(60, whitechapel.cost());
    }


    /**
     * tests the purchase method, which assigns the owner of the property
     */
    @Test
    public void purchase() {
        oldkent.purchase(humanPlayer);
        assertTrue(oldkent.isOwned());
        assertEquals(humanPlayer, oldkent.owner());

        //whitechapel is not owned
        assertFalse(whitechapel.isOwned());
        //whitechapel does not have an owner
        assertNull(whitechapel.owner());
    }


    /**
     * tests the rent method which assigns the rent to the players
     */
    @Test
    public void rent() {
        //human player purchases old kent, therefore the rent on the property is $2
        humanPlayer.addProperty(oldkent);
        oldkent.purchase(humanPlayer);
        assertEquals(2, oldkent.rent(3));

        //the property is not owned, so there is no rent on the property
        assertEquals(0, whitechapel.rent(3));
    }


    /**
     * tests the updateMonopoly function
     * checks if properties owned belong in a set
     */

    @Test
    public void updateMonopoly() {
        //only one proeprty is owned
        humanPlayer.addProperty(oldkent);
        oldkent.purchase(humanPlayer);
        humanPlayer.addProperty(whitechapel);
        whitechapel.purchase(humanPlayer);
        assertFalse(oldkent.isMonopoly());

        //both property sets owned
        humanPlayer.addProperty(oldkent);
        oldkent.purchase(humanPlayer);
        oldkent.setMonopoly();
        humanPlayer.addProperty(whitechapel);
        whitechapel.purchase(humanPlayer);
        assertTrue(oldkent.isMonopoly());

        //both properties are owned, but by different players
        humanPlayer.addProperty(oldkent);
        oldkent.purchase(humanPlayer);
        humanPlayer2.addProperty(whitechapel);
        whitechapel.purchase(humanPlayer2);
        assertTrue(oldkent.isMonopoly());


    }


}