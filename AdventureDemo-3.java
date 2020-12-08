import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.io.FileWriter;
import java.util.concurrent.atomic.AtomicInteger;

//this thread makes a log that records certain events in the game in a text file
class LogWriter extends Thread{
	private Thread t;
	private String threadName;
	final AtomicInteger atomicInteger;
	
	LogWriter(String name, AtomicInteger aInteger){
		atomicInteger = aInteger;
		threadName = name;
	}
	
	//start the thread
	public void run(){
		try{
			FileWriter fileWriter = new FileWriter("event-log.txt", true);
			fileWriter.write("Event Log Started at: " + System.currentTimeMillis() + "(Current time in milliseconds)\n");
			fileWriter.flush();
			while(true){
				if(atomicInteger.get() == 1){
					fileWriter.write("Player lost game at: " + System.currentTimeMillis() + "(Current time in milliseconds)\n");
					fileWriter.flush();
				}
			}
		} catch(IOException e){
			System.out.println("Failed to create log file.");
			e.printStackTrace();
		}
	}
	
	public void start(){
		if(t == null){
			t = new Thread(this, threadName);
			t.start();
		}
	}
}

public class AdventureDemo{
	
	//atomic integers help transfer data between the two threads
	final AtomicInteger atomicInteger = new AtomicInteger(0);
	
	Scanner scr = new Scanner(System.in);
	
	ArrayList<String> eventBuffer = new ArrayList<String>();
	
	public static class Player{
		String Name;
		int PlayerType = -1;
		
		/*
		 * Inventory
		 * Canned Food - 0
		 * Sticks - 1
		 * Firearms - 2
		 * Phones - 3
		 * */

		int[] Inventory = {0, 0, 0, 0};
		
		public void SetInventory(int cannedFood, int stick, int fireArm, int phone){
			Inventory[0] = cannedFood;
			Inventory[1] = stick;
			Inventory[2] = fireArm;
			Inventory[3] = phone;
		}
		
		public void DescribeInventory(){
			System.out.println("-------------------------------");
			System.out.println("Your Inventory:");
			System.out.println("Canned Food: " + Inventory[0]);
			System.out.println("Stick(s): " + Inventory[1]);
			System.out.println("Firearm(s): " + Inventory[2]);
			System.out.println("Phone(s): " + Inventory[3]);
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
	}
	
	public static void main(String args[]){
		AdventureDemo adventureDemo = new AdventureDemo();
		
		LogWriter logWriter = new LogWriter("gameLog-1", adventureDemo.atomicInteger);
		logWriter.start();
			System.out.println("Adventure Game v0.0.1 DEMO Edition");
			
			Player player = new Player();
			
			adventureDemo.setPlayer(player);
			adventureDemo.FlushSpace();
			adventureDemo.preparePlayer(adventureDemo, player);
			adventureDemo.startSequence(adventureDemo, player);

	}
	
	//this closes out the game when a game session ends
	public void endGame(Player player){
		System.out.println("-----------GAME OVER-----------");
		System.out.println("Press any key to exit.");
		atomicInteger.set(1);
		try{System.in.read();}
		catch(Exception e){}
		System.exit(0);
	}
	
