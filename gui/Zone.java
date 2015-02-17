import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.util.HashMap;;

/**
 * @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @version		0.3
 * @since		2015-02-17
 */

public class Zone extends JButton
{
	
	private HashMap<String, Zone> routes;
	private ImageIcon imgOn;
	private ImageIcon imgOff;
	
	public Zone(int borderX, int borderY, int sizeX, int sizeY, String off, String on)
	{
		routes = new HashMap<String, Zone>();	//Routes to other zones
		setBounds( borderX, borderY, sizeX, sizeY);
		setBorder(null);
		
		imgOn = new ImageIcon(getClass().getResource(on));	//Sets the texture for the button
		imgOff = new ImageIcon(getClass().getResource(off));
	}
	
	public void setOff()
	{
		setIcon(imgOff);
	}
	
	public void setOn()
	{
		setIcon(imgOn);
	}
	
	
	/**
	 * Change the texture when on zone
	 * 
	 * @param img
	 */
	public void changeOn(String img)
	{
		imgOn = new ImageIcon(getClass().getResource(img));
	}
	
	/**
	 * Change the texture when off zone
	 * 
	 * @param img
	 */
	public void changeOff(String img)
	{
		imgOn = new ImageIcon(getClass().getResource(img));
	}
	
	
	/**
	 * Connect two zones on the map
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
