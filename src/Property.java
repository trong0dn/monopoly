public class Property {
    private String propertyName;
    private int rent;
    private int price;
    private boolean isOwned;

    public Property(String name, int rent, int price, boolean isOwned) {
        this.propertyName = name;
        this.rent = rent;
        this.price = price;
        this.isOwned = isOwned;
    }


    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


}