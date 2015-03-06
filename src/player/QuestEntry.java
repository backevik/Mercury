package player;

/**
 * Wrapper class for quest, contains if the quest is completed or not.
 * 
 * @author      Mattias Benngård	<mbengan@gmail.com>
 * @version     1.0					<2015-03-06>
 * @since       2015-02-01
 */

public class QuestEntry
{
	private Quest quest;
	private boolean completed;
	
	/**
	 * Constructor for QuestEntry
	 * @param quest - which quest to wrap around
	 */
	public QuestEntry (Quest quest) {
		this.quest = quest;
	}
	
	/**
	 * Public getter for quest status
	 * @return the status of the quest
	 */
	public boolean getCompleted () {
		return completed;
	}
	
	/**
	 * Public getter for the quest
	 * @return the quest stored
	 */
	public Quest getQuest () {
		return quest;
	}
	
	/**
	 * Sets the quest to complete
	 */
	public void complete () {
		completed = true;
	}
}
