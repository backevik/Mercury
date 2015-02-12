package player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import character.Item;

/**
 * @author      Mattias Benngård	<mbengan@gmail.com>
 * @version     0.4
 * @since       2015-02-01
 */

public class Inventory
{
	private final static int MAX_ITEMS = 100;
	
	private final List<ItemSlot> items = new ArrayList<>();
	
	public Inventory () {
		// create empty item slots
		for (int i = 0; i < MAX_ITEMS; i++) {
			items.add(new ItemSlot(null, 0));
		}
	}
	
	/**
	 * @return	an unmodifiable list of item slots
	 */
	public List<ItemSlot> getItems () {
		return Collections.unmodifiableList(items);
	}
	
	/**
	 * Adds item to inventory with quantity 
	 * @param item -  item to be stored in the inventory
	 * @param quantity -  how many copies of item to be stored
	 * @return the amount of items that could not be fit in the inventory
	 */
	public int addItem (Item item, int quantity) {
		// add 'quantity' to current items slots that hold and item equal to 'item'
		for (int i = 0; i < MAX_ITEMS && quantity > 0; i++) {
			if (items.get(i).equals(item) && items.get(i).getQuantity() < items.get(i).getItem().getMaxStackSize()) {				
				ItemSlot tmp = items.get(i);
				int deltaQuantiy = tmp.getItem().getMaxStackSize() - tmp.getQuantity();
				
				quantity -= deltaQuantiy;
				tmp.addQuantity(deltaQuantiy);
			}
		}
		
		// add items to new items slots
		for (int i = 0; i < MAX_ITEMS && quantity > 0; i++) {
			if (items.get(i).getItem() == null) {
				int deltaQuantiy = 0;
				
				items.get(i).addItem (item, deltaQuantiy);
				quantity -= deltaQuantiy;
			}
		}
		
		return quantity;
	}
	
	public void useItem (int index) {
		if (index < 0 || index > MAX_ITEMS) {
			return;
		}
		items.get(index).use();
	}
}