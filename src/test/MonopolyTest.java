package test;

import monopoly17.*;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testing Monopoly class.
 * @author Ibrahim Almalki
 * Modified by Trong Nguyen
 */
public class MonopolyTest {
    private final Monopoly monopoly = new Monopoly();
    private final Player humanPlayer = new HumanPlayer("tester");
    private final HumanPlayer humanPlayer2 = new HumanPlayer("tester2");
    private int position;
    int rent = 2;
    int oneHouse = 10;
    int twoHouse = 30;
    int threeHouse = 90;
    int fourHouse = 160;
    int hotel = 250;
    int propertyCost = 60;
    int houses = 50;
    private final Square oldKent = new Property(position, "OLD KENT ROAD",
            rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);

    /**
     * Test the buyProperty method.
     */
    @Test
    public void testBuyProperty() {
        humanPlayer2.setMoney(1500);
        monopoly.buyProperty(humanPlayer, oldKent);
        Assert.assertEquals(oldKent, humanPlayer.properties().toArray()[0]);

        // Property is already owned, cannot purchase it
        oldKent.isOwned();
        monopoly.buyProperty(humanPlayer, oldKent);
        Assert.assertEquals(0, humanPlayer2.properties().toArray().length);

        // player 2 purchases oldKent from player1
        humanPlayer2.setMoney(10);
        monopoly.buyProperty(humanPlayer2, oldKent);
        Assert.assertEquals(oldKent, humanPlayer2.properties().toArray()[0]);
    }

    /**
     * Test the square is owned by the player, they must pay rent method.
     */
    @Test
    public void testOwned() {
        //player2 does not own the property, must pay rent
        humanPlayer2.setMoney(1500);
        monopoly.buyProperty(humanPlayer, oldKent);
        monopoly.owned(humanPlayer2, oldKent, 5);
        Assert.assertEquals(1498, humanPlayer2.getMoney());

        //player landed on their own property, does not pay rent
        humanPlayer2.setMoney(1500);
        monopoly.buyProperty(humanPlayer2, oldKent);
        monopoly.owned(humanPlayer2, oldKent, 5);
        Assert.assertEquals(1500, humanPlayer2.getMoney());

        // player landed on their own property, does not have any money, they are not bankrupt
        humanPlayer2.setMoney(1500);
        monopoly.buyProperty(humanPlayer2, oldKent);
        humanPlayer2.setMoney(0);
        monopoly.owned(humanPlayer2, oldKent, 5);
        Assert.assertEquals(0, humanPlayer2.getMoney());
        assertFalse(monopoly.isBankrupt());

        // player1 landed on player2's property, does not have any money, they are bankrupt
        humanPlayer2.setMoney(1500);
        monopoly.buyProperty(humanPlayer, oldKent);
        humanPlayer2.setMoney(0);
        monopoly.owned(humanPlayer2, oldKent, 5);
        assertTrue(monopoly.isBankrupt());
    }
}