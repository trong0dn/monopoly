import monopoly17.HumanPlayer;
import monopoly17.Player;
import monopoly17.Railroad;
import monopoly17.SquareInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing Railroad class.
 * @author Trong Nguyen
 */
public class RailroadTest {
    Railroad rA;
    Railroad rB;
    Railroad rC;
    Railroad rD;

    @Before
    public void setUp() {
        rA = new Railroad(SquareInfo.SQUARE_5.getPosition(), SquareInfo.SQUARE_5.getName());
        rB = new Railroad(SquareInfo.SQUARE_15.getPosition(), SquareInfo.SQUARE_15.getName());
        rC = new Railroad(SquareInfo.SQUARE_25.getPosition(), SquareInfo.SQUARE_25.getName());
        rD = new Railroad(SquareInfo.SQUARE_35.getPosition(), SquareInfo.SQUARE_35.getName());

        rA.setGroup(rB, rC, rD);
        rB.setGroup(rA, rC, rD);
        rC.setGroup(rA, rB, rD);
        rD.setGroup(rA, rB, rC);
    }

    @Test
    public void testPosition() {
        Assert.assertEquals(rA.position(), SquareInfo.SQUARE_5.getPosition());
        Assert.assertEquals(rB.position(), SquareInfo.SQUARE_15.getPosition());
        Assert.assertEquals(rC.position(), SquareInfo.SQUARE_25.getPosition());
        Assert.assertEquals(rD.position(), SquareInfo.SQUARE_35.getPosition());
    }

    @Test
    public void testName() {
        Assert.assertEquals(rA.name(), SquareInfo.SQUARE_5.getName());
        Assert.assertEquals(rB.name(), SquareInfo.SQUARE_15.getName());
        Assert.assertEquals(rC.name(), SquareInfo.SQUARE_25.getName());
        Assert.assertEquals(rD.name(), SquareInfo.SQUARE_35.getName());
    }

    @Test
    public void testIsOwnable() {
        Assert.assertTrue(rA.isOwnable());
        Assert.assertTrue(rB.isOwnable());
        Assert.assertTrue(rC.isOwnable());
        Assert.assertTrue(rD.isOwnable());
    }

    @Test
    public void testIsOwned() {
        Player player = new HumanPlayer("Tester");
        rA.purchase(player);
        Assert.assertTrue(rA.isOwned());
        Assert.assertFalse(rB.isOwned());
        Assert.assertFalse(rC.isOwned());
        Assert.assertFalse(rD.isOwned());
    }

    @Test
    public void testCost() {
        Assert.assertEquals(rA.cost(), 200);
        Assert.assertEquals(rB.cost(), 200);
        Assert.assertEquals(rC.cost(), 200);
        Assert.assertEquals(rD.cost(), 200);
    }

    @Test
    public void testRent() {
        Player player = new HumanPlayer("Tester");
        rA.purchase(player);
        rB.purchase(player);
        rC.purchase(player);
        Assert.assertEquals(rA.rent(0), 100);
        Assert.assertEquals(rD.rent(0), 25);
    }

    @Test
    public void testOwner() {
        Player player = new HumanPlayer("Tester");
        rA.purchase(player);
        rB.purchase(player);
        rC.purchase(player);
        rD.purchase(player);
        Assert.assertEquals(rA.owner(), player);
        Assert.assertEquals(rD.owner(), player);
        Assert.assertEquals(rC.owner(), player);
        Assert.assertEquals(rD.owner(), player);
    }
}