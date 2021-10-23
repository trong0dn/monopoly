/**
 * This class represents the game board.
 */
public class GameBoard {
    private final int NUM_TILES = 41;
    private final Square[] board; // representation of the game board

    public GameBoard() {
        this.board = new Square[NUM_TILES];
        for (int i = 0; i < NUM_TILES; i++) {
            board[i] = makeSquare(i);
        }
        groupProperties();
        groupRailroads();
        groupUtilities();
    }

    /**
     * Get the size of the number of square tiles on the game board.
     * @return  int
     */
    public int size() {
        return this.NUM_TILES;
    }

    /**
     * Get the board.
     * @return  Square[], representation of the board game
     */
    public Square[] getBoard() {
        return this.board;
    }

    /**
     * Get board position from square position.
     * @param position  int, index of the postion
     * @return          Square
     */
    public Square square(int position) {
        return board[position];
    }

    /**
     * Downcast all square that is a property.
     * @param name  String, property name
     * @return      Property
     */
    private Property property(String name) {
        for (Square sq : board) {
            if (sq instanceof Property && sq.name().equals(name)) {
                return (Property) sq;
            }
        }
        return null;
    }

    /**
     * Create the square if the position is being occupied.
     * @param position  int, index of the position
     * @return          Square
     */
    private Square makeSquare(int position) {
        return switch (position) {
            case 0 -> go(position);
            case 1 -> oldKent(position);
            case 2 -> community(position);
            case 3 -> whitechapel(position);
            case 4 -> incomeTax(position);
            case 5 -> kingsCross(position);
            case 6 -> theAngelIslington(position);
            case 7 -> chance(position);
            case 8 -> euston(position);
            case 9 -> pentonville(position);
            case 10 -> justVisiting(position);
            case 11 -> pallMall(position);
            case 12 -> electricCompany(position);
            case 13 -> whitehall(position);
            case 14 -> northumrld(position);
            case 15 -> marylebone(position);
            case 16 -> bow(position);
            case 17 -> community(position);
            case 18 -> marlborough(position);
            case 19 -> vine(position);
            case 20 -> freeParking(position);
            case 21 -> strand(position);
            case 22 -> chance(position);
            case 23 -> fleet(position);
            case 24 -> trafalgar(position);
            case 25 -> fenchurch(position);
            case 26 -> leicester(position);
            case 27 -> conventry(position);
            case 28 -> waterWorks(position);
            case 29 -> piccadilly(position);
            case 30 -> goToJail(position);
            case 31 -> regent(position);
            case 32 -> oxford(position);
            case 33 -> community(position);
            case 34 -> bond(position);
            case 35 -> liverpool(position);
            case 36 -> chance(position);
            case 37 -> park(position);
            case 38 -> superTax(position);
            case 39 -> mayfair(position);
            case 40 -> inJail(position);
            default -> null;
        };
    }

    /**
     * Group properties by color set.
     */
    private void groupProperties() {
        Property brownA = (Property) square(1);
        Property brownB = (Property) square(3);
        Property skyA = (Property) square(6);
        Property skyB = (Property) square(8);
        Property skyC = (Property) square(9);
        Property pinkA = (Property) square(11);
        Property pinkB = (Property) square(13);
        Property pinkC = (Property) square(14);
        Property orangeA = (Property) square(16);
        Property orangeB = (Property) square(18);
        Property orangeC = (Property) square(19);
        Property redA = (Property) square(21);
        Property redB = (Property) square(23);
        Property redC = (Property) square(24);
        Property yellowA = (Property) square(26);
        Property yellowB = (Property) square(27);
        Property yellowC = (Property) square(29);
        Property greenA = (Property) square(31);
        Property greenB = (Property) square(32);
        Property greenC = (Property) square(24);
        Property blueA = (Property) square(37);
        Property blueB = (Property) square(29);

        // Brown property group
        brownA.setGroup(brownB);
        brownB.setGroup(brownA);
        // Sky-blue property group
        skyA.setGroup(skyB, skyC);
        skyB.setGroup(skyA, skyC);
        skyC.setGroup(skyA, skyB);
        // Pink property group
        pinkA.setGroup(pinkB, pinkC);
        pinkB.setGroup(pinkA, pinkC);
        pinkC.setGroup(pinkA, pinkB);
        // Orange property group
        orangeA.setGroup(orangeB, orangeC);
        orangeB.setGroup(orangeA, orangeC);
        orangeC.setGroup(orangeA, orangeB);
        // Red property group
        redA.setGroup(redB, redC);
        redB.setGroup(redA, redC);
        redC.setGroup(redA, redB);
        // Yellow property group
        yellowA.setGroup(yellowB, yellowC);
        yellowB.setGroup(yellowA, yellowC);
        yellowC.setGroup(yellowA, yellowB);
        // Green property group
        greenA.setGroup(greenB, greenC);
        greenB.setGroup(greenA, greenC);
        greenC.setGroup(greenA, greenB);
        // Blue property group
        blueA.setGroup(blueB);
        blueB.setGroup(blueA);
    }

