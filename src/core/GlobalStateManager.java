package core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import player.Player;

public class GlobalStateManager implements Serializable
{
	private static final long serialVersionUID = 5455542778351671247L;
	private static GlobalStateManager globalStateManager;
	private final Map<String, String> worldState = new HashMap<>();
	private String currentState = "";
	private String Location = "";
	
	private GlobalStateManager () {}	
	
	public void updateWorldState (String state, String value) {
		worldState.put(state, value);
	}
	
	public void updateCurrentLocation(String value){
		
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
	public int save (Game game, Player player) {		
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
