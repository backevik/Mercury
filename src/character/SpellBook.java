package character;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Contains a list of all the spells the character owns. Also able to add spells.
 * 
 * @author      Andreas Bäckevik
 * @version     1.0
 * @since       2015-02-01
 */

public class SpellBook
{
	private final List<Spell> spells = new ArrayList<>();
	/**
	 * Constructor
	 */
	public SpellBook () {}
	
	/**
	 * Returns a list of spells
	 * @return list - an unmodifiable list of spells
	 */
	public List<Spell> getSpells () {
		return Collections.unmodifiableList(spells);
	}
	
	/**
	 * Adds a spell to the characters spell book.
	 * @param spell - which spell to be added.
	 */
	public void addSpell (Spell spell) {
		spells.add(spell);
	}
}
