package database;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import player.Quest;

/**
 * @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @version		0.3
 * @since		2015-02-17
 */

public class QuestDatabase extends DataState implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4371759558845551606L;
	private final List<Quest> quests = new ArrayList<>();
	
	public QuestDatabase(){
		
	}
	
	/**
	 * Add quest to list
	 * @param quest
	 */
	public void addQuest(Quest quest) {
		quests.add(quest);
	}
	
	/**
	 * Remove quest from list
	 * @param quest
	 */
	public void removeQuest(Quest quest) {
		quests.remove(quest);
	}
	
	/**
	 * Return list of quests
	 * @return
	 */
	public List<Quest> getQuests() {
		return quests;
	}

	@Override
	public void save() {
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("QuestDatabase.dat"));
			out.writeObject(this);
			
			out.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		
	}


	@Override
	public void load() {
		try{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("QuestDatabase.dat"));
			QuestDatabase tmp = (QuestDatabase)in.readObject();
			quests.clear();
			
			for(Quest quest : tmp.getQuests())
			{
				quests.add(quest);
			}
			in.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		
	}

}
