package items;

/**
 * Armor items for characters
 * 
 * @author      Mattias Benngård	
 * @version     1.0					
 * @since		2015-02-21
 */

public class ItemArmor extends ItemEquipable
{
	private static final long serialVersionUID = -1447215487521527826L;
	private int armor;
	
	/**
	 * Constructor for ItemArmor
	 * @param name - name of the item
	 * @param description - description of the item
	 * @param maxStackSize - how many items a stack can contain
	 * @param sellPrice - what the sell price of an item is
	 * @param armor - how much defense it has
	 */
	public ItemArmor(String name, String description, int maxStackSize,
			int sellPrice, int armor) {
		super(name, description, maxStackSize, sellPrice);
		this.armor = armor;
	}
	
	/**
	 * Public getter for armor
	 * @return armor of this item
	 */
	public int getArmor () {
		return armor;
	}

	@Override
	public String use() {
		return "Equip";
	}
}