package database;

import java.util.ArrayList;
import java.util.List;
import character.Item;

/**
 * @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @version		0.3.1
 * @since		2015-02-18
 */

public class ItemDatabase
{
	private final List<Item> items = new ArrayList<>();
	
	public ItemDatabase(){
		
	}

	/**
	 * return this list of items
	 * @return
	 */
	
	public List<Item> getItems() {
		return items;
	}
}
