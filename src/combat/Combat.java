package combat;

<<<<<<< HEAD
import gui.CombatViewer;
import gui.WorldMapViewer;

import java.util.Random;

import core.Entity;
import core.EventAdder;
import core.GlobalStateManager;
=======
import gui.CombatGUI;
import gui.WorldMap;

import java.util.Random;

import core.GlobalStateManager;

>>>>>>> origin/Mercury-game
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
	private EventAdder eventAdder;
	
	public Combat(Playable players, Enemy enemies,EventAdder eventAdder){
		// Dummy code for one player and one enemy
		this.player = players;
		this.enemy = enemies;
		//initializing random factor;
		rand = new Random();
		// decide who starts, true for player, false for enemy.
		turn = firstTurn();
		if(turn==false){
			enemyNextMove();
		}
		this.eventAdder = eventAdder;
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
	
	/**
	 * Enemy melee attack
	 */
	public void enemyAttack(){
		if(turn==false){
<<<<<<< HEAD
			if((player.getValueOfVital("Health")-enemy.getValueOfSkill("Attack"))<=0){
				playerDeath();
			}else{
				player.reduceVital("Health", enemy.getValueOfSkill("Attack"));
				System.out.println("Enemy attacked for: "+enemy.getValueOfSkill("Attack")); //Supposed to write to log here
				//while(clockTick<30);
				System.out.println(player.getName()+" turn!");
				playerTurn();
			}
		}
	}
	
	/**
	 * Enemy spellcast
	 * @param name - name of the spell we want to cast
	 */
=======
			if((player.getValueOfVital("Health")-enemy.getValueOfSkill("Attack"))>0){
				player.reduceVital("Health", enemy.getValueOfSkill("Attack"));
				playerTurn();
				System.out.println("Enemy attacked for: "+enemy.getValueOfSkill("Attack")); //Supposed to write to log here
				//while(clockTick<30);
				System.out.println(player.getName()+" turn!");
			}else{
				playerDeath();
			}
		}
	}
