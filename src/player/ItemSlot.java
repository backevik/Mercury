package player;

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

	public ItemSlot (Item item, int quantity) {
		addItem (item, quantity);
	}
	
	/**
	 * @return reference to item
	 */
	public Item getItem () {
		return item;
	}
	
	/**
	 * @return how many items that are stored
	 */
	public int getQuantity () {
		return quantity;
	}
	
	/**
	 * Add item with quantity to this slot. Note: It overrides any existing item in the slot.
	 * @param item
	 * @param quantity
	 */
	public void addItem (Item item, int quantity) {
		this.item = item;
		this.quantity = quantity;
	}
	
	/**
	 * Adds to current quantity
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