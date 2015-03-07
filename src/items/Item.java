package items;

import java.io.Serializable;

/**
 * Base class that all items extends from
 * 
 * @author      Mattias Benngård	<mbengan@gmail.com>
 * @version     1.0					<2015-03-07>
 * @since       2015-02-01
 */

public abstract class Item implements Serializable
{
	private static final long serialVersionUID = 1212417444730053211L;

	private String name;
	private String description;
	private int maxStackSize;
	private int sellPrice;
	
	/**
	 * Constructor for Item
	 * @param name - name of the item
	 * @param description - description of the item
	 * @param maxStackSize - how many items a stack can contain
	 * @param sellPrice - what the sell price of an item is
	 */
	public Item (String name, String description, int maxStackSize,
			int sellPrice) {
		this.name = name;
		this.description = description;
		this.maxStackSize = maxStackSize;
		this.sellPrice = sellPrice;
	}
	
	/**
	 * Public getter for name
	 * @return name of the item
	 */
	public String getName () {
		return name;
	}
	
	/**
	 * Public getter for description
	 * @return description of the item
	 */
	public String getDescription () {
		return description;
	}
	
	/**
	 * Public getter for max stack size
	 * @return how many the items of this type can be stacked on each other
	 */
	public int getMaxStackSize () {
		return maxStackSize;
	}
	
	/**
	 * Public getter for sell price
	 * @return the sell price of the item
	 */
	public int getSellPrice () {
		return sellPrice;
	}

	public abstract String use();
}