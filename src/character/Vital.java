package character;

/**
 * @author      Mattias Benngård	<mbengan@gmail.com>
 * @author		Andreas Bäckevik	<backevik@student.chalmers.se>
 * @version     0.4
 * @since       2015-02-01
 */

public class Vital extends Skill
{
	private double max;
	
	public Vital (double value) {
		super(value);
		
		max = value;		
	}
	
	public double getMax () {
		return max;
	}
	public void setMax(int level) {
		this.max = this.getValue()+(level*5);
	}
	
	public void reduceVital(double value){
		setValue(getValue()-value);
	}
	
	public void healVital(double value){
		setValue(getValue()+value);
	}
	
	public void updateValue (int level) {
		this.setValue(this.getValue()+(level*5));
	}
}
