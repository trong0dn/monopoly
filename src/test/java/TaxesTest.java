import monopoly17.JsonParse;
import monopoly17.Taxes;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import static monopoly17.Taxes.FIX_INCOME_TAX;
import static monopoly17.Taxes.FIX_SUPER_TAX;

/**
 * Testing Taxes class.
 * @author Trong Nguyen
 */
public class TaxesTest {
    Taxes incomeTaxes;
    Taxes superTaxes;

    @Before
    public void setUp() {
        incomeTaxes = new Taxes(Taxes.INCOME_TAX_POSITION, Objects.requireNonNull(JsonParse.parseJSON(Taxes.INCOME_TAX_POSITION, "UK")));
        superTaxes = new Taxes(Taxes.SUPER_TAX_POSITION, Objects.requireNonNull(JsonParse.parseJSON(Taxes.SUPER_TAX_POSITION, "UK")));
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
        Assert.assertEquals(incomeTaxes.position(), Taxes.INCOME_TAX_POSITION);
        Assert.assertEquals(superTaxes.position(), Taxes.SUPER_TAX_POSITION);
    }

    @Test
    public void testName() {
        Assert.assertEquals(incomeTaxes.name(), JsonParse.parseJSON(Taxes.INCOME_TAX_POSITION, "UK"));
        Assert.assertEquals(superTaxes.name(), JsonParse.parseJSON(Taxes.SUPER_TAX_POSITION, "UK"));
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