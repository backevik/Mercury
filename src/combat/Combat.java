package combat;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import core.EventAdder;
import core.GlobalStateManager;
import core.RealTime;
import player.Playable;
import character.Character;
import character.Spell;

/**
 * @author      Andreas BÃƒÆ’Ã‚Â¤ckevik
 * @version     0.39
 * @since       2015-02-09
 */

public class Combat implements RealTime {
	private boolean turn;
	private boolean energy = true;
	private Random rand;
	private LinkedList<Character> turnList;
	private Character currentChar;
	@SuppressWarnings("unused")
	private int clockTick;
	private EventAdder eventAdder;
	private List<Playable> players;
	private Encounter encounter;
	
	public Combat(List<Playable> players, Encounter encounter,EventAdder eventAdder){
		rand = new Random();
		this.eventAdder = eventAdder;
		this.players = players;
		this.encounter = encounter;
		turnList = new LinkedList<Character>();
		initCombatList();
		nextTurn();
	}
	
	public void initCombatList(){
		for(Playable player : players){
			turnList.add(player);	
		}
		for(Enemy enemy : encounter.getEnemies()){
			turnList.add(enemy);
		}
		listSort();
		
	}
	
	public void listSort(){
		for(int i = 0; i < turnList.size(); i++){
		    for(int j = i + 1; j < turnList.size(); j++){
		        if(turnList.get(i).getValueOfSkill("Speed")<turnList.get(j).getValueOfSkill("Speed")) {
		            Character temp = turnList.get(i);
		            turnList.set(i, turnList.get(j));
		            turnList.set(j, temp);
		        }
		    }
		}
	}
	
	public void nextTurn(){
		removeFirstAddLast();
		if(currentChar instanceof Enemy){
			enemyTurn();
			enemyNextMove();
		}else if(currentChar instanceof Playable){
			playerTurn();
		}else{
			System.out.println("Returning to world map...");
		}
	}
	
	private void removeFirstAddLast(){
		currentChar=turnList.pollFirst();
		turnList.addLast(currentChar);
	}
	
	public void attack(Character src,Character dest){
			if(src instanceof Playable){
			dest = encounter.getEnemies().get(0); //TEMPORARY FOR JUST 1v1's
			}
			System.out.println(src.getName()+" hit "+dest.getName()+" for "+src.getValueOfSkill("Attack")+" damage");
			deathCheck(dest,src.getValueOfSkill("Attack"));
			dest.reduceVital("Health", src.getValueOfSkill("Attack"));
	}
	
	public void spell(Character src,Character dest,String spellName){
		if(src instanceof Playable){
		dest = encounter.getEnemies().get(0); //TEMPORARY FOR JUST 1v1's
		}
		
		for(Spell spell : src.getSpellBook().getSpells()){
			if(spell.getName().equals(spellName) && spell.getType().equals("heal") && energyCheck(spell.getName())){
				src.reduceVital("Energy", spell.getEnergyCost());
				System.out.println(src.getName()+" healed "+dest.getName()+" for "+src.healVital("Health", spell.getspellPower())+" health");
				break;
			}else if(spell.getName().equals(spellName) && spell.getType().equals("damage") && energyCheck(spell.getName())){
				System.out.println(src.getName()+" casted "+spell.getName()+" on "+dest.getName()+" for "+spell.getspellPower()+" damage");
				deathCheck(dest,spell.getspellPower());
				dest.reduceVital("Health", spell.getspellPower());
				src.reduceVital("Energy", spell.getEnergyCost());
				break;
			}else{
				if(currentChar instanceof Enemy){
					attack(currentChar,players.get(0));
					break;
				}else{
					energy = false;
					break;
				}
			}
		}
	}
	
	public void spellCheck(Character src,Character dest,String spellName){
		if(currentChar instanceof Playable && turn==true){
			spell(src,dest,spellName);
			if(energy==true){
				turn = false;
			}else{
				System.out.println("Not enough energy.");
			}
		}else if(currentChar instanceof Enemy && turn==false){
			spell(src,dest,spellName);
		}
	}
	
	public void attackCheck(Character src,Character dest){
		if(currentChar instanceof Playable && turn==true){
			attack(src,dest);
			turn = false;
		}else if(currentChar instanceof Enemy && turn==false){
			attack(src,dest);
		}
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
		System.out.println(currentChar.getName()+"'s turn!");
	}
	/**
	 * Set turn to player;
	 */
	private void playerTurn(){
		turn=true;
		System.out.println(currentChar.getName()+"'s turn!");
	}

	public void deathCheck(Character c,double damage){
		if((c.getValueOfVital("Health")-damage)<=0 && c instanceof Playable){
			GlobalStateManager.getInstance().updateCurrentState("InCombat_dead");
			System.out.println(c.getName()+" died! The fight is lost");
			currentChar = null;
			eventAdder.add("sceneTown");
		}else if((c.getValueOfVital("Health")-damage)<=0 && c instanceof Enemy){
			GlobalStateManager.getInstance().updateWorldState(GlobalStateManager.getInstance().getWorldState("Location"), "clear");
			players.get(0).addExp(c.getLevel()*5);
			if(c.getLevel()*5>players.get(0).getExpTnl()){
				System.out.println("Congratulations "+players.get(0).getName()+"! You reached level "+players.get(0).getLevel());
			}else{
				System.out.println("Congratulations "+players.get(0).getName()+"! You gained "+c.getLevel()*5+" experience!");
			}
			eventAdder.add("sceneWorldMap");
		}
	}
	
	private boolean energyCheck(String spellName){
		for(Spell spell : currentChar.getSpellBook().getSpells()){
			if(spell.getName().equals(spellName) && (currentChar.getValueOfVital("Energy")-spell.getEnergyCost())<0){
				return false;
			}else{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Calculate the chance for successful retreat
	 * @param character - player or enemy we want to calculate chance for
	 * @return the chance of successful retreat
	 */
	public double retreatChance(){
			return ((40*currentChar.getValueOfSkill("Speed"))/currentChar.getValueOfSkill("Speed"))+10;		
	}
	
	/**
	 * Player trying to retreat from combat
	 */
	public void retreat(){
		if(retreatChance()>=nextInt()){
			System.out.println("Successfull escape!");
			eventAdder.add("sceneWorldMap");
		}else{
			System.out.println("You failed to run!"); //Supposed to write to log here
		}
	}
	
	/**
	 * Uses enemy AI for calculating enemy next move
	 */
	private void enemyNextMove(){
		if(currentChar.getValueOfVital("Health")<(currentChar.getMaxOfVital("Health")/3)){
			for(Spell spell : currentChar.getSpellBook().getSpells()){
				if(spell.getName().equals("heal") && energyCheck(spell.getName())){
					spell(currentChar,currentChar,"heal");
					nextTurn();
				}
			}
		}else{
			if(rand.nextInt(2)+1==1 && energyCheck("fireball")){
				spell(currentChar,players.get(0),"fireball");
				nextTurn();
			}else{
				attack(currentChar,players.get(0));
				nextTurn();
			}
		}
	}
	private int nextInt(){
		return rand.nextInt(100)+1;
	}

}
