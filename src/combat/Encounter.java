package combat;

import java.util.ArrayList;
import java.util.List;

/**
 * @author      Andreas BÃƒÂ¤ckevik
 * @version     0.4
 * @since       2015-02-20
 */

public class Encounter {
	private final List<Enemy> enemyList = new ArrayList<>();
	private String event;
	
	public Encounter(String event,Enemy...enemies){
		for(Enemy enemy : enemies){
			enemyList.add(enemy);
		}
		this.event = event;   	
	}
	
	public String getEvent(){
		return event;
	}
	
	public List<Enemy> getEnemies(){
		return enemyList;
	}
}
