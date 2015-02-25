package database;

import java.awt.Image;

import combat.Enemy;
import player.Quest;
import worldmap.WorldMap;
import items.Item;
import character.Spell;

/**
 * @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @version		0.3.2
 * @since		2015-02-21
 */

public class GameDataManager 
{
	private static GameDataManager instance = null;
	
	private QuestDatabase quests;
	private ItemDatabase items;
	private SpellDatabase spells;
	private ImageDatabase images;
	private EnemyDatabase enemies;
	private WorldMap world;
	
	public static final int QUEST_STATE = 0;
	public static final int SPELL_STATE = 1;
	
	private GameDataManager(){
		
		quests = new QuestDatabase();
		spells = new SpellDatabase();
		items = new ItemDatabase();
		images = new ImageDatabase();
		enemies = new EnemyDatabase();
		world = new WorldMap();
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
	 * Return quest from list
	 * @param questName
	 * @return Quest
	 */
	public Quest getQuests(String questName) {
		return quests.getQuest (questName);
	}
	
	/**
	 * Return item from list
	 * @param itemName
	 * @return Item
	 */
	public Item getItems(String itemName) {
		return items.getItems(itemName);
	}
	
	/**
	 * Return spell from list
	 * @param spellName 
	 * @return Spell
	 */
	public Spell getSpells(String spellName) {
		return spells.getSpells(spellName);
	}
	
	/**
	 * Return enemy from list
	 * @param enemyName
	 * @return
	 */
	public Enemy getEnemy(String enemyName) {
		return enemies.getEnemy(enemyName);
	}
	
	/**
	 * Return the image based on the image name
	 * @param imgName
	 * @return Image
	 */
	public Image getImage(String imgName) {
		return images.getImage(imgName);
	}	
}
