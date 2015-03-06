package database;

import java.util.HashMap;

import items.Item;
import items.ItemPotion;

/**
 * A singleton that handles all items in the game.
 * 
 * @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @version		1.0
 * @since		2015-03-06
 */

public class ItemDatabase
{
	private final HashMap<String, Item> items = new HashMap<>();
	private static ItemDatabase instance = null;
	
	/**
	 * Create all items and put them in a hashmap for later use.
	 */
	private ItemDatabase(){
		//Add items
		items.put("HealingPotion1", new ItemPotion("Minor Healing Potion", "A minor healing potion. Restores 20 health.", 20, 5, 0, 20));
		items.put("HealingPotion2", new ItemPotion("Major Healing Potion", "A minor healing potion. Restores 20 health.", 20, 5, 0, 20));
		items.put("EnergyPotion1", new ItemPotion("Minor Energy Potion", "A minor energy potion. Restores 15 energy.", 20, 5, 1, 15));
	}
	
	/**
	 * Singleton initializing
	 */
	public static ItemDatabase getInstance() {
		if(instance == null) {
			instance = new ItemDatabase();
		}
		return instance;
			
	}

	/**
	 * Get an item from the database by providing the key as a String
	 * @param itemName - key
	 * @return Item corresponding to key
	 */
	public Item getItem (String itemName) {
		return items.get(itemName);
	}
}
