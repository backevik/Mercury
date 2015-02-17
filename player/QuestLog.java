package player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author      Mattias Benngård	<mbengan@gmail.com>
 * @version     0.1
 * @since       2015-02-01
 */

public class QuestLog
{
	private final List<Quest> quests = new ArrayList<>();
	
	public QuestLog () {}
	
	/**
	 * @return	an unmodifiable list of quests
	 */
	public List<Quest> getQuests () {
		return Collections.unmodifiableList(quests);
	}
	
	/**
	 * Add a quest from the quest database
	 */
	public void addQuest (Quest quest) {
		quest.setQuestStatus (QuestStatus.ACTIVE);
		quests.add(quest);
	}
	
	/**
	 * Complete a quest in your quest log
	 */
	public void completeQuest (int index) {
		quests.get(index).setQuestStatus (QuestStatus.COMPLETED);
	}
}
