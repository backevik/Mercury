package combat;

import java.util.ArrayList;
import java.util.List;

/**
 * @author      Andreas BÃƒÂ¤ckevik
 * @version     0.4
 * @since       2015-02-20
 */

public class Encounter {
	private List<Enemy> enemyList = new ArrayList<>();
	private String winEvent;
	private String loseEvent;
	
	public Encounter(String winEvent,String loseEvent,Enemy...enemies){
		for(Enemy enemy : enemies){
			enemyList.add(enemy);
		}
		this.winEvent = winEvent;
		this.loseEvent = loseEvent;
	}
	
	/**
	 * Copy Constructor for Encounter
	 */
	public Encounter (Encounter e) {
		enemyList = e.enemyList;
		winEvent = e.winEvent;
		loseEvent = e.loseEvent;
	}
	
	public String getWinEvent(){
		return winEvent;
	}
	public String getLoseEvent(){
		return loseEvent;
	}
	
	public List<Enemy> getEnemies(){
		return enemyList;
	}
}
