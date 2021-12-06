package monopoly17;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class represents the game board.
 * @author Elisha Catherasoo & Trong Nguyen
 */
public class GameBoard implements Serializable {
    public static final int BOARD_SIZE = 40;
    private final Square[] board; // representation of the game board
    private final Versions versions;

    /**
     * Default constructor for GameBoard.
     */
    public GameBoard() {
        this(Versions.US);
    }

    /**
     * Constructor for GameBoard.
     */
    public GameBoard(Versions version) {
        this.versions = version;
        this.board = new Square[BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
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
        return BOARD_SIZE;
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
     * Create the square if the position is being occupied.
     * @param position  int, index of the position
     * @return          Square
     */
    private Square makeSquare(int position) {
        return switch (position) {  // Using cases to represent individual squares on board. Clockwise.
            case 0 -> square0(0);    // go
            case 1 -> square1(1);    // oldKent
            case 2 -> square2(2);    // community
            case 3 -> square3(3);    // whitechapel
            case 4 -> square4(4);    // incomeTax
            case 5 -> square5(5);    // kingsCross
            case 6 -> square6(6);    // theAngelIslington
            case 7 -> square7(7);    // chance
            case 8 -> square8(8);    // euston
            case 9 -> square9(9);    // pentonville
            case 10 -> square10(10);  // justVisiting
            case 11 -> square11(11);  // pallMall
            case 12 -> square12(12);  // electricCompany
            case 13 -> square13(13);  // whitehall
            case 14 -> square14(14);  // northumrld
            case 15 -> square15(15);  // marylebone
            case 16 -> square16(16);  // bow
            case 17 -> square17(17);  // community
            case 18 -> square18(18);  // marlborough
            case 19 -> square19(19);  // vine
            case 20 -> square20(20);  // freeParking
            case 21 -> square21(21);  // strand
            case 22 -> square22(22);  // chance
            case 23 -> square23(23);  // fleet
            case 24 -> square24(24);  // trafalgar
            case 25 -> square25(25);  // fenchurch
            case 26 -> square26(26);  // leicester
            case 27 -> square27(27);  // conventry
            case 28 -> square28(28);  // waterWorks
            case 29 -> square29(29);  // piccadilly
            case 30 -> square30(30);  // goToJail
            case 31 -> square31(31);  // regent
            case 32 -> square32(32);  // oxford
            case 33 -> square33(33);  // community
            case 34 -> square34(34);  // bond
            case 35 -> square35(35);  // liverpool
            case 36 -> square36(36);  // chance
            case 37 -> square37(37);  // park
            case 38 -> square38(38);  // superTax
            case 39 -> square39(39);  // mayfair
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
     * @return  Square
     */
    public Square square0(int position) {
        return new Inactive(position, JsonParse.parseJSON(position, String.valueOf(this.versions)));
    }

    /**
     * Setup "Square 1" tile on the board.
     * @return  Square
     */
    public Square square1(int position) {
        int rent = 2;
        int oneHouse = 10;
        int twoHouse = 30;
        int threeHouse = 90;
        int fourHouse = 160;
        int hotel = 250;
        int propertyCost = 60;
        int houses = 50;
        return new Property(position, JsonParse.parseJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "COMMUNITY CHEST" square tile on the board.
     * @return  Square
     */
    public Square square2(int position) {
        return new Inactive(position, JsonParse.parseJSON(position, String.valueOf(this.versions)));
    }

    /**
     * Setup "Square 3" tile on the board.
     * @return  Square
     */
    public Square square3(int position) {
        int rent = 4;
        int oneHouse = 20;
        int twoHouse = 60;
        int threeHouse = 180;
        int fourHouse = 320;
        int hotel = 450;
        int propertyCost = 60;
        int houses = 50;
        return new Property(position, JsonParse.parseJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "INCOME TAX" square tile on the board.
     * @return  Square
     */
    public Square square4(int position) {
        return new Taxes(position, Objects.requireNonNull(JsonParse.parseJSON(position, String.valueOf(this.versions))));

    }

    /**
     * Setup "KINGS CROSS STATION" square tile on the board.
     * @return  Square
     */
    public Square square5(int position) {
        return new Railroad(position, JsonParse.parseJSON(position, String.valueOf(this.versions)));

    }

    /**
     * Setup "Square 6" tile on the board.
     * @return  Square
     */
    public Square square6(int position) {
        int rent = 6;
        int oneHouse = 30;
        int twoHouse = 90;
        int threeHouse = 270;
        int fourHouse = 400;
        int hotel = 550;
        int propertyCost = 100;
        int houses = 50;
        return new Property(position, JsonParse.parseJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "CHANCE" square tile on the board.
     * @return  Square
     */
    public Square square7(int position) {
        return new Inactive(position, JsonParse.parseJSON(position, String.valueOf(this.versions)));

    }

    /**
     * Setup "Square 8" tile on the board.
     * @return  Square
     */
    public Square square8(int position) {
        int rent = 6;
        int oneHouse = 30;
        int twoHouse = 90;
        int threeHouse = 270;
        int fourHouse = 400;
        int hotel = 550;
        int propertyCost = 100;
        int houses = 50;
        return new Property(position, JsonParse.parseJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "Square 9" tile on the board.
     * @return  Square
     */
    public Square square9(int position) {
        int rent = 6;
        int oneHouse = 30;
        int twoHouse = 90;
        int threeHouse = 270;
        int fourHouse = 400;
        int hotel = 550;
        int propertyCost = 120;
        int houses = 50;
        return new Property(position, JsonParse.parseJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "JUST VISITING" square tile on the board.
     * @return  Square
     */
    public Square square10(int position) {
        return new Jail(position, JsonParse.parseJSON(position, String.valueOf(this.versions)), Jail.JailType.JUST_VISITING);
    }

    /**
     * Setup "Square 11" tile on the board.
     * @return  Square
     */
    public Square square11(int position) {
        int rent = 10;
        int oneHouse = 50;
        int twoHouse = 150;
        int threeHouse = 450;
        int fourHouse = 625;
        int hotel = 750;
        int propertyCost = 140;
        int houses = 100;
        return new Property(position, JsonParse.parseJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "ELECTRIC COMPANY" square tile on the board.
     * @return  Square
     */
    public Square square12(int position) {
        return new Utility(position, JsonParse.parseJSON(position, String.valueOf(this.versions)));
    }

    /**
     * Setup "Square 13" tile on the board.
     * @return  Square
     */
    public Square square13(int position) {
        int rent = 10;
        int oneHouse = 50;
        int twoHouse = 150;
        int threeHouse = 450;
        int fourHouse = 625;
        int hotel = 750;
        int propertyCost = 140;
        int houses = 100;
        return new Property(position, JsonParse.parseJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "Square 14" tile on the board.
     * @return  Square
     */
    public Square square14(int position) {
        int rent = 10;
        int oneHouse = 50;
        int twoHouse = 150;
        int threeHouse = 450;
        int fourHouse = 625;
        int hotel = 750;
        int propertyCost = 160;
        int houses = 100;
        return new Property(position, JsonParse.parseJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "MARYLEBONE STATION" square tile on the board.
     * @return  Square
     */
    public Square square15(int position) {
        return new Railroad(position, JsonParse.parseJSON(position, String.valueOf(this.versions)));
    }

    /**
     * Setup "Square 16" tile on the board.
     * @return  Square
     */
    public Square square16(int position) {
        int rent = 14;
        int oneHouse = 70;
        int twoHouse = 200;
        int threeHouse = 550;
        int fourHouse = 750;
        int hotel = 750;
        int propertyCost = 180;
        int houses = 100;
        return new Property(position, JsonParse.parseJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "COMMUNITY CHEST" square tile on the board.
     * @return  Square
     */
    public Square square17(int position) {
        return new Inactive(position, JsonParse.parseJSON(position, String.valueOf(this.versions)));
    }

    /**
     * Setup "Square 18" tile on the board.
     * @return  Square
     */
    public Square square18(int position) {
        int rent = 14;
        int oneHouse = 70;
        int twoHouse = 200;
        int threeHouse = 550;
        int fourHouse = 750;
        int hotel = 950;
        int propertyCost = 180;
        int houses = 100;
        return new Property(position, JsonParse.parseJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "Square 19" tile on the board.
     * @return  Square
     */
    public Square square19(int position) {
        int rent = 16;
        int oneHouse = 80;
        int twoHouse = 220;
        int threeHouse = 600;
        int fourHouse = 800;
        int hotel = 1000;
        int propertyCost = 200;
        int houses = 100;
        return new Property(position, JsonParse.parseJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "FREE PARKING" square tile on the board.
     * @return  Square
     */
    public Square square20(int position) {
        return new Inactive(position, JsonParse.parseJSON(position, String.valueOf(this.versions)));
    }

    /**
     * Setup "Square 21" tile on the board.
     * @return  Square
     */
    public Square square21(int position) {
        int rent = 18;
        int oneHouse = 90;
        int twoHouse = 250;
        int threeHouse = 700;
        int fourHouse = 875;
        int hotel = 1050;
        int propertyCost = 220;
        int houses = 150;
        return new Property(position, JsonParse.parseJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "CHANCE" square tile on the board.
     * @return  Square
     */
    public Square square22(int position) {
        return new Inactive(position, JsonParse.parseJSON(position, String.valueOf(this.versions)));
    }

    /**
     * Setup "Square 23" tile on the board.
     * @return  Square
     */
    public Square square23(int position) {
        int rent = 18;
        int oneHouse = 90;
        int twoHouse = 250;
        int threeHouse = 700;
        int fourHouse = 875;
        int hotel = 1050;
        int propertyCost = 220;
        int houses = 150;
        return new Property(position, JsonParse.parseJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "Square 24" tile on the board.
     * @return  Square
     */
    public Square square24(int position) {
        int rent = 20;
        int oneHouse = 100;
        int twoHouse = 300;
        int threeHouse = 750;
        int fourHouse = 925;
        int hotel = 1100;
        int propertyCost = 260;
        int houses = 150;
        return new Property(position, JsonParse.parseJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "FENCHURCH ST. STATION" square tile on the board.
     * @return  Square
     */
    public Square square25(int position) {
        return new Railroad(position, JsonParse.parseJSON(position, String.valueOf(this.versions)));
    }

    /**
     * Setup "Square 26" tile on the board.
     * @return  Square
     */
    public Square square26(int position) {
        int rent = 22;
        int oneHouse = 110;
        int twoHouse = 330;
        int threeHouse = 800;
        int fourHouse = 975;
        int hotel = 1150;
        int propertyCost = 260;
        int houses = 150;
        return new Property(position, JsonParse.parseJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "Square 27" tile on the board.
     * @return  Square
     */
    public Square square27(int position) {
        int rent = 22;
        int oneHouse = 110;
        int twoHouse = 330;
        int threeHouse = 800;
        int fourHouse = 975;
        int hotel = 1150;
        int propertyCost = 280;
        int houses = 150;
        return new Property(position, JsonParse.parseJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "WATER WORKS" square tile on the board.
     * @return  Square
     */
    public Square square28(int position) {
        return new Utility(position, JsonParse.parseJSON(position, String.valueOf(this.versions)));
    }

    /**
     * Setup "Square 29" tile on the board.
     * @return  Square
     */
    public Square square29(int position) {
        int rent = 24;
        int oneHouse = 120;
        int twoHouse = 360;
        int threeHouse = 850;
        int fourHouse = 1025;
        int hotel = 1200;
        int propertyCost = 280;
        int houses = 150;
        return new Property(position, JsonParse.parseJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "GO TO JAIL" square tile on the board.
     * @return  Square
     */
    public Square square30(int position) {
        return new Jail(position, JsonParse.parseJSON(position, String.valueOf(this.versions)), Jail.JailType.GOTO_JAIL);
    }

    /**
     * Setup "Square 31" square tile on the board.
     * @return  Square
     */
    public Square square31(int position) {
        int rent = 26;
        int oneHouse = 130;
        int twoHouse = 390;
        int threeHouse = 900;
        int fourHouse = 1100;
        int hotel = 1250;
        int propertyCost = 300;
        int houses = 200;
        return new Property(position, JsonParse.parseJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "Square 32" square tile on the board.
     * @return  Square
     */
    public Square square32(int position) {
        int rent = 26;
        int oneHouse = 130;
        int twoHouse = 390;
        int threeHouse = 900;
        int fourHouse = 1100;
        int hotel = 1275;
        int propertyCost = 300;
        int houses = 200;
        return new Property(position, JsonParse.parseJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "COMMUNITY CHEST" square tile on the board.
     * @return  Square
     */
    public Square square33(int position) {
        return new Inactive(position, JsonParse.parseJSON(position, String.valueOf(this.versions)));
    }

    /**
     * Setup "Square 34" square tile on the board.
     * @return  Square
     */
    public Square square34(int position) {
        int rent = 26;
        int oneHouse = 130;
        int twoHouse = 390;
        int threeHouse = 900;
        int fourHouse = 1100;
        int hotel = 1275;
        int propertyCost = 320;
        int houses = 200;
        return new Property(position, JsonParse.parseJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "LIVERPOOL ST. STATION" square tile on the board.
     * @return  Square
     */
    public Square square35(int position) {
        return new Railroad(position, JsonParse.parseJSON(position, String.valueOf(this.versions)));
    }

    /**
     * Setup "CHANCE" square tile on the board.
     * @return  Square
     */
    public Square square36(int position) {
        return new Inactive(position, JsonParse.parseJSON(position, String.valueOf(this.versions)));
    }

    /**
     * Setup "Square 37" tile on the board.
     * @return  Square
     */
    public Square square37(int position) {
        int rent = 35;
        int oneHouse = 175;
        int twoHouse = 500;
        int threeHouse = 1100;
        int fourHouse = 1500;
        int hotel = 1500;
        int propertyCost = 350;
        int houses = 200;
        return new Property(position, JsonParse.parseJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "SUPER TAX" square tile on the board.
     * @return  Square
     */
    public Square square38(int position) {
        return new Taxes(position, Objects.requireNonNull(JsonParse.parseJSON(position, String.valueOf(this.versions))));
    }

    /**
     * Setup "Square 39" tile on the board.
     * @return  Square
     */
    public Square square39(int position) {
        int rent = 35;
        int oneHouse = 175;
        int twoHouse = 500;
        int threeHouse = 1100;
        int fourHouse = 1300;
        int hotel = 1500;
        int propertyCost = 400;
        int houses = 200;
        return new Property(position, JsonParse.parseJSON(position, String.valueOf(this.versions)),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }
}
