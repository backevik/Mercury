package worldmap;

import java.util.HashMap;

import constants.Directions;

/**
* @author	Daniel Edsinger 	<danieledsinger@hotmail.com>
* @author	Mattias Benngård	<mbengan@gmail.com>
* @version	0.8					<2015-02-23>
* @since	2015-02-21
*/

public class Zone
{
	private String name;
	private String type;
	private int x;
	private int y;
	
	private HashMap<String, Zone> routes = new HashMap<>();
	
	public Zone (String name, String type, int x, int y) {
		this.name = name;
		this.type = type;
		this.x = x;
		this.y = y;
		
	}
	
	public String getName () {
		return name;
	}
	
	public String getType () {
		return type;
	}
	
	public int getX () {
		return x;
	}
	
	public int getY () {
		return y;
	}
	
	/**
	 * Connect two zones on the map. You are then able to travel between them
	 * 
	 * @param direction
	 * @param other
	 */
	public void connect (String direction, Zone other) {
        if (! routes.containsKey(direction) ) {
            // Connect forwards
            routes.put(direction, other);            
            // Connect backwards
            other.connect(Directions.opposite(direction),this);
        }
	}
	
	/**
	 * Checks if this zone is connected to the other zone
	 * 
	 * @param other
	 * @return	true if the buttons are connected
	 * 			false otherwise
	 */
	public boolean isConnected (Zone other) {
		return routes.containsValue(other);
	}
	
	/**
	 * 
	 * @param direction
	 * @return	Zone if zones are connected in the chosen direction
	 * 			false if no zone connected
	 */
	public Zone getNextZone (String direction) {
		return routes.get(direction);
	}
}
