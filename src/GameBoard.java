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



}
