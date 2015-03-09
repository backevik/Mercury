package player;

import java.io.Serializable;

/**
 * Data container for quests. instantiated in database.Questdatabase
 * 
 * @author      Mattias Benngård	
 * @version     1.0					
 * @since       2015-03-07
 */

public class Quest implements Serializable
{	
	private static final long serialVersionUID = 6363280308011546027L;
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
