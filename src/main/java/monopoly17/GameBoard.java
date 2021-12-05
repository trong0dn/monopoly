package monopoly17;

import java.io.Serializable;

/**
 * This class represents the game board.
 * @author Elisha Catherasoo & Trong Nguyen
 */
public class GameBoard implements Serializable {
    public static final int BOARD_SIZE = 40;
    private final Square[] board; // representation of the game board
    Versions versions;

    /**
     * Constructor for GameBoard.
     */
    public GameBoard() {
        this.versions = Versions.US;
        this.board = new Square[BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i] = makeSquare(i);
        }
        groupProperties();
        groupRailroads();
        groupUtilities();
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

    public void setLanguage(Versions version){
        this.versions = version;
    }

    public Versions getLanguage() {
        return versions;
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
        return switch (position) {  // Using cases to represent individual squares on board. Clockwise.
            case 0 -> square0();    // go
            case 1 -> square1();    // oldKent
            case 2 -> square2();    // community
            case 3 -> square3();    // whitechapel
            case 4 -> square4();    // incomeTax
            case 5 -> square5();    // kingsCross
            case 6 -> square6();    // theAngelIslington
            case 7 -> square7();    // chance
            case 8 -> square8();    // euston
            case 9 -> square9();    // pentonville
            case 10 -> square10();  // justVisiting
            case 11 -> square11();  // pallMall
            case 12 -> square12();  // electricCompany
            case 13 -> square13();  // whitehall
            case 14 -> square14();  // northumrld
            case 15 -> square15();  // marylebone
            case 16 -> square16();  // bow
            case 17 -> square17();  // community
            case 18 -> square18();  // marlborough
            case 19 -> square19();  // vine
            case 20 -> square20();  // freeParking
            case 21 -> square21();  // strand
            case 22 -> square22();  // chance
            case 23 -> square23();  // fleet
            case 24 -> square24();  // trafalgar
            case 25 -> square25();  // fenchurch
            case 26 -> square26();  // leicester
            case 27 -> square27();  // conventry
            case 28 -> square28();  // waterWorks
            case 29 -> square29();  // piccadilly
            case 30 -> square30();  // goToJail
            case 31 -> square31();  // regent
            case 32 -> square32();  // oxford
            case 33 -> square33();  // community
            case 34 -> square34();  // bond
            case 35 -> square35();  // liverpool
            case 36 -> square36();  // chance
            case 37 -> square37();  // park
            case 38 -> square38();  // superTax
            case 39 -> square39();  // mayfair
            default -> null;
        };
    }

    /**
     * Group properties by color set.
     */
    private void groupProperties() {
        Property brownA = (Property) square(SquareInfo.SQUARE_1.getPosition());
        Property brownB = (Property) square(SquareInfo.SQUARE_3.getPosition());
        Property skyA = (Property) square(SquareInfo.SQUARE_6.getPosition());
        Property skyB = (Property) square(SquareInfo.SQUARE_8.getPosition());
        Property skyC = (Property) square(SquareInfo.SQUARE_9.getPosition());
        Property pinkA = (Property) square(SquareInfo.SQUARE_11.getPosition());
        Property pinkB = (Property) square(SquareInfo.SQUARE_13.getPosition());
        Property pinkC = (Property) square(SquareInfo.SQUARE_14.getPosition());
        Property orangeA = (Property) square(SquareInfo.SQUARE_16.getPosition());
        Property orangeB = (Property) square(SquareInfo.SQUARE_18.getPosition());
        Property orangeC = (Property) square(SquareInfo.SQUARE_19.getPosition());
        Property redA = (Property) square(SquareInfo.SQUARE_21.getPosition());
        Property redB = (Property) square(SquareInfo.SQUARE_23.getPosition());
        Property redC = (Property) square(SquareInfo.SQUARE_24.getPosition());
        Property yellowA = (Property) square(SquareInfo.SQUARE_26.getPosition());
        Property yellowB = (Property) square(SquareInfo.SQUARE_27.getPosition());
        Property yellowC = (Property) square(SquareInfo.SQUARE_29.getPosition());
        Property greenA = (Property) square(SquareInfo.SQUARE_31.getPosition());
        Property greenB = (Property) square(SquareInfo.SQUARE_32.getPosition());
        Property greenC = (Property) square(SquareInfo.SQUARE_34.getPosition());
        Property blueA = (Property) square(SquareInfo.SQUARE_37.getPosition());
        Property blueB = (Property) square(SquareInfo.SQUARE_39.getPosition());

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
        Railroad a = (Railroad) square(SquareInfo.SQUARE_5.getPosition());  // Railroad on square 5
        Railroad b = (Railroad) square(SquareInfo.SQUARE_15.getPosition()); // Railroad on square 15
        Railroad c = (Railroad) square(SquareInfo.SQUARE_25.getPosition()); // Railroad on square 25
        Railroad d = (Railroad) square(SquareInfo.SQUARE_35.getPosition()); // Railroad on square 35

        a.setGroup(b, c, d);
        b.setGroup(a, c, d);
        c.setGroup(a, b, d);
        d.setGroup(a, b, c);
    }

