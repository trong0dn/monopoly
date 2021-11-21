package monopoly17;

/**
 * This class represents the game board.
 * @author Elisha Catherasoo & Trong Nguyen
 */
public class GameBoard {
    private final int NUM_TILES = 41;
    private final Square[] board; // representation of the game board

    /**
     * Constructor for GameBoard.
     */
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
        return switch (position) { // Using cases to represent individual squares on board. Clockwise.
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
        Property brownA = (Property) square(1); // Properties associated with cases. ie case 1.
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
        Property greenC = (Property) square(34);
        Property blueA = (Property) square(37);
        Property blueB = (Property) square(39);

        // BROWN property group
        brownA.setGroup(brownB);
        brownB.setGroup(brownA);

        // SKY-BLUE property group
        skyA.setGroup(skyB, skyC);
        skyB.setGroup(skyA, skyC);
        skyC.setGroup(skyA, skyB);

        // PINK property group
        pinkA.setGroup(pinkB, pinkC);
        pinkB.setGroup(pinkA, pinkC);
        pinkC.setGroup(pinkA, pinkB);

        // ORANGE property group
        orangeA.setGroup(orangeB, orangeC);
        orangeB.setGroup(orangeA, orangeC);
        orangeC.setGroup(orangeA, orangeB);

        // RED property group
        redA.setGroup(redB, redC);
        redB.setGroup(redA, redC);
        redC.setGroup(redA, redB);

        // YELLOW property group
        yellowA.setGroup(yellowB, yellowC);
        yellowB.setGroup(yellowA, yellowC);
        yellowC.setGroup(yellowA, yellowB);

        // GREEN property group
        greenA.setGroup(greenB, greenC);
        greenB.setGroup(greenA, greenC);
        greenC.setGroup(greenA, greenB);

        // BLUE property group
        blueA.setGroup(blueB);
        blueB.setGroup(blueA);
    }

    /**
     * Group railroads.
     */
    private void groupRailroads() {
        Railroad a = (Railroad) square(5);  // Railroad on square 5
        Railroad b = (Railroad) square(15); // Railroad on square 15
        Railroad c = (Railroad) square(25); // Railroad on square 25
        Railroad d = (Railroad) square(35); // Railroad on square 35

        a.setGroup(b, c, d);
        b.setGroup(a, c, d);
        c.setGroup(a, b, d);
        d.setGroup(a, b, c);
    }

    /**
     * Group utilities.
     */
    private void groupUtilities() {
        Utility a = (Utility) square(12); // Utilities on square 12
        Utility b = (Utility) square(28); // Utilities on square 28

        a.setGroup(b);
        b.setGroup(a);
    }

