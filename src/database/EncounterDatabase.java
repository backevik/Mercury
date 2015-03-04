package database;

import java.util.HashMap;

import combat.Encounter;

/**
 * @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @version		0.3.2
 * @since		2015-02-21
 */

public class EncounterDatabase
{
	private final HashMap<String, Encounter> encounters = new HashMap<>();
	private static EncounterDatabase instance = null;
	
	private EncounterDatabase (){
		encounters.put("Encounter1", new Encounter ("defaultVictory", "defaultLose", EnemyDatabase.getInstance().getEnemy("Thief")));
		encounters.put("Encounter2", new Encounter ("defaultVictory", "defaultLose", EnemyDatabase.getInstance().getEnemy("Bandit Rogue")));
		encounters.put("Encounter3", new Encounter ("defaultVictory", "defaultLose", EnemyDatabase.getInstance().getEnemy("Bandit Lord")));
		encounters.put("Encounter4", new Encounter ("defaultVictory", "defaultLose", EnemyDatabase.getInstance().getEnemy("Bandit Boss")));
		encounters.put("DarkLord", new Encounter ("darkLordDefeated", "defaultLose", EnemyDatabase.getInstance().getEnemy("Dark Lord")));
		encounters.put("Arena", new Encounter ("arenaWon", "arenaLost", EnemyDatabase.getInstance().getEnemy("Gladiator")));
	}
	
	/**
	 * Singleton initializing
	 */
	public static EncounterDatabase getInstance() {
		if(instance == null) {
			instance = new EncounterDatabase ();
		}
		return instance;
			
	}

	/**
	 * return item from list base from name of item
	 * @return
	 */
	public Encounter getEncounter (String encounterName) {
		return encounters.get(encounterName);
	}
}

