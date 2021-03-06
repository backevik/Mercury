package character;

import java.io.Serializable;

/**
 * Where Vitals are created and modified. Each vital has its own modifiers/getters
 * 
 * @author		Andreas B�ckevik	
 * @version     1.0
 * @since       2015-03-07
 */

public class Vital extends Skill implements Serializable
{
	private static final long serialVersionUID = -6913445933204599812L;
	private double max;
	
	/**
	 * Constructor for Vital
	 * @param value - Startvalue of the Vital to be created
	 */
	public Vital (double value) {
		super(value);
		max = value;		
	}
	
	/**
	 * Returns max of the vital
	 * @return double - value of vital
	 */
	public double getMax () {
		return max;
	}
	
	/**
	 * Updates max of vital with level
	 * @param level of character
	 */
	public void setMax(int level) {
		this.max = this.getValue()+(level*5);
	}
	
	/**
	 * Reduces vital with parameter value
	 * @param value to reduce on vital
	 */
	public void reduceVital(double value){
		setValue(getValue()-value);
	}
	
	/**
	 * Heal vital with parameter value
	 * @param value to heal on vital
	 */
	public void healVital(double value){
		setValue(getValue()+value);
	}
	
	/**
	 * Updates value of vital with parameter level
	 * @param level of the character
	 */
	public void updateValue (int level) {
		this.setValue(this.getValue()+(level*5));
	}
}
