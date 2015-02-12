package combat;

import java.util.List;
import java.util.Random;

import player.Playable;
import character.Character;

/**
 * @author      Andreas Bäckevik	<backevik@student.chalmers.se>
 * @version     0.4
 * @since       2015-02-09
 */


public class Combat {
	boolean turn;
	Random rand;
	Playable player;
	Enemy enemy;
	
	public Combat(List<Playable> players, List<Enemy> enemies){
		// decide who starts, true for player, false for enemy.
		turn = firstTurn();
		// Dummy code for one player and one enemy
		player = players.get(0);
		enemy = enemies.get(0);
		//initializing random factor;
		rand = new Random();
		
	}
	
	private void enemyTurn(){
		
	}
	private void enemyAttack(){
		
	}
	private void enemySpell(){
		
	}
	private void enemyItem(){
		
	}
	private void enemyRetreat(){
		if(retreatChance(enemy)>=nextInt()){
			//Render the worldmap here.
		}else{
			System.out.println("The enemy failed to run!"); //Supposed to write to log here
		}
	}

	private void playerTurn(){
		
	}
	private void playerAttack(){
		
	}
	private void playerSpell(String name){
		player.getSpellBook().getSpells();
		for(int i=0;i<player.getSpellBook().getSpells().size();i++){
			if(player.getSpellBook().getSpells().get(i).getName().equals(name)){
				//Place the logical attack here. Fix "TakeDamage()"
				System.out.println("Spell: "+name+" damaged: "+player.getValueOfSkill("Attack")); // Supposed to write to log here
			}
		}
	}
	private void playerItem(){
		
	}
	
	private double retreatChance(Character character){
		double chance;
		if(character instanceof Playable){
			return chance = ((40*player.getValueOfSkill("Speed"))/enemy.getValueOfSkill("Speed"))+10;
		}else{
			return chance = ((20*enemy.getValueOfSkill("Speed"))/player.getValueOfSkill("Speed"));
		}
		
	}
	
	private void playerRetreat(){
		if(retreatChance(player)>=nextInt()){
			//Render the worldmap here.
		}else{
			System.out.println("You failed to run!"); //Supposed to write to log here
		}
	}
	
	private int nextInt(){
		return rand.nextInt(100) + 1;
	}
	
	private boolean firstTurn(){
		if(player.getValueOfSkill("Speed")>=enemy.getValueOfSkill("Speed")){
			return true;
		}else{
			return false;
		}
	}

}
