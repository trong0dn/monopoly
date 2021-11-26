package test;

import monopoly17.Taxes;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import static monopoly17.Taxes.FIX_INCOME_TAX;
import static monopoly17.Taxes.FIX_SUPER_TAX;

public class TaxesTest {
    Taxes incomeTaxes;
    Taxes superTaxes;

    @Before
    public void setUp() {
        incomeTaxes = new Taxes(0, true);
        superTaxes = new Taxes(1, false);
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
        Assert.assertEquals(incomeTaxes.position(), 0);
        Assert.assertEquals(superTaxes.position(), 1);
    }

    @Test
    public void testName() {
        Assert.assertEquals(incomeTaxes.name(), "INCOME TAX");
        Assert.assertEquals(superTaxes.name(), "SUPER TAX");
    }

    @Test
    public void isOwnable() {
        Assert.assertFalse(incomeTaxes.isOwnable());
        Assert.assertFalse(superTaxes.isOwnable());
    }

    @Test
    public void isOwned() {
        Assert.assertFalse(incomeTaxes.isOwned());
        Assert.assertFalse(superTaxes.isOwned());
    }

    @Test
    public void cost() {
        Assert.assertEquals(incomeTaxes.cost(), 0);
        Assert.assertEquals(superTaxes.cost(), 0);
    }

    @Test
    public void rent() {
        Assert.assertEquals(incomeTaxes.rent(0), 0);
        Assert.assertEquals(superTaxes.rent(0), 0);
    }

    @Test
    public void owner() {
        Assert.assertNull(incomeTaxes.owner());
        Assert.assertNull(superTaxes.owner());
    }
}