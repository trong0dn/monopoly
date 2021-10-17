import java.util.ArrayList;

public class Player{
    private String playerName;
    private int playerBalance;
    private ArrayList<Property> propertiesOwned;
    private Property position;
    private boolean bankrupt;


    public Player(String playerName, int playerBalance, Property position, boolean bankrupt) {
        this.playerName = playerName;
        this.playerBalance = playerBalance;
        this.propertiesOwned = new ArrayList<>();
        this.position = position;
        this.bankrupt = bankrupt;
    }


    public String getPlayerName() {
        return this.playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerBalance() {
        return this.playerBalance;
    }

    public void setPlayerBalance(int playerBalance) {
        this.playerBalance = playerBalance;
    }

    public ArrayList<Property> getPropertiesOwned() {
        return propertiesOwned;
    }

    public Property getPosition() {
        return position;
    }

    public void setPosition(Property position) {
        this.position = position;
    }

    public boolean isBankrupt() {
        return bankrupt;
    }

    public void setBankrupt(boolean bankrupt) {
        this.bankrupt = bankrupt;
    }


    public void buyProperty(Property newProperty, int price){
        playerBalance =- price;
        propertiesOwned.add(newProperty);

    }

}