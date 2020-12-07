import java.util.Scanner;

/*This class is to be the 'skeleton' for creating a new character for the user, this class should include
 * variables/attributes that will give the player's character certain traits, methods for doing some actions
 * that the player should be able to do with his character, say maybe like using an Item*/
public class BaseCharacter {

	static Scanner scr = new Scanner(System.in);

	/*Derived these classes and methods from the AdventureDemo.java file that Gene(Donkeys are pretty cool) had given to the group chat as an 
	 * example of how we could start the project, I took some methods from that file regarding the creation and setup processes for creating 
	 * a new character. I also did some editing to the code to have it fit the theme we are trying to do, a Medieval theme. Such as, 
	 * changing one of the inventory items from "firearms" to "Ranged Weapon(s)". Also adding a Health Point reference as well.*/
	public static class Player{
		String Name;

		int HealthPoints = 15;

		int PlayerType = -1;

		//need inventory for say a melee weapon, and a ranged weapon.
		/*
		 * Inventory
		 * bread - 0
		 * melee weapon - 1
		 * ranged weapon - 2
		 * armor - 3
		 * */

		int[] Inventory = {0, 0, 0, 0};

		public void SetInventory(int bread, int meleeW, int rangedW, int armor){
			Inventory[0] = bread;
			Inventory[1] = meleeW;
			Inventory[2] = rangedW;
			Inventory[3] = armor;
		}

		public void DescribeInventory(){
			System.out.println("-------------------------------");
			System.out.println("Your Inventory:");
			System.out.println("bread: " + Inventory[0]);
			System.out.println("Melee Weapon(s): " + Inventory[1]);
			System.out.println("Ranged Weapon(s): " + Inventory[2]);
			System.out.println("Armor: " + Inventory[3]);
			System.out.println("-------------------------------");
		}

		public boolean ItemExists(int itemID){
			return Inventory[itemID] > 0;
		}

		public void Reset(){
			Name = "";
			PlayerType = -1;
			SetInventory(0, 0, 0, 0);
		}
		
		//asks for player name and character to play as
		public void setPlayer(Player player){
			System.out.println("Your name son, give me your name");
			player.Name = scr.nextLine();
			System.out.println("What are you, " + player.Name);
			while(player.PlayerType <= 0){
				System.out.println("Choose your class: Warrior (1), Hunter (2), or Mage (3)");

				int playerChoice = scr.nextInt();

				//tried to use switch method as alternative to if/else
				switch(playerChoice){
				case 1:
					player.PlayerType = 1;
					break;
				case 2:
					player.PlayerType = 2;
					break;
				case 3:
					player.PlayerType = 3;
					break;
				default:
					System.out.println("Please select one of the specified classes.");
				}
			}
		}

		public void preparePlayer(Player player){
			switch(player.PlayerType){
			case 1:
				System.out.println("Tough, Strong, Determined.");
				System.out.println("Loving to get in the face of your opponents and attack head on,");
				System.out.println("you head into battle with little regards for who your enemy is,");
				System.out.println("and stop at nothing to take them out.");
				player.SetInventory(0, 1, 0, 1);
				player.DescribeInventory();
				break;
			case 2:
				System.out.println("Agile, Cunning, Reserved.");
				System.out.println("Your quick wits, and quick reflexes tend to give you the upper hand in");
				System.out.println("most situations, liking to keep your distance from your opponent,");
				System.out.println("shooting projectiles toward your enemy, that should not give your enemy comfort in");
				System.out.println("trying to get any closer to you, you being just as proficient in"
						+ " hand to hand combat.");
				player.SetInventory(2, 0, 1, 0);
				player.DescribeInventory();
				break;
			case 3:
				System.out.println("Intelligent, Wise, Reclusive.");
				System.out.println("Your past lineages show a long histroy filled with the dabbling of sorceries,");
				System.out.println("being passed down from generation to generation, you possess alot of knowledge");
				System.out.println("of the physical world and the abstract. But with such knowledge, comes scrutiny.");
				System.out.println("Others are weary of you, tending to keep their distance for they feel your");
				System.out.println("fascination with such sorceries is seen as a dark art and is frowned upon by most people.");

				player.SetInventory(1, 0, 1, 0);
				player.DescribeInventory();
				break;
			}
		}
	}	
}	