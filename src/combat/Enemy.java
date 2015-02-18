package combat;

import java.util.Random;

import character.Character;
import character.Spell;

/**
 * @author      Andreas Bäckevik
 * @version     0.1
 * @since       2015-02-01
 */

public class Enemy extends Character
{
	private int level;
	private Random rand;
	
	public Enemy (String name,int level) {
		super(name);
		this.level = level;
		rand = new Random();
	}
	
	/**
	 * Decides AI's next move.
	 * @return whatToDo - String with command on next move. heal/attack/spell 
	 */
	public String AI(){
		String whatToDo=null;
		if(this.getValueOfVital("Health")<(this.getMaxOfVital("Health")/8)){
			for(Spell spell : this.getSpellBook().getSpells()){
				if(spell.getName().equals("heal")){
					whatToDo="heal";
				}
			}
		}else{
			int atk = rand.nextInt(2)+1;
			if(atk==1){
				whatToDo="attack";
			}else{
				whatToDo="spell";
			}
		}
		return whatToDo;
	}
	
	public int getLevel(){
		return level;
	}
}
