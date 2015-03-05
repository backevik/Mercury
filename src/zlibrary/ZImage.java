package zlibrary;

import java.awt.Image;

/**
 * @author	Anton Andrén & Mattias Benngård
 * @version	1.0
 * @since	2015-02-17
 * 
 * ZImage is a Zcomponent containing an image.
 * 
 * public ZImage (Image image, int x, int y)
 * Sets the image in ZComponent.
 */

public class ZImage extends ZComponent
{	
	public ZImage (Image image, int x, int y) {
		setImage(image);
		setX(x);
		setY(y);
	}
}
