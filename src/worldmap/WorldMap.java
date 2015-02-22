package worldmap;

import java.util.Collections;
import java.util.HashMap;

import core.GlobalStateManager;

/**
 * @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @version		0.3.2
 * @since		2015-02-21
 */

public class WorldMap
{
	private final HashMap<String, Zone> zones = new HashMap<>();
	
	public WorldMap () {
		
		//Create all zones to travel between
		Zone town = new Zone ("townTown");
		zones.put("townTown", town);
		
		Zone forest = new Zone ("combatForest");
		zones.put("combatForest", forest);
		
		Zone grass = new Zone ("combatGrass");
		zones.put("combatGrass", grass);
		
		/**
		 * Connect zones
		 */
		town.connect("north", forest);
		forest.connect("east", grass);
		
	}
	
	/**
	 * 
	 */
	public HashMap<String, Zone> getZones () {
		return (HashMap<String, Zone>) Collections.unmodifiableMap(zones);
	}
	
	/**
	 * Travel to area. If connected, do so. If not, it's not reachable.
	 * @param area
	 */
	public void selectArea(String area) {		
		if( zones.get(GlobalStateManager.getInstance().getWorldState("Location")).isConnected(zones.get(area))) {
			System.out.println("Change globalWorldState from: " + GlobalStateManager.getInstance().getWorldState("Location") + " To: "+ area);
			GlobalStateManager.getInstance().updateWorldState("Location", area);
			
		}else{
			System.out.println("Not reachable area");
		}
	}
}