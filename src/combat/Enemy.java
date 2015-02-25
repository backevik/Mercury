package combat;
import java.awt.Image;


import zlibrary.ZImage;
import character.Character;
import character.Spell;
import database.ImageDatabase;

/**
 * @author      Andreas BÃƒÂ¤ckevik
 * @version     0.1
 * @since       2015-02-01
 */

public class Enemy extends Character{
	private Image img;
	public Enemy (String name,int level,Image img,Spell...spells) {
		super(name);
		for(Spell spell : spells){
			this.getSpellBook().addSpell(spell);
		}
		this.setLevel(level);
		this.img = img;
	}
	
	public Image getImage(){
		return img;
	}
}
