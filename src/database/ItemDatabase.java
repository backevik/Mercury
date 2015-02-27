package database;

import java.util.HashMap;

import items.Item;
import items.ItemPotion;

/**
 * @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @version		0.3.2
 * @since		2015-02-20
 */

public class ItemDatabase
{
	private final HashMap<String, Item> items = new HashMap<>();
	private static ItemDatabase instance = null;
	
	private ItemDatabase(){
		items.put("HealingPotion1", new ItemPotion("Minor Healing Potion", "A minor healing potion. Restores 20 health.", 20, 5, 0, 20));
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
	 * return item from list base from name of item
	 * @return
	 */
	public Item getItem (String itemName) {
		return items.get(itemName);
	}
}
