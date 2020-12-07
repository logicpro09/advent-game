public class Character {

    public static void main(String[] args){

        Character character;
        character = new Character();
        character.warriorSetUp();
        System.out.println();
        character.civilianSetUp();
        System.out.println();
        character.pirateSetUp();
        System.out.println();
        character.wizardSetUp();

    }
    public void warriorSetUp(){

        String playerType;
        playerType = "WARRIOR";
        int playerHP;
        playerHP = 100;

        String playerSkillPrimary;
        playerSkillPrimary = "Double Attack";
        String playerSkillSecondary;
        playerSkillSecondary = "Invincibility";

        String playerWeapon;
        playerWeapon = "Attack Sword";

        System.out.println("Role: " + playerType);
        System.out.println("HP: " + playerHP);
        System.out.println("Weapon: " + playerWeapon);
        System.out.println("Primary Skill: " + playerSkillPrimary);
        System.out.println("Secondary Skill: " + playerSkillSecondary);


    }
    public void civilianSetUp(){

        String playerType;
        playerType = "CIVILIAN";
        int playerHP;
        playerHP = 50;

        String playerSkillPrimary;
        playerSkillPrimary = "Telekinesis";
        String playerSkillSecondary;
        playerSkillSecondary = "Invisibility";

        String playerWeaponPrimary;
        playerWeaponPrimary = "Explosive Briefcase";

        String playerWeaponSecondary;
        playerWeaponSecondary = "Rolled-up Newspaper";

        System.out.println("Role: " + playerType);
        System.out.println("HP: " + playerHP);
        System.out.println("Primary Weapon: " + playerWeaponPrimary);
        System.out.println("Secondary Weapon: " + playerWeaponSecondary);
        System.out.println("Primary Skill: " + playerSkillPrimary);
        System.out.println("Secondary Skill: " + playerSkillSecondary);

    }
    public void pirateSetUp(){

        String playerType;
        playerType = "PIRATE";
        int playerHP;
        playerHP = 80;

        String playerSkillPrimary;
        playerSkillPrimary = "Drunken Spell";

        String playerSkillSecondary;
        playerSkillSecondary = "Angry Sea";


        String playerWeaponPrimary;
        playerWeaponPrimary = "Rusty Hook";

        String playerWeaponSecondary;
        playerWeaponSecondary = "Shark Bait";

        System.out.println("Role: " + playerType);
        System.out.println("HP: " + playerHP);
        System.out.println("Primary Weapon: " + playerWeaponPrimary);
        System.out.println("Secondary Weapon: " + playerWeaponSecondary);
        System.out.println("Primary Skill: " + playerSkillPrimary);
        System.out.println("Secondary Skill: " + playerSkillSecondary);
    }
    public void wizardSetUp(){

        String playerType;
        playerType = "WIZARD";
        int playerHP;
        playerHP = 120;

        String playerSkillPrimary;
        playerSkillPrimary = "Invisibility";

        String playerSkillSecondary;
        playerSkillSecondary = "Freezability";

        String playerWeaponPrimary;
        playerWeaponPrimary = "Poison Potion";

        String playerWeaponSecondary;
        playerWeaponSecondary = "Acid Rain";

        System.out.println("Role: " + playerType);
        System.out.println("HP: " + playerHP);
        System.out.println("Primary Weapon: " + playerWeaponPrimary);
        System.out.println("Secondary Weapon: " + playerWeaponSecondary);
        System.out.println("Primary Skill: " + playerSkillPrimary);
        System.out.println("Secondary Skill: " + playerSkillSecondary);


}}
