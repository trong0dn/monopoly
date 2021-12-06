import monopoly17.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

/**
 * Test class for parsing for International Languages.
 * @author Francisco De Grano & Ibrahim Almalki
 */
public class ParseTest {
    private Versions versions = Versions.US;

    /**
     * Test the name of square 1.
     */
    @Test
    public void testSquareName1(){
        Assert.assertEquals("MEDITERRANEAN AVENUE", JsonParse.parseJSON(1, String.valueOf(this.versions)));
    }

    /**
     * Test the name of square 2.
     */
    @Test
    public void testSquareName2() {
        Assert.assertEquals("COMMUNITY CHEST", JsonParse.parseJSON(2, String.valueOf(this.versions)));
    }

    /**
     * Test the name of square 4.
     */
    @Test
    public void testSquareName4() {
        Assert.assertEquals("INCOME TAX", JsonParse.parseJSON(4, String.valueOf(this.versions)));
        Assert.assertEquals("READING RAILROAD", JsonParse.parseJSON(5, String.valueOf(this.versions)));
        Assert.assertEquals("JUST VISITING", JsonParse.parseJSON(10, String.valueOf(this.versions)));
        Assert.assertEquals("ELECTRIC COMPANY", JsonParse.parseJSON(12, String.valueOf(this.versions)));
    }

    /**
     * Test the name of square 5.
     */
    @Test
    public void testSquareName5() {
        Assert.assertEquals("READING RAILROAD", JsonParse.parseJSON(5, String.valueOf(this.versions)));
    }

    /**
     * Test the name of square 10.
     */
    @Test
    public void testSquareName10() {
        Assert.assertEquals("JUST VISITING", JsonParse.parseJSON(10, String.valueOf(this.versions)));
    }

    /**
     * Test the name of square 12.
     */
    @Test
    public void testSquareName12() {
        Assert.assertEquals("ELECTRIC COMPANY", JsonParse.parseJSON(12, String.valueOf(this.versions)));
    }
}
