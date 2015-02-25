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
	private static QuestDatabase instance = null;
	
	private QuestDatabase(){
		quests.put("First", new Quest("First Quest", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."));
		quests.put("Second", new Quest("First Quest", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."));
	}
	
	/**
	 * Singleton initializing
	 */
	public static QuestDatabase getInstance() {
		if(instance == null) {
			instance = new QuestDatabase();
		}
		return instance;
			
	}
	
	/**
	 * 
	 * @param questName
	 * @return
	 */
	public Quest getQuest (String questName) {
		return quests.get(questName);
	}
}

