import org.junit.Test;

import static org.junit.Assert.*;

public class PropertyTest {
    private HumanPlayer humanPlayer = new HumanPlayer("test");

    Property property;

    private int position1;
    int rent = 2;
    int oneHouse = 10;
    int twoHouse = 30;
    int threeHouse = 90;
    int fourHouse = 160;
    int hotel = 250;
    int propertyCost = 60;
    int houses = 50;
    private final Square oldkent = new Property(position1, "OLD KENT ROAD",
            rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);

    int position2;
    private final Square whitechapel = new Property(position2, "WHITECHAPEL ROAD",
                        4, 20, 60, 180, 320, 450, 60, 50);



    //this function takes in a parameter that is never used?
    @Test
    public void rent() {
    }


    /**
     * tests the updateMonopoly function
     * checks if properties owned belong in a set
     */
    @Test
    public void updateMonopoly() {
        humanPlayer.addProperty(oldkent);
        humanPlayer.addProperty(whitechapel);
        property.updateMonopoly(humanPlayer);
        assertEquals(true, property.isMonopoly() );


        humanPlayer.removeProperty(whitechapel);
        property.updateMonopoly(humanPlayer);
        assertEquals(false, property.isMonopoly() );
    }


}