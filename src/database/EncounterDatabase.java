package database;

import java.util.HashMap;

import combat.Encounter;

/**
 * A singleton that handles all encounters in game
 * 
 * @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @version		1.0
 * @since		2015-03-06
 */

public class EncounterDatabase
{
	private final HashMap<String, Encounter> encounters = new HashMap<>();
	private static EncounterDatabase instance = null;
	
	/**
	 * Create encounters and put them in a hashmap for later use. Is dependent on EnemyDatabase the exist.
	 */
	private EncounterDatabase (){
		//Add encounters
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
	 * Get an encounter from the database by providing the key as a String
	 * @param encounterName - key
	 * @return Encounter corresponding to key
	 */
	public Encounter getEncounter (String encounterName) {
		return encounters.get(encounterName);
	}
}

