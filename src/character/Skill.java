package character;

import java.io.Serializable;

/**
 * Characteristics and settings for skills in  a character.
 * 
 * @author      Mattias Benngard	<mbengan@gmail.com>
 * @author		Andreas Backevik	<backevik@student.chalmers.se>
 * @version     1.0.1
 * @since       2015-03-07
 */

public class Skill implements Serializable
{
	private static final long serialVersionUID = -5577052425469320662L;
	private double value;
	
	/**
	 * Constructor for Skill
	 * @param Startvalue of the skill we create
	 */
	public Skill (double value) {
		this.value = value;
	}
	
	/**
	 * Public getter of value
	 * @return skill value
	 */
	public double getValue () {
		return value;
	}
	
	/**
	 * Set value of skill to new value in parameter
	 * @param value we want to change to
	 */
	protected void setValue (double value) {
		this.value = value;
	}
	
	/**
	 * Updates value with level
	 * @param level	Character level that we wish to scale skill value with
	 */
	public void updateValue (int level) {
		value = 5+level;
	}
}