    /**
     * Group railroads.
     */
    private void groupRailroads() {
        Railroad a = (Railroad) square(5);
        Railroad b = (Railroad) square(15);
        Railroad c = (Railroad) square(25);
        Railroad d = (Railroad) square(35);

        a.setGroup(b, c, d);
        b.setGroup(a, c, d);
        c.setGroup(a, b, d);
        d.setGroup(a, b, c);
    }

    /**
     * Group utilities.
     */
    private void groupUtilities() {
        Utility a = (Utility) square(12);
        Utility b = (Utility) square(28);

        a.setGroup(b);
        b.setGroup(a);
    }

    public Square go(int position) {
        return new Inactive(position, "Go");
    }

    public Square community(int position) {
        return new Inactive(position, "Community Chest");
    }

    public Square chance(int position) {
        return new Inactive(position, "Chance");
    }

    public Square incomeTax(int position) {
        return new Taxes(position, "Income Tax");
    }

    public Square superTax(int position) {
        return new Taxes(position,"Super Tax");
    }

    public Square electricCompany(int position) {
        return new Utility(position, "Electric Company");
    }

    public Square waterWorks(int position) {
        return new Utility(position, "Water Works");
    }

    public Square kingsCross(int position) {
        return new Railroad(position, "Kings Cross Station");
    }

    public Square marylebone(int position) {
        return new Railroad(position, "Marylebone Station");
    }

    public Square fenchurch(int position) {
        return new Railroad(position, "Fenchurch St. Station");
    }

    public Square liverpool(int position) {
        return new Railroad(position, "Liverpool St. Station");
    }

    public Square freeParking(int position) {
        return new Inactive(position, "Free Parking");
    }

    public Square goToJail(int position) {
        return new Jail(position, "Go to Jail", Jail.JailType.GOTO_JAIL);
    }

    public Square inJail(int position) {
        return new Jail(position, "In Jail", Jail.JailType.IN_JAIL);
    }

    public Square justVisiting(int position) {
        return new Jail(position, "Just Visiting", Jail.JailType.JUST_VISITING);
    }

