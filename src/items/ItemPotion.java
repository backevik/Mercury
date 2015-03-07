package items;

import java.io.Serializable;

/**
 * Potion items for characters, use effect that restores vitals
 * 
 * @author      Mattias Benngård	<mbengan@gmail.com>
 * @version     1.0					<2015-03-07>
 * @since		2015-02-17
 */

public class ItemPotion extends Item implements Serializable
{
	private static final long serialVersionUID = 3631163768021633820L;
	
	private int vitalID;
	private int effect;
	
	/**
	 * Constructor for ItemPotion
	 * @param name - name of the item to created
	 * @param description - description of the item to be created
	 * @param maxStackSize - maximum stack si
	 * @param sellPrice
	 * @param vitalID
	 * @param effect
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
