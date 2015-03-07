package database;

import java.util.HashMap;

import combat.Enemy;

/**
 * A singleton that handles all enemies in the game
 * 
 * @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @version		1.0
 * @since		2015-02-24
 */

public class EnemyDatabase
{
	private final HashMap<String, Enemy> enemies = new HashMap<>();
	private static EnemyDatabase instance = null;
	
	/**
	 * Create all enemies in game and put them in hashmap for later use. Is dependent on ImageDatabase to exist.
	 */
	private EnemyDatabase(){
		//Add enemies
		enemies.put("Thief", new Enemy("Thief",1,ImageDatabase.getInstance().getImage("enemy.png")));
		enemies.put("Bandit Rogue", new Enemy("Bandit Rogue",1,ImageDatabase.getInstance().getImage("enemy.png")));
		enemies.put("Bandit Lord", new Enemy("Bandit Lord",2,ImageDatabase.getInstance().getImage("enemy.png"), SpellDatabase.getInstance().getSpells("heal")));
		enemies.put("Bandit Boss", new Enemy("Bandit Boss",4,ImageDatabase.getInstance().getImage("enemy.png"),SpellDatabase.getInstance().getSpells("fireball"),SpellDatabase.getInstance().getSpells("heal")));
		enemies.put("Dark Lord", new Enemy("Dark Lord",15,ImageDatabase.getInstance().getImage("enemy.png"),SpellDatabase.getInstance().getSpells("fireball")));
		enemies.put("Gladiator", new Enemy ("Gladiator", 1, ImageDatabase.getInstance().getImage("enemy.png")));
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
	 * Get an enemy from the database by providing the key as a String
	 * @param enemyName - key
	 * @return Enemy corresponding to key
	 */
	public Enemy getEnemy(String enemyName) {
		return enemies.get(enemyName);
	}
}

