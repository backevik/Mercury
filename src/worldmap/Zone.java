package worldmap;

import java.util.HashMap;

import constants.Directions;

/**
* @author	Daniel Edsinger 	<danieledsinger@hotmail.com>
* @version	1.0					<2015-03-07>
* @since	2015-02-21
*/

public class Zone
{
	private String name;
	private String type;
	private String event;
	private int x;
	private int y;
	
	private HashMap<String, Zone> routes = new HashMap<>();
	
	/**
	 * Constructor for Zone
	 * @param name - name of the zone
	 * @param type - type of the zone
	 * @param event - event triggered in the zone
	 * @param x - coordinate in x of the zone
	 * @param y - coordinate in y of the zone
	 */
	public Zone (String name, String type, String event, int x, int y) {
		this.name = name;
		this.type = type;
		this.event = event;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Public getter for name
	 * @return name of the zone
	 */
	public String getName () {
		return name;
	}
	
	/**
	 * Public getter for type
	 * @return what zone it is
	 */
	public String getType () {
		return type;
	}
	
	/**
	 * Public getter for event
	 * @return what event is triggered in the zone
	 */
	public String getEvent () {
		return event;
	}
	
	/**
	 * Public getter for x
	 * @return the x coordinate of the zone
	 */
	public int getX () {
		return x;
	}
	
	/**
	 * Public getter for y
	 * @return the y coordinate of the zone
	 */
	public int getY () {
		return y;
	}
	
	/**
	 * Connect two zones on the map. You are then able to travel between them
	 * @param direction - in what direction they are connected
	 * @param other - which zone it is that this is connected to
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
	 * @param other - zone to check connection against
	 * @return if the zone is connected to this zone or not
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
