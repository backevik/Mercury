package character;

/**
 * @author      Mattias Benngård	<mbengan@gmail.com>
 * @author 		Andreas Bäckevik	<backevik@student.chalmers.se>
 * @version     0.4
 * @since       2015-02-01
 */

public class Spell
{
	private String name;
	private String description;
	private int energyCost;
	private String type;
	
	public Spell (String name, String description, int energyCost, String type) {
		this.name = name;
		this.description = description;
		this.energyCost = energyCost;
		this.type = type;
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
	
	public String getType () {
		return type;
	}
}