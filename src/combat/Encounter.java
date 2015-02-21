package combat;

import java.util.ArrayList;
import java.util.List;

import character.Spell;

/**
 * @author      Andreas BÃ¤ckevik
 * @version     0.4
 * @since       2015-02-20
 */

public class Encounter {
	private final List<Enemy> enemies = new ArrayList<>();
	private String event;
	
	public Encounter(String event){
		this.event = event;
		Enemy enemy = new Enemy("Big evil tomato",1); //temp tills vi har en enemy database
		enemy.getSpellBook().addSpell(new Spell("fireball","eld av boll",10,"damage",20));
		enemy.getSpellBook().addSpell(new Spell("heal","helar dig",10,"heal",20));
    	
		enemies.add(enemy);
	}
	
	public String getEvent(){
		return event;
	}
	
	public List<Enemy> getEnemies(){
		return enemies;
	}
}
