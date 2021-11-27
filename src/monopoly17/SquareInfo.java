package monopoly17;

public enum SquareInfo {
    SQUARE_0(0, "GO"),
    SQUARE_1(1, "OLD KENT ROAD"),
    SQUARE_2(2, "COMMUNITY CHEST"),
    SQUARE_3(3, "WHITECHAPEL ROAD"),
    SQUARE_4(4, "INCOME TAX"),
    SQUARE_5(5, "KINGS CROSS STATION"),
    SQUARE_6(6, "THE ANGEL, ISLINGTON"),
    SQUARE_7(7, "CHANCE"),
    SQUARE_8(8, "EUSTON ROAD"),
    SQUARE_9(9, "PENTONVILLE ROAD"),
    SQUARE_10(10, "JUST VISITING"),
    SQUARE_11(11, "PALL MALL"),
    SQUARE_12(12, "ELECTRIC COMPANY"),
    SQUARE_13(13, "WHITEHALL"),
    SQUARE_14(14, "NORTHUMRL'D AVENUE"),
    SQUARE_15(15, "MARYLEBONE STATION"),
    SQUARE_16(16, "BOW STREET"),
    SQUARE_17(17, "COMMUNITY CHEST"),
    SQUARE_18(18, "MARLBOROUGH STREET"),
    SQUARE_19(19, "VINE STREET"),
    SQUARE_20(20, "FREE PARKING"),
    SQUARE_21(21, "STRAND"),
    SQUARE_22(22, "CHANCE"),
    SQUARE_23(23, "FLEET STREET"),
    SQUARE_24(24, "TRAFALGAR SQUARE"),
    SQUARE_25(25, "FENCHURCH ST. STATION"),
    SQUARE_26(26, "LEICESTER"),
    SQUARE_27(27, "CONVENTRY STREET"),
    SQUARE_28(28, "WATER WORKS"),
    SQUARE_29(29, "PICCADILLY"),
    SQUARE_30(30, "GO TO JAIL"),
    SQUARE_31(31, "REGENT STREET"),
    SQUARE_32(32, "OXFORD STREET"),
    SQUARE_33(33, "COMMUNITY CHEST"),
    SQUARE_34(34, "BOND STREET"),
    SQUARE_35(35, "LIVERPOOL ST. STATION"),
    SQUARE_36(36, "CHANCE"),
    SQUARE_37(37, "PARK LANE"),
    SQUARE_38(38, "SUPER TAX"),
    SQUARE_39(39, "MAYFAIR"),
    SQUARE_40(40, "IN JAIL");

    private final int position;
    private final String name;

    SquareInfo(int position, String name) {
        this.position = position;
        this.name = name;
    }

    public int getPosition() {
        return this.position;
    }

    public String getName() {
        return this.name;
    }
}
