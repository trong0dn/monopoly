import monopoly17.*;
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
        electricUtility = new Utility(Utility.ELECTRIC_POSITION, JsonParse.parseJSON(Utility.ELECTRIC_POSITION, "UK"));
        waterUtility = new Utility(Utility.WATER_POSITION, JsonParse.parseJSON(Utility.WATER_POSITION, "UK"));

        player = new HumanPlayer("Tester");
        ownedElectricUtility = new Utility(Utility.ELECTRIC_POSITION, JsonParse.parseJSON(Utility.ELECTRIC_POSITION, "UK"));
        ownedWaterUtility = new Utility(Utility.WATER_POSITION, JsonParse.parseJSON(Utility.WATER_POSITION, "UK"));

        ownedElectricUtility.purchase(player);
        ownedWaterUtility.purchase(player);

        ownedElectricUtility.setGroup(ownedWaterUtility);
        ownedWaterUtility.setGroup(ownedElectricUtility);
    }

    @Test
    public void testPosition() {
        Assert.assertEquals(electricUtility.position(), Utility.ELECTRIC_POSITION);
        Assert.assertEquals(waterUtility .position(), Utility.WATER_POSITION);
    }

    @Test
    public void testName() {
        Assert.assertEquals(electricUtility.name(), JsonParse.parseJSON(Utility.ELECTRIC_POSITION, "UK"));
        Assert.assertEquals(waterUtility.name(), JsonParse.parseJSON(Utility.WATER_POSITION, "UK"));
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