package database;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import worldmap.Zone;

public class ZoneDatabase {
	private final Map<String, Zone> zones = new HashMap<>();
	private static ZoneDatabase instance = null;
	
	private ZoneDatabase (){
		zones.put ("Town0001", 		new Zone ("Town0001", 	"Town",		"sceneTown", 				8, 		344));
		zones.put ("Combat0001", 	new Zone ("Combat0001", "Combat",	"sceneCombat,Encounter1", 	70, 	240));
		zones.put ("Combat0002", 	new Zone ("Combat0002", "Combat",	"sceneCombat,Encounter1", 	90,		100));
		zones.put ("Combat0003", 	new Zone ("Combat0003",	"Combat",	"sceneCombat,Encounter2", 	250, 	65));
		zones.put ("Combat0004", 	new Zone ("Combat0004", "Combat",	"sceneCombat,Encounter3", 	400, 	47));
		zones.put ("Combat0005", 	new Zone ("Combat0005", "Combat",	"sceneCombat,Encounter4", 	488, 	52));
		zones.put ("Combat0006", 	new Zone ("Combat0006",	"Combat",	"sceneCombat,DarkLord", 	688, 	115));
		zones.put ("Arena0001", 	new Zone ("Arena0001",	"Arena",	"sceneArenaEntranceViewer", 		425, 	325));
		
		getZone("Town0001").connect("north", getZone("Combat0001"));
		getZone("Combat0001").connect("north", getZone("Combat0002"));
		getZone("Combat0002").connect("north", getZone("Combat0003"));
		getZone("Combat0003").connect("north", getZone("Combat0004"));
		getZone("Combat0004").connect("north", getZone("Combat0005"));
		getZone("Combat0005").connect("north", getZone("Combat0006"));
		getZone("Arena0001").connect("west", getZone("Town0001"));
	}
	
	/**
	 * Singleton initializing
	 */
	public static ZoneDatabase getInstance() {
		if(instance == null) {
			instance = new ZoneDatabase();
		}
		return instance;
			
	}

	/**
	 * return item from list base from name of item
	 * @return
	 */
	public Zone getZone(String zoneName) {
		return zones.get(zoneName);
	}
	
	public Map<String, Zone> getZones(){
		return Collections.unmodifiableMap(zones);
	}
}
