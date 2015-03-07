package combat;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import character.Character;
import character.Spell;
import player.ItemSlot;
import player.Playable;
import core.EventAdder;

/**
 * All the logic and rules for encounters throughout the game. Implements interface RealTime for realtime support.
 * 
 * @author      Andreas Bäckevik
 * @version     1.0
 * @since       2015-02-09
 */
public class Combat
{
	private boolean turn;
	private boolean energy = true;
	private Random rand;
	private final LinkedList<Character> turnList = new LinkedList<Character>();;
	private Character currentChar;
	private EventAdder eventAdder;
	private List<Playable> players;
	private Encounter encounter;
	private String winEvent;
	private String lostEvent;
	
	/**
	 * Constructor for Combat
	 * @param players - reference of player
	 * @param encounter - unique encounter
	 * @param eventAdder - refernece to the event queue
	 * @param winEvent - what event is triggered on victory
	 * @param lostEvent - what event is triggered on defeat
	 */
	public Combat(List<Playable> players, Encounter encounter,EventAdder eventAdder, String winEvent, String lostEvent){
		rand = new Random();
		this.eventAdder = eventAdder;
		this.players = players;
		this.encounter = encounter;
		this.winEvent = winEvent;
		this.lostEvent = lostEvent;
		initCombatList();
		nextTurn();
	}
	
	/**
	 * Helper method to initializes the turn based list based 
	 */
	private void initCombatList(){
		for (Playable player : players){
			turnList.add(player);	
		}
		for(Enemy enemy : encounter.getEnemies()){
			turnList.add(enemy);
		}
		listSort();
		
	}
	
