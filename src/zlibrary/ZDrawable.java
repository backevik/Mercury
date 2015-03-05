package zlibrary;

import java.awt.Graphics;

/**
 * @author	Anton Andrén
 * @version 1.0
 * @since	2015-02-13
 * 
 * Interface for all objects to be rendered.
 * All classes implementing Drawable must implement public void render (Graphics g)
 */

public interface ZDrawable
{
	public abstract void render (Graphics g);
}
