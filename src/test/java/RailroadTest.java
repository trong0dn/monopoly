import monopoly17.*;
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
        rA = new Railroad(Railroad.RAILROAD_A_POSITION, JsonParse.parseJSON(Railroad.RAILROAD_A_POSITION, "UK"));
        rB = new Railroad(Railroad.RAILROAD_B_POSITION, JsonParse.parseJSON(Railroad.RAILROAD_B_POSITION, "UK"));
        rC = new Railroad(Railroad.RAILROAD_C_POSITION, JsonParse.parseJSON(Railroad.RAILROAD_C_POSITION, "UK"));
        rD = new Railroad(Railroad.RAILROAD_D_POSITION, JsonParse.parseJSON(Railroad.RAILROAD_D_POSITION, "UK"));

        rA.setGroup(rB, rC, rD);
        rB.setGroup(rA, rC, rD);
        rC.setGroup(rA, rB, rD);
        rD.setGroup(rA, rB, rC);
    }

    @Test
    public void testPosition() {
        Assert.assertEquals(rA.position(), Railroad.RAILROAD_A_POSITION);
        Assert.assertEquals(rB.position(), Railroad.RAILROAD_B_POSITION);
        Assert.assertEquals(rC.position(), Railroad.RAILROAD_C_POSITION);
        Assert.assertEquals(rD.position(), Railroad.RAILROAD_D_POSITION);
    }

    @Test
    public void testName() {
        Assert.assertEquals(rA.name(), JsonParse.parseJSON(Railroad.RAILROAD_A_POSITION, "UK"));
        Assert.assertEquals(rB.name(), JsonParse.parseJSON(Railroad.RAILROAD_B_POSITION, "UK"));
        Assert.assertEquals(rC.name(), JsonParse.parseJSON(Railroad.RAILROAD_C_POSITION, "UK"));
        Assert.assertEquals(rD.name(), JsonParse.parseJSON(Railroad.RAILROAD_D_POSITION, "UK"));
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