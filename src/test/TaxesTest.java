package test;

import monopoly17.Taxes;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import static monopoly17.Taxes.*;

/**
 * Testing Taxes class.
 * @author Trong Nguyen
 */
public class TaxesTest {
    Taxes incomeTaxes;
    Taxes superTaxes;

    @Before
    public void setUp() {
        incomeTaxes = new Taxes(INCOME_TAX_POSITION, true);
        superTaxes = new Taxes(SUPER_TAX_POSITION, false);
    }

    @Test
    public void testGetIncomeTax() {
        Assert.assertEquals(incomeTaxes.getTax(), FIX_INCOME_TAX);
    }

    @Test
    public void testGetSuperTax() {
        Assert.assertEquals(superTaxes.getTax(), FIX_SUPER_TAX);
    }

    @Test
    public void testPosition() {
        Assert.assertEquals(incomeTaxes.position(), 4);
        Assert.assertEquals(superTaxes.position(), 38);
    }

    @Test
    public void testName() {
        Assert.assertEquals(incomeTaxes.name(), "INCOME TAX");
        Assert.assertEquals(superTaxes.name(), "SUPER TAX");
    }

    @Test
    public void TestIsOwnable() {
        Assert.assertFalse(incomeTaxes.isOwnable());
        Assert.assertFalse(superTaxes.isOwnable());
    }

    @Test
    public void testIsOwned() {
        Assert.assertFalse(incomeTaxes.isOwned());
        Assert.assertFalse(superTaxes.isOwned());
    }

    @Test
    public void testCost() {
        Assert.assertEquals(incomeTaxes.cost(), 0);
        Assert.assertEquals(superTaxes.cost(), 0);
    }

    @Test
    public void testRent() {
        Assert.assertEquals(incomeTaxes.rent(0), 0);
        Assert.assertEquals(superTaxes.rent(0), 0);
    }

    @Test
    public void testOwner() {
        Assert.assertNull(incomeTaxes.owner());
        Assert.assertNull(superTaxes.owner());
    }
}