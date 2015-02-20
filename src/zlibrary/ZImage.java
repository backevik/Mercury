package zlibrary;

import java.awt.Image;

/**
 * @author	Anton Andrén & Mattias Benngård
 * @version	0.8			<2015-02-20>
 * @since	2015-02-17
 * 
 * ZImage is the base class for all Z-components. It stores an Image and the location of said Image.
 * 
 * Can be constructed with an existing Image or by using a default appearance.
 *
 */

public class ZImage extends ZComponent
{	
	public ZImage (Image image, int x, int y) {
		setImage(image);
		setX(x);
		setY(y);
	}
}
