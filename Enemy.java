import java.util.Random;

public class Enemy extends Player{

    private Random enemyRNG = new Random();
    public Enemy(String name) {
        this.name = name;
        this.playerHP = 5;
        this.playerClass = "MOB";
        this.primaryWeapon = new Club();
        this.secondaryWeapon = null;
    }



    public int enemyRNG() {
        // 0 - attack
        // 1 - move closer
        if (this.getDistance() >= this.primaryWeapon.range) {
            return 1;
        }
        return 0;
    }
}
