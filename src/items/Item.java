package items;

import java.io.Serializable;

/**
 * @author      Mattias Benngård	<mbengan@gmail.com>
 * @author		Andreas Bäckevik	<backevik@student.chalmers.se>
 * @version     0.4
 * @since       2015-02-01
 */

public abstract class Item implements Serializable
{
	private static final long serialVersionUID = 1212417444730053211L;

	private String name;
	private String description;
	private int maxStackSize;
	private int sellPrice;
		
	public Item (String name, String description, int maxStackSize,
			int sellPrice) {
		this.name = name;
		this.description = description;
		this.maxStackSize = maxStackSize;
		this.sellPrice = sellPrice;
	}
	
	public String getName () {
		return name;
	}
	
	public String getDescription () {
		return description;
	}
	
	public int getMaxStackSize () {
		return maxStackSize;
	}
	
	public int getSellPrice () {
		return sellPrice;
	}

	public abstract String use();
}