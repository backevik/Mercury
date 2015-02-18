package character;

/**
 * @author      Mattias Benngård	<mbengan@gmail.com>
 * @author		Andreas Bäckevik	<backevik@student.chalmers.se>
 * @version     0.4
 * @since       2015-02-01
 */

public abstract class Item
{
	private String name;
	private String description;
	private int maxStackSize;
	private int sellPrice;
	private int charges;
		
	public Item (String name, String description, int maxStackSize, int sellPrice, int charges) {
		this.name = name;
		this.description = description;
		this.maxStackSize = maxStackSize;
		this.sellPrice = sellPrice;
		this.charges = charges;
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
	
	public int getCharges () {
		return charges;
	}
	
	public void reduceCharges(){ //Move this later
		charges --;
	}

	public abstract void use();
}