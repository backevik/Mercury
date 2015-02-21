package items;

/**
* @author		Mattias Benngård	<mbengan@gmail.com>
* @version		0.9					<2015-02-21>
* @since		2015-02-21
*/

public abstract class ItemEquipable extends Item
{
	private static final long serialVersionUID = 9147223473529499090L;
	
	public ItemEquipable(String name, String description, int maxStackSize,
			int sellPrice) {
		super(name, description, maxStackSize, sellPrice);
	}
}
