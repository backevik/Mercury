package database;

import java.util.HashMap;

import combat.Enemy;

/**
 * @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @version		0.3.2
 * @since		2015-02-21
 */

public class EnemyDatabase
{
	private final HashMap<String, Enemy> enemies = new HashMap<>();
	
	public EnemyDatabase(){
		//enemies.put("Big evil bengan", new Enemy("Big evil bengan",2,GameDataManager.getInstance().getSpells("fireball"),GameDataManager.getInstance().getSpells("heal")));
		//Add enemies to list
		
	}

	/**
	 * return item from list base from name of item
	 * @return
	 */
	public Enemy getEnemy(String enemyName) {
		return enemies.get(enemyName);
	}
}

