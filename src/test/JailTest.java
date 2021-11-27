package test;

import monopoly17.Jail;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing Jail class.
 * @author Trong Nguyen
 */
public class JailTest {
    Jail visitingJail;
    Jail inJail;
    Jail gotoJail;

    @Before
    public void setUp() {
        visitingJail = new Jail(10, "JUST VISITING", Jail.JailType.JUST_VISITING);
        inJail = new Jail(10, "IN JAIL", Jail.JailType.IN_JAIL);
        gotoJail = new Jail(30, "GO TO JAIL",Jail.JailType.GOTO_JAIL);
    }

    @Test
    public void testGetType() {
        Assert.assertEquals(visitingJail.getType(), Jail.JailType.JUST_VISITING);
        Assert.assertEquals(inJail.getType(), Jail.JailType.IN_JAIL);
        Assert.assertEquals(gotoJail.getType(), Jail.JailType.GOTO_JAIL);
    }

    @Test
    public void testPosition() {
        Assert.assertEquals(visitingJail.position(), 10);
        Assert.assertEquals(inJail.position(), 10);
        Assert.assertEquals(gotoJail.position(), 30);
    }

    @Test
    public void testName() {
        Assert.assertEquals(visitingJail.name(), "JUST VISITING");
        Assert.assertEquals(inJail.name(), "IN JAIL");
        Assert.assertEquals(gotoJail.name(), "GO TO JAIL");
    }

    @Test
    public void testIsOwnable() {
        Assert.assertFalse(visitingJail.isOwnable());
        Assert.assertFalse(inJail.isOwnable());
        Assert.assertFalse(gotoJail.isOwnable());
    }

    @Test
    public void testIsOwned() {
        Assert.assertFalse(visitingJail.isOwned());
        Assert.assertFalse(inJail.isOwned());
        Assert.assertFalse(gotoJail.isOwned());
    }

    @Test
    public void testCost() {
        Assert.assertEquals(visitingJail.cost(), 0);
        Assert.assertEquals(inJail.cost(), 0);
        Assert.assertEquals(gotoJail.cost(), 0);
    }

    @Test
    public void testRent() {
        Assert.assertEquals(visitingJail.rent(0), 0);
        Assert.assertEquals(inJail.rent(0), 0);
        Assert.assertEquals(gotoJail.rent(0), 0);
    }

    @Test
    public void testOwner() {
        Assert.assertNull(visitingJail.owner());
        Assert.assertNull(inJail.owner());
        Assert.assertNull(visitingJail.owner());
    }

}