package core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
		for (int i = 0; i < 10000; ++i) {
			if (keyUnique () == false) {
				number = r.nextLong();	
			}
		}
		
		// if key still is false
		if (keyUnique () == false) {
			System.out.println("Failed to generate a valid key for profile handling.");
			System.exit(0);
		}
		
		key = Long.toString(number);
	}
	//
	
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
	
	public static GlobalStateManager getInstance () {
		if (globalStateManager == null) {
			globalStateManager = new GlobalStateManager ();
		}
		
		return globalStateManager;
	}
	
	/**
	 * Check if there exists other profiles with this key
	 * @return true if no other key like this exists
	 */
	private boolean keyUnique () {
		return true;
	}
	
	/**
	 * 
	 * @param player - playerdata to save
	 * @param fileName - save data to fileName
	 */
	public void save (Player player, String fileName) {
		if (globalStateManager == null) {
			globalStateManager = new GlobalStateManager ();
		}
		
		try {
			FileOutputStream fos = new FileOutputStream ("saves/" + fileName + " savefile.dat");
			ObjectOutputStream oos = new ObjectOutputStream (fos);
			oos.writeObject(player);
			oos.writeObject(this);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	/**
	 * Load all data for player
	 * @param filename - name of file
	 * @return Player object from file
	 */
	public Player load (String filename) {
		if (globalStateManager == null) {
			globalStateManager = new GlobalStateManager ();
		}
		
		try {
			FileInputStream fis = new FileInputStream ("saves/" + filename + " savefile.dat");
			ObjectInputStream ois = new ObjectInputStream (fis);
			Player player = (Player) ois.readObject();
			globalStateManager = (GlobalStateManager) ois.readObject();
			
			ois.close();
			return player;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}