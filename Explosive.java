public class Explosive extends Weapon{
    public Explosive() {
        super("M67", 45, 0.1, 10, 1);
        super.setConsumable(true);
        super.setQuantity(3);
    }
}
