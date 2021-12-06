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

    /**
     * Set of the Taxes squares before each test.
     */
    @Before
    public void setUp() {
        incomeTaxes = new Taxes(Taxes.INCOME_TAX_POSITION, Objects.requireNonNull(JsonParse.parseJSON(Taxes.INCOME_TAX_POSITION, "UK")));
        superTaxes = new Taxes(Taxes.SUPER_TAX_POSITION, Objects.requireNonNull(JsonParse.parseJSON(Taxes.SUPER_TAX_POSITION, "UK")));
    }

    /**
     * Test the tax for Income Tax.
     */
    @Test
    public void testGetIncomeTax() {
        Assert.assertEquals(incomeTaxes.getTax(), FIX_INCOME_TAX);
    }

    /**
     * Test the tax for Super Tax.
     */
    @Test
    public void testGetSuperTax() {
        Assert.assertEquals(superTaxes.getTax(), FIX_SUPER_TAX);
    }

    /**
     * Test the position of the Taxes.
     */
    @Test
    public void testPosition() {
        Assert.assertEquals(incomeTaxes.position(), Taxes.INCOME_TAX_POSITION);
        Assert.assertEquals(superTaxes.position(), Taxes.SUPER_TAX_POSITION);
    }

    /**
     * Test the name of the Taxes.
     */
    @Test
    public void testName() {
        Assert.assertEquals(incomeTaxes.name(), JsonParse.parseJSON(Taxes.INCOME_TAX_POSITION, "UK"));
        Assert.assertEquals(superTaxes.name(), JsonParse.parseJSON(Taxes.SUPER_TAX_POSITION, "UK"));
    }

    /**
     * Test if the squares are ownable.
     */
    @Test
    public void TestIsOwnable() {
        Assert.assertFalse(incomeTaxes.isOwnable());
        Assert.assertFalse(superTaxes.isOwnable());
    }

    /**
     * Test if the squares are owned.
     */
    @Test
    public void testIsOwned() {
        Assert.assertFalse(incomeTaxes.isOwned());
        Assert.assertFalse(superTaxes.isOwned());
    }

    /**
     * Test the cost of the Taxes.
     */
    @Test
    public void testCost() {
        Assert.assertEquals(incomeTaxes.cost(), 0);
        Assert.assertEquals(superTaxes.cost(), 0);
    }

    /**
     * Test the rent of the Taxes.
     */
    @Test
    public void testRent() {
        Assert.assertEquals(incomeTaxes.rent(0), 0);
        Assert.assertEquals(superTaxes.rent(0), 0);
    }

    /**
     * Test the owner of the Taxes.
     */
    @Test
    public void testOwner() {
        Assert.assertNull(incomeTaxes.owner());
        Assert.assertNull(superTaxes.owner());
    }
}