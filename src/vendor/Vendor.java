package vendor;

import java.util.HashMap;


import items.Item;
import database.ItemDatabase;
import player.Player;

/**Where the vendor is created, purchases are made and items are added to inventory. 
 * Getters for items 
 * @author Martin Claesson
 * @version 1.0
 * @since 2015-03-03
 */

public class Vendor 
{
	private Player player;
	private HashMap<Item, Integer> items = new HashMap<>();
	private final static int 	MAX_HP_POTIONS= 50;
	private final static int 	MAX_ENERGY_POTIONS= 50;
	/**
	 * Constructor adds specific items to the vendor and declares local player reference
	 * @param player - reference to player 
	 */
	public Vendor(Player player) {
		this.player = player;
		items.put(ItemDatabase.getInstance().getItem("HealingPotion1"),MAX_HP_POTIONS);
		items.put(ItemDatabase.getInstance().getItem("EnergyPotion1"), MAX_ENERGY_POTIONS);
	};
	
	/**
	 * Handles the item purchases by adding the specified item to the players inventory and
	 * subtracts the cost from the players currency
	 * @param name -  name of item the player buys 
	 * @return true - if the player where able to buy item otherwise false
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
	 * Getter for items in vendor
	 * @return HashMap - containing the items and the amount of them 
	 */
	public HashMap<Item, Integer> getItems(){
		return items;
	}
	/**
	 * returns the specified item that matches the string
	 * @param Key - Name of item
	 * @return Item - item value of the key if it exists. Otherwise null
	 */
	public Item getItem(String key){
		for(Item item : items.keySet()){
			if(item.getName().equals(key)){
				return item;
			}
	
		}
		return null;
	}
}
