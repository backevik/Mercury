package player;

import java.io.Serializable;

/**
 * @author      Mattias Benngård	<mbengan@gmail.com>
 * @version     1.0					<2015-03-07>
 * @since       2015-03-07
 */

public class Player implements Serializable
{
	private static final long serialVersionUID = -3899132846395594636L;
	private Playable pc;
	private final Inventory inventory = new Inventory ();
	private final QuestLog questLog = new QuestLog (); 
	
	/**
	 * Constructor for Player
	 * @param name - what name to give the player at creation
	 */
	public Player (String name) {
		pc = new Playable (name);
	}
	
	/**
	 * Public getter for the pc
	 * @return the player
	 */
	public Playable getPC () {
		return pc;
	}
	
	/**
	 * Public getter for the quest log
	 * @return the quest log
	 */
	public QuestLog getQuestLog () {
		return questLog;
	}
	
	/**
	 * Public getter for the inventory
	 * @return the inventory
	 */
	public Inventory getInventory () {
		return inventory;
	}
}