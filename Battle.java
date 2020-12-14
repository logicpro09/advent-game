import java.util.Random;
import java.util.Scanner;

public class Battle {
    public Scanner scnr = new Scanner(System.in);
    private Player player;
    private Enemy enemy;
    private boolean dodge;
    private boolean flee;
    private Random battleRNG = new Random();
    public Battle(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        System.out.println("Combat START!");
    }

    public void inCombat() {
        player.distance = enemy.distance = 5;
        while(enemy.playerHP > 0 && player.playerHP > 0) {
            this.dodge = false;
            System.out.println(player.name + "'s HP - " + player.playerHP + "\n" + enemy.name + "'s HP - " + enemy.playerHP);
            playerAction();
            if (this.flee) {
                break;
            }
            // enemy rng
            // 0 - attack
            // 1 - move closer
            switch (enemy.enemyRNG()) {
                case 0:
                    if (this.dodge) {
                        System.out.println(enemy.name + " attacked! You dodged!");
                        break;
                    } else {
                        enemy.primaryWeapon.attack(player);
                        break;
                    }
                case 1:
                    this.moveCloser();
                    System.out.println(enemy.name + " moved closer...");
                    System.out.println("Distance is now " + enemy.getDistance());
                    break;
            }
        }
        if (this.flee) {
            System.out.println("Got away safely...");
            this.flee = false;
        }
        else if (player.playerHP > 0) {
            System.out.println("You win!");
            player.primaryWeapon.upgrade();
            System.out.println("You feel more experienced with " + player.primaryWeapon.name);
        }
        else if (player.playerHP < 0) {
            System.out.println("YOU DIED...");
        }
        System.out.println("Combat END!");
    }

    public void playerAction() {
        System.out.println("Distance:: " + player.getDistance());
        System.out.println("1 - Attack | 2 - Move Closer | 3 - Dodge | 0 - Run Away");
        int playerMove;
        do {
            playerMove = scnr.nextInt();
        } while (playerMove <= 0 && playerMove >= 3);
        switch (playerMove) {
            case 1:
                player.primaryWeapon.attack(enemy);
                break;
            case 2:
                this.moveCloser();
                System.out.println("You moved closer...");
                System.out.println("Distance is now " + player.getDistance());
                break;
            case 3:
                this.dodge = this.dodge();
                break;
            case 0:
                this.flee = this.runAway();
                break;
        }
    }

    public void moveCloser() {

        if (this.player.distance > 0 || this.enemy.distance > 0) {
            this.player.distance -= 1;
            this.enemy.distance -= 1;
        }
    }

    public boolean runAway() {
        // 0.25 chance to escape
        return battleRNG.nextDouble() <= 0.25;
    }

    public boolean dodge() {
        // 0.50 chance to dodge/evade
        return battleRNG.nextDouble() <= 0.5;
    }
}
