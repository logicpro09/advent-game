// jpluu
// CellPhone object
// A Weapon that is an Item
// no extra functional currently - reload function that could pull from inventory
// businessman primary weapon
// consumable
public class Firearm extends Weapon{
    public Firearm() {
        super("Glock", 10, .25, 5, 3);
        super.setConsumable(true);
        super.setQuantity(17);
    }
}
