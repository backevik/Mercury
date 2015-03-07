package combat;

import java.util.ArrayList;
import java.util.List;

/**
 * A unique encounter for each zone that includes a chosen number of enemies (can be 0) and win/lose events.
 * 
 * @author      Andreas Bäckevik
 * @version     1.0
 * @since       2015-02-20
 */

public class Encounter {
	private List<Enemy> enemyList = new ArrayList<>();
	private String winEvent;
	private String loseEvent;
	
	/**
	 * Constructor for Encounter
	 * @param winEvent - event if encounter is won by player
	 * @param loseEvent event if encounter is lost by player
	 * @param enemies - 0 to unlimited amount of enemies in encounter
	 */
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
	 
	/**
	 * Public getter for win event
	 * @return event triggered on victory
	 */
	public String getWinEvent(){
		return winEvent;
	}
	
	/**
	 * Public getter for lose event
	 * @return event triggered on defeat
	 */
	public String getLoseEvent(){
		return loseEvent;
	}
	
	/**
	 * Returns list of enemies
	 * @return all enemies in this encounter
	 */
	public List<Enemy> getEnemies(){
		return enemyList;
	}
}
