package character;

import java.util.ArrayList;
import java.util.List;

/**
 * @author      Mattias Benngård	<mbengan@gmail.com>
 * @version     0.4
 * @since       2015-02-01
 */

public class Attribute
{
	private double value;
	private final List<AttributeModifier> attributeModifiers = new ArrayList<>();
		
	public Attribute (double value) {
		this.value = value;
	}
	
	/**
	 * @return	value of the attribute
	 */
	public double getValue () {
		return value;
	}
	
	/**
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
	 * @return	total value of the attribute, including buffs, items
	 */
	public double getTotalValue () {
		return getValue () + getBuffValue ();
	}
	
	public void addValue (double v) {
		value += v;
	}
}
