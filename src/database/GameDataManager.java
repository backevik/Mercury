package database;

import java.awt.Image;
import player.Quest;
import character.Item;
import character.Spell;
import database.ItemDatabase;

/**
 * @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @version		0.3.1
 * @since		2015-02-17
 */

public class GameDataManager 
{
	private static GameDataManager instance = null;
	
	private QuestDatabase quests;
	private ItemDatabase items;
	private SpellDatabase spells;
	private ImageDatabase images;
	
	public static final int QUEST_STATE = 0;
	public static final int SPELL_STATE = 1;
	
	private GameDataManager(){
		
		quests = new QuestDatabase();
		spells = new SpellDatabase();
		
		items = new ItemDatabase();
		images = new ImageDatabase();
		
	}
	
	/**
	 * Singleton initializing
	 */
	public static GameDataManager getInstance() {
		if(instance == null) {
			instance = new GameDataManager();
		}
		return instance;
			
	}
	
	/**
	 * Return lists of objects
	 */
	public java.util.List<Quest> getQuests() {
		return quests.getQuests();
	}
	
	public java.util.List<Item> getItems() {
		return items.getItems();
	}
	
	public java.util.List<Spell> getSpells() {
		return spells.getSpells();
	}
	
	public Image getImage(String imgName) {
		return images.getImage(imgName);
	}
}
