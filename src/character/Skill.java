package character;

/**
 * @author      Mattias Benngård	<mbengan@gmail.com>
 * @author		Andreas Bäckevik	<backevik@student.chalmers.se>
 * @version     0.1
 * @since       2015-02-01
 */

public class Skill
{
	private double value;
	
	public Skill (double value) {
		this.value = value;
	}
	
	public double getValue () {
		return value;
	}
	
	protected void setValue (double value) {
		this.value = value;
	}
	
	public void updateValue (int level) {
		value = 5+level;
	}
}
