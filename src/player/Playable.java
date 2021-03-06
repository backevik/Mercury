package player;

import java.io.Serializable;

import character.Attribute;
import character.Character;

/**
 * A playable character, can get experience and level up 
 * 
 * @author      Mattias Benngard	
 * @version     1.0					
 * @since       2015-03-07
 */

public class Playable extends Character implements Serializable
{	
	private static final long serialVersionUID = -4578604823177359363L;
	private int level;
	private double expCur;
	private double expTnl;
	private double currency;
	
	/**
	 * Constructor for Playable
	 * @param name - name to give the playable character
	 */
	public Playable (String name) {
		super (name);
		
		level = 1;
		
		updateSkills (this.getLevel());
		updateAttributes(this.getLevel());
		updateVitals (this.getLevel());
		
		setExpTnl ();
		currency = 100;
	}
	
	/**
	 * Public getter for current experience
	 * @return 	current experience accumulated this level for the player
	 */
	public double getExpCur() {
		return expCur;
	}

	/**
	 * Public getting for experience to next level
	 * @return 	experience required to reach the next level up
	 */
	public double getExpTnl() {
		return expTnl;
	}

	/**
	 * Public getter for current level
	 * @return	current level the player has achieved
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * Adds experience to the player.
	 * 
	 * Calls checkForLevel(), if it returns true:
	 * calls doLevelUp() 
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
	 * Checks if a level up has occurred
	 * @return if a level up has occurred or not
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
 * Public getter for current currency
 * @return the  players currency
 */
	public double getCurrency() {
		return currency;
	}
	
	/**
	 * Subtracts money from current currency
	 * @param currency - the money to subtract from currency
	 */
	public void subtractCurrency (double currency) {
		this.currency -= currency;
	}
	
	/**
	 * Adds money to current currency
	 * @param coins - the money to add to currency
	 */
	public void addCurrency (double currency) {
		this.currency += currency;
	}
	
}
