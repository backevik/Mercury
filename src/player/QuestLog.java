package player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Quest container for Player
 * 
 * @author      Mattias Benngård	<mbengan@gmail.com>
 * @version     1.0					<2015-03-06>
 * @since       2015-02-01
 */

public class QuestLog
{
	private final List<QuestEntry> questEntries = new ArrayList<>();
	
	/**
	 * Public getter for quests entries.
	 * @return an unmodifiable list of quests
	 */
	public List<QuestEntry> getQuestEntries () {
		return Collections.unmodifiableList(questEntries);
	}
	
	/**
	 * Add a quest from the quest database to active quests
	 * @param quest - which quest to add to the journal
	 */
	public void addQuest (Quest quest) {
		// forbid duplicates to be added
		for (QuestEntry q : questEntries) {
			if (q.getQuest() == quest) {
				return;
			}
		}
		
		questEntries.add(new QuestEntry(quest));
	}
	
	/**
	 * Complete a quest in your quest log. If it wasn't active it will first be set to active then completed
	 * @param quest - which quest to complete in the journal
	 */
	public void completeQuest (Quest quest) {
		questEntries.get(questEntries.indexOf(quest)).complete();
	}
}
