import monopoly17.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;
/*
    helpful link https://mkyong.com/java/jsonassert-how-to-unit-test-json-data/
 */

/**
 * Test class for parsing for International Languages.
 *
 * @author Francisco De Grano & Ibrahim Almalki
 * Modified By...
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

    @Before
    public void init(){
        // Property Sq.1 (Property)
        property = new Property(SquareInfo.SQUARE_1.getPosition(),JsonParse.parseJSON(1, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);

        // Community Sq.2 (Inactive)
        inactive = new Inactive(SquareInfo.SQUARE_2.getPosition(), JsonParse.parseJSON(2, String.valueOf(this.versions)));

        // Income Tax Sq.4 (TAXES)
        taxes = new Taxes(SquareInfo.SQUARE_4.getPosition(),
                Objects.requireNonNull(JsonParse.parseJSON(4, String.valueOf(this.versions))));

        // Railroad
        railroad = new Railroad(SquareInfo.SQUARE_5.getPosition(), JsonParse.parseJSON(5, String.valueOf(this.versions)));

        // Jail
        jail = new Jail(SquareInfo.SQUARE_10.getPosition(), JsonParse.parseJSON(10, String.valueOf(this.versions)), Jail.JailType.JUST_VISITING);

        //Utility
        utility = new Utility(SquareInfo.SQUARE_12.getPosition(), JsonParse.parseJSON(12, String.valueOf(this.versions)));
    }

    @Test
    public void testName(){

        Assert.assertEquals("MEDITERRANEAN AVENUE", JsonParse.parseJSON(1, String.valueOf(this.versions)));
        Assert.assertEquals("COMMUNITY CHEST", JsonParse.parseJSON(2, String.valueOf(this.versions)));
        Assert.assertEquals("INCOME TAX", JsonParse.parseJSON(4, String.valueOf(this.versions)));
        Assert.assertEquals("READING RAILROAD", JsonParse.parseJSON(5, String.valueOf(this.versions)));
        Assert.assertEquals("JUST VISITING", JsonParse.parseJSON(10, String.valueOf(this.versions)));
        Assert.assertEquals("ELECTRIC COMPANY", JsonParse.parseJSON(12, String.valueOf(this.versions)));
    }

    @Test
    public void testPosition(){
        Assert.assertEquals(1, SquareInfo.SQUARE_1.getPosition());
        Assert.assertEquals(2, SquareInfo.SQUARE_2.getPosition());
        Assert.assertEquals(4, SquareInfo.SQUARE_4.getPosition());
        Assert.assertEquals(5, SquareInfo.SQUARE_5.getPosition());
        Assert.assertEquals(10, SquareInfo.SQUARE_10.getPosition());
        Assert.assertEquals(12, SquareInfo.SQUARE_12.getPosition());
    }




}
