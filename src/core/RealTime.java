package core;

/**
 * Interface for all objects that need real-time updates.
 * 
 * @author	Anton Andrén
 * @version	1.0					
 * @since	2015-02-16
 */

public interface RealTime
{
	/**
	 * Executes one time each run of game loop 
	 */
	public void update ();
}