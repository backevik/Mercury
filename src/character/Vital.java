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
	
	public void reduceVital(double value){
		setValue(getValue()-value);
	}
	
	public void healVital(double value){
		setValue(getValue()+value);
	}
	
	//@Override
	//public void updateValue () {
		/*double c = getValue()/max;
		max = 5;
		setValue(getValue() * c);*/
	//}
}
