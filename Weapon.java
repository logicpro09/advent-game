// jpluu
// Weapon object
// Weapon is an Item object that has 'weapon' stats
// attacking is also done here but should be redone as an interface
import java.util.Random;

public class Weapon extends Item{
    public int level;
    public double damage;
    public double accuracy;
    public double range;
    public int speed;
    private Random weaponRNG = new Random();

    // constructors
    public Weapon() {
        super("null", 1);
        this.level = 0;
        this.damage = 0;
        this.accuracy = 0;
        this.range = 1;
        this.speed = 1;
    }

    public Weapon(String name, double damage, double accuracy, double range, int speed) {
        super(name, 1);
        this.level = 1;
        this.damage = damage;
        this.accuracy = accuracy;
        this.range = range;
        this.speed = speed;
    }

    // helpers
    // attack - takes in an enemy to attack
    // some weapons are faster than other, allowing for more attacks
    // if in range, there will be an attack attempt
    // if accuracy is greater than the random double generated, attack will occur, otherwise miss
    public void attack(Player enemy) {
        double damageCalc;
        int attack = 0;
        // multi attack loop
        while (attack < speed) {
            // in range?
            if (inRange(enemy.getDistance())) {
                // accuracy checker
                if (accuracy >= weaponRNG.nextDouble()) {
                    damageCalc = this.damage;
                    // for firearm and explosive, weapons that have ammo
                    if (super.isConsumable() && super.getQuantity() > 0) {
                        super.use();
                        damageCalc += Math.pow(this.damage, (1 + (weaponRNG.nextDouble() * this.level)));
                    }
                    // out of ammo
                    else if(super.isConsumable() && super.getQuantity() == 0) {
                        System.out.println("Out of ammo!");
                        break;
                    }
                    // melee weapons
                    else {
                        damageCalc += Math.pow(this.damage, (1 + (weaponRNG.nextDouble() * this.level)));
                    }
                    // damage calculations
                    System.out.println(this.name + " did " + Math.ceil(damageCalc) + " damage to " + enemy.name);
                    enemy.playerHP -= Math.ceil(damageCalc);
                }
                // missed
                else {
                    System.out.println(this.name + " missed!");
                    break;
                }
            }
            // increase multi attack counter
            attack++;
        }

    }

    // upgrade - upgrade the level of the weapon by one
    public void upgrade() {
        this.level += 1;
    }

    // inRange - checks if the target is in range
    public boolean inRange(double distance) {
        return distance <= this.range;
    }

    // toString
    @Override
    public String toString() {
        return "Weapon{" +
                "name=" + name +
                ", level='" + level + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
