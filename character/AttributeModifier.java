package character;

/**
 * @author      Mattias Benngård	<mbengan@gmail.com>
 * @version     0.1
 * @since       2015-02-01
 */

public class AttributeModifier
{	
	private double value;
	
	public AttributeModifier (double value) {
		this.value = value;
	}
	
	public double getValue () {
		return value;
	}
}
