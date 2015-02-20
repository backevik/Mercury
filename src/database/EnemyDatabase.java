package database;

import java.util.HashMap;

import combat.Enemy;

/**
 * @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @version		0.3.2
 * @since		2015-02-20
 */

public class EnemyDatabase
{
	private final HashMap<String, Enemy> items = new HashMap<>();
	
	public EnemyDatabase(){
		//Add enemies to list
	}

	/**
	 * return item from list base from name of item
	 * @return
	 */
	public Enemy getItems(String enemyName) {
		return items.get(enemyName);
	}
}
