package player;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import items.Item;

/**
 * @author      Mattias Benngard	<mbengan@gmail.com>
 * @version     0.9					<2015-02-27>
 * @since       2015-02-01
 */

public class Inventory
{
	private final ItemSlot[] items = new ItemSlot[constants.Player.MAX_ITEMS];
	
	public Inventory () {}
	
	/**
	 * @return	an unmodifiable list of item slots
	 */
	public List<ItemSlot> getItems () {
		return Collections.unmodifiableList(Arrays.asList(items));
	}
	
	/**
	 * Get an item slot by index greater than or equal to 0 and lesser than MAX_ITEMS 
	 * @param index
	 * @return ItemSlot at index
	 */
	public ItemSlot getItem (int index) {
		if (index < 0 || index >= constants.Player.MAX_ITEMS) {
			return null;
		}
		
		return items[index];
	}

	/**
	 * Adds item to inventory with quantity 
	 * @param item -  item to be stored in the inventory
	 * @param quantity -  how many copies of item to be stored
	 * @return the amount of items that could not be fit in the inventory
	 */
	public int addItem (Item item, int quantity) {
		// add 'quantity' to current items slots that hold and item equal to 'item'
		for (int i = 0; i < constants.Player.MAX_ITEMS && quantity > 0; i++) {
			if (items[i] != null && items[i].equals(item) && items[i].getQuantity() < items[i].getItem().getMaxStackSize()) {
				ItemSlot tmp = items[i];
				int deltaQuantiy = tmp.getItem().getMaxStackSize() - tmp.getQuantity();
				
				quantity -= deltaQuantiy;
				tmp.addQuantity(deltaQuantiy);
			}
		}
		
		// add items to new items slots
		for (int i = 0; i < constants.Player.MAX_ITEMS && quantity > 0; i++) {
			if (items[i] == null) {
				int deltaQuantiy = 0;
				
				items[i] = new ItemSlot (item, deltaQuantiy);
				quantity -= deltaQuantiy;
			}
		}
		
		return quantity;
	}
	
	public void useItem (int index) {
		if (index < 0 || index >= constants.Player.MAX_ITEMS) {
			return;
		}
		items[index].use();
	}
}