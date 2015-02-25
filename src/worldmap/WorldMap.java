package worldmap;

import java.util.HashMap;
import java.util.Map;

import core.GlobalStateManager;

/**
 * @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @version		0.3.2
 * @since		2015-02-21
 */

public class WorldMap
{
	private final Map<String, Zone> zones = new HashMap<>();
	
	public WorldMap () {
		// create zones
		zones.put("townTown", new Zone ("townTown", "Town", 16, 352));
		zones.put("combatForest", new Zone ("combatForest", "Zone", 104, 128));
		zones.put("combatGrass", new Zone ("combatGrass", "Zone", 184, 104));
		
		// connect zones
		zones.get("townTown").connect("north", zones.get("combatForest"));
		zones.get("combatForest").connect("east", zones.get("combatGrass"));
	}
	
	/**
	 * Return an unmodfifiableMap of zones
	 * @return
	 */
	public HashMap<String, Zone> getZones () {
		
		
		return (HashMap<String, Zone>)zones;
	}
	
	/**
	 * Travel to area. If connected, do so. If not, it's not reachable.
	 * @param String area
	 */
	public boolean selectArea (String area) {
		if(!zones.get(GlobalStateManager.getInstance().getWorldState("Location")).isConnected(zones.get(area))) {
			return false;
		}
			
		System.out.println("Change globalWorldState from: " + GlobalStateManager.getInstance().getWorldState("Location") + " To: "+ area);
		GlobalStateManager.getInstance().updateWorldState("Location", area);
		return true;
	}
}