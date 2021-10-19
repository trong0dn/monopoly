public class Jail implements Square {
    private final int position;
    private final JailType type;
    private final String name;

    public Jail(int position, String name, JailType type) {
        this.position = position;
        this.name = name;
        this.type = type;
    }

    @Override
    public int position() {
        return this.position;
    }

    @Override
    public String name() {
        return this.name;
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
    public void purchase(HumanPlayer player) {
    }

    @Override
    public int rent(int value) {
        return 0;
    }

    @Override
    public HumanPlayer owner() {
        return null;
    }

    public enum JailType {
        JUST_VISITING, IN_JAIL, GOTO_JAIL
    }
}
