package character;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Contains a list of all the spells the character owns. Also able to add spells.
 * 
 * @author      Andreas Bäckevik		<>
 * @version     1.0.1					<2015-03-07>
 * @since       2015-03-07
 */

public class SpellBook implements Serializable
{
	private static final long serialVersionUID = -1175481083251095481L;
	private final List<Spell> spells = new ArrayList<>();
	
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
