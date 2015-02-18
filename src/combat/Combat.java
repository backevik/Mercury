package combat;




import gui.CombatGUI;
import gui.WorldMap;

import java.util.Random;

import player.Playable;
import character.Character;
import character.Spell;

/**
 * @author      Andreas Bäckevik
 * @version     0.39
 * @since       2015-02-09
 */

public class Combat implements Entity{
	private boolean turn;
	private Random rand;
	private Playable player;
	private Enemy enemy;
	private int clockTick;
	
	public Combat(Playable players, Enemy enemies){
		// Dummy code for one player and one enemy
		this.player = players;
		this.enemy = enemies;
		// decide who starts, true for player, false for enemy.
		turn = firstTurn();
		//initializing random factor;
		rand = new Random();
		
	}
	
	/**
	 * Counting amount of updates for real-time clock
	 */
	public void update(){
		clockTick++;
	}
	
	/**
	 * Resetting count of real-time clock
	 */
	public void resetUpdateCount(){
		clockTick=0;
	}
	
	/**
	 * Set turn to enemy;
	 */
	private void enemyTurn(){
		turn=false;
		//enemy.manaReg();
	}
	
	public void enemyAttack(){
		if(turn==false){
			if((player.getValueOfVital("Health")-enemy.getValueOfSkill("Attack"))>0){
				player.reduceVital("Health", enemy.getValueOfSkill("Attack"));
				playerTurn();
				System.out.println("Enemy attacked for: "+enemy.getValueOfSkill("Attack")); //Supposed to write to log here
			}else{
				playerDeath();
			}
		}
	}
	public void enemySpell(String name){ //Look at playerSpell for comments
		for(Spell spell : enemy.getSpellBook().getSpells()){
			if(spell.getName().equals(name)){
				if((enemy.getValueOfVital("Energy")-spell.getEnergyCost())>=0){
					if(spell.getType().equals("heal")){
						System.out.println(enemy.getName()+" casted spell "+name+" that healed: "+enemy.healVital("Health", spell.getEffect())+" on "+enemy.getName()); // Supposed to write to log here
					}else{
						if((player.getValueOfVital("Health")-spell.getEffect())>0){
							player.reduceVital("Health", spell.getEffect());		
							System.out.println(enemy.getName()+" casted spell "+name+" that damaged: "+spell.getEffect()+" on "+player.getName()); //Write to log
						}else{
							playerDeath();
						}
					}
					enemy.reduceVital("Energy", spell.getEnergyCost());
					enemyTurn();
					System.out.println("Player turn!"); //Supposed to write to log here
				}else{
					System.out.println("Not enough Energy!"); //Supposed to write to log here
				}
			}
		}
	}
	public void enemyItem(String itemName){
		for(int i=0;i<enemy.getInventory().getItems().size();i++){
			if(enemy.getInventory().getItems().get(i).getItem().getName().equals(itemName)){ //Gör om med en extra loop när jag har tillgång till datasbas med items.
				enemy.getInventory().getItems().get(i).getItem().reduceCharges();
				enemy.healVital("Health", 40); //Fixar effekt i item senare.
			}
		}	
	}
	
	public void enemyRetreat(){
		if(retreatChance(enemy)>=nextInt()){
			System.out.println("Run bitch!");
			//Render the worldmap here.
		}else{
			System.out.println("The enemy failed to run!"); //Supposed to write to log here
		}
	}
	
	/**
	 * Set turn to player;
	 */
	private void playerTurn(){
		turn=true;
		//player.manaReg();
	}
	private void playerAttack(){
		if(turn==true){
			if((player.getValueOfVital("Health")-player.getValueOfSkill("Attack"))>0){
				enemy.reduceVital("Health", player.getValueOfSkill("Attack"));
				enemyTurn();
				System.out.println("Player attacked for: "+player.getValueOfSkill("Attack")); //Supposed to write to log here
		
				System.out.println("Enemy turn!"); //Supposed to write to log here
				resetUpdateCount();
				//while(clockTick<30);
				enemyAttack();
			}else{
				enemyDeath();
			}
		}
	}
	private void playerSpell(String name){
		for(Spell spell : player.getSpellBook().getSpells()){
			if(spell.getName().equals(name)){ //Check for correct spell
				if((player.getValueOfVital("Energy")-spell.getEnergyCost())>=0){ //Check if char has Energy for spell
					if(spell.getType().equals("heal")){ //Check if spell is a heal
						System.out.println(player.getName()+" casted spell "+name+" that healed: "+player.healVital("Health", spell.getEffect())+" on "+player.getName()); // Supposed to write to log here
					}else{
						if((enemy.getValueOfVital("Health")-spell.getEffect())>0){ //check if attacked person dies or not
							enemy.reduceVital("Health", spell.getEffect());		
							System.out.println(player.getName()+" casted spell "+name+" that damaged: "+spell.getEffect()+" on "+enemy.getName()); //Write to log
						}else{
							enemyDeath();
						}
					}
					player.reduceVital("Energy", spell.getEnergyCost());
					enemyTurn();
					System.out.println("Enemy turn!"); //Supposed to write to log here
				}else{
					System.out.println("Not enough Energy!"); //Supposed to write to log here
				}
			}
		}
		resetUpdateCount();
		//while(clockTick<30);
		enemyAttack();
	}
	
	private void playerDeath(){
		player.reduceVital("Health",player.getValueOfVital("Health"));
		System.out.println(player.getName()+" has died! The fight is lost.");
		
		player.healVital("Health", player.getMaxOfVital("Health"));
		player.healVital("Energy", player.getMaxOfVital("Energy"));				
		
		enemy.healVital("Health", enemy.getMaxOfVital("Health"));
		enemy.healVital("Energy", enemy.getMaxOfVital("Energy"));
		
		//Return to world map
	}
	
	private void enemyDeath(){
		enemy.reduceVital("Health",enemy.getValueOfVital("Health"));
		System.out.println(enemy.getName()+" has died! The fight is won."); //Supposed to write to log here
		int levelCheck=player.getLevel();
		player.addExp(5*enemy.getLevel());
		if(levelCheck<player.getLevel()){
			System.out.println("Congratulations "+player.getName()+"! You leveled to "+player.getLevel());
		}else{
			System.out.println(player.getName()+" got "+5*enemy.getLevel()+" xp!");
		}
		
		//Return to world map
	}
	
	private void playerItem(String itemName){								
		for(int i=0;i<player.getInventory().getItems().size();i++){
			if(player.getInventory().getItems().get(i).getItem().getName().equals(itemName)){ //Gör om med en extra loop när jag har tillgång till datasbas med items.
				player.getInventory().getItems().get(i).getItem().reduceCharges();
				System.out.println(player.healVital("health", 40)); //Fixar effekt i item senare.
			}
		}
	}
	
	private double retreatChance(Character character){
		if(character instanceof Playable){
			return ((40*player.getValueOfSkill("Speed"))/enemy.getValueOfSkill("Speed"))+10;
		}else{
			return ((20*enemy.getValueOfSkill("Speed"))/player.getValueOfSkill("Speed"));
		}
		
	}
	
	private void playerRetreat(){
		if(retreatChance(player)>=nextInt()){
			System.out.println("Successfull escape!");
			//returnToWorldMap();
			//Render the world map here.
		}else{
			System.out.println("You failed to run!"); //Supposed to write to log here
		}
	}
	
	private int nextInt(){
		return rand.nextInt(100) + 1;
	}
	
	private boolean firstTurn(){
		return(player.getValueOfSkill("Speed")>=enemy.getValueOfSkill("Speed"));
	}

}
