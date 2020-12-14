public class Player {
    String name;
    int playerHP;
    String playerClass;
    Item[] inventory = new Item[4];
    Weapon primaryWeapon;
    Weapon secondaryWeapon;
    String description;
    public double distance;

    public void civilianSetup(String name) {
        this.name = name;
        this.playerHP = 15;
        this.playerClass = "CIVILIAN";
        this.primaryWeapon = new CellPhone();
        this.secondaryWeapon = new NickelSock();
        this.description = "You cannot defend yourself, but you have a phone\nYou are an average Joe who is afraid of things most people are afraid of, like conflict, zombies, and communism";
    }

    public void hoboSetup(String name) {
        this.name = name;
        this.playerHP = 25;
        this.playerClass = "HOBO";
        this.primaryWeapon = new Sticks();
        this.secondaryWeapon = new Newspaper();
        this.description = "You have some sticks to defend yourself, and some scavenged food to keep you alive\nYour life on the streets has made you tougher and stinkier than most";
    }

    public void businessmanSetup(String name) {
        this.name = name;
        this.playerHP = 20;
        this.playerClass = "BUSINESSMAN";
        this.primaryWeapon = new Firearm();
        this.secondaryWeapon = new Explosive();
        this.description = "You have purchased a firearm in order to stay safe on the streets, but pray you should not be forced to use it\nYour cozy city life has made you fearful and cautious of the lower income communities";
    }

    public void enemySetup(String name) {
        this.name = name;
        this.playerHP = 50;
        this.playerClass = "MOB";
        this.primaryWeapon = new Club();
        this.secondaryWeapon = null;
    }

    public double getDistance() {
        return distance;
    }

    public void getInfo() {
        System.out.println("Name: " + this.name);
        System.out.println("Role: " + this.playerClass);
        System.out.println("HP: " + this.playerHP);
        System.out.println("Primary Weapon: " + this.primaryWeapon);
        System.out.println("Secondary Weapon: " + this.secondaryWeapon);
        System.out.println(this.description);
    }


    public static void main(String[] args) {
        Player civiTest = new Player();
        civiTest.civilianSetup("Civi Test");
        Player hoboTest = new Player();
        hoboTest.hoboSetup("Hobo Test");
        Player busiTest = new Player();
        busiTest.businessmanSetup("Busi Test");
        Enemy enemyTest = new Enemy("Hoblin");

        civiTest.getInfo();

        Battle testBattle = new Battle(civiTest, enemyTest);
        testBattle.inCombat();

        civiTest.getInfo();


    }
}