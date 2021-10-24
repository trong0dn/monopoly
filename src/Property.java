import java.util.LinkedList;
import java.util.Queue;

/**
 * This class represents the way properties are structured in the game.
 */
public class Property implements Square{
    private final int rent;
    private final int oneHouse;
    private final int twoHouse;
    private final int threeHouse;
    private final int fourHouse;
    private final int hotel;

    private final int position;
    private final String name;
    private Player owner;
    private boolean owned;
    private final Property[] others;
    private final int propertyCost; // cost to purchase the property
    private final int houseCost; // cost to purchase one house on the property
    private int buildings; // number of buildings on property
    private boolean monopoly; // player may own all property set

    public Property(int position, String name, int rent, int oneHouse, int twoHouse, int threeHouse, int fourHouse,
                    int hotel, int propertyCost, int houseCost) {
        this.position = position;
        this.name = name;
        this.rent = rent;
        this.oneHouse = oneHouse;
        this.twoHouse = twoHouse;
        this.threeHouse = threeHouse;
        this.fourHouse = fourHouse;
        this.hotel = hotel;
        this.propertyCost = propertyCost;
        this.houseCost = houseCost;
        this.others = new Property[2];
        this.buildings = 0;
        this.monopoly = false;
        this.owned = false;
    }

    @Override
    public int position() {
        return this.position;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public boolean isOwnable() {
        return true;
    }

    @Override
    public boolean isOwned() {
        return this.owned;
    }

    @Override
    public int cost() {
        return this.propertyCost;
    }

    @Override
    public void purchase(Player player) {
        owned = true;
        owner = player;
        updateMonopoly(player);
    }

    @Override
    public int rent(int value) {
        if (!owned) {
            return 0;
        }
        switch (buildings) {
            case 0:
                if (this.monopoly) {
                    return 2 * this.rent;
                } else {
                    return this.rent;
                }
            case 1: return oneHouse;
            case 2: return twoHouse;
            case 3: return threeHouse;
            case 4: return fourHouse;
            case 5: return hotel;
            default: return 0;
        }
    }

    @Override
    public Player owner() {
        return this.owner;
    }

    public boolean isMonopoly() {
        return this.monopoly;
    }

    public boolean setMonopoly() {
        return this.monopoly = true;
    }

    public boolean breakMonopoly() {
        return this.monopoly = false;
    }

    public void updateMonopoly(Player player) {
        boolean setA = false;
        boolean setB = false;

        if (others[1] == null) {
            setB = true;
        }
        Queue<Property> properties = new LinkedList<>();
        // Check if the square owned by a player is a property
        for (Square square : player.properties()) {
            if (square instanceof Property) {
                properties.add((Property) square);
            }
        }
        // Check if property owned by each player is part of a set
        for (Property property : properties) {
            if (property.name().equals(others[0].name())) {
                setA = true;
            }
            if (others[1] != null && property.name().equals(others[1].name())) {
                setB = true;
            }
        }
        // Set monopoly if both properties are part of the same set
        if (setA && setB) {
            setMonopoly();
            others[0].setMonopoly();
            if (others[1] != null) {
                others[1].setMonopoly();
            }
        } else {
            breakMonopoly();
            others[0].breakMonopoly();
            if (others[1] != null) {
                others[1].breakMonopoly();
            }
        }
    }

    /**
     * Getter method for cost of house in game.
     * @return houseCost
     */
    public int getHouseCost() {
        return this.houseCost;
    }

    /**
     * Method used for setting objects into groups
     * @param propertyA - propertyA, propertyB:null
     */
    public void setGroup(Property propertyA) {
        this.setGroup(propertyA, null);
    }

    /**
     *  Method used in railroad method for setting railroads in game
     * @param propertyA - Railroad
     * @param propertyB - Railroad
     */
    public void setGroup(Property propertyA, Property propertyB) {
        this.others[0] = propertyA;
        this.others[1] = propertyB;
    }
}

