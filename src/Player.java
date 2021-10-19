public interface Player {
    void move(int numTiles);
    void moveTo(int position);
    int getPosition();
    Iterable<Square> properties();
    String name();
    int getMoney();
    void collectMoney(int money);
    void buyProperty(Square square);
    void sellProperty(Square square);
}
