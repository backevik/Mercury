import java.util.HashMap;

/**
 * @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @version		0.3
 * @since		2015-02-17
 */

public class Directions
{
	private static HashMap<String,String> directions;
    private static final Directions instance = new Directions();
	
	private Directions()
	{
		directions = new HashMap<String,String>();
		directions.put("north","south");
		directions.put("south","north");
		directions.put("east","west");
		directions.put("west","east");
	}
	
	public static Directions getInstance() {
	    return instance;
	}
	
	public static String opposite(String d)
	{
		return directions.get(d);
	}
}
