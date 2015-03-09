package constants;

import java.util.HashMap;

/**
 * Constants to match opposite directions
 * 
 * @author	Daniel Edsinger 
 * @version 1.0					
 * @since   2015-02-01
 */ 

public final class Directions
{
	private static HashMap<String,String> directions = new HashMap<String, String>();
    static {
    	directions.put("north","south");
		directions.put("south","north");
		directions.put("east","west");
		directions.put("west","east");
    }
	
    /**
     * Public getter for opposite direction
     * @param d - current direction
     * @return opposite direction
     */
	public static String opposite(String d) {
		return directions.get(d);
	}
}