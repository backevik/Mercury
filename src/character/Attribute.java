package character;

import java.util.ArrayList;
import java.util.List;

/**
 * Where attributes are created and modified. Each attribute has its own modifiers/getters.
 * 
 * @author      Andreas Bäckevik
 * @version     1.0
 * @since       2015-02-01
 */

public class Attribute
{
	private double value;
	private final List<AttributeModifier> attributeModifiers = new ArrayList<>();
	/**
	 * Constructor
	 * @param value - Startvalue of the created attribute 
	 */
	public Attribute (double value) {
		this.value = value;
	}
	
	/**
	 * Returns value of attribute
	 * @return	value of the attribute
	 */
	public double getValue () {
		return value;
	}
	
	/**
	 * @unused
	 * @return	value of buffs, items
	 */
	public double getBuffValue () {
		double d = 0.0;
		
		for (AttributeModifier am : attributeModifiers) {
			d += am.getValue();
		}
		
		return d;
	}
	
	/**
	 * Returns total value of attribute which includes buffed value
	 * @return	total value of the attribute, including buffs, items
	 */
	public double getTotalValue () {
		return getValue () + getBuffValue ();
	}
	/**
	 * Add value to attribute
	 * @param v value that is added to attributes total value
	 */
	public void addValue (double v) {
		value += v;
	}
	/**
	 * Updates value with level
	 * @param level	Character level that we wish to scale attribute value with
	 */
	public void updateValue (int level) {
		value = 5+level;
	}
}