    /**
     * Setup "GO" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square go(int position) {
        return new Inactive(position, "GO");
    }

    /**
     * Setup "COMMUNITY CHEST" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square community(int position) {
        return new Inactive(position, "COMMUNITY CHEST");
    }

    /**
     * Setup "CHANCE" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square chance(int position) {
        return new Inactive(position, "CHANCE");
    }

    /**
     * Setup "INCOME TAX" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square incomeTax(int position) {
        return new Taxes(position, true);
    }

    /**
     * Setup "SUPER TAX" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square superTax(int position) {
        return new Taxes(position,false);
    }

    /**
     * Setup "ELECTRIC COMPANY" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square electricCompany(int position) {
        return new Utility(position, "ELECTRIC COMPANY");
    }

    /**
     * Setup "WATER WORKS" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square waterWorks(int position) {
        return new Utility(position, "WATER WORKS");
    }

    /**
     * Setup "KINGS CROSS STATION" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square kingsCross(int position) {
        return new Railroad(position, "KINGS CROSS STATION");
    }

    /**
     * Setup "MARYLEBONE STATION" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square marylebone(int position) {
        return new Railroad(position, "MARYLEBONE STATION");
    }

    /**
     * Setup "FENCHURCH ST. STATION" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square fenchurch(int position) {
        return new Railroad(position, "FENCHURCH ST. STATION");
    }

    /**
     * Setup "LIVERPOOL ST. STATION" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square liverpool(int position) {
        return new Railroad(position, "LIVERPOOL ST. STATION");
    }

    /**
     * Setup "FREE PARKING" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square freeParking(int position) {
        return new Inactive(position, "FREE PARKING");
    }

    /**
     * Setup "GO TO JAIL" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square goToJail(int position) {
        return new Jail(position, "GO TO JAIL", Jail.JailType.GOTO_JAIL);
    }

    /**
     * Setup "IN JAIL" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square inJail(int position) {
        return new Jail(position, "IN JAIL", Jail.JailType.IN_JAIL);
    }

    /**
     * Setup "JUST VISITING" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square justVisiting(int position) {
        return new Jail(position, "JUST VISITING", Jail.JailType.JUST_VISITING);
    }

    /**
     * Setup "OLD KENT ROAD" square tile on the board.
     * @param position  int
     * @return  Square
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
        return new Property(position, "OLD KENT ROAD",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "WHITECHAPEL ROAD" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square whitechapel(int position) {
        int rent = 4;
        int oneHouse = 20;
        int twoHouse = 60;
        int threeHouse = 180;
        int fourHouse = 320;
        int hotel = 450;
        int propertyCost = 60;
        int houses = 50;
        return new Property(position, "WHITECHAPEL ROAD",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "THE ANGEL, ISLINGTON" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square theAngelIslington(int position) {
        int rent = 6;
        int oneHouse = 30;
        int twoHouse = 90;
        int threeHouse = 270;
        int fourHouse = 400;
        int hotel = 550;
        int propertyCost = 100;
        int houses = 50;
        return new Property(position, "THE ANGEL, ISLINGTON",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "EUSTON ROAD" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square euston(int position) {
        int rent = 6;
        int oneHouse = 30;
        int twoHouse = 90;
        int threeHouse = 270;
        int fourHouse = 400;
        int hotel = 550;
        int propertyCost = 100;
        int houses = 50;
        return new Property(position, "EUSTON ROAD",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "PENTONVILLE ROAD" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square pentonville(int position) {
        int rent = 6;
        int oneHouse = 30;
        int twoHouse = 90;
        int threeHouse = 270;
        int fourHouse = 400;
        int hotel = 550;
        int propertyCost = 120;
        int houses = 50;
        return new Property(position, "PENTONVILLE ROAD",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "PALL MALL" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square pallMall(int position) {
        int rent = 10;
        int oneHouse = 50;
        int twoHouse = 150;
        int threeHouse = 450;
        int fourHouse = 625;
        int hotel = 750;
        int propertyCost = 140;
        int houses = 100;
        return new Property(position, "PALL MALL",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "WHITEHALL" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square whitehall(int position) {
        int rent = 10;
        int oneHouse = 50;
        int twoHouse = 150;
        int threeHouse = 450;
        int fourHouse = 625;
        int hotel = 750;
        int propertyCost = 140;
        int houses = 100;
        return new Property(position, "WHITEHALL",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "NORTHUMRL'D AVENUE" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square northumrld(int position) {
        int rent = 10;
        int oneHouse = 50;
        int twoHouse = 150;
        int threeHouse = 450;
        int fourHouse = 625;
        int hotel = 750;
        int propertyCost = 160;
        int houses = 100;
        return new Property(position, "NORTHUMRL'D AVENUE",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "BOW STREET" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square bow(int position) {
        int rent = 14;
        int oneHouse = 70;
        int twoHouse = 200;
        int threeHouse = 550;
        int fourHouse = 750;
        int hotel = 750;
        int propertyCost = 180;
        int houses = 100;
        return new Property(position, "BOW STREET",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "MARLBOROUGH STREET" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square marlborough(int position) {
        int rent = 14;
        int oneHouse = 70;
        int twoHouse = 200;
        int threeHouse = 550;
        int fourHouse = 750;
        int hotel = 950;
        int propertyCost = 180;
        int houses = 100;
        return new Property(position, "MARLBOROUGH STREET",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "VINE STREET" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square vine(int position) {
        int rent = 16;
        int oneHouse = 80;
        int twoHouse = 220;
        int threeHouse = 600;
        int fourHouse = 800;
        int hotel = 1000;
        int propertyCost = 200;
        int houses = 100;
        return new Property(position, "VINE STREET",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "STRAND" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square strand(int position) {
        int rent = 18;
        int oneHouse = 90;
        int twoHouse = 250;
        int threeHouse = 700;
        int fourHouse = 875;
        int hotel = 1050;
        int propertyCost = 220;
        int houses = 150;
        return new Property(position, "STRAND",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "FLEET STREET" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square fleet(int position) {
        int rent = 18;
        int oneHouse = 90;
        int twoHouse = 250;
        int threeHouse = 700;
        int fourHouse = 875;
        int hotel = 1050;
        int propertyCost = 220;
        int houses = 150;
        return new Property(position, "FLEET STREET",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "TRAFALGAR SQUARE" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square trafalgar(int position) {
        int rent = 20;
        int oneHouse = 100;
        int twoHouse = 300;
        int threeHouse = 750;
        int fourHouse = 925;
        int hotel = 1100;
        int propertyCost = 260;
        int houses = 150;
        return new Property(position, "TRAFALGAR SQUARE",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "LEICESTER" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square leicester(int position) {
        int rent = 22;
        int oneHouse = 110;
        int twoHouse = 330;
        int threeHouse = 800;
        int fourHouse = 975;
        int hotel = 1150;
        int propertyCost = 260;
        int houses = 150;
        return new Property(position, "LEICESTER",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "CONVENTRY STREET" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square conventry(int position) {
        int rent = 22;
        int oneHouse = 110;
        int twoHouse = 330;
        int threeHouse = 800;
        int fourHouse = 975;
        int hotel = 1150;
        int propertyCost = 280;
        int houses = 150;
        return new Property(position, "CONVENTRY STREET",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "PICCADILLY" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square piccadilly(int position) {
        int rent = 24;
        int oneHouse = 120;
        int twoHouse = 360;
        int threeHouse = 850;
        int fourHouse = 1025;
        int hotel = 1200;
        int propertyCost = 280;
        int houses = 150;
        return new Property(position, "PICCADILLY",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "REGENT STREET" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square regent(int position) {
        int rent = 26;
        int oneHouse = 130;
        int twoHouse = 390;
        int threeHouse = 900;
        int fourHouse = 1100;
        int hotel = 1250;
        int propertyCost = 300;
        int houses = 200;
        return new Property(position, "REGENT STREET",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "OXFORD STREET" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square oxford(int position) {
        int rent = 26;
        int oneHouse = 130;
        int twoHouse = 390;
        int threeHouse = 900;
        int fourHouse = 1100;
        int hotel = 1275;
        int propertyCost = 300;
        int houses = 200;
        return new Property(position, "OXFORD STREET",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "BOND STREET" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square bond(int position) {
        int rent = 26;
        int oneHouse = 130;
        int twoHouse = 390;
        int threeHouse = 900;
        int fourHouse = 1100;
        int hotel = 1275;
        int propertyCost = 320;
        int houses = 200;
        return new Property(position, "BOND STREET",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "PARK LANE" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square park(int position) {
        int rent = 35;
        int oneHouse = 175;
        int twoHouse = 500;
        int threeHouse = 1100;
        int fourHouse = 1500;
        int hotel = 1500;
        int propertyCost = 350;
        int houses = 200;
        return new Property(position, "PARK LANE",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "MAYFAIR" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square mayfair(int position) {
        int rent = 35;
        int oneHouse = 175;
        int twoHouse = 500;
        int threeHouse = 1100;
        int fourHouse = 1300;
        int hotel = 1500;
        int propertyCost = 400;
        int houses = 200;
        return new Property(position, "MAYFAIR",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }
}
