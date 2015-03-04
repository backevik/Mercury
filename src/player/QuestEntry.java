package player;

/**
 * Wrapper class for quest, contains if the quest is completed or not.
 * 
 * @author      Mattias Benngård	<mbengan@gmail.com>
 * @version     1.0					<2015-02-27>
 * @since       2015-02-01
 */

public class QuestEntry {

	private Quest quest;
	private boolean completed;
	
	public QuestEntry (Quest quest) {
		this.quest = quest;
	}
	
	public boolean getCompleted () {
		return completed;
	}
	
	public Quest getQuest () {
		return quest;
	}
	
	public void complete() {
		completed = true;
	}
}
