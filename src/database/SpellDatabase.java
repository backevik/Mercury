package database;

import java.util.ArrayList;
import java.util.List;
import character.Spell;

/**
 * @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @version		0.3
 * @since		2015-02-17
 */
	
public class SpellDatabase
{
	
	private final List<Spell> spells = new ArrayList<>();
	
	public SpellDatabase(){
		//Add spells to game
	}
	
	/**
	 * return this list of items
	 * @return
	 */
	public List<Spell> getSpells() {
		return spells;
	}
}
