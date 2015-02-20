package database;

import java.util.HashMap;

import character.Spell;

/**
 * @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @version		0.3.2
 * @since		2015-02-20
 */
	
public class SpellDatabase
{
	
	private final HashMap<String, Spell> spells = new HashMap<>();
	
	public SpellDatabase(){
		//Add spells to game
	}
	
	/**
	 * return this list of items
	 * @return
	 */
	public Spell getSpells(String spellName) {
		return spells.get(spellName);
	}
}
