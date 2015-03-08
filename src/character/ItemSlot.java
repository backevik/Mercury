package character;

import java.io.Serializable;

import items.Item;

/**
 * Wrapper class for item, contains how many exists in the player inventory.
 * 
 * @author      Mattias Benngard	<mbengan@gmail.com>
 * @version     1.0					<2015-02-27>
 * @since       2015-03-07
 */

public class ItemSlot implements Serializable
{
	private static final long serialVersionUID = -5028802384998565603L;
	private Item item;
	private int quantity;

	/**
	 * Adds items to the item slot
	 * @param item - which item to add
	 * @param quantity - how many to add of them
	 */
	public ItemSlot (Item item, int quantity) {
		addItem (item, quantity);
	}
	
	/**
	 * Public getter for item
	 * @return reference to item
	 */
	public Item getItem () {
		return item;
	}
	
	/**
	 * Public getter for quantity
	 * @return how many items that are stored
	 */
	public int getQuantity () {
		return quantity;
	}
	
	/**
	 * Add item with quantity to this slot. Note: It overrides any existing item in the slot.
	 * @param item reference to item to be added 
	 * @param quantity how many items that should be added 
	 */
	public void addItem (Item item, int quantity) {
		this.item = item;
		this.quantity = quantity;
	}
	
	/**
	 * Adds to current quantity
	 * @param quantity amount to be added to local quantity
	 */
	public void addQuantity (int quantity) {
		this.quantity += quantity;
	}
	
	/**
	 * Uses an item, calls item.use()
	 */
	public void use () {
		if (item != null) {
			item.use();
		}			
	}
}