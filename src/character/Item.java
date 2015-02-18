package character;

import java.io.Serializable;

/**
 * @author      Mattias Benngård	<mbengan@gmail.com>
 * @author		Andreas Bäckevik	<backevik@student.chalmers.se>
 * @version     0.3.1
 * @since       2015-02-18
 */

public abstract class Item implements Serializable
{
	private static final long serialVersionUID = 1212417444730053211L;
<<<<<<< HEAD

=======
>>>>>>> origin/Mercury-game
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
