package player;

import character.Attribute;
import character.Character;

public class Playable extends Character
{	
	private int level;
	private double expCur;
	private double expTnl;
	
	public Playable (String name) {
		super (name);
		
		level = 1;
		setExpTnl ();
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
		expTnl = 100*level;
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
		
		updateSkills ();
		updateVitals ();
		
		if (checkForLevel () == true) {
			doLevelUp ();
		}
	}	
}
