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
        buildings = 0;
        monopoly = false;
        owned = false;
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
        //updateMonopoly(player);
    }

    @Override
    public int rent(int value) {
        return 0;
    }

    @Override
    public Player owner() {
        return this.owner;
    }

    public boolean isMonopoly() {
        return this.monopoly;
    }

    public int getHouseCost() {
        return this.houseCost;
    }  
    
    public int getCost() {
        return this.propertyCost;
    }

    public int getRent() {
        return this.rent;
    }

    public int getPropertyCost() {
        return propertyCost;
    }

}

