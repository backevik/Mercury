package core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import player.Player;

public class GlobalStateManager implements Serializable
{
	private static final long serialVersionUID = 5455542778351671247L;
	private static GlobalStateManager globalStateManager;
	private final Map<String, String> worldState = new HashMap<>();
	private String currentState = "";
	
	// used for profile handling in save files.
	private String key;
	
	private GlobalStateManager () {
		Random r = new Random (System.currentTimeMillis());
		Long number = r.nextLong();
		
		// key is not unique for this profile
		if (false) {
			for (int i = 0; i < 10000; ++i) {
				number++;
			}
		}
		
		// if key still is false
		if (false) {
			System.exit(0);
		}
		
		key = Long.toString(number);
	}	
	
	public void updateWorldState (String state, String value) {
		worldState.put(state, value);
	}
	
	public String getWorldState (String state) {
		return (worldState.get(state) != null) ? worldState.get(state) : "";
	}
	
	public void updateCurrentState (String value) {
		currentState = value;
	}
	
	public String getCurrentState () {
		return currentState;
	}
	
	/**
	 * 
	 * @return
	 */
	public int save (Player player, String filename) {
				
		String directory = player.getPC().getName() + key;
		
		return saveThisToFile ();
	}
	
	/**
	 * TODO
	 */
	private int saveThisToFile () {return 0;}
	
	
	/**
	 * 
	 * @return
	 */
	public int load () {
		return loadFromFile ();
	}
	
	/**
	 * 
	 */
	private int loadFromFile () {return 0;}
	
	public static GlobalStateManager getInstance () {
		if (globalStateManager == null) {
			globalStateManager = new GlobalStateManager ();
		}
		
		return globalStateManager;
	}
	
}
