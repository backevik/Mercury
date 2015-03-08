package zlibrary;

import java.awt.Graphics;

/**
 * Interface for all drawables
 * 
 * @author      Anton Andren		<>
 * @version     1.0					<2015-02-27>
 * @since       2015-03-07
 */

public interface ZDrawable
{
	/**
	 * Abstract rendering method
	 * @param g - screen to render in
	 */
	public abstract void render (Graphics g);
}
