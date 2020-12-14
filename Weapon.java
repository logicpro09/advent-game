import java.util.Random;

public class Weapon extends Item{
    public int level;
    public double damage;
    public double accuracy;
    public double range;
    public int speed;
    private Random weaponRNG = new Random();

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

    public void attack(Player enemy) {
        double damageCalc;
        int attack = 0;
        while (attack < speed) {
            if (inRange(enemy.getDistance())) {
                if (accuracy >= weaponRNG.nextDouble()) {
                    damageCalc = this.damage;
                    if (super.isConsumable() && super.getQuantity() > 0) {
                        super.use();
                        damageCalc += Math.pow(this.damage, (1 + (weaponRNG.nextDouble() * this.level)));
                    } else if(super.isConsumable() && super.getQuantity() == 0) {
                        System.out.println("Out of ammo!");
                        break;
                    } else {
                        damageCalc += Math.pow(this.damage, (1 + (weaponRNG.nextDouble() * this.level)));
                    }
                    System.out.println(this.name + " did " + Math.ceil(damageCalc) + " damage to " + enemy.name);
                    enemy.playerHP -= Math.ceil(damageCalc);
                }else {
                    System.out.println(this.name + " missed!");
                    break;
                }
            }
            attack++;
        }

    }
    
    public void upgrade() {
        this.level += 1;
    }

    public boolean inRange(double distance) {
        return distance <= this.range;
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "name=" + name +
                ", level='" + level + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
