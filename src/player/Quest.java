package player;

/**
 * @author      Mattias Benngård	<mbengan@gmail.com>
 * @version     1.0					<2015-02-17>
 * @since       2015-02-01
 */

public class Quest
{	
	private String name;
	private String description;
	
	public Quest (String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	/**
	 * Public getter for name
	 * @return String name
	 */
	public String getName () {
		return name;
	}
	
	/**
	 * Public getter for description
	 * @return String description
	 */
	public String getDescription () {
		return description;
	}
}
