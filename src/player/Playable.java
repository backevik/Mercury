package player;
/**
 * @author      Mattias Benngård	<mbengan@gmail.com>
 * @author		Martin Claesson		<marclae@student.chalmers.se>
 * @author	Andreas Bäckevik	<a.backevik@student.chalmers.se>
 * @version     0.4
 * @since       2015-02-09
 */

import character.Attribute;
import character.Character;

public class Playable extends Character
{	
	private int level;
	private double expCur;
	private double expTnl;
	private double currency;
	
	public Playable (String name) {
		super (name);
		
		level = 1000;
		
		updateSkills (this.getLevel());
		updateAttributes(this.getLevel());
		updateVitals (this.getLevel());
		
		setExpTnl ();
		currency = 100;
	}
	
	/**
	 * @return 	current experience accumlated this level for the player
	 */
	public double getExpCur() {
		return expCur;
	}

	/**
	 * @return 	experience required to reach the next level up
	 */
	public double getExpTnl() {
		return expTnl;
	}

	/**
	 * @return	current level the player has achieved
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * Adds experience to the player.
	 * 
	 * Calls checkForLevel(), if it returns true
	 * Calls doLevelUp() 
	 * 
	 * @param e	amount of experience to give the player.
	 */
	public void addExp (double e) {
		expCur += e;
		if (checkForLevel () == true) {
			doLevelUp ();
		}
	}	

	/**
	 * Sets the expTnl based on your current level
	 */
	private void setExpTnl () {
		expTnl = 10*level;
	}
	
	/**
	 * Checks if a level up has occured
	 */
	private boolean checkForLevel () {
		return (expCur >= expTnl);
	}
	
	/**
	 * Does a level up, currently increases all attributes by 1.
	 * Recursive, checks itself for another level up.
	 */
	private void doLevelUp () {
		level++;
		expCur -= expTnl;
		setExpTnl ();
		
		for (Attribute a : getAttributes().values()) {
			a.addValue(1);
		}
		
		updateSkills (this.getLevel());
		updateAttributes (this.getLevel());
		updateVitals (this.getLevel());
		
		if (checkForLevel () == true) {
			doLevelUp ();
		}
	}
	/**
	 * 
	 * @return current amount of currency
	 */
	public double getCurrency() {
		return currency;
	}
	/**
	 * 
	 * @param price amount of currency to subtract from player
	 */
	public void subtractCurrency(double price) {
		currency-=price;
	}
	/**
	 * 
	 * @param adds coins amount of currency to player
	 */
	public void addCurrency(double coins) {
		currency+=coins;
	}
	
}
