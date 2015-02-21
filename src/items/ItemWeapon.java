package items;

/**
* @author		Mattias Benngård	<mbengan@gmail.com>
* @version		0.9					<2015-02-21>
* @since		2015-02-21
*/

public class ItemWeapon extends ItemEquipable
{
	private static final long serialVersionUID = -1447215487521527826L;
	int minDmg;
	int maxDmg;
	
	public ItemWeapon(String name, String description, int maxStackSize,
			int sellPrice, int minDmg, int maxDmg) {
		super(name, description, maxStackSize, sellPrice);
		this.minDmg = minDmg;
		this.maxDmg = maxDmg;
	}
	
	public int getMinDmg () {
		return minDmg;
	}
	
	public int getMaxDmg () { 
		return maxDmg;
	}

	@Override
	public String use() {
		return "Equip";
	}
}