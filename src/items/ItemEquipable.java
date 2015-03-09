package items;

/**
 * Equipable items for characters
 * 
 * @author      Mattias Benngård	
 * @version     1.0					
 * @since		2015-02-21
 */

public abstract class ItemEquipable extends Item
{
	private static final long serialVersionUID = 9147223473529499090L;
	
	/**
	 * Constructor for ItemEquipable
	 * @param name - name of the item
	 * @param description - description of the item
	 * @param maxStackSize - how many items a stack can contain
	 * @param sellPrice - what the sell price of an item is
	 */
	public ItemEquipable(String name, String description, int maxStackSize,
			int sellPrice) {
		super(name, description, maxStackSize, sellPrice);
	}
}
