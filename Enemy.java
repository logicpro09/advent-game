// jpluu
// Enemy object
// Enemy is a Player object with some additional methods for attack choice
import java.util.Random;

public class Enemy extends Player{

    private Random enemyRNG = new Random();

    // constructor
    public Enemy(String name) {
        this.name = name;
        this.playerHP = 5;
        this.playerClass = "MOB";
        this.primaryWeapon = new Club();
        this.secondaryWeapon = null;
    }


    // enemyRNG - currently has two choices with one force occurring if out of range
    // future actions include blocking, dodging, and healing
    public int enemyRNG() {
        // 0 - attack
        // 1 - move closer
        if (this.getDistance() >= this.primaryWeapon.range) {
            return 1;
        }
        return 0;
    }
}
