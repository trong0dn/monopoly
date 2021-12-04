package test;

import monopoly17.Jail;
import monopoly17.SquareInfo;
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
        visitingJail = new Jail(SquareInfo.SQUARE_10.getPosition(), SquareInfo.SQUARE_10.getName(), Jail.JailType.JUST_VISITING);
        inJail = new Jail(SquareInfo.SQUARE_10.getPosition(), SquareInfo.SQUARE_10.getName(), Jail.JailType.IN_JAIL);
        gotoJail = new Jail(SquareInfo.SQUARE_30.getPosition(), SquareInfo.SQUARE_30.getName(),Jail.JailType.GOTO_JAIL);
    }

    @Test
    public void testGetType() {
        Assert.assertEquals(visitingJail.getType(), Jail.JailType.JUST_VISITING);
        Assert.assertEquals(inJail.getType(), Jail.JailType.IN_JAIL);
        Assert.assertEquals(gotoJail.getType(), Jail.JailType.GOTO_JAIL);
    }

    @Test
    public void testPosition() {
        Assert.assertEquals(visitingJail.position(), SquareInfo.SQUARE_10.getPosition());
        Assert.assertEquals(inJail.position(), SquareInfo.SQUARE_10.getPosition());
        Assert.assertEquals(gotoJail.position(), SquareInfo.SQUARE_30.getPosition());
    }

    @Test
    public void testName() {
        Assert.assertEquals(visitingJail.name(), SquareInfo.SQUARE_10.getName());
        Assert.assertEquals(inJail.name(), SquareInfo.SQUARE_10.getName());
        Assert.assertEquals(gotoJail.name(), SquareInfo.SQUARE_30.getName());
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