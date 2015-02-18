package combat;

import java.util.List;


import java.util.Random;

import player.Playable;
import character.Character;

/**
 * @author      Andreas Bäckevik
 * @version     0.2
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
	}
	public void enemyAttack(){
		if(turn==false){
			player.reduceVital("Health", enemy.getValueOfSkill("Attack"));
			playerTurn();
			System.out.println("Enemy attacked for: "+enemy.getValueOfSkill("Attack")); //Supposed to write to log here
		}
	}
	public void enemySpell(String name){
		for(int i=0;i<enemy.getSpellBook().getSpells().size();i++){
			if(enemy.getSpellBook().getSpells().get(i).getName().equals(name)){
				player.reduceVital("Health", enemy.getValueOfSkill("Attack"));
				enemy.reduceVital("Energy", enemy.getSpellBook().getSpells().get(i).getEnergyCost());
				playerTurn();
				System.out.println("Enemy casted spell "+name+" that damaged: "+enemy.getValueOfSkill("Attack")); // Supposed to write to log here	
			}
		}
	}
	public void enemyItem(int itemIndex){
		for(int i=0;i<enemy.getInventory().getItems().size();i++){
			if(enemy.getInventory().getItems().get(i).getItem().equals("health potion")){ //Gör om med en extra loop när jag har tillgång till datasbas med items.
				enemy.getInventory().getItems().get(i).getItem().reduceCharges();
				enemy.healVital("Health", 40); //Fixar effekt i item senare.
				enemy.getInventory().useItem(itemIndex);
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
	}
	public void playerAttack(){
		if(turn==true){
			enemy.reduceVital("Health", player.getValueOfSkill("Attack"));
			enemyTurn();
			System.out.println("Player attacked for: "+player.getValueOfSkill("Attack")); //Supposed to write to log here
		
			System.out.println("Enemy turn!"); //Supposed to write to log here
			resetUpdateCount();
			while(clockTick<30);
			enemyAttack();
		}
	}
	public void playerSpell(String name){
		for(int i=0;i<player.getSpellBook().getSpells().size();i++){
			if(player.getSpellBook().getSpells().get(i).getName().equals(name)){
				enemy.reduceVital("Health", player.getValueOfSkill("Attack"));
				player.reduceVital("Energy", player.getSpellBook().getSpells().get(i).getEnergyCost());
				enemyTurn();
				System.out.println("Player casted spell "+name+" that damaged: "+player.getValueOfSkill("Attack")); // Supposed to write to log here
			}
		}
	}
	public void playerItem(int itemIndex){
		for(int i=0;i<player.getInventory().getItems().size();i++){
			if(player.getInventory().getItems().get(i).getItem().equals("health potion")){ //Gör om med en extra loop när jag har tillgång till datasbas med items.
				player.getInventory().getItems().get(i).getItem().reduceCharges();
				System.out.println(player.healVital("health", 40)); //Fixar effekt i item senare.
				player.getInventory().useItem(itemIndex);
			}
		}
	}
	
	public double retreatChance(Character character){
		double chance;
		if(character instanceof Playable){
			return chance = ((40*player.getValueOfSkill("Speed"))/enemy.getValueOfSkill("Speed"))+10;
		}else{
			return chance = ((20*enemy.getValueOfSkill("Speed"))/player.getValueOfSkill("Speed"));
		}
		
	}
	
	public void playerRetreat(){
		if(retreatChance(player)>=nextInt()){
			System.out.println("Run bitch!");
			//Render the worldmap here.
		}else{
			System.out.println("You failed to run!"); //Supposed to write to log here
		}
	}
	
	private int nextInt(){
		return rand.nextInt(100) + 1;
	}
	
	public boolean firstTurn(){
		return(player.getValueOfSkill("Speed")>=enemy.getValueOfSkill("Speed"));
	}

}
