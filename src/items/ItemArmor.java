package items;

/**
* @author		Mattias Benngård	<mbengan@gmail.com>
* @version		0.9					<2015-02-21>
* @since		2015-02-21
*/

public class ItemArmor extends ItemEquipable
{
	private static final long serialVersionUID = -1447215487521527826L;
	int armor;
	
	public ItemArmor(String name, String description, int maxStackSize,
			int sellPrice, int armor) {
		super(name, description, maxStackSize, sellPrice);
		this.armor = armor;
	}
	
	public int getArmor () {
		return armor;
	}

	@Override
	public String use() {
		return "Equip";
	}
}