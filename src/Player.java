public interface Player {

    String name();

    void getBalance();

    /* This call can be called something like playerAssets later on
       depending on what assets we add that generate player revenue
     */
    void getProperty();

    int position();

    boolean isBankrupt();

    void buyProperty(Square square); // Change to sellProperty?

    int payRent(); // Can go under assets

    int receiveRent(); // Can go under assets

    void noMoney();


}
