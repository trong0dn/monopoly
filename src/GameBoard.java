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
        switch (position) {
            case 0:
                return go(position);
            case 1:
                return oldKent(position);
            case 2:
                return communityChest(position);
            case 3:
                return whitechapel(position);
            case 4:
                return incomeTax(position);
            case 5:
                return kingsCrossStation(position);
            case 6:
                return theAngelIslington(position);
            case 7:
                return chance(position);
            case 8:
                return euston(position);
            case 9:
                return pentonville(position);
            case 10:
                return justVisiting(position);
            case 11:
                return pallMall(position);
            case 12:
                return electricCompany(position);
            case 13:
                return whitehall(position);
            case 14:
                return northumrld(position);
            case 15:
                return maryleboneStation(position);
            case 16:
                return bow(position);
            case 17:
                return communityChest(position);
            case 18:
                return marlborough(position);
            case 19:
                return vine(position);
            case 20:
                return freeParking(position);
            case 21:
                return strand(position);
            case 22:
                return chance(position);
            case 23:
                return fleet(position);
            case 24:
                return trafalgar(position);
            case 25:
                return fenchurchStStation(position);
            case 26:
                return leicester(position);
            case 27:
                return conventry(position);
            case 28:
                return waterWorks(position);
            case 29:
                return piccadilly(position);
            case 30:
                return goToJail(position);
            case 31:
                return regent(position);
            case 32:
                return oxford(position);
            case 33:
                return communityChest(position);
            case 34:
                return bond(position);
            case 35:
                return liverpoolStStation(position);
            case 36:
                return chance(position);
            case 37:
                return park(position);
            case 38:
                return superTax(position);
            case 39:
                return mayfair(position);
            case 40:
                return inJail(position);
            default:
                return null;
        }
    }

    public Square go(int position) {
        return new Inactive(position, "Go");
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
