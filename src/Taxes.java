public class Taxes implements Square {
    @Override
    public int position() {
        return 0;
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public boolean isOwnable() {
        return false;
    }

    @Override
    public boolean isOwned() {
        return false;
    }

    @Override
    public int cost() {
        return 0;
    }

    @Override
    public void purchase(Player player) {

    }

    @Override
    public int rent(int value) {
        return 0;
    }

    @Override
    public Player owner() {
        return null;
    }
}
