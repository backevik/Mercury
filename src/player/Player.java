package player;

import java.io.Serializable;

/**
 * @author      Mattias Benngård	<mbengan@gmail.com>
 * @version     0.1
 * @since       2015-03-07
 */

public class Player implements Serializable
{
	private static final long serialVersionUID = -3899132846395594636L;
	private Playable pc;
	private final Inventory inventory = new Inventory ();
	private final QuestLog questLog = new QuestLog (); 
	
	public Player (String name) {
		pc = new Playable (name);
	}
	
	/**
	 * @return the player
	 */
	public Playable getPC () {
		return pc;
	}
	
	/**
	 * @return the quest log
	 */
	public QuestLog getQuestLog () {
		return questLog;
	}
	
	/**
	 * @return the inventory
	 */
	public Inventory getInventory () {
		return inventory;
	}
}