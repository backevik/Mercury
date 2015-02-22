package constants;
import java.util.HashMap;

/**
* @author	Daniel Edsinger <danieledsinger@hotmail.com>
* @version	0.5				<2015-02-22>
* @since	2015-02-21
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
	
	public static String opposite(String d) {
		return directions.get(d);
	}
}