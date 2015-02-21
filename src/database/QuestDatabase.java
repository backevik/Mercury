package database;

import java.util.HashMap;

import player.Quest;

/**
 * @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @version		0.3.3
 * @since		2015-02-21
 */

public class QuestDatabase
{
	private final HashMap<String, Quest> quests = new HashMap<>();
	
	public QuestDatabase(){
		//Add quests to database
	}
	
	/**
	 * Return list of quests
	 * @return
	 */
	public Quest getQuests(String questName) {
		return quests.get(questName);
	}
}
