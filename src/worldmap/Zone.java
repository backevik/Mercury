package worldmap;

import java.util.HashMap;

/**
* @author	Daniel Edsinger <danieledsinger@hotmail.com>
* @version	0.3.2
* @since	2015-02-21
*/
public class Zone
{
	private String zoneName;
	private HashMap<String, Zone> routes = new HashMap<>();
	
	public Zone(String name){
		this.zoneName = name;
	}
	
	public String getName(){
		return zoneName;
	}
	
	/**
	 * Connect two zones on the map. You are then able to travel between them
	 * 
	 * @param direction
	 * @param other
	 */
	public void connect( String direction, Zone other)
	{
		Directions directions = Directions.getInstance();
        if (! routes.containsKey(direction) ) 
        {
            // Connect forwards
            routes.put(direction,other);
            // Connect backwards
            other.connect(directions.opposite(direction),this);
        }
	}
	
	/**
	 * Checks if this zone is connected to the other zone
	 * 
	 * @param other
	 * @return	true if the buttons are connected
	 * 			false otherwise
	 */
	public boolean isConnected(Zone other)
	{
		if(routes.containsValue(other))
		{
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 
	 * @param direction
	 * @return	Zone if zones are connected in the chosen direction
	 * 			false if no zone connected
	 */
	public Zone getNextZone(String direction)
	{
		return routes.get(direction);
	}
}
