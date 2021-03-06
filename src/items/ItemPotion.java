package items;

import java.io.Serializable;

/**
 * Potion items for characters, use effect that restores vitals
 * 
 * @author      Mattias Benng�rd	
 * @version     1.0					
 * @since		2015-02-17
 */

public class ItemPotion extends Item implements Serializable
{
	private static final long serialVersionUID = 3631163768021633820L;
	
	private int vitalID;
	private int effect;
	
	/**
	 * Constructor for ItemPotion
	 * @param name  name of the item to created
	 * @param description  description of the item to be created
	 * @param maxStackSize  maximum stack 
	 * @param sellPrice item prize
	 * @param vitalID	id of inventory 
	 * @param effect	value of intem effect
	 */
	public ItemPotion (String name, String description, int maxStackSize, int sellPrice, int vitalID, int effect)	{
		super(name, description, maxStackSize, sellPrice);
		this.vitalID = vitalID;
		this.effect = effect;
	}
	
	@Override
	public String use () {
		return constants.Vitals.getNames().get(vitalID) + "#" + effect;
	}
}
