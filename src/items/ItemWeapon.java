package items;

/**
 * 
 * 
 * @author		Mattias Benngård	<mbengan@gmail.com>
 * @version		1.0					<2015-02-21>
 * @since		2015-02-21
 */

public class ItemWeapon extends ItemEquipable
{
	private static final long serialVersionUID = -1447215487521527826L;
	private int minDmg;
	private int maxDmg;
	
	/**
	 * Constructor for ItemWeapon
	 * @param name - name of the item
	 * @param description - description of the item
	 * @param maxStackSize - how many items a stack can contain
	 * @param sellPrice - what the sell price of an item is
	 * @param minDmg - the minimum damage of the weapon
	 * @param maxDmg - the maximum damage of the weaponm
	 */
	public ItemWeapon(String name, String description, int maxStackSize,
			int sellPrice, int minDmg, int maxDmg) {
		super(name, description, maxStackSize, sellPrice);
		this.minDmg = minDmg;
		this.maxDmg = maxDmg;
	}
	
	/**
	 * Public getter for minDmg
	 * @return maximum damage
	 */
	public int getMinDmg () {
		return minDmg;
	}
	
	/**
	 * Public getter for maxDmg
	 * @return maximum damage
	 */
	public int getMaxDmg () { 
		return maxDmg;
	}

	@Override
	public String use() {
		return "Equip";
	}
}