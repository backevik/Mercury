package database;

import java.awt.Image;
import java.util.ArrayList;
import player.Quest;
import character.Item;
import character.Spell;

/**
 * @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @version		0.3
 * @since		2015-02-17
 */

public class GameDataManager 
{
	
	private static GameDataManager instance = null;
	private ArrayList<DataState> states;
	private int currentState;
	
	private QuestDatabase quests;
	private ItemDatabase items;
	private SpellDatabase spells;
	private ImageDatabase images;
	
	public static final int ITEMS_STATE = 0;
	public static final int QUEST_STATE = 1;
	public static final int SPELL_STATE = 2;
	
	private GameDataManager(){
		states = new ArrayList<DataState>();
		items = new ItemDatabase();
		quests = new QuestDatabase();
		spells = new SpellDatabase();
		
		states.add(items);
		states.add(quests);
		states.add(spells);
		setState(0);
		
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
	 * saving and loading
	 * The currentState variable is in charge of what to save and load
	 */
	public void save() {
		states.get(currentState).save();
	}
	
	public void load() {
		states.get(currentState).load();
	}
	
	public void setState(int state) {
		currentState = state;
	}
	
	public int getCurrentState() {
		return currentState;
	}
	
	
	/**
	 * Add objects to data
	 */
	public void addItem(Item item) {
		items.addItem(item);
	}
	
	public void addQuest(Quest quest) {
		quests.addQuest(quest);
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
