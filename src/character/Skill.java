package character;

/**
 * Characteristics and settings for skills in  a character.
 * 
 * @author      Mattias BenngÃ¥rd	<mbengan@gmail.com>
 * @author		Andreas Bäckevik	<backevik@student.chalmers.se>
 * @version     1.0
 * @since       2015-02-01
 */

public class Skill
{
	private double value;
	/**
	 * Constructor 
	 * @param Startvalue of the skill we create
	 */
	public Skill (double value) {
		this.value = value;
	}
	/**
	 * Return value of skill
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
