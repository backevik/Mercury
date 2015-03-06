package database;

import java.util.HashMap;

import player.Quest;

/**
 * A singleton that handles all quests in the game.
 * 
 * @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @version		1.0
 * @since		2015-03-06
 */

public class QuestDatabase
{
	private final HashMap<String, Quest> quests = new HashMap<>();
	private static QuestDatabase instance = null;
	
	/**
	 * Create all quests in game and put in a hashmap for later use.
	 */
	private QuestDatabase(){
		//Add quests
		quests.put("First Quest", new Quest("First Quest", "Kill the Dark Lord."));
		quests.put("Second Quest", new Quest("Second Quest", "Save Farmer Teds pig."));
		quests.put("Third Quest", new Quest("Third Quest", "Get married and have children."));
	}
	
	/**
	 * Get a quest from the database by providing the key as a String
	 * @param questName - key
	 * @return Quest corresponding to key
	 */
	public Quest getQuest (String questName) {
		return quests.get(questName);
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
}

