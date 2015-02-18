package database;

import java.util.ArrayList;
import java.util.List;
import player.Quest;

/**
 * @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @version		0.3.1
 * @since		2015-02-18
 */

public class QuestDatabase
{

	private final List<Quest> quests = new ArrayList<>();
	
	public QuestDatabase(){
		
	}
	
	/**
	 * Return list of quests
	 * @return
	 */
	public List<Quest> getQuests() {
		return quests;
	}


}
