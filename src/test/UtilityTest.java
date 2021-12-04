package test;

import monopoly17.HumanPlayer;
import monopoly17.Player;
import monopoly17.SquareInfo;
import monopoly17.Utility;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

/**
 * Testing Utility class.
 * @author Trong Nguyen
 */
public class UtilityTest {
    Utility electricUtility;
    Utility waterUtility;
    Utility ownedElectricUtility;
    Utility ownedWaterUtility;
    Player player;

    @Before
    public void SetUp() {
        electricUtility = new Utility(SquareInfo.SQUARE_12.getPosition(), SquareInfo.SQUARE_12.getName());
        waterUtility = new Utility(SquareInfo.SQUARE_28.getPosition(), SquareInfo.SQUARE_28.getName());

        player = new HumanPlayer("Tester");
        ownedElectricUtility = new Utility(SquareInfo.SQUARE_12.getPosition(), SquareInfo.SQUARE_12.getName());
        ownedWaterUtility = new Utility(SquareInfo.SQUARE_28.getPosition(), SquareInfo.SQUARE_28.getName());

        ownedElectricUtility.purchase(player);
        ownedWaterUtility.purchase(player);

        ownedElectricUtility.setGroup(ownedWaterUtility);
        ownedWaterUtility.setGroup(ownedElectricUtility);
    }

    @Test
    public void testPosition() {
        Assert.assertEquals(electricUtility.position(),SquareInfo.SQUARE_12.getPosition());
        Assert.assertEquals(waterUtility .position(),SquareInfo.SQUARE_28.getPosition());
    }

    @Test
    public void testName() {
        Assert.assertEquals(electricUtility.name(), SquareInfo.SQUARE_12.getName());
        Assert.assertEquals(waterUtility.name(), SquareInfo.SQUARE_28.getName());
    }

    @Test
    public void testIsOwnable() {
        Assert.assertTrue(electricUtility.isOwnable());
        Assert.assertTrue(waterUtility.isOwnable());
    }

    @Test
    public void testIsOwned() {
        Assert.assertFalse(electricUtility.isOwned());
        Assert.assertFalse(waterUtility.isOwned());
    }

    @Test
    public void testIsOwnedElectric() {
        Assert.assertTrue(ownedElectricUtility.isOwned());
    }

    @Test
    public void testIsOwnedWater() {
        waterUtility.purchase(player);
        Assert.assertTrue(ownedWaterUtility.isOwned());
    }

    @Test
    public void testRentUngroup() {
        int waterRent = waterUtility.rent(4);
        Assert.assertEquals(waterRent, 16);
    }

    @Test
    public void testRentGroup() {
        int electricRent = ownedElectricUtility.rent(4);
        Assert.assertEquals(electricRent, 40);
    }

    @Test
    public void testOwner() {
        Assert.assertEquals(ownedWaterUtility.owner(), player);
        Assert.assertEquals(ownedElectricUtility.owner(), player);
    }

    @Test
    public void testNullOwner() {
        Assert.assertNull(waterUtility.owner());
        Assert.assertNull(electricUtility.owner());
    }
}