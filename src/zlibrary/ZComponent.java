package zlibrary;

import java.awt.Graphics;
import java.awt.Image;

/**
 * @author	Anton Andrén & Mattias Benngård
 * @version	0.8			<2015-02-20>
 * @since	2015-02-20
 * 
 * ZComponent is the abstract base class for all Z-components. It stores an Image and the location of said Image.
 *
 */

public abstract class ZComponent implements ZDrawable
{
	private Image image;
	private int x;
	private int y;
		
	protected Image getImage () {
		return image;
	}
	
	protected void setImage (Image image) {
		this.image = image;
	}
	
	protected int getX () {
		return x;
	}
	
	protected void setX (int x) {
		this.x = x;
	}
	
	protected int getY () {
		return y;
	}
	
	protected void setY (int y) {
		this.y = y;
	}

	/**
	 * Renders the Image that ZImage holds in the right position.
	 */
	public void render (Graphics g) {
		g.drawImage (image, x, y, null);
	}
}
