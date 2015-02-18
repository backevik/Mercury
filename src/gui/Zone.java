package gui;

import java.util.HashMap;;

/**
 * @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @version		0.3
 * @since		2015-02-17
 */

public class Zone
{	
	private HashMap<String, Zone> routes;
	
	public Zone(int borderX, int borderY, int sizeX, int sizeY, String off, String on)
	{
		routes = new HashMap<String, Zone>();	//Routes to other zones
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
		return routes.containsValue(other);
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
