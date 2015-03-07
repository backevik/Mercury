package zlibrary;

import java.awt.Image;

/**
 * A wrapper for AWT Image
 * 
 * @author	Anton Andren		<>
 * @version	1.0					<2015-03-07>
 * @since	2015-02-16
 */

public class ZImage extends ZComponent
{	
	/**
	 * Constructor for ZImage
	 * @param image - what image it should display
	 * @param x - position of the ZImage in x
	 * @param y - position of the ZImage in y
	 */
	public ZImage (Image image, int x, int y) {
		setImage(image);
		setX(x);
		setY(y);
	}
}
