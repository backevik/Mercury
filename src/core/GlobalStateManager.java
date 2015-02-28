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
	 * @return 0 if the save was succesful, if not -1
	 */
	public int save (Player player, String filename) {
		if (globalStateManager == null) {
			globalStateManager = new GlobalStateManager ();
		}
		
		String directory = player.getPC().getName() + key;
		
		try {
			FileOutputStream fos = new FileOutputStream (directory+"\\"+filename);
			ObjectOutputStream oos = new ObjectOutputStream (fos);
			oos.writeObject(this);
			oos.close();
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}	
	
	/**
	 * 
	 * @return 0 if the load was succesful, if not -1
	 */
	public int load (String directory, String filename) {
		if (globalStateManager == null) {
			globalStateManager = new GlobalStateManager ();
		}
		
		try {
			FileInputStream fis = new FileInputStream (directory+"\\"+filename);
			ObjectInputStream ois = new ObjectInputStream (fis);
			globalStateManager = (GlobalStateManager) ois.readObject();
			ois.close();
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
}