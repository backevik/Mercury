package database;

import java.util.HashMap;

import character.Item;

/**
 * @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @version		0.3.2
 * @since		2015-02-20
 */

public class ItemDatabase
{
	private final HashMap<String, Item> items = new HashMap<>();
	
	public ItemDatabase(){
		//Add items to list
	}

	/**
	 * return item from list base from name of item
	 * @return
	 */
	public Item getItems(String itemName) {
		return items.get(itemName);
	}
}