	//asks for player name and character to play as
	public void setPlayer(Player player){
		System.out.println("Your name son, give me your name");
		player.Name = scr.nextLine();
		System.out.println("What are you, " + player.Name);
		while(player.PlayerType <= 0){
			System.out.println("Choose your class: Civilian (1), Hobo (2), or Businessman (3)");
			
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
	
	public void preparePlayer(AdventureDemo adventureDemo, Player player){
		switch(player.PlayerType){
			case 1:
				System.out.println("You cannot defend yourself, but you have a phone");
				System.out.println("You are an average Joe who is afraid of things most people are afraid of, like conflict, zombies, and communism");
				player.SetInventory(1, 0, 0, 1);
				player.DescribeInventory();
				break;
			case 2:
				System.out.println("You have some sticks to defend yourself, and some scavenged food to keep you alive");
				System.out.println("Your life on the streets has made you tougher and stinkier than most");
				player.SetInventory(2, 1, 0, 0);
				player.DescribeInventory();
				break;
			case 3:
				System.out.println("You have purchased a firearm in order to stay safe on the streets, but pray you should not be forced to use it");
				System.out.println("Your cozy city life has made you fearful and cautious of the lower income communities");
				player.SetInventory(0, 0, 1, 1);
				player.DescribeInventory();
				break;
		}
	}
	
	//frees space on certain prompts so the text isn't squished together
	public void FlushSpace(){
		for(int i = 0; i < 50; i++){
			System.out.println("");
		}
	}
	
	public int useItem(Player player){
		while(true){
			System.out.println("What item would you like to use?");
			player.DescribeInventory();
			int playerChoice = scr.nextInt();
			if(player.ItemExists(playerChoice - 1)){
				return playerChoice;
			} else{
				System.out.println("You don't own this item.");
			}
		}
	}
	
	//starts the game
	public void startSequence(AdventureDemo adventureDemo, Player player){
		adventureDemo.kittenEvent(adventureDemo, player);
	}
	
	public void kittenEvent(AdventureDemo adventureDemo, Player player){
		boolean kittenComplete = false;
		while(!kittenComplete){
			System.out.println("-------------------------------------------------------------------");
			System.out.println("You're walking down a sidewalk and see a small kitten under a box");
			System.out.println("He looks tired and scared");
			System.out.println("What do you want to do?");
			System.out.println("1: Observe the kitten");
			System.out.println("2: Pick up the kitten");
			System.out.println("3: Kick the kitten's box");
			System.out.println("4: Check Items");
			System.out.println("-------------------------------------------------------------------");
			
			int playerChoice = scr.nextInt();
			
			switch(playerChoice){
				case 1:
					adventureDemo.FlushSpace();
					System.out.println("You observe the kitten and see that he is also hungry.");
					kittenComplete = false;
					break;
				case 2:
					adventureDemo.FlushSpace();
					System.out.println("You pick up the poor kitten and conclude that he needs immediate care.");
					System.out.println("1: Take him home");
					System.out.println("2: Put him back down.");
					int actionChoice = scr.nextInt();
					switch(actionChoice){
						case 1:
							adventureDemo.FlushSpace();
							System.out.println("You take the kitten home, and nurse him back to health.");
							System.out.println("Your adventure has been cut short, but now you have a new friend.");
							System.out.println("Press ENTER to continue.");
							try{System.in.read();}
							catch(Exception e){}
							adventureDemo.endGame(player);
							kittenComplete = true;
							break;
						case 2:
							adventureDemo.FlushSpace();
							System.out.println("You put the kitten back in his box");
							kittenComplete = false;
							break;
						default:
							kittenComplete = false;
					}
					break;
				case 3:
					adventureDemo.FlushSpace();
					System.out.println("Being the jerk you apparently are, you kick the kitten's box.");
					System.out.println("Enraged, the kitten lets out a Bruce Lee yell and kung fu kicks you in your soft spot.");
					System.out.println("Humiliated, you fall to the floor and cry yourself to sleep.");
					System.out.println("Press ENTER to continue.");
					try{System.in.read();}
					catch(Exception e){}
					adventureDemo.endGame(player);
					kittenComplete = true;
					break;
				case 4:
					adventureDemo.FlushSpace();
					int itemChoice = adventureDemo.useItem(player);
					switch(itemChoice){
						case 1: 
							adventureDemo.FlushSpace();
							System.out.println("You give the cat food, he gobbles it up and lets out a happy meow.");
							System.out.println("Satisfied, you move on to your next location.");
							System.out.println("Press ENTER to continue.");
							try{System.in.read();}
							catch(Exception e){}
							adventureDemo.streetEvent(adventureDemo, player);
							kittenComplete = true;
							break;
						case 2:
							adventureDemo.FlushSpace();
							System.out.println("You point the stick towards the kitten.");
							System.out.println("Scared, he claws it up and destroys the stick.");
							kittenComplete = false;
							break;
						case 3:
							adventureDemo.FlushSpace();
							System.out.println("For some reason you decided to pull out a GUN on a helpless kitten.");
							System.out.println("Unfortunately for you, this kitten knows 50 different forms of jiujitsu.");
							System.out.println("The kitten gives you a beating and you end up in the hospital for a lengthy and painful recovery.");
							System.out.println("Press ENTER to continue.");
							try{System.in.read();}
							catch(Exception e){}
							adventureDemo.endGame(player);
							kittenComplete = true;
							break;
						case 4:
							adventureDemo.FlushSpace();
							System.out.println("You get out your phone and call animal services.");
							System.out.println("They soon arrive but alongside them are the FBI.");
							System.out.println("Your description of the kitten has brought up red flags across the country.");
							System.out.println("This kitten was apparently wanted in 10 states for aggravated assault.");
							System.out.println("You receive praise for your discovery and become an instant local celebrity.");
							System.out.println("You did not expect such an outcome, but you go with it and move along.");
							System.out.println("Press  ENTER to continue.");
							try{System.in.read();}
							catch(Exception e){}
							adventureDemo.streetEvent(adventureDemo, player);
							kittenComplete = true;
							break;
					}
					break;
			}
		}
	}
	public void streetEvent(AdventureDemo adventureDemo, Player player){
		boolean streetComplete = false;
		while(!streetComplete){
			System.out.println("-------------------------------------------------------------");
			System.out.println("You have now come across a bustling busy street");
			System.out.println("It is nighttime, but there are lights, people and open stores everywhere");
			System.out.println("Danger is very unlikely to reach you here, What would you like to do?");
			System.out.println("1: Eat at a restaurant");
			System.out.println("2: Go to the store");
			System.out.println("3: Socialize with the citizens");
			System.out.println("4: Make your way home");
			
			int streetChoice = scr.nextInt();
			
			switch(streetChoice){
				case 1:
				adventureDemo.FlushSpace();
				System.out.println("Which restaurant would you like to go to?");
				System.out.println("1: Donatello's Pizzaria");
				System.out.println("2: Ali's Cuisine of the Middle East");
				System.out.println("3: Saturday's All American Diner");
				System.out.println("4: Beijing Express");
				
				int restaurantChoice = scr.nextInt();
				
				switch(restaurantChoice){
					case 1:
					adventureDemo.FlushSpace();
					System.out.println("You enter one of the most famous Italian restaurants in town");
					System.out.println("The smell of fresh spaghetti and pizza entices your appetite");
					System.out.println("What would you like to order?");
					System.out.println("1: Meat Lover's Pizza");
					System.out.println("2: Vegetarian Pizza");
					System.out.println("3: Spaghetti and Meat Sauce");
					System.out.println("4: Meatball Sandwich");
					System.out.println("5: Torpedo Sandwich");
					
					int italFoodChoice = scr.nextInt();
					
					switch(italFoodChoice){
						case 1, 2, 3, 4, 5:
						adventureDemo.FlushSpace();
						System.out.println("Your meal has arrived");
						System.out.println("But as you are about to indulge, something interesting occurs");
						System.out.println("A group of men in black enter the establishment, asking to see the owner");
						System.out.println("As they are escorted, one henchman stays behind and catches your eye");
						System.out.println("Hungry and angry, he demands to have your meal");
						System.out.println("What do you do?");
						System.out.println("1: Give the man your meal");
						System.out.println("2: Check Items");
						System.out.println("3: Call him fat");
						
						int thugChoice = scr.nextInt();
						
						switch(thugChoice){
							case 1:
							adventureDemo.FlushSpace();
							System.out.println("You give the thug your meal");
							System.out.println("Satisfied, the thug leaves you alone and joins his friends in the back");
							System.out.println("You leave the restaurant, hungry, but safe");
							streetComplete = false;
							break;
							case 2:
							adventureDemo.FlushSpace();
							int itemChoice = adventureDemo.useItem(player);
					        switch(itemChoice){
								case 1:
								adventureDemo.FlushSpace();
								System.out.println("You offer a piece of canned food to the henchman");
								System.out.println("Confused and insulted, he smacks the food out of your hand and demands the meal on your table");
								System.out.println("Give the man your meal? Y/N?");
								
							    char giveMeal = scr.next().charAt(0);
								
								switch(giveMeal){
									case 'Y', 'y':
									adventureDemo.FlushSpace();
									System.out.println("You give the thug your meal");
							        System.out.println("Satisfied, the thug leaves you alone and joins his friends in the back");
							        System.out.println("You leave the restaurant, hungry, but safe");
							        streetComplete = false;
							        break;
							        case 'N', 'n':
							        adventureDemo.FlushSpace();
							        System.out.println("Tired of bargaining, the man takes a swing at you");
							        System.out.println("However, before he lands the blow, a voice call out to him");
							        System.out.println("His friend is telling him to come to the back with the others");
							        System.out.println("Frustrated, he leaves, and you finish your meal, pay, and go back out to the street");
							        streetComplete = false;
							        break;
								}
								break;
							
								case 2:
								adventureDemo.FlushSpace();
								System.out.println("You pull out your stick against the man");
								System.out.println("You poke him with the stick, angering him");
								System.out.println("The following 5 minutes involve a brutal beating performed on you, followed by an ambulance immediatey taking you to the ICU");
								System.out.println("Now you are defeated and hungry");
								System.out.println("Press ENTER to continue");
								try{System.in.read();}
							    catch(Exception e){}
							    adventureDemo.endGame(player);
							    break;
							    case 3:
							    adventureDemo.FlushSpace();
							    System.out.println("You pull out your firearm against the thug");
							    System.out.println("Surprised, the thug puts both his hands in the air");
							    System.out.println("Scared, he says he doesn't want any trouble and runs out the back");
							    System.out.println("The owner of the restaurant notices the commotion and goes to see you");
							    System.out.println("Impressed by your comnbat skill, he informs you of his dilemma");
							    System.out.println("");			
							    System.out.println("He tells you that the group that showed up was a group known as the Bratoni Mafia Family");
							    System.out.println("They have been demanding protection money from the owner but he is out of money");
							    System.out.println("He asks if you can go to their hideout and convince the boss to leave him alone");
							    System.out.println("Will you accept the quest? Y/N?");
							    
							    char mafiaQuest = scr.next().charAt(0);
							    switch(mafiaQuest){
									case 'Y', 'y':
									System.out.println("You vow to the kind man that you will return to him with news that he is debt free");
									System.out.println("You ask him where their base is and you go off to convince the boss");
									break;
									case 'N', 'n':
									System.out.println("Fearing for your safety, you call the man crazy and rush out back to the street");
									streetComplete = false;
									break;
								}																								
						   }
						   break;
						   case 3:
						   adventureDemo.FlushSpace();
						   System.out.println("In your infinite wisdom you decide to insult the man who is twice your height and weight");		
						   System.out.println("What follows is a beating so horrific that it seems likely your journey has come to an end...");
						   try{System.in.read();}
							    catch(Exception e){}
							    adventureDemo.endGame(player);
							    break;				   
					   }						   
				    }
				  break;				   				    														
	              case 2:
	              adventureDemo.FlushSpace();
	              System.out.println("A renowned restaurant celebrating the food of the Middle East, the scent of spices and exotic meats entice your sense of smell");
	              System.out.println("It is not a cuisine you are used to, but you'll try anything once");
	              System.out.println("What would you like to order?");
	              System.out.println("1: Falafel with Fresh Hummus");
	              System.out.println("2: Fish curry with tilapia");
	              System.out.println("3: Lamb with Turkish coffee");
	              
	              int ArabFoodChoice = scr.nextInt();
	              
	              switch(ArabFoodChoice){
					  case 1, 2:
					  adventureDemo.FlushSpace();
					  System.out.println("Your meal has arrived");
					  System.out.println("You chow down and have one of the most enjoyable meals of your life");
					  System.out.println("You feel the need to go to the owner himself and thank him");
					  System.out.println("However, he is not present at the moment, so you pay with the momey you have and leave");
					  streetComplete = false;
					  break;
					  case 3:
					  adventureDemo.FlushSpace();
					  System.out.println("The waiter regretfully informs you that they are out of lamb");
					  System.out.println("He asks if you want to wait until they receive some from the owner, who is out shopping");
					  System.out.println("Wait for the lamb? Y/N?");
					  
					  char lambWait = scr.next().charAt(0);
					  
					  switch(lambWait){
						  case 'Y', 'y':
						  adventureDemo.FlushSpace();
						  System.out.println("You decide to stay and wait for the lamb");
						  System.out.println("After a few minutes you see the owner return with shopping bags");
						  System.out.println("Minutes later the waiter arrives with your meal, and thanks you for waiting");
						  System.out.println("It is the most delicious meal you've had in a long time, and feel the need to thank the owner for bringing it");
						  System.out.println("When you enter the kitchen, you see the owner in the middle of a phone call");
						  System.out.println("He looks frustrated and distressed");
						  System.out.println("After he is done, you ask him what is the matter");
						  System.out.println("He says that his son had disappeared the night before, and has been unable to receive word from the police");
						  System.out.println("He then asks with guilt if you may go out and search for his son");
						  System.out.println("Search for the son? Y/N");
						  
						  char sonQuest = scr.next().charAt(0);	
						  
						  switch(sonQuest){
							  case 'Y', 'y':
							  adventureDemo.FlushSpace();
							  System.out.println("You offer to help and tell the man you will look for his son");
							  System.out.println("He thanks you and you set off to look for the young man");
							  System.out.println("However, you realize you know nothing about his disappearance");
							  System.out.println("Hopefully you will find clues by exploring the streets");
							  streetComplete = false;
							  break;
							  case 'N', 'n':
							  adventureDemo.FlushSpace();
							  System.out.println("You feel bad for the poor man, but you decline, concerned about your own well-being");
							  System.out.println("You thank him for the lamb and leave the restaurant, back into the street");
							  streetComplete = false;
							  break;
						  }
						  break;
						  case 'N', 'n':
						  adventureDemo.FlushSpace();
						  System.out.println("You decide to not wait for the lamb, and leave the establishment");
						  streetComplete = false;
						  break;
					   }		              	              				    	          	             	       						
				}	         	 
                break;
                case 3:
                adventureDemo.FlushSpace();
                System.out.println("One of the most beloved restaurants in town, this classic diner contains everyone's favorites!");
                System.out.println("What would you like to order?");
                System.out.println("1: Cheeseburger with fries");
                System.out.println("2: Country Fried Steak");
                System.out.println("3: Hearty Sausage Skillet with Eggs");
                
                int USFoodChoice = scr.nextInt();
                
                switch(USFoodChoice){
					case 1, 2, 3:
					adventureDemo.FlushSpace();
					System.out.println("As you are waiting for your order you hear a commotion behind you");
					System.out.println("You turn around and see that a man is arguing with a waitress");
					System.out.println("The argument appears to be about a wrong order, but it looks like it is getting heated");
					System.out.println("Even the man's dog is getting riled up");
					System.out.println("What do you want to do?");
					System.out.println("1: Approach the two and tell the man to stop");
					System.out.println("2: Ignore it and wait for your food");
					
					int customerChoice = scr.nextInt();
					
					switch(customerChoice){
						case 1:
						adventureDemo.FlushSpace();
						System.out.println("You approach the man and ask him to tone it down a bit");
						System.out.println("He gets up and asks you if you want a piece of him");
						System.out.println("What now?");
						System.out.println("1: Apologize and return to your table");
						System.out.println("2: Check Items");
						System.out.println("3: Call him fat");
						
						int angryManChoice = scr.nextInt();
						
						switch(angryManChoice){
							case 1:
							adventureDemo.FlushSpace();
							System.out.println("Scared, you get on your knees and apologize frantically to the man");
							System.out.println("Seeing you shaking and crying, the man feels bad for you and says to just forget it");
							System.out.println("You return to your table, put on your bib and eat your meal");
							System.out.println("Afterwards, you pay and leave, going back to the streets");
						    streetComplete = false;
						    break;
						    case 2:
						    adventureDemo.FlushSpace();
							int itemChoice = adventureDemo.useItem(player);
					        switch(itemChoice){
								case 1:
								adventureDemo.FlushSpace();
								System.out.println("You pull out a piece of canned food");
								System.out.println("Confused, the angry man feels insulted and breaks open the can");
								System.out.println("He then proceeds to force feed the contents to you in a cartoonish and humorous fashion");
								System.out.println("Humiliated, you break into a fetal position on the floor and start sobbing quietly to yourself");
								System.out.println("Yeah, it looks like you're not recovering from that, physically and mentally");
								System.out.println("Press ENTER to continue.");
					            try{System.in.read();}
					            catch(Exception e){}
					            adventureDemo.endGame(player);
					            break;
					            case 2:
					            adventureDemo.FlushSpace();
					            System.out.println("You take your stick and throw it at the man");
					            System.out.println("You miss and it goes out the window into the street");
					            System.out.println("The man's dog barks excitedly and chases after the stick");
					            System.out.println("The man immediately runs out of the restaurant to chase after the dog");
					            System.out.println("That should not have worked, but you've chased the disgruntled officer away");
					            System.out.println("You then return to your table to finish your meal");
					            System.out.println("");
					            System.out.println("Suddenly a man walks up to you");
					            System.out.println("He introduces himself as the commanding officer of the soldier you just chased off");
					            System.out.println("Impressed by your combat skill, he makes you an offer");				           
					            System.out.println("He challenges you to catch a drug lord his unit had been pursuing in South America and has fled to the city");
					            System.out.println("He says if you succeed, you will be rewarded handsomely");
					            System.out.println("Accept quest? Y/N?");
					            
					            char bountyQuest = scr.next().charAt(0);
					            
					            switch(bountyQuest){
									case 'Y', 'y':
									adventureDemo.FlushSpace();
									System.out.println("You accept the assignment and are escorted by helicopter to the culprit's last known location");
									streetComplete = false;
									break;
									case 'N', 'n':
									adventureDemo.FlushSpace();
									System.out.println("You politely decline and leave the restaurant");
									System.out.println("You return to the street");
									streetComplete = false;
									break;																					    							
				                    }				    
				                break;
				                case 3:
				                adventureDemo.FlushSpace();
				                System.out.println("You pull out your firearm on the man");
				                System.out.println("This might've been a good idea to intimidate the man...");
				                System.out.println("...If he wasn't a Navy SEAL");
				                System.out.println("You are laid out faster than you can count to one");
				                System.out.println("You lapse into a coma, nobody knowing if you will recover from your epic beating");
				                System.out.println("Press ENTER to continue");
				                try{System.in.read();}
					            catch(Exception e){}
					            adventureDemo.endGame(player);
					            break;
					            case 4:
					            adventureDemo.FlushSpace();
					            System.out.println("You pull out your phone and call the police");
					            System.out.println("The cops arrive at the scene and attempt to arrest the man for public disturbance");
					            System.out.println("The agitated customer proceeds to use his Navy SEAL training to vanquish the officers");
					            System.out.println("Scared, you make a run for it in the commotion, not looking back, going home and crying yourself to sleep that night");
					            System.out.println("Never again will you involve yourself in conflict");
					            System.out.println("Your days as a protagonist are over");
					            System.out.println("Press ENTER to continue");
					            try{System.in.read();}
					            catch(Exception e){}
					            adventureDemo.endGame(player);
					            break;				                
                                }
                            break;
                            case 3:
                            adventureDemo.FlushSpace();
                            System.out.println("Being the tactical genius and great compromiser that you are, you call this hulking man's appearance");
                            System.out.println("Prous of yourself, you are then immediately knocked out by probably the angriest punch this man has ever thrown");
                            System.out.println("Your backwards logic has once again become your hubris");
                            System.out.println("Press ENTER to continue");
				            try{System.in.read();}
					        catch(Exception e){}
					        adventureDemo.endGame(player);}
					        break;
					        case 2:
                            adventureDemo.FlushSpace();
                            System.out.println("You ignore the commotion and return to your table");
                            System.out.println("You enjoy your All American meal, pay and leave, not looking back at the noisy commotion inside");
                            System.out.println("As you return to the streets, you wonder if you could've done anything");
                            streetComplete = false;
                            break;
	                        }                     
                          }
    }
   }
  }
 }
}