    /**
     *
     * @param int position
     * @return Property(position, " Old Kent Road " rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses)
     */
    public Square oldKent(int position) {
        int rent = 2;
        int oneHouse = 10;
        int twoHouse = 30;
        int threeHouse = 90;
        int fourHouse = 160;
        int hotel = 250;
        int propertyCost = 60;
        int houses = 50;
        return new Property(position, "Old Kent Road",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    public Square whitechapel(int position) {
        int rent = 4;
        int oneHouse = 20;
        int twoHouse = 60;
        int threeHouse = 180;
        int fourHouse = 320;
        int hotel = 450;
        int propertyCost = 60;
        int houses = 50;
        return new Property(position, "Whitechapel Road",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    public Square theAngelIslington(int position) {
        int rent = 6;
        int oneHouse = 30;
        int twoHouse = 90;
        int threeHouse = 270;
        int fourHouse = 400;
        int hotel = 550;
        int propertyCost = 100;
        int houses = 50;
        return new Property(position, "The Angel, Islington",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    public Square euston(int position) {
        int rent = 6;
        int oneHouse = 30;
        int twoHouse = 90;
        int threeHouse = 270;
        int fourHouse = 400;
        int hotel = 550;
        int propertyCost = 100;
        int houses = 50;
        return new Property(position, "Euston Road",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    public Square pentonville(int position) {
        int rent = 6;
        int oneHouse = 30;
        int twoHouse = 90;
        int threeHouse = 270;
        int fourHouse = 400;
        int hotel = 550;
        int propertyCost = 120;
        int houses = 50;
        return new Property(position, "Pentonville Road",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    public Square pallMall(int position) {
        int rent = 10;
        int oneHouse = 50;
        int twoHouse = 150;
        int threeHouse = 450;
        int fourHouse = 625;
        int hotel = 750;
        int propertyCost = 140;
        int houses = 100;
        return new Property(position, "Pall Mall",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    public Square whitehall(int position) {
        int rent = 10;
        int oneHouse = 50;
        int twoHouse = 150;
        int threeHouse = 450;
        int fourHouse = 625;
        int hotel = 750;
        int propertyCost = 140;
        int houses = 100;
        return new Property(position, "Whitehall",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    public Square northumrld(int position) {
        int rent = 10;
        int oneHouse = 50;
        int twoHouse = 150;
        int threeHouse = 450;
        int fourHouse = 625;
        int hotel = 750;
        int propertyCost = 160;
        int houses = 100;
        return new Property(position, "Northumrl'd Avenue",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    public Square bow(int position) {
        int rent = 14;
        int oneHouse = 70;
        int twoHouse = 200;
        int threeHouse = 550;
        int fourHouse = 750;
        int hotel = 750;
        int propertyCost = 180;
        int houses = 100;
        return new Property(position, "Bow Street",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    public Square marlborough(int position) {
        int rent = 14;
        int oneHouse = 70;
        int twoHouse = 200;
        int threeHouse = 550;
        int fourHouse = 750;
        int hotel = 950;
        int propertyCost = 180;
        int houses = 100;
        return new Property(position, "Marlborough Street",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    public Square vine(int position) {
        int rent = 16;
        int oneHouse = 80;
        int twoHouse = 220;
        int threeHouse = 600;
        int fourHouse = 800;
        int hotel = 1000;
        int propertyCost = 200;
        int houses = 100;
        return new Property(position, "Vine Street",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    public Square strand(int position) {
        int rent = 18;
        int oneHouse = 90;
        int twoHouse = 250;
        int threeHouse = 700;
        int fourHouse = 875;
        int hotel = 1050;
        int propertyCost = 220;
        int houses = 150;
        return new Property(position, "Strand",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    public Square fleet(int position) {
        int rent = 18;
        int oneHouse = 90;
        int twoHouse = 250;
        int threeHouse = 700;
        int fourHouse = 875;
        int hotel = 1050;
        int propertyCost = 220;
        int houses = 150;
        return new Property(position, "Fleet Street",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    public Square trafalgar(int position) {
        int rent = 20;
        int oneHouse = 100;
        int twoHouse = 300;
        int threeHouse = 750;
        int fourHouse = 925;
        int hotel = 1100;
        int propertyCost = 260;
        int houses = 150;
        return new Property(position, "Trafalgar Square",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    public Square leicester(int position) {
        int rent = 22;
        int oneHouse = 110;
        int twoHouse = 330;
        int threeHouse = 800;
        int fourHouse = 975;
        int hotel = 1150;
        int propertyCost = 260;
        int houses = 150;
        return new Property(position, "Leicester",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    public Square conventry(int position) {
        int rent = 22;
        int oneHouse = 110;
        int twoHouse = 330;
        int threeHouse = 800;
        int fourHouse = 975;
        int hotel = 1150;
        int propertyCost = 280;
        int houses = 150;
        return new Property(position, "Conventry Street",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    public Square piccadilly(int position) {
        int rent = 24;
        int oneHouse = 120;
        int twoHouse = 360;
        int threeHouse = 850;
        int fourHouse = 1025;
        int hotel = 1200;
        int propertyCost = 280;
        int houses = 150;
        return new Property(position, "Piccadilly",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    public Square regent(int position) {
        int rent = 26;
        int oneHouse = 130;
        int twoHouse = 390;
        int threeHouse = 900;
        int fourHouse = 1100;
        int hotel = 1250;
        int propertyCost = 300;
        int houses = 200;
        return new Property(position, "Regent Street",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    public Square oxford(int position) {
        int rent = 26;
        int oneHouse = 130;
        int twoHouse = 390;
        int threeHouse = 900;
        int fourHouse = 1100;
        int hotel = 1275;
        int propertyCost = 300;
        int houses = 200;
        return new Property(position, "Oxford Street",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    public Square bond(int position) {
        int rent = 26;
        int oneHouse = 130;
        int twoHouse = 390;
        int threeHouse = 900;
        int fourHouse = 1100;
        int hotel = 1275;
        int propertyCost = 320;
        int houses = 200;
        return new Property(position, "Bond Street",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    public Square park(int position) {
        int rent = 35;
        int oneHouse = 175;
        int twoHouse = 500;
        int threeHouse = 1100;
        int fourHouse = 1500;
        int hotel = 1500;
        int propertyCost = 350;
        int houses = 200;
        return new Property(position, "Park Lane",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    public Square mayfair(int position) {
        int rent = 35;
        int oneHouse = 175;
        int twoHouse = 500;
        int threeHouse = 1100;
        int fourHouse = 1300;
        int hotel = 1500;
        int propertyCost = 400;
        int houses = 200;
        return new Property(position, "Mayfair",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }
}
