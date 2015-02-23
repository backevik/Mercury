package combat;
import character.Character;
import character.Spell;

/**
 * @author      Andreas BÃ¤ckevik
 * @version     0.1
 * @since       2015-02-01
 */

public class Enemy extends Character{
	public Enemy (String name,int level,Spell...spells) {
		super(name);
		for(Spell spell : spells){
			this.getSpellBook().addSpell(spell);
		}
		this.setLevel(level);
	}
}
