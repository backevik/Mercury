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
		spells.put("fireball", new Spell("fireball","eld av boll",10,"damage",20));
		spells.put("heal", new Spell("heal","helar dig",10,"heal",20));
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
