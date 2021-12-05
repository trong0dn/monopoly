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



    @Before
    public void init(){
        //Property Sq.1
        property = new Property(SquareInfo.SQUARE_1.getPosition(),JsonParse.parseJSON(1, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);

        //Inactive Community
        inactive = new Inactive(SquareInfo.SQUARE_2.getPosition(), JsonParse.parseJSON(2, String.valueOf(this.versions)));

        //Taxes
        taxes = new Taxes(SquareInfo.SQUARE_4.getPosition(),
                Objects.requireNonNull(JsonParse.parseJSON(4, String.valueOf(this.versions))));
    }

    @Test
    public void testName(){
        Assert.assertEquals("MEDITERRANEAN AVENUE", JsonParse.parseJSON(1, String.valueOf(this.versions)));
        Assert.assertEquals("COMMUNITY CHEST", JsonParse.parseJSON(2, String.valueOf(this.versions)));
        Assert.ass

    }



}
