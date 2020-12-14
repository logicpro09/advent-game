public class Gold extends Item{
    public Gold() {
        this(1);
    }

    public Gold(int value) {
        super("Gold", value);
        super.setConsumable(true);
    }

    public int getGold() {
        return getQuantity();
    }

    public boolean useGold(int value) {
        if (this.quantity > value) {
            this.quantity -= value;
            return true;
        }
        return false;
    }

    public void addGold(int value) {
        this.quantity += value;
    }
}
