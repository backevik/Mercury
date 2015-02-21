package items;

import java.io.Serializable;

import character.Item;

/**
* @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
* @version		0.3
* @since		2015-02-17
*/
public class PotionItem extends Item implements Serializable{
	
	private static final long serialVersionUID = 3631163768021633820L;
	int healPower;
	
	public PotionItem()
	{
		super("Potion", "Heal for 10", 5, 9, 1);
		healPower = 10;
	}
	
	@Override
	public void use() {
		
	}
}
