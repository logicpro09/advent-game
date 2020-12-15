// jpluu
// Gold object
// Gold is an Item object that is consumable
public class Gold extends Item{
    // constructors
    public Gold() {
        this(1);
    }

    public Gold(int value) {
        super("Gold", value);
        super.setConsumable(true);
    }

    // getter
    public int getGold() {
        return getQuantity();
    }

    // helper
    // useGold - when at a shop, gold can be used
    // could replace with override/interface
    public boolean useGold(int value) {
        if (this.quantity > value) {
            this.quantity -= value;
            return true;
        }
        return false;
    }

    // addGold - increase gold from selling items or event
    public void addGold(int value) {
        this.quantity += value;
    }
}
