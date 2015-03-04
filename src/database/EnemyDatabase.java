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
	 * return item from list base from name of item
	 * @return
	 */
	public Enemy getEnemy(String enemyName) {
		return enemies.get(enemyName);
	}
}

