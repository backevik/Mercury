package character;

/**
 * @author      Mattias Benng�rd	<mbengan@gmail.com>
 * @version     0.4
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
	
	public void updateValue () {
		value = 5;
	}
}
