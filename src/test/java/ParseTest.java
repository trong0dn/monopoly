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
    // For Sq.1
    int rent = 2;
    int oneHouse = 10;
    int twoHouse = 30;
    int threeHouse = 90;
    int fourHouse = 160;
    int hotel = 250;
    int propertyCost = 60;
    int houses = 50;

    Versions versions = Versions.US;

    Property property;
    Inactive inactive;
    Taxes taxes;
    Railroad railroad;
    Jail jail;
    Utility utility;

    /**
     * Set up the squares before each test.
     */
    @Before
    public void init(){
        //Property      Sq.1    (Property)
        property = new Property(1,JsonParse.parseJSON(1, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);

        //Community     Sq.2    (Inactive)
        inactive = new Inactive(2, JsonParse.parseJSON(2, String.valueOf(this.versions)));

        //Income Tax    Sq.4    (TAXES)
        taxes = new Taxes(4,
                Objects.requireNonNull(JsonParse.parseJSON(4, String.valueOf(this.versions))));

        //Railroad      Sq.5    (Railroad)
        railroad = new Railroad(5, JsonParse.parseJSON(5, String.valueOf(this.versions)));

        //Jail          Sq.10   (Jail)
        jail = new Jail(10, JsonParse.parseJSON(10, String.valueOf(this.versions)), Jail.JailType.JUST_VISITING);

        //Utility       Sq.12   (Utility)
        utility = new Utility(12, JsonParse.parseJSON(12, String.valueOf(this.versions)));
    }

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
