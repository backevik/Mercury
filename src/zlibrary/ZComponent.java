package zlibrary;

import java.awt.Graphics;
import java.awt.Image;

/**
 * ZComponent is the abstract base class for all Z-components. It stores an Image and the location of said Image.
 * 
 * @author	Anton Andrén
 * @version	1.0			
 * @since	2015-02-20
 */

public abstract class ZComponent implements ZDrawable
{
	private Image image;
	private int x;
	private int y;
	
	/**
	 * Protected getter for image
	 * @return the image
	 */
	protected Image getImage () {
		return image;
	}
	
	/**
	 * Public setter for image
	 * @param what image to be displayed instead of the current
	 */
	public void setImage (Image image) {
		this.image = image;
	}
	
	/**
	 * Protected getter for x
	 * @return the current x position
	 */
	protected int getX () {
		return x;
	}
	
	/**
	 * Public setter for what x it should be drawn on
	 * @param x - the new x coordinate
	 */
	public void setX (int x) {
		this.x = x;
	}
	
	/**
	 * Protected getter for y
	 * @return the current y position
	 */
	protected int getY () {
		return y;
	}
	
	/**
	 * Public setter for what y it should be drawn on
	 * @param y - the new y coordinate
	 */
	public void setY (int y) {
		this.y = y;
	}

	/**
	 * Renders the Image that ZImage holds in the right position.
	 */
	public void render (Graphics g) {
		g.drawImage (image, x, y, null);
	}
}
