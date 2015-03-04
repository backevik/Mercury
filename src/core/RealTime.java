package core;

/**
 * @author	Anton Andr�n & Mattias Benng�rd
 * @version	0.4
 * @since	2015-02-16
 *
 * Interface for all objects that need real-time updates
 * All classes that implement Entity must implement public void update()
 */

public interface RealTime
{
	public void update ();
}