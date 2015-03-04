package character;

/**
 * Creates spell and handles getters
 * 
 * @author      Mattias Benngård	<mbengan@gmail.com>
 * @author 		Andreas Bäckevik	<backevik@student.chalmers.se>
 * @version     1.0
 * @since       2015-02-01
 */

public class Spell
{
	private String name;
	private String description;
	private int energyCost;
	private String type;
	private double spellPower;
	
	
	/**
	 * Constructor
	 * @param name - name of spell
	 * @param description - spell description
	 * @param energyCost - How much energy it costs to cast
	 * @param type - type is fixed damage or heal
	 * @param spellPower - How much damage the spell does
	 */
	public Spell (String name, String description, int energyCost, String type, double spellPower) {
		this.name = name;
		this.description = description;
		this.energyCost = energyCost;
		this.type = type;
		this.spellPower = spellPower;
	}
	/**
	 * Returns name of spell
	 * @return String - name of spell
	 */
	public String getName () {
		return name;
	}
	/**
	 * Returns description of spell
	 * @return String - description
	 */
	public String getDescription () {
		return description;
	}
	/**
	 * Returns energycost of spell
	 * @return int - energy
	 */
	public int getEnergyCost () {
		return energyCost;
	}
	/**
	 * Returns type of spell
	 * @return String - type of the spell
	 */
	public String getType () {
		return type;
	}
	/**
	 * Returns spellpower of spell
	 * @return double spellpower
	 */
	public double getspellPower(){
		return spellPower;
	}
}