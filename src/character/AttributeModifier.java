package character;

/**
 * If items and/or buffs modify attributes it is handled in this class.
 * 
 * @unused
 * @author      Andreas Backevik
 * @version     1.0						<2015-03-17>
 * @since       2015-02-01
 */

public class AttributeModifier
{	
	private double value;
	/**
	 * Value we want to modify with
	 * @param value value that will change attributes total value
	 */
	public AttributeModifier (double value) {
		this.value = value;
	}
	/**
	 * Get modify-value
	 * @return double value that modifies attributes with
	 */
	public double getValue () {
		return value;
	}
}
