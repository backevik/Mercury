package items;

import java.io.Serializable;

/**
* @author		Mattias Benngård	<mbengan@gmail.com>
* @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
* @version		0.4					<2015-02-21>
* @since		2015-02-17
*/

public class ItemPotion extends Item implements Serializable
{
	private static final long serialVersionUID = 3631163768021633820L;
	
	private int vitalID;
	private int effect;
	
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
