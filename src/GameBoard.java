public class GameBoard {
    private final int NUM_TILES = 41;
    private final Square[] board; // representation of the game board

    public GameBoard() {
        this.board = new Square[NUM_TILES];
    }

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

}
