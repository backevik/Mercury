package player;

/**
 * Data container for quests. instantiated in database.Questdatabase
 * 
 * @author      Mattias Benngård	<mbengan@gmail.com>
 * @version     1.0					<2015-03-06>
 * @since       2015-02-01
 */

public class Quest
{	
	private String name;
	private String description;
	
	/**
	 * Constructor for Quest
	 * @param name - name of the quest to be added
	 * @param description - description of the quest to be added
	 */
	public Quest (String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	/**
	 * Public getter for quest name
	 * @return the name of the quest
	 */
	public String getName () {
		return name;
	}
	
	/**
	 * Public getter for quest description
	 * @return the description of the quest
	 */
	public String getDescription () {
		return description;
	}
}
