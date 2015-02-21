package vendor;

import java.util.HashMap;
import java.util.Set;

import character.Item;
import database.GameDataManager;
import database.ItemDatabase;
import player.Inventory;
import player.Playable;
import player.Player;
/**
 * 
 * @author Martin Claesson
 * @version 0.4
 * @since 2015-02-21
 *
 */
public class Vendor 
{
	private Player player;
	private HashMap<Item, Integer> items = new HashMap<>();
	private final int 	maxHpPotions= 50;
	public Vendor(Player player) {
		this.player = player;
		items.put(GameDataManager.getInstance().getItems("hpPotion"),maxHpPotions);
		// add more item to vendor
	};
	
	/**
	 * 
	 * @param name of item that player buys 
	 * @return true if the player where able to buy item  else false
	 */
	public boolean buyItem(String item){
		
		if(player.getPC().getCurrency() >= getItem(item).getSellPrice() && items.get(getItem(item))!=0){
			player.getPC().subtractCurrency(getItem(item).getSellPrice());
			player.getPC().getInventory().addItem(getItem(item),1);
			items.put(getItem(item), items.get(getItem(item))-1);
			
			return true;
		}
		else{
			return false;
		}
	}
	/**
	 * 
	 * @param Name of item
	 * @return the value of the key 
	 */
	
	private Item getItem(String key){
		for(Item item : items.keySet()){
			if(item.getName().equals(key)){
				return item;
			}
	
		}
		return null;
	}
}
