import monopoly17.Jail;
import monopoly17.JsonParse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing Jail class.
 * @author Trong Nguyen
 */
public class JailTest {
    private Jail visitingJail;
    private Jail inJail;
    private Jail gotoJail;

    /**
     * Set up the squares before each test.
     */
    @Before
    public void setUp() {
        visitingJail = new Jail(Jail.IN_JAIL, JsonParse.parseJSON(Jail.IN_JAIL, "UK"), Jail.JailType.JUST_VISITING);
        inJail = new Jail(Jail.IN_JAIL, JsonParse.parseJSON(Jail.IN_JAIL, "UK"), Jail.JailType.IN_JAIL);
        gotoJail = new Jail(Jail.GOTO_JAIL, JsonParse.parseJSON(Jail.GOTO_JAIL, "UK"),Jail.JailType.GOTO_JAIL);
    }

    /**
     * Test the type of Jail each square is.
     */
    @Test
    public void testGetType() {
        Assert.assertEquals(visitingJail.getType(), Jail.JailType.JUST_VISITING);
        Assert.assertEquals(inJail.getType(), Jail.JailType.IN_JAIL);
        Assert.assertEquals(gotoJail.getType(), Jail.JailType.GOTO_JAIL);
    }

    /**
     * Test the position of each Jail square.
     */
    @Test
    public void testPosition() {
        Assert.assertEquals(visitingJail.position(), Jail.IN_JAIL);
        Assert.assertEquals(inJail.position(), Jail.IN_JAIL);
        Assert.assertEquals(gotoJail.position(), Jail.GOTO_JAIL);
    }

    /**
     * Test the name of each Jail square.
     */
    @Test
    public void testName() {
        Assert.assertEquals(visitingJail.name(), JsonParse.parseJSON(Jail.IN_JAIL, "UK"));
        Assert.assertEquals(inJail.name(), JsonParse.parseJSON(Jail.IN_JAIL, "UK"));
        Assert.assertEquals(gotoJail.name(), JsonParse.parseJSON(Jail.GOTO_JAIL, "UK"));
    }

    /**
     * Test if the square is ownable.
     */
    @Test
    public void testIsOwnable() {
        Assert.assertFalse(visitingJail.isOwnable());
        Assert.assertFalse(inJail.isOwnable());
        Assert.assertFalse(gotoJail.isOwnable());
    }

    /**
     * Test if the square is owned.
     */
    @Test
    public void testIsOwned() {
        Assert.assertFalse(visitingJail.isOwned());
        Assert.assertFalse(inJail.isOwned());
        Assert.assertFalse(gotoJail.isOwned());
    }

    /**
     * Test the cost of the squares.
     */
    @Test
    public void testCost() {
        Assert.assertEquals(visitingJail.cost(), 0);
        Assert.assertEquals(inJail.cost(), 0);
        Assert.assertEquals(gotoJail.cost(), 0);
    }

    /**
     * Test the rent of the squares.
     */
    @Test
    public void testRent() {
        Assert.assertEquals(visitingJail.rent(0), 0);
        Assert.assertEquals(inJail.rent(0), 0);
        Assert.assertEquals(gotoJail.rent(0), 0);
    }

    /**
     * Test the owner of the squares.
     */
    @Test
    public void testOwner() {
        Assert.assertNull(visitingJail.owner());
        Assert.assertNull(inJail.owner());
        Assert.assertNull(visitingJail.owner());
    }

}