package combat;

import java.awt.Image;

import character.Character;
import character.Spell;

/**
 * Enemy data, extends Character for more settings.
 * 
 * @author      Andreas Bäckevik
 * @version     1.0
 * @since       2015-02-01
 */

public class Enemy extends Character
{
	private static final long serialVersionUID = 410123268366271927L;
	private Image img;
	
	/**
	 * Constructor
	 * @param name - Name of the enemy
	 * @param level - Starting level of enemy
	 * @param img - Image of enemy which later connects to GUI
	 * @param spells - Which spells enemy will have
	 */
	public Enemy (String name,int level,Image img,Spell...spells) {
		super(name);
		for(Spell spell : spells){
			this.getSpellBook().addSpell(spell);
		}
		this.setLevel(level);
		this.img = img;
	}
	/**
	 * Returns the image of enemy
	 * @return Image - Image of enemy
	 */
	public Image getImage(){
		return img;
	}
}
