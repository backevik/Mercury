package character;

/**
 * @author      Mattias Benngård	<mbengan@gmail.com>
 * @version     0.1
 * @since       2015-02-01
 */

public class Spell
{
	private String name;
	private String description;
	private int energyCost;
	
	public Spell (String name, String description, int energyCost) {
		this.name = name;
		this.description = description;
		this.energyCost = energyCost;
	}
	
	public String getName () {
		return name;
	}
	
	public String getDescription () {
		return description;
	}
	
	public int getEnergyCost () {
		return energyCost;
	}
}