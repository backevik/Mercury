package combat;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import character.Character;
import character.Spell;
import player.ItemSlot;
import player.Playable;
import core.EventAdder;
import core.GlobalStateManager;
import core.RealTime;

/**
 * @author      Andreas Bäckevik
 * @version     0.39
 * @since       2015-02-09
 */
public class Combat implements RealTime {
	private boolean turn;
	private boolean energy = true;
	private Random rand;
	private final LinkedList<Character> turnList = new LinkedList<Character>();;
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
		initCombatList();
		nextTurn();
	}
	
	public void initCombatList(){
		for (Playable player : players){
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
		if(currentChar instanceof Playable && turn==true){
			eventAdder.add("popupWindow, You forgot to make your move!");
			}else{
				removeFirstAddLast();
				if(currentChar instanceof Enemy){
					enemyTurn();
					enemyNextMove();
				}else if(currentChar.getValueOfVital("Health")>0){
					playerTurn();
				}
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
				System.out.println(src.getName()+" restored "+src.getName()+" health for "+src.healVital("Health", spell.getspellPower()));
				break;
			}else if(spell.getName().equals(spellName) && spell.getType().equals("damage") && energyCheck(spell.getName())){
				System.out.println(src.getName()+" casted "+spell.getName()+" on "+dest.getName()+" for "+spell.getspellPower()+" damage");
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
	
	public void useItem(String itemName){
		for(ItemSlot itemslot : currentChar.getInventory().getItems()){
			if(itemslot.getItem().getName().equals(itemName)){
				String item = itemslot.getItem().use();
				String[] a = item.split("#");
				currentChar.healVital(a[0], Integer.parseInt(a[1]));
				System.out.println(currentChar.getName()+" used "+itemslot.getItem().getName()+" that restored "+a[1]+" "+a[0]);
				turn = false;
				break;
			}
		}
	}
	
	public void itemCheck(String itemName){
		if(currentChar instanceof Playable && turn==true){
			useItem(itemName);
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
		eventAdder.add("addTextToLog,"+currentChar.getName()+"'s turn!");
		System.out.println(currentChar.getName()+"'s turn!");
	}
	/**
	 * Set turn to player;
	 */
	private void playerTurn(){
		turn=true;
		eventAdder.add("addTextToLog,"+currentChar.getName()+"'s turn!");
		System.out.println(currentChar.getName()+"'s turn!");
	}

	public void deathCheck(Character c,double damage){
		if((c.getValueOfVital("Health")-damage)<=0 && c instanceof Playable){
			System.out.println(c.getName()+" died! The fight is lost");
			currentChar = null;
			GlobalStateManager.getInstance().updateCurrentState("InCombat_dead");
			c.healVital("Health", c.getMaxOfVital("Health"));
			c.healVital("Energy", c.getMaxOfVital("Energy"));
			eventAdder.add("sceneTown");
		}else if((c.getValueOfVital("Health")-damage)<=0 && c instanceof Enemy){
			players.get(0).addExp(c.getLevel()*5);
			System.out.println(c.getName()+" was killed!");
			if(c.getLevel()*5>players.get(0).getExpTnl()){
				System.out.println("Congratulations "+players.get(0).getName()+"! You reached level "+players.get(0).getLevel());
			}else{
				System.out.println("Congratulations "+players.get(0).getName()+"! You gained "+c.getLevel()*5+" experience!");
			}
			GlobalStateManager.getInstance().updateWorldState(GlobalStateManager.getInstance().getCurrentState(), "CLEAR");
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
			turn = false;
			System.out.println("You failed to run!"); //Supposed to write to log here
		}
	}
	
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