	/**
	 * Helper method to sort the list, highest speed goes first
	 */
	private void listSort(){
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
	
	/**
	 * Checks list to set next turn to that character
	 */
	public void nextTurn () {
		if(currentChar instanceof Playable && turn==true){
			eventAdder.add("popupWindow, You forgot to make your move!");
		} else {
			removeFirstAddLast();
			if(currentChar instanceof Enemy){
				enemyTurn();
				enemyNextMove();
			}else if(currentChar.getValueOfVital("Health")>0){
				playerTurn();
			}
		}
	}
	
	/**
	 * Puts the first character last in turnlist
	 */
	private void removeFirstAddLast(){
		currentChar=turnList.pollFirst();
		turnList.addLast(currentChar);
	}
	
	/**
	 * Character attacks
	 * @param src - character who attacks
	 * @param dest - target of the character
	 */
	public void attack(Character src,Character dest){
			if(src instanceof Playable){
			dest = encounter.getEnemies().get(0); //TEMPORARY FOR JUST 1v1's
			}
			eventAdder.add("addTextToLog,"+src.getName()+" hit "+dest.getName()+" for "+src.getValueOfSkill("Attack")+" damage");
			System.out.println(src.getName()+" hit "+dest.getName()+" for "+src.getValueOfSkill("Attack")+" damage");
			//eventAdder.add("updateVisual,"+players.get(0).getValueOfSkill("Attack"));
			eventAdder.add("updateVisuals");
			deathCheck(dest,src.getValueOfSkill("Attack"));
			dest.reduceVital("Health", src.getValueOfSkill("Attack"));			
	}
	
	/**
	 * Character casts a spell
	 * @param src - character who has the turn
	 * @param dest - the other character
	 * @param spellName - Name of the spell casted
	 */
	public void spell(Character src,Character dest,String spellName){
		if(src instanceof Playable){
			dest = encounter.getEnemies().get(0); //TEMPORARY FOR JUST 1v1's
		}
		
		for(Spell spell : src.getSpellBook().getSpells()){
			if(spell.getName().equals(spellName) && spell.getType().equals("heal")){
				src.reduceVital("Energy", spell.getEnergyCost());
				eventAdder.add("addTextToLog,"+src.getName()+" restored "+src.getName()+" health for "+src.healVital("Health", spell.getspellPower()));
				System.out.println(src.getName()+" restored "+src.getName()+" health for "+src.healVital("Health", spell.getspellPower()));
				eventAdder.add("updateVisuals");
				break;
			}else if(spell.getName().equals(spellName) && spell.getType().equals("damage")){
				eventAdder.add("addTextToLog,"+src.getName()+" casted "+spell.getName()+" on "+dest.getName()+" for "+spell.getspellPower()+" damage");
				System.out.println(src.getName()+" casted "+spell.getName()+" on "+dest.getName()+" for "+spell.getspellPower()+" damage");
				eventAdder.add("updateVisuals");
				deathCheck(dest,spell.getspellPower());
				dest.reduceVital("Health", spell.getspellPower());
				src.reduceVital("Energy", spell.getEnergyCost());
				break;
			}else if(currentChar instanceof Enemy){
				attack(currentChar,players.get(0));
				break;
			}
		}
	}

	/**
	 * Character uses item
	 * @param itemName - name of item used
	 */
	public void useItem(String itemName){
		for(ItemSlot itemslot : currentChar.getInventory().getItems()){
			if(itemslot.getItem().getName().equals(itemName)){
				String item = itemslot.getItem().use();
				String[] a = item.split("#");
				currentChar.healVital(a[0], Integer.parseInt(a[1]));
				eventAdder.add("addTextToLog,"+currentChar.getName()+" used "+itemslot.getItem().getName()+" that restored "+a[1]+" "+a[0]);
				System.out.println(currentChar.getName()+" used "+itemslot.getItem().getName()+" that restored "+a[1]+" "+a[0]);
				eventAdder.add("updateVisuals");
				turn = false;
				break;
			}
		}
	}

	/**
	 * Check if player turn
	 * @param itemName - name of item used
	 */
	public void itemCheck(String itemName){
		if(turn==true){
			useItem(itemName);
		}
	}

	/**
	 * Check if player turn and enough energy
	 * @param src - character who has the turn
	 * @param dest - the other character
	 */
	public void spellCheck(Character src,String spellName){
		Enemy dest;
		if(currentChar instanceof Playable && turn==true && energyCheck(spellName)){
			dest = encounter.getEnemies().get(0);
			spell(src,dest,spellName);
			turn=false;
		}else if(energy==false){
			eventAdder.add("addTextToLog,"+"Not enough energy.");
			System.out.println("Not enough energy.");
		}else if(currentChar instanceof Enemy && turn==false){
			Playable desti = players.get(0);
			spell(src,desti,spellName);
		}
	}

	/**
	 * Check if player turn
	 * @param src - character who has the turn
	 */
	public void attackCheck(Character src){
		if(currentChar instanceof Playable && turn==true){
			Enemy dest = encounter.getEnemies().get(0);
			attack(src,dest);
			turn = false;
		}else if(currentChar instanceof Enemy && turn==false){
			Playable desti = players.get(0);
			attack(src,desti);
		}
	}
	
	/**
	 * Set turn to enemy
	 */
	private void enemyTurn(){	
		turn=false;
		eventAdder.add("addTextToLog,"+currentChar.getName()+"'s turn!");
		System.out.println(currentChar.getName()+"'s turn!");
	}

	/**
	 * Set turn to player
	 */
	private void playerTurn(){
		turn=true;
		eventAdder.add("addTextToLog,"+currentChar.getName()+"'s turn!");
		System.out.println(currentChar.getName()+"'s turn!");
	}

	/**
	 * 
	 * @param Character who is checked if dead
	 * @param damage - the damage which might kill character
	 */
	public void deathCheck(Character c,double damage){
		if((c.getValueOfVital("Health")-damage)<=0 && c instanceof Playable){
			eventAdder.add("addTextToLog,"+c.getName()+" died! The fight is lost");
			System.out.println(c.getName()+" died! The fight is lost");
			currentChar = null;
			eventAdder.add(lostEvent);
		}else if((c.getValueOfVital("Health")-damage)<=0 && c instanceof Enemy){
			players.get(0).addExp(c.getLevel()*2);
			eventAdder.add("addTextToLog,"+c.getName()+" was killed!");
			System.out.println(c.getName()+" was killed!");
			if(c.getLevel()*2>players.get(0).getExpTnl()){
				eventAdder.add("addTextToLog,"+"Congratulations "+players.get(0).getName()+"! You reached level "+players.get(0).getLevel());
				System.out.println("Congratulations "+players.get(0).getName()+"! You reached level "+players.get(0).getLevel());
			}else{
				eventAdder.add("addTextToLog,"+"Congratulations "+players.get(0).getName()+"! You gained "+c.getLevel()*2+" experience!");
				System.out.println("Congratulations "+players.get(0).getName()+"! You gained "+c.getLevel()*5+" experience!");
			}
			eventAdder.add(winEvent);
		}
	}

	/**
	 * check if character has sufficient energy
	 * @param spellName - name of spell character wants to cast
	 * @return boolean - true if energy was sufficient, else false
	 */
	private boolean energyCheck(String spellName){
		for(Spell spell : currentChar.getSpellBook().getSpells()){
			if(spell.getName().equals(spellName) && ((currentChar.getValueOfVital("Energy"))-(spell.getEnergyCost()))>=0){
				return true;
			}else if(spell.getName().equals(spellName) && (currentChar.getValueOfVital("Energy")-spell.getEnergyCost())<=0){
				energy = false;
				return false;
			}
		}
		return false;
	}
	
	/**
	 * Calculate the chance for successful retreat
	 * @param character - player or enemy we want to calculate chance for
	 * @return double - the chance of successful retreat
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
			eventAdder.add("runFromBattle");
		}else{
			turn = false;
			eventAdder.add("addTextToLog,"+"You failed to run!");
			System.out.println("You failed to run!"); //Supposed to write to log here
			
		}
	}

	/**
	 * Check if player turn
	 */
	public void retreatCheck(){
		if(turn==true){
			retreat();
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
		}
		if(rand.nextInt(2)+1==1 && energyCheck("fireball")){
			spell(currentChar,players.get(0),"fireball");
			nextTurn();
		} else {
			attack(currentChar,players.get(0));
			nextTurn();
		}
	}

	/**
	 * randomizes numbers
	 * @return 1-100
	 */
	private int nextInt () {
		return rand.nextInt(100)+1;
	}
}
