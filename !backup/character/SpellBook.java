package character;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author      Mattias Benngård	<mbengan@gmail.com>
 * @version     0.4
 * @since       2015-02-01
 */

public class SpellBook
{
	private final List<Spell> spells = new ArrayList<>();
	
	public SpellBook () {}
	
	/**
	 * @return an unmodifiable list of spells
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
