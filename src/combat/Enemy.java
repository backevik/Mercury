package combat;
import character.Character;

/**
 * @author      Andreas Bäckevik
 * @version     0.1
 * @since       2015-02-01
 */

public class Enemy extends Character{
	public Enemy (String name,int level) {
		super(name);
		this.setLevel(level);
	}
}
