package database;

import java.util.HashMap;

import character.Spell;

/**
 * A singleton that handles all spells in the game.
 * 
 * @author		Daniel Edsinger 
 * @version		1.0
 * @since		2015-03-06
 */
	
public class SpellDatabase
{
	private final HashMap<String, Spell> spells = new HashMap<>();
	private static SpellDatabase instance = null;
	
	/**
	 * Create all spells in game and put in a hashmap for later use.
	 */
	private SpellDatabase(){
		//Add spells to game
		spells.put("fireball", new Spell("fireball","eld av boll",10,"damage",20));
		spells.put("heal", new Spell("heal","helar dig",10,"heal",20));
	}
	
	/**
	 * Singleton initializing
	 */
	public static SpellDatabase getInstance() {
		if(instance == null) {
			instance = new SpellDatabase();
		}
		return instance;
			
	}
	
	/**
	 * Get a spell from the database by providing the key as a String
	 * @param spellName - key
	 * @return Spell corresponding to key
	 */
	public Spell getSpells(String spellName) {
		return spells.get(spellName);
	}
}
