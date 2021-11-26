package test;

import monopoly17.HumanPlayer;
import monopoly17.Player;
import monopoly17.Utility;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import static monopoly17.Utility.ELECTRIC_POSITION;
import static monopoly17.Utility.WATER_POSITION;

public class UtilityTest {
    Utility electricUtility;
    Utility waterUtility;
    Utility ownedElectricUtility;
    Utility ownedWaterUtility;
    Player player;

    @Before
    public void SetUp() {
        electricUtility = new Utility(ELECTRIC_POSITION, "ELECTRIC COMPANY");
        waterUtility = new Utility(WATER_POSITION, "WATER WORKS");

        player = new HumanPlayer("Tester");
        ownedElectricUtility = new Utility(ELECTRIC_POSITION, "ELECTRIC COMPANY");
        ownedWaterUtility = new Utility(WATER_POSITION, "WATER WORKS");

        ownedElectricUtility.purchase(player);
        ownedWaterUtility.purchase(player);

        ownedElectricUtility.setGroup(ownedWaterUtility);
        ownedWaterUtility.setGroup(ownedElectricUtility);
    }

    @Test
    public void testPosition() {
        Assert.assertEquals(electricUtility.position(),12);
        Assert.assertEquals(waterUtility .position(),28);
    }

    @Test
    public void testName() {
        Assert.assertEquals(electricUtility.name(),"ELECTRIC COMPANY");
        Assert.assertEquals(waterUtility.name(),"WATER WORKS");
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