import java.util.ArrayList;

public class Player{
    private String playerName;
    private int playerBalance;
    private ArrayList<Property> propertiesOwned;
    private boolean bankrupt;


    public Player(String playerName, int playerBalance,  boolean bankrupt) {
        this.playerName = playerName;
        this.playerBalance = playerBalance;
        this.propertiesOwned = new ArrayList<>();
        this.bankrupt = bankrupt;
    }


    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerBalance() {
        return playerBalance;
    }

    public void setPlayerBalance(int playerBalance) {
        this.playerBalance = playerBalance;
    }

    public ArrayList<Property> getPropertiesOwned() {
        return propertiesOwned;
    }

    public void printPropertiesOwned(){
        for (Property p: propertiesOwned){
            System.out.println(p.getPropertyName());
        }
    }

    public boolean isBankrupt() {
        return bankrupt;
    }


    public void buyProperty(Property newProperty){
        if (playerBalance - newProperty.getPrice() < 0){
            System.out.println("cannot purchase this property, current balance: $" + playerBalance + ". Price of property: $" + newProperty.getPrice());
        }
        else if (playerBalance - newProperty.getPrice() == 0){
            bankrupt = true;
        }
        else{
            playerBalance -= newProperty.getPrice();
            propertiesOwned.add(newProperty);
        }
    }



    public void sellProperty(Property property){
        playerBalance += property.getPrice();
        propertiesOwned.remove(property);
    }



    public void payRent(Property property){
        if (playerBalance - property.getRent() < 0){
            System.out.println("cannot pay rent on this property, current balance: $" + playerBalance + ". Property rent: $" + property.getRent());
        }
        else if (playerBalance - property.getRent() == 0){
            bankrupt = true;
        }
        else{
            playerBalance -= property.getRent();
        }
    }


    public void receiveRent(Property property){
        playerBalance += property.getRent();
    }


}