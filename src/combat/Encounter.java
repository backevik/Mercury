package combat;

import java.util.ArrayList;
import java.util.List;

public class Encounter {
	List<Enemy> enemies;
	String event;
	
	public Encounter(String event){
		this.event = event;
		enemies = new ArrayList();
		Enemy enemy = new Enemy("Big evil tomato",5); //temp tills vi har en enemy database
		enemies.add(enemy);
	}
	
	public String getEvent(){
		return event;
	}
	
	public List<Enemy> getEnemies(){
		return enemies;
	}
}
