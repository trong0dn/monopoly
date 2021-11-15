package test;

import monopoly17.*;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MonopolyTest {
    private Monopoly monopoly = new Monopoly();
    private Monopoly.GameState gameState;
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
    private final Square oldkent = new Property(position, "OLD KENT ROAD",
            rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);


    /**
     * tests the buyProprty method.
     *
     */
    @Test
    public void buyProperty() {
        humanPlayer2.setMoney(1500);
        monopoly.buyProperty(humanPlayer, oldkent);
        Assert.assertEquals(oldkent, humanPlayer.properties().toArray()[0]);

        //the property is already owned, cannot purchase it
        oldkent.isOwned();
        monopoly.buyProperty(humanPlayer, oldkent);
        Assert.assertEquals(0, humanPlayer2.properties().toArray().length);

        //player 2 purchases oldkent from player1
        humanPlayer2.setMoney(10);
        monopoly.buyProperty(humanPlayer2, oldkent);
        Assert.assertEquals(oldkent, humanPlayer2.properties().toArray()[0]);

    }


    /**
     * tests the square is owned by the player,
     * if it is they must pay rent  method.
     *
     */
    @Test
    public void owned() {
        //player2 does not own the property, must pay rent
        humanPlayer2.setMoney(1500);
        monopoly.buyProperty(humanPlayer, oldkent);
        monopoly.owned(humanPlayer2, oldkent, 5);
        Assert.assertEquals(1498, humanPlayer2.getMoney());


        //player landed on their own property, does not pay rent
        humanPlayer2.setMoney(1500);
        monopoly.buyProperty(humanPlayer2, oldkent);
        monopoly.owned(humanPlayer2, oldkent, 5);
        Assert.assertEquals(1500, humanPlayer2.getMoney());


        //player landed on their own property, does not have any money, they are not bankrupt
        humanPlayer2.setMoney(1500);
        monopoly.buyProperty(humanPlayer2, oldkent);
        humanPlayer2.setMoney(0);
        monopoly.owned(humanPlayer2, oldkent, 5);
        Assert.assertEquals(0, humanPlayer2.getMoney());
        assertFalse(monopoly.isBankrupt());


        //player1 landed on player2's property, does not have any money, they are bankrupt
        humanPlayer2.setMoney(1500);
        monopoly.buyProperty(humanPlayer, oldkent);
        humanPlayer2.setMoney(0);
        monopoly.owned(humanPlayer2, oldkent, 5);
        assertTrue(monopoly.isBankrupt());


    }


}