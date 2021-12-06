import monopoly17.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

/**
 * Testing Utility class.
 * @author Trong Nguyen
 */
public class UtilityTest {
    private Utility electricUtility;
    private Utility waterUtility;
    private Utility ownedElectricUtility;
    private Utility ownedWaterUtility;
    private Player player;

    /**
     * Set up the Utility squares before each test.
     */
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

    /**
     * Test the position of the utilities.
     */
    @Test
    public void testPosition() {
        Assert.assertEquals(electricUtility.position(), Utility.ELECTRIC_POSITION);
        Assert.assertEquals(waterUtility .position(), Utility.WATER_POSITION);
    }

    /**
     * Test the name of the utilities.
     */
    @Test
    public void testName() {
        Assert.assertEquals(electricUtility.name(), JsonParse.parseJSON(Utility.ELECTRIC_POSITION, "UK"));
        Assert.assertEquals(waterUtility.name(), JsonParse.parseJSON(Utility.WATER_POSITION, "UK"));
    }

    /**
     * Test if the utilities are ownable.
     */
    @Test
    public void testIsOwnable() {
        Assert.assertTrue(electricUtility.isOwnable());
        Assert.assertTrue(waterUtility.isOwnable());
    }

    /**
     * Test if the utilities are owned.
     */
    @Test
    public void testIsOwned() {
        Assert.assertFalse(electricUtility.isOwned());
        Assert.assertFalse(waterUtility.isOwned());
    }

    /**
     * Test if Electric Company is owned.
     */
    @Test
    public void testIsOwnedElectric() {
        Assert.assertTrue(ownedElectricUtility.isOwned());
    }

    /**
     * Test if Water Works is owned.
     */
    @Test
    public void testIsOwnedWater() {
        waterUtility.purchase(player);
        Assert.assertTrue(ownedWaterUtility.isOwned());
    }

    /**
     * Test the rent of Water Works when only one Utility is owned.
     */
    @Test
    public void testRentUngroup() {
        int waterRent = waterUtility.rent(4);
        Assert.assertEquals(waterRent, 16);
    }

    /**
     * Test the rent of Electric Company when both Utilities are owned.
     */
    @Test
    public void testRentGroup() {
        int electricRent = ownedElectricUtility.rent(4);
        Assert.assertEquals(electricRent, 40);
    }

    /**
     * Test the owner of the utilities.
     */
    @Test
    public void testOwner() {
        Assert.assertEquals(ownedWaterUtility.owner(), player);
        Assert.assertEquals(ownedElectricUtility.owner(), player);
    }

    /**
     * Test the owner for the unowned utilities.
     */
    @Test
    public void testNullOwner() {
        Assert.assertNull(waterUtility.owner());
        Assert.assertNull(electricUtility.owner());
    }
}