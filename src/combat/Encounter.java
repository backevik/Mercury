package combat;

import java.util.ArrayList;
import java.util.List;

import database.GameDataManager;

/**
 * @author      Andreas Bäckevik
 * @version     0.4
 * @since       2015-02-20
 */

public class Encounter {
	List<Enemy> enemies;
	String event;
	
	public Encounter(String event){
		this.event = event;
		enemies = new ArrayList();
		Enemy enemy = new Enemy("Big evil tomato",1); //temp tills vi har en enemy database
    	for(int i=0;i<GameDataManager.getInstance().getSpells().size();i++){
    		enemy.getSpellBook().addSpell(GameDataManager.getInstance().getSpells().get(i));
    	}
		enemies.add(enemy);
	}
	
	public String getEvent(){
		return event;
	}
	
	public List<Enemy> getEnemies(){
		return enemies;
	}
}

