// jpluu
// CellPhone object
// A Weapon that is an Item
// no extra functional currently
// businessman secondary weapon
// consumable
public class Explosive extends Weapon{
    public Explosive() {
        super("M67", 45, 0.1, 10, 1);
        super.setConsumable(true);
        super.setQuantity(3);
    }
}
