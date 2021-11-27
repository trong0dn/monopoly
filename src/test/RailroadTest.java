package test;

import monopoly17.HumanPlayer;
import monopoly17.Player;
import monopoly17.Railroad;
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
        rA = new Railroad(Railroad.RAILROAD_A_POSITION, "KINGS CROSS STATION");
        rB = new Railroad(Railroad.RAILROAD_B_POSITION, "MARYLEBONE STATION");
        rC = new Railroad(Railroad.RAILROAD_C_POSITION, "FENCHURCH ST. STATION");
        rD = new Railroad(Railroad.RAILROAD_D_POSITION, "LIVERPOOL ST. STATION");

        rA.setGroup(rB, rC, rD);
        rB.setGroup(rA, rC, rD);
        rC.setGroup(rA, rB, rD);
        rD.setGroup(rA, rB, rC);
    }

    @Test
    public void testPosition() {
        Assert.assertEquals(rA.position(), 5);
        Assert.assertEquals(rB.position(), 15);
        Assert.assertEquals(rC.position(), 25);
        Assert.assertEquals(rD.position(), 35);
    }

    @Test
    public void testName() {
        Assert.assertEquals(rA.name(), "KINGS CROSS STATION");
        Assert.assertEquals(rB.name(), "MARYLEBONE STATION");
        Assert.assertEquals(rC.name(), "FENCHURCH ST. STATION");
        Assert.assertEquals(rD.name(), "LIVERPOOL ST. STATION");
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