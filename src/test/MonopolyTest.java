package test;

import monopoly17.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

import static monopoly17.MonopolyGUI.*;
import static org.junit.Assert.*;

/**
 * Testing Monopoly class.
 * @author Ibrahim Almalki & Trong Nguyen
 */
public class MonopolyTest {
    private Monopoly monopoly;
    private Player humanPlayer;
    private HumanPlayer humanPlayer2;
    private int position;
    int rent = 2;
    int oneHouse = 10;
    int twoHouse = 30;
    int threeHouse = 90;
    int fourHouse = 160;
    int hotel = 250;
    int propertyCost = 60;
    int houses = 50;
    private Square oldKent;
    private Square goToJail;
    private Square inJail;

    private ArrayList<Object> arrayList;

    @Before
    public void setUp() {
        monopoly = new Monopoly();
        humanPlayer = new HumanPlayer("Tester1");
        humanPlayer2 = new HumanPlayer("Tester2");

        oldKent = new Property(position, SquareInfo.SQUARE_1.getName(),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
        goToJail = new Jail(SquareInfo.SQUARE_30.getPosition(), SquareInfo.SQUARE_30.getName(), Jail.JailType.GOTO_JAIL);
        inJail = new Jail(SquareInfo.SQUARE_10.getPosition(), SquareInfo.SQUARE_10.getName(), Jail.JailType.IN_JAIL);

        ArrayList<PlayerGUI> playersGUI = new ArrayList<>();
        PlayerGUI playerGUI1 = new PlayerGUI(Color.RED, "Tester1");
        PlayerGUI playerGUI2 = new PlayerGUI(Color.BLUE, "Tester2");
        playersGUI.add(playerGUI1);
        playersGUI.add(playerGUI2);

        LinkedList<Player> playersList = new LinkedList<>();
        playersList.add(humanPlayer);
        playersList.add(humanPlayer2);

        arrayList = new ArrayList<>();
        arrayList.add(MONOPOLY_IDX, monopoly);
        arrayList.add(PLAYER_GUI_IDX, playersGUI);
        arrayList.add(PLAYER_LIST_IDX, playersList);
    }

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

    /**
     * Test when the player goes when they land on GO TO JAIL
     */
    @Test
    public void testGoToJail() {
        humanPlayer.moveTo(SquareInfo.SQUARE_30.getPosition());
        monopoly.handleSquare(humanPlayer, goToJail, 0);
        Assert.assertEquals(SquareInfo.SQUARE_10.getPosition(), humanPlayer.getPosition());
    }

    /**
     * Test when a playing is IN JAIL.
     */
    @Test
    public void testInJail() {
        humanPlayer.moveTo(SquareInfo.SQUARE_10.getPosition());
        monopoly.handleSquare(humanPlayer, inJail, 0);
        Assert.assertEquals(SquareInfo.SQUARE_10.getPosition(), humanPlayer.getPosition());
    }

    /**
     * Check whether the game is exported.
     */
    @Test
    public void testExportAndImportGame() {
        monopoly.exportGame(arrayList);
        ArrayList<Object> imported = monopoly.importGame();
        assertEquals(arrayList, imported);
    }
}