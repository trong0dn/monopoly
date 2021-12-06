import monopoly17.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testing Monopoly class.
 * @author Ibrahim Almalki & Trong Nguyen
 */
public class MonopolyTest {
    private Monopoly monopoly;
    private Player humanPlayer;
    private HumanPlayer humanPlayer2;
    private int position = 1;
    private int rent = 2;
    private int oneHouse = 10;
    private int twoHouse = 30;
    private int threeHouse = 90;
    private int fourHouse = 160;
    private int hotel = 250;
    private int propertyCost = 60;
    private int houses = 50;
    private Square oldKent;
    private Square goToJail;
    private Square inJail;

    /**
     * Set up Monopoly and some squares before each test.
     */
    @Before
    public void setUp() {
        monopoly = new Monopoly();
        humanPlayer = new HumanPlayer("Tester1");
        humanPlayer2 = new HumanPlayer("Tester2");

        oldKent = new Property(position, JsonParse.parseJSON(position, "UK"),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
        goToJail = new Jail(Jail.GOTO_JAIL, JsonParse.parseJSON(Jail.GOTO_JAIL, "UK"), Jail.JailType.GOTO_JAIL);
        inJail = new Jail(Jail.IN_JAIL, JsonParse.parseJSON(Jail.IN_JAIL, "UK"), Jail.JailType.IN_JAIL);
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

        // Player 2 purchases oldKent from player1
        humanPlayer2.setMoney(10);
        monopoly.buyProperty(humanPlayer2, oldKent);
        Assert.assertEquals(oldKent, humanPlayer2.properties().toArray()[0]);
    }

    /**
     * Test the square is owned by the player, they must pay rent method.
     */
    @Test
    public void testOwned() {
        // Player2 does not own the property, must pay rent
        humanPlayer2.setMoney(1500);
        monopoly.buyProperty(humanPlayer, oldKent);
        monopoly.owned(humanPlayer2, oldKent, 5);
        Assert.assertEquals(1498, humanPlayer2.getMoney());

        // Player landed on their own property, does not pay rent
        humanPlayer2.setMoney(1500);
        monopoly.buyProperty(humanPlayer2, oldKent);
        monopoly.owned(humanPlayer2, oldKent, 5);
        Assert.assertEquals(1500, humanPlayer2.getMoney());

        // Player landed on their own property, does not have any money, they are not bankrupt
        humanPlayer2.setMoney(1500);
        monopoly.buyProperty(humanPlayer2, oldKent);
        humanPlayer2.setMoney(0);
        monopoly.owned(humanPlayer2, oldKent, 5);
        Assert.assertEquals(0, humanPlayer2.getMoney());
        assertFalse(monopoly.isBankrupt());

        // Player1 landed on player2's property, does not have any money, they are bankrupt
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
        humanPlayer.moveTo(Jail.GOTO_JAIL);
        monopoly.handleSquare(humanPlayer, goToJail, 0);
        Assert.assertEquals(Jail.IN_JAIL, humanPlayer.getPosition());
    }

    /**
     * Test when a playing is IN JAIL.
     */
    @Test
    public void testInJail() {
        humanPlayer.moveTo(Jail.IN_JAIL);
        monopoly.handleSquare(humanPlayer, inJail, 0);
        Assert.assertEquals(Jail.IN_JAIL, humanPlayer.getPosition());
    }

    /**
     * Check whether the game is imported and export contents are the same.
     */
    @Test
    public void testExportAndImportGame() {
        monopoly.exportGame(monopoly);
        Monopoly importedMonopoly = monopoly.importGame();

        Assert.assertEquals(monopoly.getPlayers(), importedMonopoly.getPlayers());
        Assert.assertEquals(monopoly.getPlayerGUI(), importedMonopoly.getPlayerGUI());
    }
}