    /**
     * Group utilities.
     */
    private void groupUtilities() {
        Utility a = (Utility) square(SquareInfo.SQUARE_12.getPosition()); // Utilities on square 12
        Utility b = (Utility) square(SquareInfo.SQUARE_28.getPosition()); // Utilities on square 28

        a.setGroup(b);
        b.setGroup(a);
    }

    /**
     * Setup "GO" square tile on the board.
     * @return  Square
     */
    public Square square0() {
        return new Inactive(SquareInfo.SQUARE_0.getPosition(), JsonParse.parseJSON(0, String.valueOf(this.versions )));

        //return new Inactive(SquareInfo.SQUARE_0.getPosition(), SquareInfo.SQUARE_0.getName());
    }

    /**
     * Setup "Square 1" tile on the board.
     * @return  Square
     */
    public Square square1() {
        int rent = 2;
        int oneHouse = 10;
        int twoHouse = 30;
        int threeHouse = 90;
        int fourHouse = 160;
        int hotel = 250;
        int propertyCost = 60;
        int houses = 50;
        return new Property(SquareInfo.SQUARE_1.getPosition(),JsonParse.parseJSON(1, String.valueOf(this.versions )) ,
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "COMMUNITY CHEST" square tile on the board.
     * @return  Square
     */
    public Square square2() {
        return new Inactive(SquareInfo.SQUARE_2.getPosition(), SquareInfo.SQUARE_2.getName());
    }

    /**
     * Setup "Square 3" tile on the board.
     * @return  Square
     */
    public Square square3() {
        int rent = 4;
        int oneHouse = 20;
        int twoHouse = 60;
        int threeHouse = 180;
        int fourHouse = 320;
        int hotel = 450;
        int propertyCost = 60;
        int houses = 50;
        return new Property(SquareInfo.SQUARE_3.getPosition(), SquareInfo.SQUARE_3.getName(),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "INCOME TAX" square tile on the board.
     * @return  Square
     */
    public Square square4() {
        return new Taxes(SquareInfo.SQUARE_4.getPosition(), SquareInfo.SQUARE_4.getName());
    }

    /**
     * Setup "KINGS CROSS STATION" square tile on the board.
     * @return  Square
     */
    public Square square5() {
        return new Railroad(SquareInfo.SQUARE_5.getPosition(), SquareInfo.SQUARE_5.getName());
    }

    /**
     * Setup "Square 6" tile on the board.
     * @return  Square
     */
    public Square square6() {
        int rent = 6;
        int oneHouse = 30;
        int twoHouse = 90;
        int threeHouse = 270;
        int fourHouse = 400;
        int hotel = 550;
        int propertyCost = 100;
        int houses = 50;
        return new Property(SquareInfo.SQUARE_6.getPosition(), SquareInfo.SQUARE_6.getName(),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "CHANCE" square tile on the board.
     * @return  Square
     */
    public Square square7() {
        return new Inactive(SquareInfo.SQUARE_7.getPosition(), SquareInfo.SQUARE_7.getName());
    }

    /**
     * Setup "Square 8" tile on the board.
     * @return  Square
     */
    public Square square8() {
        int rent = 6;
        int oneHouse = 30;
        int twoHouse = 90;
        int threeHouse = 270;
        int fourHouse = 400;
        int hotel = 550;
        int propertyCost = 100;
        int houses = 50;
        return new Property(SquareInfo.SQUARE_8.getPosition(), SquareInfo.SQUARE_8.getName(),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "Square 9" tile on the board.
     * @return  Square
     */
    public Square square9() {
        int rent = 6;
        int oneHouse = 30;
        int twoHouse = 90;
        int threeHouse = 270;
        int fourHouse = 400;
        int hotel = 550;
        int propertyCost = 120;
        int houses = 50;
        return new Property(SquareInfo.SQUARE_9.getPosition(), SquareInfo.SQUARE_9.getName(),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "JUST VISITING" square tile on the board.
     * @return  Square
     */
    public Square square10() {
        return new Jail(SquareInfo.SQUARE_10.getPosition(), SquareInfo.SQUARE_10.getName(), Jail.JailType.JUST_VISITING);
    }

    /**
     * Setup "Square 11" tile on the board.
     * @return  Square
     */
    public Square square11() {
        int rent = 10;
        int oneHouse = 50;
        int twoHouse = 150;
        int threeHouse = 450;
        int fourHouse = 625;
        int hotel = 750;
        int propertyCost = 140;
        int houses = 100;
        return new Property(SquareInfo.SQUARE_11.getPosition(), SquareInfo.SQUARE_11.getName(),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "ELECTRIC COMPANY" square tile on the board.
     * @return  Square
     */
    public Square square12() {
        return new Utility(SquareInfo.SQUARE_12.getPosition(), SquareInfo.SQUARE_12.getName());
    }

    /**
     * Setup "Square 13" tile on the board.
     * @return  Square
     */
    public Square square13() {
        int rent = 10;
        int oneHouse = 50;
        int twoHouse = 150;
        int threeHouse = 450;
        int fourHouse = 625;
        int hotel = 750;
        int propertyCost = 140;
        int houses = 100;
        return new Property(SquareInfo.SQUARE_13.getPosition(), SquareInfo.SQUARE_13.getName(),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "Square 14" tile on the board.
     * @return  Square
     */
    public Square square14() {
        int rent = 10;
        int oneHouse = 50;
        int twoHouse = 150;
        int threeHouse = 450;
        int fourHouse = 625;
        int hotel = 750;
        int propertyCost = 160;
        int houses = 100;
        return new Property(SquareInfo.SQUARE_14.getPosition(), SquareInfo.SQUARE_14.getName(),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "MARYLEBONE STATION" square tile on the board.
     * @return  Square
     */
    public Square square15() {
        return new Railroad(SquareInfo.SQUARE_15.getPosition(), SquareInfo.SQUARE_15.getName());
    }

    /**
     * Setup "Square 16" tile on the board.
     * @return  Square
     */
    public Square square16() {
        int rent = 14;
        int oneHouse = 70;
        int twoHouse = 200;
        int threeHouse = 550;
        int fourHouse = 750;
        int hotel = 750;
        int propertyCost = 180;
        int houses = 100;
        return new Property(SquareInfo.SQUARE_16.getPosition(), SquareInfo.SQUARE_16.getName(),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "COMMUNITY CHEST" square tile on the board.
     * @return  Square
     */
    public Square square17() {
        return new Inactive(SquareInfo.SQUARE_17.getPosition(), SquareInfo.SQUARE_17.getName());
    }

    /**
     * Setup "Square 18" tile on the board.
     * @return  Square
     */
    public Square square18() {
        int rent = 14;
        int oneHouse = 70;
        int twoHouse = 200;
        int threeHouse = 550;
        int fourHouse = 750;
        int hotel = 950;
        int propertyCost = 180;
        int houses = 100;
        return new Property(SquareInfo.SQUARE_18.getPosition(), SquareInfo.SQUARE_18.getName(),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "Square 19" tile on the board.
     * @return  Square
     */
    public Square square19() {
        int rent = 16;
        int oneHouse = 80;
        int twoHouse = 220;
        int threeHouse = 600;
        int fourHouse = 800;
        int hotel = 1000;
        int propertyCost = 200;
        int houses = 100;
        return new Property(SquareInfo.SQUARE_19.getPosition(), SquareInfo.SQUARE_19.getName(),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "FREE PARKING" square tile on the board.
     * @return  Square
     */
    public Square square20() {
        return new Inactive(SquareInfo.SQUARE_20.getPosition(), SquareInfo.SQUARE_20.getName());
    }

    /**
     * Setup "Square 21" tile on the board.
     * @return  Square
     */
    public Square square21() {
        int rent = 18;
        int oneHouse = 90;
        int twoHouse = 250;
        int threeHouse = 700;
        int fourHouse = 875;
        int hotel = 1050;
        int propertyCost = 220;
        int houses = 150;
        return new Property(SquareInfo.SQUARE_21.getPosition(), SquareInfo.SQUARE_21.getName(),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "CHANCE" square tile on the board.
     * @return  Square
     */
    public Square square22() {
        return new Inactive(SquareInfo.SQUARE_22.getPosition(), SquareInfo.SQUARE_22.getName());
    }

    /**
     * Setup "Square 23" tile on the board.
     * @return  Square
     */
    public Square square23() {
        int rent = 18;
        int oneHouse = 90;
        int twoHouse = 250;
        int threeHouse = 700;
        int fourHouse = 875;
        int hotel = 1050;
        int propertyCost = 220;
        int houses = 150;
        return new Property(SquareInfo.SQUARE_23.getPosition(), SquareInfo.SQUARE_23.getName(),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "Square 24" tile on the board.
     * @return  Square
     */
    public Square square24() {
        int rent = 20;
        int oneHouse = 100;
        int twoHouse = 300;
        int threeHouse = 750;
        int fourHouse = 925;
        int hotel = 1100;
        int propertyCost = 260;
        int houses = 150;
        return new Property(SquareInfo.SQUARE_24.getPosition(), SquareInfo.SQUARE_24.getName(),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "FENCHURCH ST. STATION" square tile on the board.
     * @return  Square
     */
    public Square square25() {
        return new Railroad(SquareInfo.SQUARE_25.getPosition(), SquareInfo.SQUARE_25.getName());
    }

    /**
     * Setup "Square 26" tile on the board.
     * @return  Square
     */
    public Square square26() {
        int rent = 22;
        int oneHouse = 110;
        int twoHouse = 330;
        int threeHouse = 800;
        int fourHouse = 975;
        int hotel = 1150;
        int propertyCost = 260;
        int houses = 150;
        return new Property(SquareInfo.SQUARE_26.getPosition(), SquareInfo.SQUARE_26.getName(),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "Square 27" tile on the board.
     * @return  Square
     */
    public Square square27() {
        int rent = 22;
        int oneHouse = 110;
        int twoHouse = 330;
        int threeHouse = 800;
        int fourHouse = 975;
        int hotel = 1150;
        int propertyCost = 280;
        int houses = 150;
        return new Property(SquareInfo.SQUARE_27.getPosition(), SquareInfo.SQUARE_27.getName(),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "WATER WORKS" square tile on the board.
     * @return  Square
     */
    public Square square28() {
        return new Utility(SquareInfo.SQUARE_28.getPosition(), SquareInfo.SQUARE_28.getName());
    }

    /**
     * Setup "Square 29" tile on the board.
     * @return  Square
     */
    public Square square29() {
        int rent = 24;
        int oneHouse = 120;
        int twoHouse = 360;
        int threeHouse = 850;
        int fourHouse = 1025;
        int hotel = 1200;
        int propertyCost = 280;
        int houses = 150;
        return new Property(SquareInfo.SQUARE_29.getPosition(), SquareInfo.SQUARE_29.getName(),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "GO TO JAIL" square tile on the board.
     * @return  Square
     */
    public Square square30() {
        return new Jail(SquareInfo.SQUARE_30.getPosition(), SquareInfo.SQUARE_30.getName(), Jail.JailType.GOTO_JAIL);
    }

    /**
     * Setup "Square 31" square tile on the board.
     * @return  Square
     */
    public Square square31() {
        int rent = 26;
        int oneHouse = 130;
        int twoHouse = 390;
        int threeHouse = 900;
        int fourHouse = 1100;
        int hotel = 1250;
        int propertyCost = 300;
        int houses = 200;
        return new Property(SquareInfo.SQUARE_31.getPosition(), SquareInfo.SQUARE_31.getName(),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "Square 32" square tile on the board.
     * @return  Square
     */
    public Square square32() {
        int rent = 26;
        int oneHouse = 130;
        int twoHouse = 390;
        int threeHouse = 900;
        int fourHouse = 1100;
        int hotel = 1275;
        int propertyCost = 300;
        int houses = 200;
        return new Property(SquareInfo.SQUARE_32.getPosition(), SquareInfo.SQUARE_32.getName(),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "COMMUNITY CHEST" square tile on the board.
     * @return  Square
     */
    public Square square33() {
        return new Inactive(SquareInfo.SQUARE_33.getPosition(), SquareInfo.SQUARE_33.getName());
    }

    /**
     * Setup "Square 34" square tile on the board.
     * @return  Square
     */
    public Square square34() {
        int rent = 26;
        int oneHouse = 130;
        int twoHouse = 390;
        int threeHouse = 900;
        int fourHouse = 1100;
        int hotel = 1275;
        int propertyCost = 320;
        int houses = 200;
        return new Property(SquareInfo.SQUARE_34.getPosition(), SquareInfo.SQUARE_34.getName(),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "LIVERPOOL ST. STATION" square tile on the board.
     * @return  Square
     */
    public Square square35() {
        return new Railroad(SquareInfo.SQUARE_35.getPosition(), SquareInfo.SQUARE_35.getName());
    }

    /**
     * Setup "CHANCE" square tile on the board.
     * @return  Square
     */
    public Square square36() {
        return new Inactive(SquareInfo.SQUARE_36.getPosition(), SquareInfo.SQUARE_36.getName());
    }

    /**
     * Setup "Square 37" tile on the board.
     * @return  Square
     */
    public Square square37() {
        int rent = 35;
        int oneHouse = 175;
        int twoHouse = 500;
        int threeHouse = 1100;
        int fourHouse = 1500;
        int hotel = 1500;
        int propertyCost = 350;
        int houses = 200;
        return new Property(SquareInfo.SQUARE_37.getPosition(), SquareInfo.SQUARE_37.getName(),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Setup "SUPER TAX" square tile on the board.
     * @return  Square
     */
    public Square square38() {
        return new Taxes(SquareInfo.SQUARE_38.getPosition(), SquareInfo.SQUARE_38.getName());
    }

    /**
     * Setup "Square 39" tile on the board.
     * @return  Square
     */
    public Square square39() {
        int rent = 35;
        int oneHouse = 175;
        int twoHouse = 500;
        int threeHouse = 1100;
        int fourHouse = 1300;
        int hotel = 1500;
        int propertyCost = 400;
        int houses = 200;
        return new Property(SquareInfo.SQUARE_39.getPosition(), SquareInfo.SQUARE_39.getName(),
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }
}
