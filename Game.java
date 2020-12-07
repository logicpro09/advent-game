import java.util.Scanner;

public class Game {
    public static void main(String[] args){

        Scanner myScnr;
        myScnr = new Scanner(System.in);

        System.out.println("A Covid-19 Adventure");
        System.out.println("Press enter to continue");

        String playerName;
        playerName = myScnr.nextLine();
        System.out.println("Enter your name: ");
        playerName = myScnr.nextLine();

        System.out.println("Welcome " + playerName);


        System.out.println("Covid-19 is attacking the world!!!\nAre you ready for a pandemic adventure?!!!\n\n");

        String myWeapon;
        String myArmor;
        String myCharacter;

        myWeapon = "Bottle of Hand Sanitizer";
        myArmor = "N95 Mask";
        myCharacter = "Registered Nurse";

        System.out.println("Your weapon is a " + myWeapon);
        System.out.println("Your armor is " + myArmor);
        System.out.println("Your character is " + myCharacter);

        int yourHP;
        yourHP = 10;

        System.out.println("Your Health is " + yourHP);

        System.out.println("You are being attacked by Covid-19 and lost 5 HP!");

        yourHP = yourHP - 5;

        System.out.println("Your current HP is " + yourHP);
    }
}
