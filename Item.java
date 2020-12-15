// jpluu
// Item object
public class Item {
    public String name;
    public int quantity;
    public boolean consumable;
    public String description;

    // constructors
    public Item() {
        this.name = "null";
        this.quantity = -1;
        this.consumable = false;
    }

    public Item(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
        this.consumable = false;
    }

    // getters
    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isConsumable() {
        return consumable;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    protected void setConsumable(boolean consumable) {
        this.consumable = consumable;
    }

    // helpers
    // add - when refilling items at the shop
    public void add(int amount) {
        this.quantity += amount;
    }

    // use - if an item can be use and has quantity left
    // returns a boolean if the item is used
    public boolean use() {
        if (consumable && this.quantity > 0) {
            this.quantity -= 1;
            return true;
        }
        return false;
    }
}
