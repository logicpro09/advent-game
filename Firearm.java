public class Firearm extends Weapon{
    public Firearm() {
        super("Glock", 10, .25, 5, 3);
        super.setConsumable(true);
        super.setQuantity(17);
    }
}
