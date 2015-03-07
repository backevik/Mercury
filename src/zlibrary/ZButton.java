package zlibrary;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import core.EventAdder;

/**
 * A GUI Button that trigger event on click
 * 
 * @author      Mattias Benngard	<mbengan@gmail.com>
 * @version     1.0					<2015-02-27>
 * @since       2015-03-07
 */

public class ZButton extends ZEntity
{
	/**
	 * Constructor for Image
	 * @param image - which image to be drawn as the button
	 * @param x - where it is placed in x
	 * @param y - where it is placed in y
	 * @param eventAdder - to what event queue it should be adding
	 * @param eventOnClick - what event is triggered on press
	 */
	public ZButton (Image image, int x, int y, EventAdder eventAdder, String eventOnClick) {
		setImage(image);
		setX(x);
		setY(y);
		setEventAdder(eventAdder);
		setEventOnClick(eventOnClick);
	}
	
	/**
	 * Constructor to create an image from Text
	 * @param string - string to be rendered as image
	 * @param x - where it is placed in x
	 * @param y - where it is placed in y
	 * @param w - how width the button the should be
	 * @param h - what height the button should have
	 * @param eventAdder - to what event queue it should be adding
	 * @param eventOnClick - what event is triggered on press 
	 */
	public ZButton (String string, int x, int y, int w, int h, EventAdder eventAdder, String eventOnClick) {
		setImage(createButtonFromString(string, w, h));
		setX(x);
		setY(y);
		setEventAdder (eventAdder);
		setEventOnClick (eventOnClick);
	}

	/**
	 * Creates an image based on parameters. Private helper method for ZButtonb
	 * @param String s	- string to be displayed on the button
	 * @param int w		- width of the button
	 * @param int h		- height of the button
	 * @return an image created specified by above variables
	 */
	private Image createButtonFromString (String s, int w, int h) {
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        
        // setting funky constants
        g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g.setFont(new Font("Verdana", Font.PLAIN, 18));
        FontMetrics fm = g.getFontMetrics();

        // draw button border and background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, w, h);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(2, 2, w-4, h-4);
		
		// draw string centered in button
		g.setColor(Color.BLACK);
		g.drawString(s, (float)((w-fm.stringWidth(s))/2), (float) (h+fm.getHeight()/2)/2);

		//releasing resources
		g.dispose();
		
		return img;
	}
}