>>>>>>> origin/Mercury-game
	public void enemySpell(String name){ //Look at playerSpell for comments
		for(Spell spell : enemy.getSpellBook().getSpells()){
			if(spell.getName().equals(name)){
				if((enemy.getValueOfVital("Energy")-spell.getEnergyCost())>=0){
					if(spell.getType().equals("heal")){
						System.out.println(enemy.getName()+" casted spell "+name+" that healed: "+enemy.healVital("Health", spell.getEffect())+" on "+enemy.getName()); // Supposed to write to log here
					}else{
<<<<<<< HEAD
						if((player.getValueOfVital("Health")-spell.getEffect())<=0){
							playerDeath();
						}else{
							player.reduceVital("Health", spell.getEffect());		
							System.out.println(enemy.getName()+" casted spell "+name+" that damaged: "+spell.getEffect()+" on "+player.getName()); //Write to log
=======
						if((player.getValueOfVital("Health")-spell.getEffect())>0){
							player.reduceVital("Health", spell.getEffect());		
							System.out.println(enemy.getName()+" casted spell "+name+" that damaged: "+spell.getEffect()+" on "+player.getName()); //Write to log
						}else{
							playerDeath();
>>>>>>> origin/Mercury-game
						}
					}
					enemy.reduceVital("Energy", spell.getEnergyCost());
					enemyTurn();
					//while(clockTick<30);
					System.out.println(player.getName()+" turn!"); //Supposed to write to log here
				}else{
					enemyAttack();
				}
			}else{
				System.out.println("FATAL ERROR! SPELL NOT FOUND");
			}
		}
	}
<<<<<<< HEAD
=======
	public void enemyItem(String itemName){
		for(int i=0;i<enemy.getInventory().getItems().size();i++){
			if(enemy.getInventory().getItems().get(i).getItem().getName().equals(itemName)){ //Gör om med en extra loop när jag har tillgång till datasbas med items.
				enemy.getInventory().getItems().get(i).getItem().reduceCharges();
				enemy.healVital("Health", 40); //Fixar effekt i item senare.
			}
		}	
	}
>>>>>>> origin/Mercury-game
	/**
	 * Set turn to player;
	 */
	private void playerTurn(){
		turn=true;
		//player.manaReg();
	}
<<<<<<< HEAD
	
	/**
	 * Player melee attack
	 */
	public void playerAttack(){
		if(turn==true){
			if((enemy.getValueOfVital("Health")-player.getValueOfSkill("Attack"))<=0){
				enemyDeath();
			}else{
				enemy.reduceVital("Health", player.getValueOfSkill("Attack"));
				System.out.println("Player attacked for: "+player.getValueOfSkill("Attack")); //Supposed to write to log here
		
				System.out.println("Enemy turn!"); //Supposed to write to log here
				//resetUpdateCount();
				enemyTurn();
				enemyNextMove();	
				//while(clockTick<30);
			}
		}
	}
	
	/**
	 * Player spellcast
	 * @param name - name of spell we want to cast
	 */
	public void playerSpell(String name){
		if(turn==true){
			for(Spell spell : player.getSpellBook().getSpells()){
				if(spell.getName().equals(name)){ //Check for correct spell
					if((player.getValueOfVital("Energy")-spell.getEnergyCost())>=0){ //Check if char has Energy for spell
						if(spell.getType().equals("heal")){ //Check if spell is a heal
							System.out.println(player.getName()+" casted spell "+name+" that healed: "+player.healVital("Health", spell.getEffect())+" on "+player.getName()); // Supposed to write to log here
							player.reduceVital("Energy", spell.getEnergyCost());
							enemyTurn();
							System.out.println("Enemy turn!"); //Supposed to write to log here
							enemyNextMove();
						}else{
							if((enemy.getValueOfVital("Health")-spell.getEffect())<=0){ //check if attacked person dies or not
								enemyDeath();
								break;
							}else if((enemy.getValueOfVital("Health")-spell.getEffect())>0){
								enemy.reduceVital("Health", spell.getEffect());
								player.reduceVital("Energy", spell.getEnergyCost());
								System.out.println(player.getName()+" casted spell "+name+" that damaged: "+spell.getEffect()+" on "+enemy.getName()); //Write to log
								player.reduceVital("Energy", spell.getEnergyCost());
								enemyTurn();
								System.out.println("Enemy turn!"); //Supposed to write to log here
								enemyNextMove();
							}
=======
	private void playerAttack(){
		if(turn==true){
			if((player.getValueOfVital("Health")-player.getValueOfSkill("Attack"))>0){
				enemy.reduceVital("Health", player.getValueOfSkill("Attack"));
				enemyTurn();
				System.out.println("Player attacked for: "+player.getValueOfSkill("Attack")); //Supposed to write to log here
		
				System.out.println("Enemy turn!"); //Supposed to write to log here
				resetUpdateCount();
				enemyNextMove();
				//while(clockTick<30);		
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
>>>>>>> origin/Mercury-game
						}
					}else{
						System.out.println("Not enough Energy!"); //Supposed to write to log here
					}
				}
			}
			resetUpdateCount();
			//while(clockTick<30);
		}
<<<<<<< HEAD
	}
	
	/**
	 * Method for handling player death
	 */
	public void playerDeath(){
		player.reduceVital("Health",player.getValueOfVital("Health"));
		System.out.println(player.getName()+" has died! The fight is lost.");
		
		player.healVital("Health", player.getMaxOfVital("Health"));
		player.healVital("Energy", player.getMaxOfVital("Energy"));				
		
		enemy.healVital("Health", enemy.getMaxOfVital("Health"));
		enemy.healVital("Energy", enemy.getMaxOfVital("Energy"));
		
		GlobalStateManager.getInstance().updateCurrentState("InCombat_dead");
		eventAdder.add("sceneTown");
		//Return to town
	}
	
	/**
	 * Method for handling enemy death
	 */
	public void enemyDeath(){
=======
		resetUpdateCount();
		//while(clockTick<30);
		enemyNextMove();
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
>>>>>>> origin/Mercury-game
		enemy.reduceVital("Health",enemy.getValueOfVital("Health"));
		System.out.println(enemy.getName()+" has died! The fight is won."); //Supposed to write to log here
		int levelCheck=player.getLevel();
		player.addExp(5*enemy.getLevel());
		if(levelCheck<player.getLevel()){
			System.out.println("Congratulations "+player.getName()+"! You leveled to "+player.getLevel());
		}else{
			System.out.println(player.getName()+" got "+5*enemy.getLevel()+" xp!");
		}
		
<<<<<<< HEAD
		GlobalStateManager.getInstance().updateWorldState(GlobalStateManager.getInstance().getWorldState("Location"), "clear");
		eventAdder.add("sceneWorldMap");
		//Return to world map
	}
	
	/**
	 * Player using an item
	 * @param itemName - Name of the item we want to use
	 */
	public void playerItem(String itemName){								
		for(int i=0;i<player.getInventory().getItems().size();i++){
			if(player.getInventory().getItems().get(i).getItem().getName().equals(itemName)){ //G�r om med en extra loop n�r jag har tillg�ng till datasbas med items.
=======
		//Return to world map
	}
	
	private void playerItem(String itemName){								
		for(int i=0;i<player.getInventory().getItems().size();i++){
			if(player.getInventory().getItems().get(i).getItem().getName().equals(itemName)){ //Gör om med en extra loop när jag har tillgång till datasbas med items.
>>>>>>> origin/Mercury-game
				player.getInventory().getItems().get(i).getItem().reduceCharges();
				System.out.println(player.healVital("health", 40)); //Fixar effekt i item senare.
			}
		}
	}
	
<<<<<<< HEAD
	/**
	 * Calculate the chance for successful retreat
	 * @param character - player or enemy we want to calculate chance for
	 * @return the chance of successful retreat
	 */
	public double retreatChance(Character character){
=======
	private double retreatChance(Character character){
>>>>>>> origin/Mercury-game
		if(character instanceof Playable){
			return ((40*player.getValueOfSkill("Speed"))/enemy.getValueOfSkill("Speed"))+10;
		}else{
			return ((20*enemy.getValueOfSkill("Speed"))/player.getValueOfSkill("Speed"));
		}
		
	}
	
<<<<<<< HEAD
	/**
	 * Player trying to retreat from combat
	 */
	public void playerRetreat(){
		if(retreatChance(player)>=nextInt()){
			System.out.println("Successfull escape!");
			eventAdder.add("sceneWorldMap");
=======
	private void playerRetreat(){
		if(retreatChance(player)>=nextInt()){
			System.out.println("Successfull escape!");
			//returnToWorldMap();
			//Render the world map here.
>>>>>>> origin/Mercury-game
		}else{
			System.out.println("You failed to run!"); //Supposed to write to log here
		}
	}
	
	/**
	 * Calculates an rnd value between 0-100
	 * @return a value 0-100
	 */
	private int nextInt(){
		return rand.nextInt(100) + 1;
	}
	
<<<<<<< HEAD
	/**
	 * Decides who get to attack first
	 * @return
	 */
=======
>>>>>>> origin/Mercury-game
	private boolean firstTurn(){
		return(player.getValueOfSkill("Speed")>=enemy.getValueOfSkill("Speed"));
	}
	
<<<<<<< HEAD
	/**
	 * Uses enemy AI for calculating enemy next move
	 */
	private void enemyNextMove(){
			if(enemy.AI()=="attack"){
				enemyAttack();
			}else if(enemy.AI()=="spell"){
				enemySpell("fireball");
			}else if(enemy.AI()=="heal"){
				enemySpell("heal");
			}else{
				enemyAttack();	
			}
=======
	private void enemyNextMove(){
		if(enemy.AI()=="attack"){
			enemyAttack();
		}else if(enemy.AI()=="spell"){
			enemySpell("fireball");
		}else if(enemy.AI()=="heal"){
			enemySpell("heal");
		}else{
			enemyAttack();	
		}
>>>>>>> origin/Mercury-game
	}

}
