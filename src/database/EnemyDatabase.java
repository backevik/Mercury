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
	private static EnemyDatabase instance = null;
	
	private EnemyDatabase(){
		enemies.put("Big evil bengan", new Enemy("Big evil bengan",2,SpellDatabase.getInstance().getSpells("fireball"),SpellDatabase.getInstance().getSpells("heal")));
		//Add enemies to list
		
	}
	
	/**
	 * Singleton initializing
	 */
	public static EnemyDatabase getInstance() {
		if(instance == null) {
			instance = new EnemyDatabase();
		}
		return instance;
			
	}

	/**
	 * return item from list base from name of item
	 * @return
	 */
	public Enemy getEnemy(String enemyName) {
		return enemies.get(enemyName);
	}
}

